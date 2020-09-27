<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>药品供应商管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/color.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.easyui.min.js"></script>
</head>
<body>
	<!-- 在页面中使用框架进行页面设计 -->
	<!-- css样式有三种使用方法：行内样式，内嵌样式，外部样式表 -->
	<!-- HTML布局设计主要有两种方法：table表格布局(tr行，th表头/列，td单元格/列)，  div块布局 (块级元素，行级元素) -->
	<!-- 在pssms中获取http://localhost:8080/JXCMS/user/userList.do的数据，这是跨域（名）请求;
                 系统数据在实际环境中需要避免发生跨域 -->
	<table id="provider_dg" class="easyui-datagrid" title="药品供应商管理"
		style="width: 100%; height: 598px" toolbar="#provider_tb"
		data-options="singleSelect:true,pagination:true,url:'${pageContext.request.contextPath}/sys/provider/list',method:'get'">
		<thead>
			<tr>
				<th field="providerNo" width="80" align="center">供应商编号</th>
				<th field="providerFactory" width="245" align="center">供应商名称</th>
				<th field="providerName" width="80" align="center">法人姓名</th>
				<th field="providerLicenseNo" width="140" align="center">营业许可证编号</th>
				<th field="providerLicenseImg" formatter="imageFormatter"
					width="140" align="center">营业许可证照片</th>
				<th field="providerAddress" width="260" align="center">供应商地址</th>
				<th field="providerLinkName" width="100" align="center">供应商联系人</th>
				<th field="providerPhone" width="120" align="center">供应商电话</th>
				<th field="providerEmail" width="150" align="center">供应商邮箱</th>
			</tr>
		</thead>
	</table>

	<div id="provider_tb">
		<a href="#" class="easyui-linkbutton" onclick="newProvider()"
			data-options="iconCls:'icon-add',plain:true">新增</a> <a href="#"
			class="easyui-linkbutton" onclick="editProvider()"
			data-options="iconCls:'icon-edit',plain:true">修改</a> <a href="#"
			class="easyui-linkbutton" onclick="destroyProvider()"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
		<div>
			&nbsp;供应商名称： <input type="text" id="providerNameText" size="20"
				onkeydown="if(event.keyCode == 13) searchProviderInformation()" /> <a
				href="javascript:searchProviderInformation()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<!-- datagrid- end -->


	<!-- add/edit diaglog start -->
	<div id="provider_dlg" class="easyui-dialog"
		style="width: 400px; height: 380px; padding: 20px 20px" closed="true"
		buttons="#provider_dlg-buttons">
		<div class="ftitle"></div>
		<form id="provider_fm" enctype="multipart/form-data" method="post">
			<div class="fitem">
				<label>供应商名称:</label>
				<div style="float: right; margin-right: 50px">
					<input name="providerFactory" class="easyui-textbox"
						required="true">
				</div>
			</div>
			<br>
			<div class="fitem">
				<label>法人姓名:</label>
				<div style="float: right; margin-right: 50px">
					<input name="providerName" class="easyui-textbox" required="true">
				</div>
			</div>
			<br>
			<div class="fitem">
				<label>营业许可证编号:</label>
				<div style="float: right; margin-right: 50px">
					<input name="providerLicenseNo" class="easyui-textbox"
						required="true">
				</div>
			</div>
			<br>
			<div class="fitem">
				<label>供应商地址:</label>
				<div style="float: right; margin-right: 50px">
					<input name="providerAddress" class="easyui-textbox">
				</div>
			</div>
			<br>
			<div class="fitem">
				<label>供应商联系人:</label>
				<div style="float: right; margin-right: 50px">
					<input name="providerLinkName" class="easyui-textbox">
				</div>
			</div>
			<br>
			<div class="fitem">
				<label>供应商电话:</label>
				<div style="float: right; margin-right: 50px">
					<input name="providerPhone" class="easyui-textbox">
				</div>
			</div>
			<br>
			<div class="fitem" validType="email">
				<label>供应商邮箱:</label>
				<div style="float: right; margin-right: 50px">
					<input name="providerEmail" class="easyui-textbox">
				</div>
			</div>
			<br>
			<div class="fitem">
				<label>营业许可证照片:</label>
				<div style="float: right; margin-right: 50px">
					<input name="imageFile" class="easyui-filebox" />
				</div>
			</div>
		</form>
	</div>
	<center>
		<div id="provider_dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveProvider()">保存</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-cancel"
				onclick="javascript:$('#provider_dlg').dialog('close')">取消</a>
		</div>
	</center>
	<!-- add/edit diaglog end -->


	<script type="text/javascript">
		function imageFormatter(value, row, index) {
			if (value && value != null) {
				return "<img width='120' src='${pageContext.request.contextPath}/"+value+"'/>";
			}
		}

		//点击新增按钮使调用，新增对话框
		function newProvider() {
			$('#provider_dlg').dialog('open').dialog('setTitle', '新增供应商信息');
			$('#provider_fm').form('clear');
			url = '${pageContext.request.contextPath}/sys/provider/add';
		}

		//修改用户
		function editProvider() {
			var row = $('#provider_dg').datagrid('getSelected');
			if (row) {
				$('#provider_dlg').dialog('open').dialog('setTitle', '编辑供应商');
				$('#provider_fm').form('load', row);
				url = '${pageContext.request.contextPath}/sys/provider/edit?providerNo=' + row.providerNo;
			} else {
				$.messager.show({
					title : 'Info',
					msg : "请选择一条数据！"
				});
			}
		}

		//保存
		function saveProvider() {
			$('#provider_fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					$('#provider_dlg').dialog('close'); // close the dialog
					$('#provider_dg').datagrid('reload'); // reload the user data
					if (result.errorMsg) {
						$.messager.show({
							title : 'Error',
							msg : result.errorMsg
						});
					}
				}
			});
		}
		//删除按钮动作
		function destroyProvider() {
			var row = $('#provider_dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '您确认要删除该供应商信息吗?', function(r) {
					if (r) {
						$.post('${pageContext.request.contextPath}/sys/provider/delete', {
							providerNo : row.providerNo
						}, function(result) {
							if (result.success) {
								$('#provider_dg').datagrid('reload'); // reload the user data
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
		
		//搜索按钮
		function searchProviderInformation() {
			$("#provider_dg").datagrid('load', {
				"providerFactory" : $("#providerNameText").val()
			});
		}
	</script>
</body>
</html>