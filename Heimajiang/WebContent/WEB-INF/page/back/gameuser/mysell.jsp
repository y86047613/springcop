<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery-ui.custom.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery.gritter.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/select2.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/datepicker.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/bootstrap-editable.css" />

<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery-ui.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/ui.jqgrid.css" />
<!-- 我的账户信息 -->
<div class="page-header">
	<h1>
		<small> 用户名:<c:out value="${sessionScope.SESSION_SYS_USER.userName}" /> 
				我的推荐码:<c:out value="${sessionScope.SESSION_SYS_USER.id}" /> 
				余额  <label id="mysocre"><c:out value="${sessionScope.SESSION_SYS_USER.score}" /> </label>
				会员返利 <c:out value="${sessionScope.SESSION_SYS_USER.ticeng}" /> <!-- 开始提现 -->
				
		</small>
	</h1>
</div>

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-sm-12">
				<!-- #section:elements.tab -->
				<div class="tabbable">
					<ul class="nav nav-tabs" id="myTab">
						<li class="active">
							<a data-toggle="tab" href="#sell">
								<i class="green ace-icon fa fa-home bigger-120"></i> 出售
							</a>
						</li>
						
						<li>
							<a data-toggle="tab" href="#selllog">
							<i class="green ace-icon fa fa-home bigger-120"></i> 出售记录
							</a>
						</li>

						<li>
							<a data-toggle="tab" href="#buylog"> 
							<i class="green ace-icon fa fa-home bigger-120"></i>购买记录 
							</a>
						</li>

						<li>
							<a data-toggle="tab" href="#promote">
							<i class="green ace-icon fa fa-home bigger-120"></i> 我的推荐
							 </a>
						</li>
						
						<li>
							<a data-toggle="tab" href="#zhuanzhang">
							<i class="green ace-icon fa fa-home bigger-120"></i> 转账
							 </a>
						</li>
						
						<li>
							<a data-toggle="tab" href="#zhuanzhanglog">
							<i class="green ace-icon fa fa-home bigger-120"></i> 转账记录
							 </a>
						</li>
						
						<li class="">
							<a data-toggle="tab" href="#pwd">
							<i class="green ace-icon fa fa-key bigger-120"></i>修改密码 
							</a>
						</li>
					</ul>
					
					<!-- 出售 -->
					<div class="tab-content">
						<div id="sell" class="tab-pane fade in active">
							<div class="row">
								<div class="col-xs-12">
									<div class="well well-sm">
										<a>输入玩家ID:</a><input type="text" id="userId" name="userId">
										<a class="btn btn-info btn-sm" id="sellQuery">搜索玩家</a>
										
									</div>
								</div>
							</div>
							<div class="profile-user-info profile-user-info-striped" style="display: none" id="myselldiv">
								<div class="profile-info-row">
									<div class="profile-info-name">玩家ID</div>
									<div class="profile-info-value">
										<span class="editable" id="yuserId"></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">玩家昵称 </div>
									<div class="profile-info-value">
										<span class="editable" id="ynickName"></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">玩家余额 </div>
									<div class="profile-info-value">
										<span class="editable" id="yinsureScore"></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">充值房卡 </div>
									<div class="profile-info-value">
										<input type="text" id="score">
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> </div>
									<div class="profile-info-value">
										<a id="sellchongzhi" role="button" class="btn btn-purple btn-sm" data-toggle="modal">充值</a>
									</div>
								</div>
								
							</div>
						</div>
						<!-- ===================================================================================================================================== -->
						<!-- 出售记录 -->
						<div id="selllog" class="tab-pane fade">
							<div class="form-group">
								<table id="grid-table"></table>
								<div id="grid-pager"></div>
								<script type="text/javascript">
									var $path_base = "${contextPath}/static";//in Ace demo this will be used for editurl parameter
								</script>
							</div>
						</div>
						<!-- ===================================================================================================================================== -->
						<!-- 购买记录 -->
						<div id="buylog" class="tab-pane fade">
							<div class="form-group">
								<table id="grid-table2"></table>
								<div id="grid-pager2"></div>
								<script type="text/javascript">
									var $path_base = "${contextPath}/static";//in Ace demo this will be used for editurl parameter
								</script>
							</div>
						</div>
						<!-- ===================================================================================================================================== -->
						<!-- 推荐 -->
						<div id="promote" class="tab-pane fade">
							<div class="form-group">
								<table id="grid-table3"></table>
								<div id="grid-pager3"></div>
								<script type="text/javascript">
									var $path_base = "${contextPath}/static";//in Ace demo this will be used for editurl parameter
								</script>
							</div>
						</div>
						<!-- ===================================================================================================================================== -->
						<!-- 转账记录 -->
						<div id="zhuanzhanglog" class="tab-pane fade">
							<div class="form-group">
								<table id="grid-table4"></table>
								<div id="grid-pager4"></div>
								<script type="text/javascript">
									var $path_base = "${contextPath}/static";//in Ace demo this will be used for editurl parameter
								</script>
							</div>
						</div>
						
						<!-- ===================================================================================================================================== -->
						<!-- 转账 -->
						<div id="zhuanzhang" class="tab-pane fade">
							<div class="row">
								<div class="col-xs-12">
									<div class="well well-sm">
										<a>转账账户【登录账户】:</a><input type="text" id="email" name="email">
										<a class="btn btn-info btn-sm" id="emailQuery">搜索账户</a>
									</div>
								</div>
							</div>
							<div class="profile-user-info profile-user-info-striped" style="display: none" id="myEmaildiv">
								<div class="profile-info-row">
									<div class="profile-info-name">账户ID</div>
									<div class="profile-info-value">
										<span class="editable" id="emailId"></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">用户账户</div>
									<div class="profile-info-value">
										<span class="editable" id="emailEmail"></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">剩余房卡</div>
									<div class="profile-info-value">
										<span class="editable" id="emailScore"></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">转账房卡</div>
									<div class="profile-info-value">
										<input type="text" id="zhuanzhangScore">
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> </div>
									<div class="profile-info-value">
										<a id="Emailchongzhi" role="button" class="btn btn-purple btn-sm" data-toggle="modal">转账</a>
									</div>
								</div>
								
							</div>
						</div>
						<!-- ===================================================================================================================================== -->	
						<!-- 修改密码 -->
						<div id="pwd" class="tab-pane fade">
							<form class="form-horizontal">
								<div class="tabbable">

									<div class="tab-content profile-edit-tab-content">
										<div id="edit-basic" class="tab-pane"></div>

										<div id="edit-password" class="tab-pane in active">
											<div class="space-10"></div>

											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right"
													for="form-field-pass1">新密码</label>

												<div class="col-sm-9">
													<input type="password" id="form-field-pass1" />
												</div>
											</div>
											<div class="space-4"></div>
											<div class="form-group">
												<label class="col-sm-3 control-label no-padding-right"
													for="form-field-pass2">确认密码</label>
												<div class="col-sm-9">
													<input type="password" id="form-field-pass2" />
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button id="submitButton" class="btn btn-info" type="button">
											<i class="ace-icon fa fa-check bigger-110"></i> 保存
										</button>
										&nbsp; &nbsp;
										<button class="btn" type="reset">
											<i class="ace-icon fa fa-undo bigger-110"></i> 重置
										</button>
									</div>
								</div>
							</form>
						</div>
						
						<!-- 以上代码结束  -->
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var scripts = [ null,
			"${contextPath}/static/assets/js/jquery-ui.custom.js",
			"${contextPath}/static/assets/js/jquery.ui.touch-punch.js",
			"${contextPath}/static/assets/js/bootbox.js",
			"${contextPath}/static/assets/js/jquery.easypiechart.js",
			"${contextPath}/static/assets/js/jquery.gritter.js",
			"${contextPath}/static/assets/js/date-time/bootstrap-datepicker.js", 
			"${contextPath}/static/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js",
			"${contextPath}/static/assets/js/jqGrid/jquery.jqGrid.js", 
			"${contextPath}/static/assets/js/jqGrid/i18n/grid.locale-cn.js",
			"${contextPath}/static/assets/js/spin.js", null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
      		jQuery(function($) {
      			$(document).one('ajaxloadstart.page', function(e) {
      				$.gritter.removeAll();
      				$('.modal').modal('hide');
      			});
      			
      		}); 
        		
		/* 修改密码 */
		$('#submitButton').on('click', function() {
			if($('#form-field-pass1').val() != '' && ($('#form-field-pass1').val().length < 6 || $('#form-field-pass1').val().length > 14)){
				$.gritter.add({
	                title: '系统信息',
	                text: '新密码长度至少为6个字符，至多为14个字符',
	                class_name: 'gritter-warning gritter-center'
	            });
			}else if($('#form-field-pass1').val() != '' && $('#form-field-pass1').val() != $('#form-field-pass2').val()){
				$.gritter.add({
	                title: '系统信息',
	                text: '新密码和确认密码不一样，请重新输入',
	                class_name: 'gritter-warning gritter-center'
	            });
			}else if($('#form-field-pass1').val() != '' && $('#form-field-pass1').val() == $('#form-field-pass2').val()){
				$.ajax({
					dataType : "json",
					url : "${contextPath}" + "/sys/sysuser/resetPassword",
					type : "post",
					data : {
						password : $('#form-field-pass1').val()
					},
					complete : function(xmlRequest) {
						$.gritter.add({
			                title: '系统信息',
			                text: '密码修改成功',
			                class_name: 'gritter-success gritter-center'
			            });
					}
				});
			}
		});
		// 搜索账户
		
		$('#emailQuery').on('click', function() {
			if($('#email').val() == '' || $('#email').val().length < 1){
				$.gritter.add({
	                title: '系统消息',
	                text: '请输入用户',
	                class_name: 'gritter-warning gritter-center'
	            });
			}else{
				$.ajax({
					dataType : "json",
					url : "${contextPath}" + "/sys/sell/emailInformation",
					type : "post",
					data : {
						email : $('#email').val()
					},
					complete : function(data) {
						var jsonData = eval("("+data.responseText+")");
						var state= jsonData.state;
						if(state ==1){
							$.gritter.add({
				                title: '系统信息',
				                text: '查询成功',
				                class_name: 'gritter-success gritter-center'
				            });
							$('#myEmaildiv').css("display","block");
							$('#emailId').html(jsonData.id);
							$('#emailEmail').html(jsonData.email);
							$('#emailScore').html(jsonData.score);
						}else{
							$('#myEmaildiv').css("display","none");
							$('#emailId').html("");
							$('#emailEmail').html("");
							$('#emailScore').html("");
							$.gritter.add({
				                title: '系统信息',
				                text: '查询失败',
				                class_name: 'gritter-warning gritter-center'
				            });
						}
					}
				});	
			}
		});	
		// 转账
		var but_2 = false; 
		$('#Emailchongzhi').on('click', function() {
			if(but_2 == false){
				var reg = new RegExp("^[0-9]*$"); 
				if($('#emailId').html() == ''){
					$.gritter.add({
		                title: '系统消息',
		                text: '无ID信息',
		                class_name: 'gritter-warning gritter-center'
		            });
				}else if(!reg.test($('#zhuanzhangScore').val())){
					$.gritter.add({
		                title: '系统消息',
		                text: '转账金额错误',
		                class_name: 'gritter-warning gritter-center'
		            });
				}else{
					but_2 = true; 
					$.ajax({
						dataType : "json",
						url : "${contextPath}" + "/sys/sell/zhuanzhang",
						type : "post",
						data : {
							emailId : $('#emailId').html(),
							emailScore  :$('#zhuanzhangScore').val()
						},
						complete : function(data) {
							$("#zhuanzhangScore").val("0");
							var jsonData = eval("("+data.responseText+")");
							var success = jsonData.success;
							$('#myEmaildiv').css("display","none");
							if(success =='0'){
								$.gritter.add({
					                title: '系统信息',
					                text: '转账成功',
					                class_name: 'gritter-success gritter-center'
					            });
								$("#mysocre").html(jsonData.score);
							}else{
								$.gritter.add({
					                title: '系统信息',
					                text: success,
					                class_name: 'gritter-warning gritter-center'
					            });
							} 
						}
					});	
					but_2 = false; 
				}
			}
			
		});
		
		// 查询
		$('#sellQuery').on('click', function() {
			var reg = new RegExp("^[0-9]*$"); 
			if($('#userId').val() == '' || $('#userId').val().length < 1){
				$.gritter.add({
	                title: '系统消息',
	                text: '请输入用户ID',
	                class_name: 'gritter-warning gritter-center'
	            });
			}else if(!reg.test($('#userId').val())){
				$.gritter.add({
	                title: '系统消息',
	                text: '请输入数字',
	                class_name: 'gritter-warning gritter-center'
	            });
			}else{
				$.ajax({
					dataType : "json",
					url : "${contextPath}" + "/sys/sell/information",
					type : "post",
					data : {
						userId : $('#userId').val()
					},
					complete : function(data) {
						var jsonData = eval("("+data.responseText+")");
						var userId = jsonData.userId;
						var nickName= jsonData.nickName;
						var insureScore = jsonData.insureScore;
						if(nickName !=''){
							$.gritter.add({
				                title: '系统信息',
				                text: '查询成功',
				                class_name: 'gritter-success gritter-center'
				            });
							$('#myselldiv').css("display","block");
							$('#yuserId').html(userId);
							$('#ynickName').html(nickName);
							$('#yinsureScore').html(insureScore);
						}else{
							$('#myselldiv').css("display","none");
							$('#yuserId').html("");
							$('#ynickName').html("");
							$('#yinsureScore').html("");
							$.gritter.add({
				                title: '系统信息',
				                text: '查询失败',
				                class_name: 'gritter-warning gritter-center'
				            });
						}
					}
				});	
			}
			
		});	
	});
