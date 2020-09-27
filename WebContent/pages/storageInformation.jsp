<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>库存信息管理</title>
<link rel="stylesheet" type="text/css"
	href="../js/jquery-easyui-1.9.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../js/jquery-easyui-1.9.5/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="../js/jquery-easyui-1.9.5/themes/color.css">
<script type="text/javascript"
	src="../js/jquery-easyui-1.9.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../js/jquery-easyui-1.9.5/jquery.easyui.min.js"></script>
</head>
<body>
	<!-- 在页面中使用框架进行页面设计 -->
	<!-- css样式有三种使用方法：行内样式，内嵌样式，外部样式表 -->
	<!-- HTML布局设计主要有两种方法：table表格布局(tr行，th表头/列，td单元格/列)，  div块布局 (块级元素，行级元素) -->
	<!-- 在pssms中获取http://localhost:8080/JXCMS/user/userList.do的数据，这是跨域（名）请求;
                 系统数据在实际环境中需要避免发生跨域 -->
	<table id="storage_info_dg" class="easyui-datagrid" title="库存信息管理"
		style="width: 100%; height: 500px" toolbar="#storage_info_tb"
		data-options="singleSelect:true,pagination:true,url:'http://localhost:8080//DurgShopSystem/storage/list',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'storageNo',width:80,align:'center'">库存编号</th>
				<th data-options="field:'drugNo',width:80,align:'center'">药品编号</th>
				<th formatter="drugNameFormatter"data-options="field:'drugName',width:200,align:'center'">
					药品名称</th>
				<th formatter="formatPrice" data-options="field:'storageNumber',width:100,align:'center'">
					库存总量</th>
			</tr>
		</thead>
	</table>

	<div id="storage_info_tb">
		<a href="#" class="easyui-linkbutton" onclick="newStorage_info()"
			data-options="iconCls:'icon-add',plain:true">新增</a> <a href="#"
			class="easyui-linkbutton" onclick="destroyStorage_info()"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
		<div>
			&nbsp;请输入药品编号： <input type="text" id="storage_info_NameText" size="20"
				onkeydown="if(event.keyCode == 13) searchDrugNameInfo()" /> <a
				href="javascript:searchDrugNameInfo()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>


	<!-- datagrid- end -->


	<!-- add/edit diaglog start -->
	<div id="storage_info_dlg" class="easyui-dialog"
		style="width: 300px; height: 440px; padding: 10px 20px" closed="true"
		buttons="#storage_info_dlg-buttons">
		<div class="ftitle"></div>
		<form id="doctor_fm" method="post">
			<br>
			<div class="fitem">
				<label>药品编号:</label> <input
					name="drugNo" class="easyui-textdbox" required="true">
			</div>
			<br>

			<div class="fitem">
				<label>库存总量:</label> <input name="storageNumber" class="easyui-textbox">
			</div>
		</form>
	</div>
	<center>
		<div id="storage_info_dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveStorage_info()">保存</a> <a href="#" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#storage_info_dlg').dialog('close')">取消</a>
		</div>
	</center>
	<!-- add/edit diaglog end -->

	<script type="text/javascript">

		function formatPrice(val,row){
	        if (val < 100){
	            return '<span style="color:red;">('+val+')</span>';
	        } else {
	            return val;
	        }
	    }
		
		function drugNameFormatter(value,row,index){
			return row.drug.drugName;
		}
	
		//搜索按钮
		function searchDrugNameInfo() {
			$("#storage_info_dg").datagrid('load', {
				"drugNo" : $("#storage_info_NameText").val()
			});
		}

		//点击新增按钮使调用，新增对话框
		function newStorage_info() {
			$('#storage_info_dlg').dialog('open').dialog('setTitle', '库存数量信息');
			$('#doctor_fm').form('clear');
			url = '../storage/add';
		}
		

		//保存按钮
		function saveStorage_info() {
			$("#doctor_fm").form("submit", {
				url : url,
				onSubmit : function() {
					return $(this).form("validate");
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					$('#storage_info_dlg').dialog('close');
					$('#storage_info_dg').datagrid('reload');
					if (result.success) {
						$.messager.alert("系统提示", "新增成功！");
						resetValue();
					} else {
						$.messager.alert("系统提示", "新增失败,该药品已存在请直接搜索！");
						return;
					}
				}
			});
		}

		//删除按钮动作
		function destroyStorage_info() {
			var row = $('#storage_info_dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '您确认要删除该药品库存信息吗?', function(r) {
					if (r) {
						$.post('../storage/delete', {
							storageNo : row.storageNo
						}, function(result) {
							if (result.success) {
								$.messager.alert("系统提示", "删除成功！");
								$('#storage_info_dg').datagrid('reload'); // reload the user data
							} else {
								$.messager.show({ // show error message
									title : 'Error',
									msg : result.errorMsg
								});
							}
						}, 'json');
					}
				});
			} else {
				$.messager.show({
					title : 'Info',
					msg : "请选择要删除的数据!"
				});
			}
		}
	</script>
</body>
</html>