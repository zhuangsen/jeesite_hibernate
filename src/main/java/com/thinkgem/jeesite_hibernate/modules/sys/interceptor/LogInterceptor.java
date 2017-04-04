package com.thinkgem.jeesite_hibernate.modules.sys.interceptor;

import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite_hibernate.common.utils.excel.UserAgentUtils;
import org.springframework.core.NamedThreadLocal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.thinkgem.jeesite_hibernate.common.utils.StringUtils;
import com.thinkgem.jeesite_hibernate.common.config.Global;
import com.thinkgem.jeesite_hibernate.common.utils.DateUtils;
import com.thinkgem.jeesite_hibernate.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite_hibernate.common.service.BaseService;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 日志拦截器
 */
public class LogInterceptor extends BaseService implements HandlerInterceptor {

    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        logger.debug("==============================执行preHandle==============================");
        String requestRri = request.getRequestURI();
        String uriPrefix = request.getContextPath() + Global.getAdminPath();
        logger.debug("---------requestRri:" + requestRri);
        logger.debug("---------uriPrefix" + uriPrefix);
        if (logger.isDebugEnabled()) {
            long beginTime = System.currentTimeMillis();//1、开始时间
            startTimeThreadLocal.set(beginTime);        //线程绑定变量（该数据只有当前请求的线程可见）
            logger.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.debug("==============================执行postHandle==============================");
        String requestRri = request.getRequestURI();
        String uriPrefix = request.getContextPath() + Global.getAdminPath();
        logger.debug("------------requestRri:" + requestRri);
        logger.debug("------------uriPrefix:" + uriPrefix);
        if (modelAndView != null) {
            String viewName = modelAndView.getViewName();
            logger.debug("------------viewName:" + viewName);
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            logger.debug("------------deviceType:" + userAgent.getOperatingSystem().getDeviceType());

            // 如果是手机或平板访问的话，则跳转到手机视图页面。
            logger.debug(""+viewName.startsWith("modules/"));
            if (viewName.startsWith("modules/") && UserAgentUtils.isMobileOrTablet(request)) {
                logger.debug("進入到手機...");
//                modelAndView.setViewName(viewName.replaceFirst("modules", "mobile"));
                modelAndView.setViewName("mobile/"+modelAndView.getViewName());
            }
            logger.info("ViewName: " + modelAndView.getViewName());
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        String requestRri = request.getRequestURI();
        String uriPrefix = request.getContextPath() + Global.getAdminPath();

        logger.debug("==============================执行afterCompletion==============================");
        logger.debug("------------requestRri:" + requestRri);
        logger.debug("------------uriPrefix:" + uriPrefix);

        if ((StringUtils.startsWith(requestRri, uriPrefix) && (StringUtils.endsWith(requestRri, "/save")
                || StringUtils.endsWith(requestRri, "/delete") || StringUtils.endsWith(requestRri, "/import")
                || StringUtils.endsWith(requestRri, "/updateSort"))) || ex != null) {
            // 保存日志
            LogUtils.saveLog(request, handler, ex, null);
        }

        // 打印JVM信息。
        if (logger.isDebugEnabled()) {
            long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
            long endTime = System.currentTimeMillis();    //2、结束时间
            logger.debug("计时结束：{}  耗时：{}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), DateUtils.formatDateTime(endTime - beginTime),
                    request.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024, Runtime.getRuntime().totalMemory() / 1024 / 1024, Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }

    }

}