</script>

<!-- 分页出售记录 -->
<script type="text/javascript">
        $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
        	jQuery(function($) {
        		var grid_selector = "#grid-table";
        		var pager_selector = "#grid-pager";
        		 $(window).on('resize.jqGrid', function() {
        			$(grid_selector).jqGrid('setGridWidth', $(".page-content").width()-25);
        		})
        		var parent_column = $(grid_selector).closest('[class*="col-"]');
        		$(document).on('settings.ace.jqGrid', function(ev, event_name, collapsed) {
        			if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
        				setTimeout(function() {
        					$(grid_selector).jqGrid('setGridWidth', parent_column.width());
        				}, 0);
        			}
        		}) 
        		jQuery(grid_selector).jqGrid({
        			subGrid : false,
        			url : "${contextPath}/sys/sell/sellLog",
        			datatype : "json",
        			height : "50%",
        			colNames : [ '充值账户ID','账户名称', '被充值ID', '被充值账户', '房卡','充值时间','功能'],
        			colModel : [{
        				name : 'a_id',
        				index : 'a_id',
        				label : 'a_id',
        				width : 60,
        				sorttype : "long"
        			}, {
        				name : 'a_name',
        				index : 'a_name',
        				width : 60
        			}, {
        				name : 'b_id',
        				index : 'b_id',
        				width : 100,
        				sorttype : "long"
        			}, {
        				name : 'b_name',
        				index : 'b_name',
        				width : 80
        			}, {
        				name : 'score',
        				index : 'score',
        				width : 160,
        				sorttype : "long"
        			},{
        				name : 'createTime',
        				index : 'createTime',
        				width : 110,
        				search : false,
        				editable : false,
        				sorttype : 'date',
        				unformat : pickDate
        			},{
        				name : 'func',
        				index : 'func',
        				width : 110
        			}],
        			sortname : "id",
        			sortorder : "asc",
        			viewrecords : true,
        			rowNum : 10,
        			rowList : [ 10, 20, 30 ],
        			pager : pager_selector,
        			altRows : true,
        			//toppager : true,
        			multiselect : false,
        			//multikey : "ctrlKey",
        	        multiboxonly : true,
        			
        		});
        		
        		$(window).triggerHandler('resize.jqGrid');
        		
        		/* function aceSwitch(cellvalue, options, cell) {
        			setTimeout(function() {
        				$(cell).find('input[type=checkbox]').addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
        			}, 0);
        		} */
        		
        		// enable datepicker
        		 function pickDate(cellvalue, options, cell) {

        			setTimeout(function() {
        				$(cell).find('input[type=text]').datepicker({
        					format : 'yyyy-mm-dd',
        					autoclose : true,
        				    language: 'zh-CN'
        				});
        			}, 0);
        		}

        		// var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

        		/* $(document).one('ajaxloadstart.page', function(e) {
        			$(grid_selector).jqGrid('GridUnload');
        			$('.ui-jqdialog').remove();
        		}); */
        		
        	});
        });
