<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>药品类型信息管理</title>
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
	<table id="type_dg" class="easyui-datagrid" title="药品类型信息管理"
		style="width: 100%; height: 500px" toolbar="#type_tb"
		data-options="singleSelect:true,pagination:true,url:'${pageContext.request.contextPath}/sys/type/list',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'typeNo',width:200,align:'center'">
					药品类型编号</th>
				<th data-options="field:'typeName',width:200,align:'center'">
					药品类型名称</th>
				<!-- <th data-options="field:'superiorNo',width:200,align:'center'">
					上级分类编号</th> -->
			</tr>
		</thead>
	</table>

	<div id="type_tb">
		<a href="#" class="easyui-linkbutton" onclick="newType()"
			data-options="iconCls:'icon-add',plain:true">新增</a> <a href="#"
			class="easyui-linkbutton" onclick="editType()"
			data-options="iconCls:'icon-edit',plain:true">修改</a> <a href="#"
			class="easyui-linkbutton" onclick="destroyType()"
			data-options="iconCls:'icon-remove',plain:true">删除</a>
		<div>
			&nbsp;药品类型名称： <input type="text" id="typeNameText" size="20"
				onkeydown="if(event.keyCode == 13) searchDrugTypeInformation()" />
			<a href="javascript:searchDrugTypeInformation()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>

	</div>
	<!-- datagrid- end -->


	<!-- add/edit diaglog start -->
	<div id="type_dlg" class="easyui-dialog"
		style="width: 370px; height: 200px; padding: 30px 20px" closed="true"
		buttons="#type_dlg-buttons">
		<div class="ftitle"></div>
		<form id="doctor_fm" method="post">
			<div class="fitem">
				<label>药品类型名称:</label> <input name="typeName" class="easyui-textbox"  required="true">
			</div>
			<!-- <div class="fitem">
				<label>分类上级编号:</label> <input name="superiorNo"
					class="easyui-textbox">
			</div> -->
		</form>
	</div>
	<center>
		<div id="type_dlg-buttons">
			<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
				onclick="saveType()">保存</a> <a href="#" class="easyui-linkbutton"
				iconCls="icon-cancel"
				onclick="javascript:$('#type_dlg').dialog('close')">取消</a>
		</div>
	</center>
	<!-- add/edit diaglog end -->


	<script type="text/javascript">
		//点击新增按钮使调用，新增对话框
		function newType() {
			$('#type_dlg').dialog('open').dialog('setTitle', '新增药品类型信息');
			$('#type_fm').form('clear');
			url = '${pageContext.request.contextPath}/sys/type/add';
		}
		//修改用户
		function editType() {
			var row = $('#type_dg').datagrid('getSelected');
			if (row) {
				$('#type_dlg').dialog('open').dialog('setTitle', '编辑药品类型信息');
				$('#doctor_fm').form('load', row);
				url = '${pageContext.request.contextPath}/sys/type/edit?typeNo=' + row.typeNo;
			} else {
				$.messager.show({
					title : 'Info',
					msg : "请选择一条数据！"
				});
			}
		}

		//保存
		function saveType() {
			$('#doctor_fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					$('#type_dlg').dialog('close'); // close the dialog
					$('#type_dg').datagrid('reload'); // reload the user data
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
		function destroyType() {
			var row = $('#type_dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '您确认要删除该药品类型信息吗?', function(r) {
					if (r) {
						$.post('${pageContext.request.contextPath}/sys/type/delete', {
							typeNo : row.typeNo
						}, function(result) {
							if (result.success) {
								$('#type_dg').datagrid('reload'); // reload the user data
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
		function searchDrugTypeInformation() {
			$("#type_dg").datagrid('load', {
				"typeName" : $("#typeNameText").val()
			});
		}
	</script>


</body>
</html>