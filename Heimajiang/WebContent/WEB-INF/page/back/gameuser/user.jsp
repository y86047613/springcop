<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<link rel="stylesheet" href="${contextPath}/static/assets/css/jquery-ui.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/datepicker.css" />
<link rel="stylesheet" href="${contextPath}/static/assets/css/ui.jqgrid.css" />

<input type="text" hidden="" id="gameRole" value="${gameRole}">


<div id="modal-table" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="min-width: 420px;">
		<form id="informationForm">
			<div class="modal-content">
				<div class="modal-header no-padding">
					<div class="table-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							<span class="white">&times;</span>
						</button>
						用户充值
					</div>
				</div>
				<div class="modal-body" style="max-height: 500px;overflow-y: scroll;">
					<div id="modal-tip" class="red clearfix"></div>
					<div class="blue clearfix">
						<label for="title">用户ID：</label>
						<input type="text" id="chongZhiId" class="width-100" />
					</div>
					<div class="space-4"></div>
					<div class="blue clearfix">
						<label for="author">金币：</label>
						<input type="text" id="chongZhiScore" class="width-100" />
					</div>
					
				</div>
				<div class="modal-footer no-margin-top">
					<div class="text-center">
						<button id="submitButton" type="submit" class="btn btn-app btn-success btn-xs">
							<i class="ace-icon fa fa-floppy-o bigger-160"></i>
							保存
						</button>
						<button class="btn btn-app btn-pink btn-xs" data-dismiss="modal">
							<i class="ace-icon fa fa-share bigger-160"></i>
							取消
						</button>
					</div>
				</div>
			</div><!-- /.modal-content -->
		</form>
	</div><!-- /.modal-dialog -->
</div>


<div class="row">
	<div class="col-xs-12">
		<div class="well well-sm">
			<a>账户:</a><input type="text" id="myaccounts" name="accounts">
			<a>账户ID:</a><input type="text" id="userId" name="userId">
			<a>昵称:</a><input type="text" id="mynickName" name="nickName">
			<a>推荐人账户:</a><input type="text" id="myspreaderID" name="spreaderID">
			<a class="btn btn-info btn-sm" onclick="queryUser()">
				检索
			</a>
			<!-- <a id="editInformationButton" role="button" class="btn btn-purple btn-sm" data-toggle="modal">
				充值
			</a> -->
		</div>
	</div>
	<div class="col-xs-12">
		<table id="grid-table"></table>

		<div id="grid-pager"></div>

		<script type="text/javascript">
			var $path_base = "${contextPath}/static";//in Ace demo this will be used for editurl parameter
		</script>

		<!-- PAGE CONTENT ENDS -->
	</div><!-- /.col -->
