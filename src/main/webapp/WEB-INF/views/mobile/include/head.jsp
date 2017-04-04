<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="shortcut icon" href="${ctxStatic}/favicon.ico">
<link rel="stylesheet" href="${ctxStatic}/jingle/css/Jingle.css">
<link rel="stylesheet" href="${ctxStatic}/jingle/css/app.css">
<link rel="stylesheet"  href="${ctxStatic}/jquery.mobile/jquery.mobile-1.4.0.min.css">
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<%--<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/cordova.js"></script>--%>
<!-- lib -->
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/zepto1.1.3.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/iscroll.js"></script>
<%-- <script type="text/javascript" src="${ctxStatic}/jingle/js/lib/template.min.js"></script> --%>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/Jingle.debug.js"></script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/lib/zepto.touch2mouse.js"></script>
<%-- <script type="text/javascript" src="${ctxStatic}/jingle/js/lib/JChart.debug.js"></script> --%>
<!--- app --->
<script type="text/javascript">var ctx ='${ctx}';</script>
<script type="text/javascript" src="${ctxStatic}/jingle/js/app/app.js"></script>
<!--<script src="http://192.168.2.153:8080/target/target-script-min.js#anonymous"></script>-->
<script type="text/javascript">
	$( document ).on( "mobileinit", function(){
		$.mobile.ajaxEnabled = false;
	});
</script>
<script src="${ctxStatic}/jquery.mobile/jquery.mobile-1.4.0.min.js"></script>
<script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/jquery-validation/1.11.1/jquery.validate.method.min.js" type="text/javascript"></script>
<style>
	label.error {
	    color: red;
	    font-size: 16px;
	    font-weight: normal;
	    line-height: 1.4;
	    margin-top: 0.5em;
	    width: 100%;
	    float: none;
	}
	@media screen and (orientation: portrait){
	    label.error {
	        margin-left: 0;
	        display: block;
	    }
	}
	@media screen and (orientation: landscape){
	    label.error {
	        display: inline-block;
	        margin-left: 22%;
	    }
	}
</style>