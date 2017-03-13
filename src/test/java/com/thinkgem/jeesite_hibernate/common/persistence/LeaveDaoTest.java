package com.thinkgem.jeesite_hibernate.common.persistence;

import com.thinkgem.jeesite_hibernate.modules.oa.dao.LeaveDao;
import com.thinkgem.jeesite_hibernate.modules.oa.entity.Leave;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * LeaveDaoTest
 * @author madison
 * @version 2017-02-27
 */
public class LeaveDaoTest extends SpringTransactionalContextTests {

    @Autowired
    private LeaveDao leaveDao;

    @Test
    public void find(){
        DetachedCriteria dc = leaveDao.createDetachedCriteria();
        dc.add(Restrictions.eq("delFlag", Leave.DEL_FLAG_NORMAL));
        DetachedCriteria createByDc = dc.createCriteria("createBy");
//        DetachedCriteria createByDc = dc.createAlias("createBy","createBy");
        createByDc.add(Restrictions.eq("no","0001"));
        dc.addOrder(Order.desc("id"));
        List<Leave> leaveList = leaveDao.find(dc);
        for (Leave leave : leaveList) {
            System.out.println(leave.getId()+"\t"+leave.getReason());
        }
    }
}
