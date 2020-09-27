<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>患者信息管理</title>
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
	<table id="patient_dg" class="easyui-datagrid" title="患者信息管理"
		style="width: 100%; height: 500px" toolbar="#patient_tb"
		data-options="singleSelect:true,pagination:true,url:'http://localhost:8080//DurgShopSystem/sys/patient/list',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'patientNo',width:80,align:'center'">
					患者编号</th>
				<th data-options="field:'patientName',width:80,align:'center'"> 
					患者姓名</th>
				<th data-options="field:'patientSex',width:80,align:'center'">
					患者性别</th>
				<th data-options="field:'patientAge',width:100,align:'center'">
					患者年龄</th>
				<th data-options="field:'patientPhone',width:140,align:'center'">
					患者电话</th>
				<th data-options="field:'patientEmail',width:180,align:'center'">
					患者邮件</th>
				<th data-options="field:'patientAddress',width:300,align:'center'">
					患者住址</th>
				<th
					data-options="field:'patientAllergicDrugs',width:180,align:'center'">
					过敏药品</th>
			</tr>
		</thead>
	</table>

	<div id="patient_tb">
		<a href="#" class="easyui-linkbutton" onclick="newPatient()"
			data-options="iconCls:'icon-add',plain:true">新增</a> <a href="#"
			class="easyui-linkbutton" onclick="editPatient()"
			data-options="iconCls:'icon-edit',plain:true">修改</a> <a href="#"
			class="easyui-linkbutton" onclick="destroyPatient()"
			data-options="iconCls:'icon-remove',plain:true">删除</a>

		<div>
			&nbsp;患者姓名： <input type="text" id="patientNameText" size="20"
				onkeydown="if(event.keyCode == 13) searchPatientInformation()" /> <a
				href="javascript:searchPatientInformation()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<!-- datagrid- end -->


	<!-- add/edit diaglog start -->
	<div id="patient_dlg" class="easyui-dialog"
		style="width: 300px; height: 460px; padding: 10px 20px" closed="true"
		buttons="#patient_dlg-buttons">
		<div class="ftitle"></div>
		<form id="patient_fm" method="post">

			<div class="fitem">
				<label>患者姓名:</label> <input name="patientName"
					class="easyui-textbox"  required="true">
			</div>
			<br>
			<div class="fitem">
				<label>患者性别:</label> 
				<select name="patientSex" id="patientSex" class="easyui-combobox" style="width:160px;" data-options="editable:false,panelHeight:'auto'">
					<option>男</option>
					<option>女</option>
				</select>
			</div>
			<br>
			<div class="fitem">
				<label>患者年龄:</label> <input name="patientAge" class="easyui-textbox">
			</div>
			<br>
			<div class="fitem">
				<label>患者电话:</label> <input name="patientPhone" class="easyui-textbox">
			</div>
			<br>
			<div class="fitem" validType="email">
				<label>患者邮件:</label> <input name="patientEmail" class="easyui-textbox">
			</div>
			<br>
			<div class="fitem">
				<label>患者住址:</label> <input name="patientAddress" class="easyui-textbox">
			</div>
			<br>
			<div class="fitem">
				<label>过敏药品:</label> <textarea type="text" id="patientAllergicDrugs" 
					name="patientAllergicDrugs" rows="5" cols="20"></textarea>
			</div>
			
		</form>
	</div>
	<center>
		<div id="patient_dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="savePatient()">保存</a> <a href="#" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#patient_dlg').dialog('close')">取消</a>
		</div>
	</center>
	<!-- add/edit diaglog end -->


	<script type="text/javascript">
		//搜索按钮
		function searchPatientInformation() {
			$("#patient_dg").datagrid('load', {
				"patientName" : $("#patientNameText").val()
			});
		}
		//点击新增按钮使调用，新增对话框
		function newPatient() {
			$('#patient_dlg').dialog('open').dialog('setTitle', '新增患者信息');
			$('#patient_fm').form('clear');
			url = '${pageContext.request.contextPath}/sys/patient/add';
		}
		//修改用户
		function editPatient() {
			var row = $('#patient_dg').datagrid('getSelected');
			if (row) {
				$('#patient_dlg').dialog('open').dialog('setTitle', '编辑患者信息');
				$('#patient_fm').form('load', row);
				url = '${pageContext.request.contextPath}/sys/patient/edit?patientNo=' + row.patientNo;
			} else {
				$.messager.show({
					title : 'Info',
					msg : "请选择一条数据！"
				});
			}
		}

		//保存按钮
		function savePatient() {
			$("#patient_fm").form("submit", {
				url : url,
				onSubmit : function() {
					return $(this).form("validate");
				},
				success : function(result) {
					var result = eval("(" + result + ")");
					$("#patient_dlg").dialog("close");
					$("#patient_dg").datagrid("reload");

					if (result.success) {
						$.messager.alert("系统提示", "保存成功！");
						resetValue();

					} else {
						$.messager.alert("系统提示", "保存失败！");
						return;
					}
					$('#patient_fm').form('load', row);
				}
			});
		}

		//删除按钮动作
		function destroyPatient() {
			var row = $('#patient_dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '您确认要删除该患者信息吗?', function(r) {
					if (r) {
						$.post('${pageContext.request.contextPath}/sys/patient/delete', {
							patientNo : row.patientNo
						}, function(result) {
							if (result.success) {
								$.messager.alert("系统提示", "删除成功！");
								$('#patient_dg').datagrid('reload'); // reload the user data
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