</script>


<!-- 购买记录 -->
<script type="text/javascript">
        $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
        	jQuery(function($) {
        		var grid_selector2 = "#grid-table2";
        		var pager_selector2 = "#grid-pager2";
        		  $(window).on('resize.jqGrid', function() {
        			$(grid_selector2).jqGrid('setGridWidth', $(".page-content").width()-25);
        		})
        		var parent_column2 = $(grid_selector2).closest('[class*="col-"]');
        		$(document).on('settings.ace.jqGrid', function(ev, event_name, collapsed) {
        			if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
        				setTimeout(function() {
        					$(grid_selector2).jqGrid('setGridWidth', parent_column2.width());
        				}, 0);
        			}
        		})
        		jQuery(grid_selector2).jqGrid({
        			subGrid : false,
        			url : "${contextPath}/sys/sell/buyLog",
        			datatype : "json",
        			height : "50%",
        			colNames : [ '充值账户ID','账户名称', '被充值ID', '被充值账户', '房卡','充值时间','功能'],
        			colModel : [{
        				name : 'a_id',
        				index : 'a_id',
        				label : 'a_id',
        				width : 60,
        				sorttype : "long"
        			}, {
        				name : 'a_name',
        				index : 'a_name',
        				width : 60
        			}, {
        				name : 'b_id',
        				index : 'b_id',
        				width : 100,
        				sorttype : "long"
        			}, {
        				name : 'b_name',
        				index : 'b_name',
        				width : 80
        			}, {
        				name : 'score',
        				index : 'score',
        				width : 160,
        				sorttype : "long"
        			},{
        				name : 'createTime',
        				index : 'createTime',
        				width : 110,
        				search : false,
        				editable : false,
        				sorttype : 'date',
        				unformat : pickDate
        			}, {
        				name : 'func',
        				index : 'func',
        				width : 160,
        			}],
        			sortname : "id",
        			sortorder : "asc",
        			viewrecords : true,
        			rowNum : 10,
        			rowList : [ 10, 20, 30 ],
        			pager : pager_selector2,
        			altRows : true,
        			//toppager : true,
        			multiselect : false,
        			//multikey : "ctrlKey",
        	        multiboxonly : true,
        			
        		});
        		
        		$(window).triggerHandler('resize.jqGrid');
        		
        		/* function aceSwitch(cellvalue, options, cell) {
        			setTimeout(function() {
        				$(cell).find('input[type=checkbox]').addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
        			}, 0);
        		} */
        		
        		// enable datepicker
        		 function pickDate(cellvalue, options, cell) {

        			setTimeout(function() {
        				$(cell).find('input[type=text]').datepicker({
        					format : 'yyyy-mm-dd',
        					autoclose : true,
        				    language: 'zh-CN'
        				});
        			}, 0);
        		}

        		// var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

        		/* $(document).one('ajaxloadstart.page', function(e) {
        			$(grid_selector).jqGrid('GridUnload');
        			$('.ui-jqdialog').remove();
        		}); */
        		
        	});
        });
