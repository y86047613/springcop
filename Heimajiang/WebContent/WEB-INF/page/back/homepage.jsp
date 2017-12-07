<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<script language="javascript" type="text/javascript"> 
window.location.href="${contextPath}/sys/sysuser/home#page/mysell";
location.reload() ;
//alert(${pageContext.request.contextPath});
</script> 


<div class="row" style="display: none">
	<div class="col-sm-12">
		<h3 class="header smaller lighter green">
			<i class="ace-icon fa fa-bullhorn"></i> 欢迎进入后台管理系统
		</h3>

		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert">
				<i class="ace-icon fa fa-times"></i>
			</button>

			<strong> 公司地址:</strong>&nbsp;xxx工作室 <br />
			<strong> 联系电话:</strong>&nbsp;1361287xxxx <br />
			<strong> 合作QQ:</strong>&nbsp;262351579<br />
			<strong> 公司地址:</strong>&nbsp;江西省南昌市xxx<br />
			<strong> 公司官网:</strong><a  href="http://www.baidu.com" target="_Blank">www.baidu.com</a> <br />
		</div>

		 <div class="alert alert-warning">
			<button type="button" class="close" data-dismiss="alert"><i class="ace-icon fa fa-times"></i>
			</button>
			<strong>成功案例</strong> <a  href="http://www.baidu.com" target="_Blank">www.baidu.com</a><br />
		</div>
		
		<div class="alert alert-block alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<i class="ace-icon fa fa-times"></i>
			</button>

			<p>
				<strong> <i class="ace-icon fa fa-check"></i> 通知</strong> 待续.....<br/>
			</p>

		</div>
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert">
				<i class="ace-icon fa fa-times"></i>
			</button>
			<strong>产品价格参考以及说明</strong>  <br />
		</div>
	</div>
	<!-- /.col -->

</div>




<!--[if lte IE 8]>
  <script src="${contextPath}/static/assets/js/excanvas.js"></script>
<![endif]-->
<script type="text/javascript">
	var scripts = [ null,
			"${contextPath}/static/assets/js/jquery-ui.custom.js",
			"${contextPath}/static/assets/js/jquery.ui.touch-punch.js",
			"${contextPath}/static/assets/js/jquery.easypiechart.js",
			"${contextPath}/static/assets/js/jquery.sparkline.js",
			"${contextPath}/static/assets/js/flot/jquery.flot.js",
			"${contextPath}/static/assets/js/flot/jquery.flot.pie.js",
			"${contextPath}/static/assets/js/flot/jquery.flot.resize.js",
			"${contextPath}/static/assets/js/activities-serverload.js", null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {

	});
</script>
