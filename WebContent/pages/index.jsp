<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统首页</title>
<!-- 下载并引入jquery easyUI -->
<link rel="stylesheet" type="text/css"
	href="../js/jquery-easyui-1.9.5/themes/ui-cupertino/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../js/jquery-easyui-1.9.5/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="../js/jquery-easyui-1.9.5/themes/color.css">
<script type="text/javascript"
	src="../js/jquery-easyui-1.9.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../js/jquery-easyui-1.9.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../js/context-menu.js"></script>
<script type="text/javascript"
	src="../js/datagrid-detailview.js"></script>
	
	<style type="text/css">
	
	.top {
    	height:80px;
    	border-bottom:1px #95b8e7 solid;
    	background-image:url(../images/top5.jpg);
	}
	.button-tools {
	    float:right;
	    margin-right:10px;
	    margin-top:40px;
	}
	
	.exit-system {
	    float:right;
	    margin-right:10px;
	    margin-left:20px;
	}
	
	</style>
	
</head>
<body class="easyui-layout">
	<script type="text/javascript">
		if (window.top != window.self) {
			window.top.location = "./index.html";
		}
	</script>

	<div id="cc" class="easyui-layout"
		style="width: 100%; heith: 100%; min-height: 600px;"
		data-options="fit:true" >

		<div data-options="region:'north',border:false"  style="overflow: hidden; height: 80px;" >
	        <div class="top">
	            <div class="button-tools">
	                <div class="exit-system"><a id="exitSystem" target="_top" class="easyui-linkbutton"  href="../login.jsp" data-options="iconCls:'icon-no'">安全退出</a></div>
	                <div class="exit-system"><a id="modifyPassSystem" onclick="updatepass()" class="easyui-linkbutton" data-options="iconCls:'icon-large-clipart'">修改密码</a></div>
	                <div class="exit-system">
	                    <div class="curUserInfo" style="color:white; font-size:20px;">当前登录用户：${LOGIN_DOCTOR.doctorName}</div>
	                    
	                </div>
	            </div>
	        </div>
	    </div>
		
		
		<div data-options="region:'west',title:'管理菜单',split:true"
			style="width: 200px;">
			<div id="aa" class="easyui-accordion"
				data-options="fit:true,border:false">

				<div title="基本信息管理" data-options="iconCls:'icon-1',selected:true"
					style="overflow: auto; padding: 10px;">

					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton" 
						data-options="plain:true,"
						onclick="open_menu('医生信息管理','../pages/doctorInformation.jsp')">医生信息管理</a><br>

					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true"
						onclick="open_menu('患者信息管理','../pages/patientInformation.jsp')">患者信息管理</a><br>
				</div>
				
				<!--  -->
				<div title="药品信息管理" data-options="iconCls:'icon-2',selected:true"
					style="overflow: auto; padding: 10px;">

					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton" 
						data-options="plain:true,"
						onclick="open_menu('药品类型信息管理','../pages/typeInformation.jsp')">药品类型信息管理</a><br>

					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true"
						onclick="open_menu('药品信息管理','../pages/drugInformation.jsp')">药品信息管理</a>
						<br>
						
					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true"
						onclick="open_menu('药品供应商管理','../pages/providerInformation.jsp')">药品供应商管理</a>
						<br>
					
				</div>
				
				
				<!--  -->
				<div title="药品入库管理" data-options="iconCls:'icon-3',selected:true"
					style="overflow: auto; padding: 10px;">

					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true"
						onclick="open_menu('药品入库信息管理',' ../pages/stockIn-list.jsp')">药品入库信息管理</a>
						<br>
						<a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true"
						onclick="open_menu('药品入库明细','../pages/stockInDetail.jsp')">药品入库明细管理</a>
				</div>
				
				
				<div title="药品出库管理" data-options="iconCls:'icon-4',selected:true"
					style="overflow: auto; padding: 10px;">
					
					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
					data-options="plain:true"
					onclick="open_menu('药品出库信息管理',' ../pages/stockOut-list.jsp')">药品出库信息管理</a>
					<br>
					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
					data-options="plain:true"
					onclick="open_menu('药品出库明细','../pages/stockOutDetail-list.jsp')">药品出库明细管理</a>
					<br>
						
				</div>
				
				<div title="药品库存管理" data-options="iconCls:'icon-5',selected:true"
					style="overflow: auto; padding: 10px;">

					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true"
						onclick="open_menu('药品库存信息管理',' ../pages/storageInformation.jsp')">药品库存信息管理</a>
			
				</div>
					<!--  -->
				<div title="药品采购管理" data-options="iconCls:'icon-6',selected:true"
					style="overflow: auto; padding: 10px;">
					

					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton" 
						data-options="plain:true,"
						onclick="open_menu('药品采购管理','../pages/purchasingInformation.jsp')">药品采购管理</a><br>

					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true"
						onclick="open_menu('药品采购明细管理','../pages/purchasingDetailInformation.jsp')">药品采购明细管理</a>
						<br>
						
				</div>
				
				<div title="药品出售管理" data-options="iconCls:'icon-7',selected:true"
					style="overflow: auto; padding: 10px;">
					

					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton" 
						data-options="plain:true,"
						onclick="open_menu('药品出售管理','../pages/saleInformation.jsp')">药物出售管理</a><br>

					<a id="btn" href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true"
						onclick="open_menu('药品出售明细管理','../pages/saleDetailInformation.jsp')">药品出售明细管理</a>
						<br>		
				</div>

				<div title="系统管理" data-options="iconCls:'icon-reload'"
					style="padding: 10px;">
						
					<a href="javascript:void(0);" class="easyui-linkbutton"
						data-options="plain:true"
						onclick="updatepass()">修改密码</a>
					<br> <a
						href="../login.jsp"
						class="easyui-linkbutton" data-options="plain:true">退出系统</a>
				</div>

			</div>
		</div>

		<div data-options="region:'center',border:false,plain:true" >
			<div id="tt" class="easyui-tabs" data-options="fit:true"
				data-options="tools:'#tab-tools'" >
				<div title="首页" style="font-size: 40px; background:url(../images/bg.jpg)no-repeat;" >
					<p style="margin-left:940px; margin-top:200px;">欢迎来到</p>
					<p style="margin-left:900px; margin-top:10px;">医院中央药房</p>
					<p style="margin-left:900px; margin-top:10px;">后台管理系统</p>
					
				</div>
			</div>
		</div>

		<div id="tab-tools">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-reload'" onclick="reload()"></a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-delete'" onclick="closecur()"></a>
		</div>

		<div id="mm" class="easyui-menu" style="width: 150px;">
			<div data-options="iconCls:'icon-reload'" id="refresh">刷新</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-delete0'" id="closecur">关闭当前</div>
			<div data-options="iconCls:'icon-delete0'" id="closeall">全部关闭</div>
			<div data-options="iconCls:'icon-delete0'" id="closeother">除此之外全部关闭</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-delete0'" id="closeright">当前页右侧全部关闭</div>
			<div data-options="iconCls:'icon-delete0'" id="closeleft">当前页左侧全部关闭</div>
		</div>


		<div data-options="region:'south',border:false,plain:true"
			style="height: 40px; text-align: center; font-size: 12px;">
			<p>版权所有：练习生组&copy;2016-2018</p>
		</div>
	</div>
	
	<div id="win" class="easyui-window" title="修改密码" style="width:400px;height:260px;" closed="true" data-options="collapsible:false,minimizable:false,resizable:false,maximizable:false"  buttons="#updatepass_dlg-buttons">
		<form id="updatePassWord" style="padding:10px 20px 10px 40px;" method="post">
			<p>旧密码: <input  name="opassword" style="width: 260px;"
					class="easyui-passwordbox " 
					prompt="请输入旧密码" required="true" ></p>
			<p>新密码: <input name="newpassword" style="width: 260px;"
					class="easyui-passwordbox " prompt="请输入新密码" required="true" ></p>
			<p>确认密码: <input name="password" style="width: 260px;"
					class="easyui-passwordbox " prompt="再次输入新密码" required="true" ></p>
			<div id="updatepass_dlg-buttons" style="text-align: center; margin-top:50px;">
				<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
					onclick="saveUsers()">保存</a> <a href="#" class="easyui-linkbutton"
					iconCls="icon-cancel" style="margin-left:50px;"
					onclick="javascript:$('#win').dialog('close')">取消</a>
			</div>
		</form>
	</div>
	
	

	<script type="text/javascript">
		function open_menu(titleName, menuUrl) {
			if ($("#tt").tabs("exists", titleName)) {
				$("#tt").tabs("select", titleName);
			} else {
				$('#tt').tabs('add', {
					title : titleName,
					href : menuUrl,
					closable : true
				});
			}
		}

		$(function() {
			bindContextMenu('#tt');
		});

		//修改密码
		function updatepass() {
			var doctorNo = ${LOGIN_DOCTOR.doctorNo};
			console.log(doctorNo);
			if (doctorNo) {
				$('#win').window('open');
				url = '${pageContext.request.contextPath}/doctor/edit?doctorNo='+doctorNo;
				
			} 
		}
		
		function saveUsers(){
			
			$('#updatePassWord').form("submit",
				{
				url:url,				
				onSubmit : function() {
					return $(this).form("validate");
				},
			success:function(result){
				var result = eval("("+result+")");
				
				$('#win').dialog('close');
				
				if (result.success) {
					$.messager.alert("系统提示", "保存成功！");
					$(window).attr('location','${pageContext.request.contextPath}/login.jsp');
				} else {
					$.messager.alert("系统提示", "保存失败！");
					return;
				}
				
			}
			})
			
		}
		
	</script>

</body>
</html>