</script>



<!-- 转账记录 -->
<script type="text/javascript">
        $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
        	jQuery(function($) {
        		var grid_selector4 = "#grid-table4";
        		var pager_selector4 = "#grid-pager4";
        		  $(window).on('resize.jqGrid', function() {
        			$(grid_selector4).jqGrid('setGridWidth', $(".page-content").width()-25);
        		})
        		var parent_column4 = $(grid_selector4).closest('[class*="col-"]');
        		$(document).on('settings.ace.jqGrid', function(ev, event_name, collapsed) {
        			if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
        				setTimeout(function() {
        					$(grid_selector4).jqGrid('setGridWidth', parent_column4.width());
        				}, 0);
        			}
        		})
        		jQuery(grid_selector4).jqGrid({
        			subGrid : false,
        			url : "${contextPath}/sys/sell/zhuanzhangLog",
        			datatype : "json",
        			height : "50%",
        			colNames : [ '转账账户ID','账户名称', '被转账ID', '被转账账户', '房卡','转账时间','功能'],
        			colModel : [{
        				name : 'a_id',
        				index : 'a_id',
        				label : 'a_id',
        				width : 60,
        				sorttype : "long"
        			}, {
        				name : 'a_name',
        				index : 'a_name',
        				width : 60
        			}, {
        				name : 'b_id',
        				index : 'b_id',
        				width : 100,
        				sorttype : "long"
        			}, {
        				name : 'b_name',
        				index : 'b_name',
        				width : 80
        			}, {
        				name : 'score',
        				index : 'score',
        				width : 160,
        				sorttype : "long"
        			},{
        				name : 'createTime',
        				index : 'createTime',
        				width : 110,
        				search : false,
        				editable : false,
        				sorttype : 'date',
        				unformat : pickDate
        			}, {
        				name : 'func',
        				index : 'func',
        				width : 160,
        			}],
        			sortname : "id",
        			sortorder : "asc",
        			viewrecords : true,
        			rowNum : 10,
        			rowList : [ 10, 20, 30 ],
        			pager : pager_selector4,
        			altRows : true,
        			//toppager : true,
        			multiselect : false,
        			//multikey : "ctrlKey",
        	        multiboxonly : true,
        			
        		});
        		
        		$(window).triggerHandler('resize.jqGrid');
        		
        		/* function aceSwitch(cellvalue, options, cell) {
        			setTimeout(function() {
        				$(cell).find('input[type=checkbox]').addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
        			}, 0);
        		} */
        		
        		// enable datepicker
        		 function pickDate(cellvalue, options, cell) {

        			setTimeout(function() {
        				$(cell).find('input[type=text]').datepicker({
        					format : 'yyyy-mm-dd',
        					autoclose : true,
        				    language: 'zh-CN'
        				});
        			}, 0);
        		}

        		// var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

        		/* $(document).one('ajaxloadstart.page', function(e) {
        			$(grid_selector).jqGrid('GridUnload');
        			$('.ui-jqdialog').remove();
        		}); */
        		
        	});
        });
