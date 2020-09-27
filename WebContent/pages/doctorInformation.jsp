<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>医生信息管理</title>
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
	<table id="doctor_dg" class="easyui-datagrid" title="医生信息管理"
		style="width: 100%; height: 500px" toolbar="#doctor_tb"
		data-options="singleSelect:true,pagination:true,url:'http://localhost:8080//DurgShopSystem/sys/doctor/list',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'doctorNo',width:80,align:'center'">工号</th>
				<th data-options="field:'doctorName',width:100,align:'center'">
					医生姓名</th>
				<th data-options="field:'doctorSex',width:100,align:'center'">
					医生性别</th>
				<th data-options="field:'doctorAge',width:100,align:'center'">
					医生年龄</th>
				<th data-options="field:'doctorEntryTime',width:110,align:'center'">
					入职时间</th>
				<th data-options="field:'doctorPhone',width:150,align:'center'">
					医生电话</th>
				<th data-options="field:'doctorEmail',width:150,align:'center'">
					医生邮件</th>
			</tr>
		</thead>
	</table>

	<div id="doctor_tb">
		<a href="#" class="easyui-linkbutton" onclick="newDoctor()"
			data-options="iconCls:'icon-add',plain:true">新增</a> <a href="#"
			class="easyui-linkbutton" onclick="editDoctor()"
			data-options="iconCls:'icon-edit',plain:true">修改</a> <a href="#"
			class="easyui-linkbutton" onclick="destroyDoctor()"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
		<div>
			&nbsp;医生姓名： <input type="text" id="doctorNameText" size="20"
				onkeydown="if(event.keyCode == 13) searchDoctorInformation()" /> <a
				href="javascript:searchDoctorInformation()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>


	<!-- datagrid- end -->


	<!-- add/edit diaglog start -->
	<div id="doctor_dlg" class="easyui-dialog"
		style="width: 300px; height: 400px; padding: 10px 20px" closed="true"
		buttons="#doctor_dlg-buttons">
		<div class="ftitle"></div>
		<form id="doctor_fm" method="post">
			<div class="fitem">
				<label>医生姓名:</label> <input name="doctorName" class="easyui-textbox"
					required="true">
			</div>
			<br>
			<div class="fitem">
				<label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label> <input
					name="password" class="easyui-passwordbox" required="true">
			</div>
			<br>

			<div class="fitem">
				<label>医生性别:</label> <select name="doctorSex" id=""
					class="easyui-combobox" style="width: 160px;"
					data-options="editable:false,panelHeight:'auto'">
					<option>男</option>
					<option>女</option>
				</select>
			</div>
			<br>

			<div class="fitem">
				<label>医生年龄:</label> <input name="doctorAge" class="easyui-textbox">
			</div>
			<br>
			<div class="fitem">
				<label>入职时间:</label> <input name="doctorEntryTime"
					class="easyui-datebox">
			</div>
			<br>

			<div class="fitem">
				<label>医生电话:</label> <input name="doctorPhone"
					class="easyui-textbox">
			</div>
			<br>
			<div class="fitem" validType="email">
				<label>医生邮件:</label> <input name="doctorEmail"
					class="easyui-textbox">
			</div>
		</form>
	</div>
	<center>
		<div id="doctor_dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveDoctor()">保存</a> <a href="#" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#doctor_dlg').dialog('close')">取消</a>
		</div>
	</center>
	<!-- add/edit diaglog end -->

	<script type="text/javascript">
	
		 //时间格式化器
		$.fn.datebox.defaults.formatter = function(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'/'+m+'/'+d;
		}
			
		//时间转换器
		$.fn.datebox.defaults.parser = function(s){
			var t = Date.parse(s);
			if (!isNaN(t)){
				return new Date(t);
			} else {
				return new Date();
			}
		}
		
	
	
	
		//搜索按钮
		function searchDoctorInformation() {
			$("#doctor_dg").datagrid('load', {
				"doctorName" : $("#doctorNameText").val()
			});
		}

		//点击新增按钮使调用，新增对话框
		function newDoctor() {
			$('#doctor_dlg').dialog('open').dialog('setTitle', '新增医生信息');
			$('#doctor_fm').form('clear');
			url = '${pageContext.request.contextPath}/sys/doctor/add';
		}
		//修改用户
		function editDoctor() {
			var row = $('#doctor_dg').datagrid('getSelected');
			if (row) {
				$('#doctor_dlg').dialog('open').dialog('setTitle', '编辑医生信息');
				$('#doctor_fm').form('load', row);
				url = '${pageContext.request.contextPath}/sys/doctor/update?doctorNo=' + row.doctorNo;
			} else {
				$.messager.show({
					title : 'Info',
					msg : "请选择一条数据！"
				});
			}
		}

		//保存按钮
		function saveDoctor() {
			$("#doctor_fm").form("submit", {
				url : url,
				onSubmit : function() {
					return $(this).form("validate");
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					$('#doctor_dlg').dialog('close');
					$('#doctor_dg').datagrid('reload');
					if (result.success) {
						$.messager.alert("系统提示", "保存成功！");
						resetValue();
					} else {
						$.messager.alert("系统提示", "保存失败！");
						return;
					}
				}
			});
		}

		//删除按钮动作
		function destroyDoctor() {
			var row = $('#doctor_dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '您确认要删除该用户吗?', function(r) {
					if (r) {
						$.post('${pageContext.request.contextPath}/sys/doctor/delete', {
							doctorNo : row.doctorNo
						}, function(result) {
							if (result.success) {
								$.messager.alert("系统提示", "删除成功！");
								$('#doctor_dg').datagrid('reload'); // reload the user data
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