</div><!-- /.row -->
<!-- page specific plugin scripts -->
<script type="text/javascript">
		var gameRole =$("#gameRole").val();
		if(gameRole=="true"){
			gameRole=true;
		}else{
			gameRole = false;
		}
		
		var scripts = [ null, "${contextPath}/static/assets/js/date-time/bootstrap-datepicker.js", "${contextPath}/static/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js", "${contextPath}/static/assets/js/jqGrid/jquery.jqGrid.js", "${contextPath}/static/assets/js/jqGrid/i18n/grid.locale-cn.js", null ]
        $('.page-content-area').ace_ajax('loadScripts', scripts, function() {
        	// inline scripts related to this page
        	jQuery(function($) {
        		var grid_selector = "#grid-table";
        		var pager_selector = "#grid-pager";

        		// resize to fit page size
        		$(window).on('resize.jqGrid', function() {
        			$(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
        		})
        		// resize on sidebar collapse/expand
        		var parent_column = $(grid_selector).closest('[class*="col-"]');
        		$(document).on('settings.ace.jqGrid', function(ev, event_name, collapsed) {
        			if (event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed') {
        				// setTimeout is for webkit only to give time for DOM changes and then redraw!!!
        				setTimeout(function() {
        					$(grid_selector).jqGrid('setGridWidth', parent_column.width());
        				}, 0);
        			}
        		})
        		jQuery(grid_selector).jqGrid({
        			subGrid : false,
        			url : "${contextPath}/sys/game/getuser",
        			datatype : "json",
        			height : "50%",
        			colNames : ['操作', '用户ID','推荐人ID', '账户', '昵称', '密码','权限','金币','房卡','胜利次数','逃跑次数','平均次数','失败次数', '银行密码', '性别', '上次登录IP', '注册IP', '最后登录时间', '注册时间'],
        			colModel : [ {
        				name : '',
        				index : '',
        				width : 80,
        				fixed : true,
        				sortable : false,
        				resize : false,
        				hidden:gameRole,
        				formatter : 'actions',
        				formatoptions : {
        					keys : true,
        					delbutton : false,//disable delete button
        					delOptions : {
        						recreateForm : true,
        						beforeShowForm : beforeDeleteCallback
        					}
        					//editformbutton:true, editOptions:{recreateForm:true, beforeShowForm:beforeEditCallback}
        				}
        			}, {
        				name : 'id',
        				index : 'id',
        				label : 'ID',
        				width : 60,
        				sorttype : "long",
        				search : false
        			}, {
        				name : 'spreaderID',
        				index : 'spreaderID',
        				label : '推荐人ID',
        				searchoptions : {sopt : ['eq']},
        				width : 60,
        				sorttype : "long",
        			}, {
        				name : 'accounts',
        				index : 'accounts',
        				label : '账户',
        				width : 100,
        				searchoptions : {sopt : ['eq']},
        				editable : true
        			}, {
        				name : 'nickName',
        				index : 'nickName',
        				label : '昵称',
        				width : 80,
        				searchoptions : {sopt : ['eq']},
        				editable : true,
        			}, {
        				name : 'logonPass',
        				index : 'logonPass',
        				label : '密码',
        				width : 160,
        				search : false,
        				editable : true
        			}, {
        				name : 'userRight',
        				index : 'userRight',
        				label : '权限',
        				width : 160,
        				search : false,
        				editable : true
        			}, {
        				name : 'score',
        				index : 'score',
        				label : '金币',
        				sorttype : "long",
        				width : 160,
        				search : false,
        				editable : true
        			}, {
        				name : 'insureScore',
        				index : 'insureScore',
        				label : '房卡',
        				width : 160,
        				sorttype : "long",
        				search : false,
        				editable : true
        			}, {
        				name : 'winCount',
        				index : 'winCount',
        				label : '胜利次数',
        				width : 160,
        				search : false,
        				editable : false
        			}, {
        				name : 'drawCount',
        				index : 'drawCount',
        				label : '平均次数',
        				width : 160,
        				search : false,
        				editable : false
        			}, {
        				name : 'fleeCount',
        				index : 'fleeCount',
        				label : '逃跑次数',
        				width : 160,
        				search : false,
        				editable : false
        			}, {
        				name : 'lostCount',
        				index : 'lostCount',
        				label : '失败次数',
        				width : 160,
        				search : false,
        				editable : false
        			}, {
        				name : 'insurePass',
        				index : 'insurePass',
        				label : '银行密码',
        				width : 150,
        				search : false,
        				hidden:true,
        				editable : false
        			} , {
        				name : 'gender',
        				index : 'gender',
        				label : '性别',
        				width : 110,
        				hidden:true,
        				search : false,
        				editoptions : {value : "0:男;1:女"},
        				editable : false
        			}, {
        				name : 'lastLogonIP',
        				index : 'lastLogonIP',
        				label : '上次登录IP',
        				width : 110,
        				hidden:true,
        				search : false,
        				editable : false  
        			}, {
        				name : 'registerIP',
        				index : 'registerIP',
        				label : '上次登录IP',
        				width : 110,
        				search : false,
        				hidden:true,
        				editable : false  
        			}, {
        				name : 'lastLogonDate',
        				index : 'lastLogonDate',
        				label : '最后登录时间',
        				width : 110,
        				search : false,
        				editable : false,
        				sorttype : 'date',
        				unformat : pickDate
        			}, {
        				name : 'registerDate',
        				index : 'registerDate',
        				label : '注册时间',
        				width : 110,
        				search : false,
        				editable : false,
        				sorttype : 'date',
        				unformat : pickDate
        			}],
        			//scroll : 1, // set the scroll property to 1 to enable paging with scrollbar - virtual loading of records
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
        			loadComplete : function() {
        				var table = this;
        				setTimeout(function(){
        					styleCheckbox(table);
        					updateActionIcons(table);
        					updatePagerIcons(table);
        					enableTooltips(table);
        				}, 0);
        			},
        			editurl : "${contextPath}/sys/game/operateGameUser"
        			//caption : "用户管理列表",
        			//autowidth : true,
        			/**
        			grouping : true, 
        			groupingView : { 
        				 groupField : ['name'],
        				 groupDataSorted : true,
        				 plusicon : 'fa fa-chevron-down bigger-110',
        				 minusicon : 'fa fa-chevron-up bigger-110'
        			},
        			*/
        		});
        		
        		
        		$(window).triggerHandler('resize.jqGrid');// trigger window resize to make the grid get the correct size
        		
        		// enable search/filter toolbar
        		// jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
        		// jQuery(grid_selector).filterToolbar({});
        		// switch element when editing inline
        		function aceSwitch(cellvalue, options, cell) {
        			setTimeout(function() {
        				$(cell).find('input[type=checkbox]').addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
        			}, 0);
        		}
        		
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
        		
        		
        		
        		function style_edit_form(form) {
        			// enable datepicker on "birthday" field and switches for "stock" field
        			form.find('input[name=birthday]').datepicker({
        				format : 'yyyy-mm-dd',
        				autoclose : true,
        			    language: 'zh-CN'
        			})

        			form.find('input[name=statusCn]').addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
        			// don't wrap inside a label element, the checkbox value won't be submitted (POST'ed)
        			// .addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

        			// update buttons classes
        			var buttons = form.next().find('.EditButton .fm-button');
        			buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();// ui-icon, s-icon
        			buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
        			buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')

        			buttons = form.next().find('.navButton a');
        			buttons.find('.ui-icon').hide();
        			buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
        			buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');
        		}

        		function style_delete_form(form) {
        			var buttons = form.next().find('.EditButton .fm-button');
        			buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();// ui-icon, s-icon
        			buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
        			buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
        		}

        		function style_search_filters(form) {
        			form.find('.delete-rule').val('X');
        			form.find('.add-rule').addClass('btn btn-xs btn-primary');
        			form.find('.add-group').addClass('btn btn-xs btn-success');
        			form.find('.delete-group').addClass('btn btn-xs btn-danger');
        		}
        		function style_search_form(form) {
        			var dialog = form.closest('.ui-jqdialog');
        			var buttons = dialog.find('.EditTable')
        			buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
        			buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
        			buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
        		}

        		function beforeDeleteCallback(e) {
        			var form = $(e[0]);
        			alert(form);
        			if (form.data('styled'))
        				return false;
        			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        			style_delete_form(form);
        			form.data('styled', true);
        		}

        		function beforeEditCallback(e) {
        			var form = $(e[0]);
        			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
        			style_edit_form(form);
        		}

        		$("#editInformationButton").bind("click", function() {
        			$("#modal-table").modal("toggle");
        		});
        		
        		$("#informationForm").on("submit", function(e) {
        			var s = $("#chongZhiId").val();
        			var b = $("#chongZhiScore").val();
        			if ($("#chongZhiId").val() == "") {
    					$("#modal-tip").html("请填写充值账户ID");
    			        return;
    			    } else if(isNaN(s)){
    			    	$("#modal-tip").html("请填写数字");
    			        return;
    			    }else if ($("#chongZhiScore").val() == "") {
    					$("#modal-tip").html("请填写充值金额");
    			        return;
    			    } else if(isNaN(b)){
    			    	$("#modal-tip").html("请填写数字");
    			        return;
    			    }else {
    			    	$.ajax({
            				dataType : "json",
            				url : "${contextPath}/sys/game/chongzhi",
            				type : "post",
            				data : {
            					chongZhiId : s,
            					chongZhiScore :b
            				},
            				complete : function(xmlRequest) {
            					$("#modal-table").modal("toggle");
            					jQuery(grid_selector).trigger("reloadGrid");
            				}
            			});
    			    }
        		});
        		
        		// it causes some flicker when reloading or navigating grid
        		// it may be possible to have some custom formatter to do this as the grid is being created to prevent this
        		// or go back to default browser checkbox styles for the grid
        		function styleCheckbox(table) {
        			/**
        			 * $(table).find('input:checkbox').addClass('ace') .wrap('<label />') .after('<span class="lbl align-top" />') $('.ui-jqgrid-labels th[id*="_cb"]:first-child')
        			 * .find('input.cbox[type=checkbox]').addClass('ace') .wrap('<label />').after('<span class="lbl align-top" />');
        			 */
        		}

        		// unlike navButtons icons, action icons in rows seem to be hard-coded
        		// you can change them like this in here if you want
        		function updateActionIcons(table) {
        			/**
        			 * var replacement = { 'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue', 'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red', 'ui-icon-disk' : 'ace-icon fa fa-check green', 'ui-icon-cancel' :
        			 * 'ace-icon fa fa-times red' }; $(table).find('.ui-pg-div span.ui-icon').each(function(){ var icon = $(this); var $class = $.trim(icon.attr('class').replace('ui-icon', '')); if($class in replacement)
        			 * icon.attr('class', 'ui-icon '+replacement[$class]); })
        			 */
        		}

        		
        		// replace icons with FontAwesome icons like above
        		function updatePagerIcons(table) {
        			var replacement = {
        				'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
        				'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
        				'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
        				'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
        			};
        			$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function() {
        				var icon = $(this);
        				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

        				if ($class in replacement)
        					icon.attr('class', 'ui-icon ' + replacement[$class]);
        			})
        		}

        		function enableTooltips(table) {
        			$('.navtable .ui-pg-button').tooltip({
        				container : 'body'
        			});
        			$(table).find('.ui-pg-div').tooltip({
        				container : 'body'
        			});
        		}

        		// var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

        		$(document).one('ajaxloadstart.page', function(e) {
        			$(grid_selector).jqGrid('GridUnload');
        			$('.ui-jqdialog').remove();
        		});
        		
        	});
        });
</script>


<script>

    function queryUser() {
    	var grid_selector = "#grid-table";
        var pager_selector = "#grid-pager";	
    	var accounts =$('#myaccounts').val();
    	var userId =$('#userId').val();
		var nickName =$('#mynickName').val();
		var spreaderID =$('#myspreaderID').val();
        $(grid_selector).jqGrid('setGridParam', {
            url: "${contextPath}/sys/game/getuser",
            //发送数据
            postData: {
                "accounts": accounts,
                "nickName": nickName,
                "userId":userId,
                "spreaderID": spreaderID
            },
            page: 1,
            mtype: "post",
            //该方法是加载完
            loadComplete: function (xhr) {
                /*alert("查询完成"+xhr.result);*/
            }
        }).trigger("reloadGrid");//重新载入
    }
</script> 