</script>


<!--我的推荐  -->
<script type="text/javascript">
        $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
        	jQuery(function($) {
        		var grid_selector3 = "#grid-table3";
        		var pager_selector3 = "#grid-pager3";
        		  $(window).on('resize.jqGrid', function() {
        			$(grid_selector3).jqGrid('setGridWidth', $(".page-content").width()-25);
        		})
        		var parent_column3 = $(grid_selector3).closest('[class*="col-"]');
        		$(document).on('settings.ace.jqGrid', function(ev, event_name, collapsed) {
        			if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
        				setTimeout(function() {
        					$(grid_selector3).jqGrid('setGridWidth', parent_column3.width());
        				}, 0);
        			}
        		})
        		jQuery(grid_selector3).jqGrid({
        			subGrid : false,
        			url : "${contextPath}/sys/sell/parent",
        			datatype : "json",
        			height : "50%",
        			colNames : [ '账户ID','账户', '账户昵称', '房卡', '联系方式','最后登录时间'],
        			colModel : [{
        				name : 'id',
        				index : 'id',
        				label : 'id',
        				width : 60,
        				sorttype : "long"
        			}, {
        				name : 'email',
        				index : 'email',
        				width : 60
        			}, {
        				name : 'userName',
        				index : 'userName',
        				width : 100,
        			}, {
        				name : 'score',
        				index : 'score',
        				width : 160,
        				sorttype : "long"
        			}, {
        				name : 'phone',
        				index : 'phone',
        				width : 80
        			},{
        				name : 'lastLoginTime',
        				index : 'lastLoginTime',
        				width : 110,
        				search : false,
        				editable : false,
        				sorttype : 'date',
        				unformat : pickDate
        			}],
        			sortname : "id",
        			sortorder : "asc",
        			viewrecords : true,
        			rowNum : 10,
        			rowList : [ 10, 20, 30 ],
        			pager : pager_selector3,
        			altRows : true,
        			//toppager : true,
        			multiselect : false,
        			//multikey : "ctrlKey",
        	        multiboxonly : true,
        			
        		});
        		
        		$(window).triggerHandler('resize.jqGrid');
        		
        		 function pickDate(cellvalue, options, cell) {

        			setTimeout(function() {
        				$(cell).find('input[type=text]').datepicker({
        					format : 'yyyy-mm-dd',
        					autoclose : true,
        				    language: 'zh-CN'
        				});
        			}, 0);
        		}

        		
        	});
        });
</script>
<script type="text/javascript">
/* 充值记录 */
var but_1 = false; 
$('#sellchongzhi').on('click', function() {
	if(but_1 == false){
		but_1 =true;
		var reg = new RegExp("^[0-9]*$"); 
		if($('#yuserId').html() == ''){
			$.gritter.add({
                title: '系统消息',
                text: '无ID信息',
                class_name: 'gritter-warning gritter-center'
            });
		}else if(!reg.test($('#score').val())){
			$.gritter.add({
                title: '系统消息',
                text: '房卡信息错误',
                class_name: 'gritter-warning gritter-center'
            });
		}else{
			$('#myselldiv').css("display","none");
			$.ajax({
				dataType : "json",
				url : "${contextPath}" + "/sys/sell/chongzhi",
				type : "post",
				data : {
					userId : $('#yuserId').html(),
					score  :$('#score').val()
				},
				complete : function(data) {
					$("#score").val("0");
					var jsonData = eval("("+data.responseText+")");
					var success = jsonData.success;
					if(success =='0'){
						$.gritter.add({
			                title: '系统信息',
			                text: '充值成功',
			                class_name: 'gritter-success gritter-center'
			            });
						$("#mysocre").html(jsonData.score);
					}else{
						$.gritter.add({
			                title: '系统信息',
			                text: success,
			                class_name: 'gritter-warning gritter-center'
			            });
					}
				}
			});	
			but_1 = false; 
		}
		
	}
	
});
</script>			