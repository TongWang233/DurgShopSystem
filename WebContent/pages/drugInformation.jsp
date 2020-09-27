<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>药品信息管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/color.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.easyui.min.js"></script>
</head>
<body>
      <table id="drug_dg" class="easyui-datagrid" title="药品信息管理" style="width:100%;height:598px"
            toolbar="#drug_tb" 
			data-options="singleSelect:true,pagination:true,url:'${pageContext.request.contextPath}/sys/drug/list',method:'get'">
		<thead>
			<tr>
				<th field="drugNo" width="100" align="center">药品编号</th>
				<th field="drugName" width="180" align="center">药品名称</th>
				<th field="typeNo" width="280" align="center" hidden="true">药品类型编号 </th>
				<th field="type" formatter="typeFormatter" width="120" align="center" >药品类型名称</th>
				<th field="providerNo" width="140" align="center" hidden="true">药品供应商编号</th>
				<th field="provider" formatter="providerFormatter" width="240" align="center" >药品供应商名称</th>
				<th field="drugImage" formatter="imageFormatter" width="180" align="center">药品图片</th>
				<th field="drugSpecification" width="120" align="center">药品规格</th>
				<th field="drugValidity" width="120" align="center">药品有效期</th>
				<th field="drugRemark" width="160" align="center">批准文号</th>
			</tr>
		</thead>
	</table>
	
	<div id="drug_tb">
	  <a href="#" class="easyui-linkbutton" onclick="newDrug()" data-options="iconCls:'icon-add',plain:true">新增</a>
	  <a href="#" class="easyui-linkbutton" onclick="editDrug()" data-options="iconCls:'icon-edit',plain:true">修改</a>
	  <a href="#" class="easyui-linkbutton" onclick="destroyDrug()" data-options="iconCls:'icon-remove',plain:true">删除</a>
		<div>
			&nbsp;药品名称： <input type="text" id="drugNameText" size="20"
				onkeydown="if(event.keyCode == 13) searchDrugInformation()" /> <a
				href="javascript:searchDrugInformation()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<!-- datagrid- end -->
	
	
	<!-- add/edit diaglog start -->
	<div id="drug_dlg" class="easyui-dialog" style="width:400px;height:340px;padding:20px 20px"
		closed="true" buttons="#drug_dlg-buttons">
	<div class="ftitle"></div>
	<form id="drug_fm" enctype="multipart/form-data" method="post">
		<div class="fitem">
			<label>药品名称:</label>
			<div style="float:right;margin-right:50px">
				<input name="drugName" class="easyui-textbox" required="true">
			</div>
		</div><br>
		<div class="fitem">
			<label>药品类型:</label>
			<div style="float:right;margin-right:50px">
				<input name="type.typeNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'typeNo',textField:'typeName',
						url:'${pageContext.request.contextPath}/sys/drug/typelist'"  required="true">
			</div>
		</div>
		<br>
		<div class="fitem">
			<label>供应商名称:</label>
			<div style="float:right;margin-right:50px">
				<input name="provider.providerNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'providerNo',textField:'providerFactory',
						url:'${pageContext.request.contextPath}/sys/drug/providerlist'" >
			</div>
		</div><br>
		<div class="fitem">
			<label>药品图片:</label>
			<div style="float:right;margin-right:50px">
				<input name="imageFile" class="easyui-filebox" />
			</div>
		</div><br>
		<div class="fitem">
			<label>药品规格:</label>
			<div style="float:right;margin-right:50px">
				<input name="drugSpecification" class="easyui-textbox">
			</div>
		</div><br>
		<div class="fitem">
			<label>药品有效期:</label>
			<div style="float:right;margin-right:50px">
				<input name="drugValidity" class="easyui-textbox">
			</div>
		</div><br>
		<div class="fitem">
			<label>药品备注:</label>
			<div style="float:right;margin-right:50px">
				<input name="drugRemark" class="easyui-textbox">
			</div>
		</div>
	</form>
</div>
<center><div id="drug_dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveDrug()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#drug_dlg').dialog('close')">取消</a>
	</div></center>
<!-- add/edit diaglog end -->


<script type="text/javascript">

	function imageFormatter(value, row, index){    
		if(value && value!=null){       
			return "<img width='120' src='${pageContext.request.contextPath}/"+value+"'/>";    
		} 
	}
	
	
	function providerFormatter(value,row,index){
		return value.providerFactory;
	}
	
	function typeFormatter(value,row,index){
		return value.typeName;
	}


    //新增
	function newDrug(){
		$('#drug_dlg').dialog('open').dialog('setTitle','新增药品信息');
		$('#drug_fm').form('clear');
		url = '${pageContext.request.contextPath}/sys/drug/add';
	}
    
    //修改用户
	function editDrug(){
		var row = $('#drug_dg').datagrid('getSelected');
		if (row){
			$('#drug_dlg').dialog('open').dialog('setTitle','编辑药品信息');
			$('#drug_fm').form('load',row);
			url = '${pageContext.request.contextPath}/sys/drug/edit?drugNo='+row.drugNo;
		}else{
			$.messager.show({
				title: 'Info',
				msg: "请选择一条数据！"
			});
		}
	}
	
	//保存
	function saveDrug(){
	$('#drug_fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			$('#drug_dlg').dialog('close');		// close the dialog
			$('#drug_dg').datagrid('reload');	// reload the user data
			if (result.errorMsg){
				$.messager.show({
					title: 'Error',
					msg: result.errorMsg
				});
			} 
		}
	});
}
	//删除
	function destroyDrug(){
	var row = $('#drug_dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('确认','您确认要删除该药品信息吗?',function(r){
			if (r){
				$.post('${pageContext.request.contextPath}/sys/drug/delete',{drugNo:row.drugNo},function(result){
					if (result.success){
						$('#drug_dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.errorMsg
						});
					}
				},'json');
			}
		});
	}else{
		$.messager.show({
			title: 'Info',
			msg:"请选择要删除的数据!"
		});
	}
}
	
	//搜索按钮
	function searchDrugInformation() {
		$("#drug_dg").datagrid('load', {
			"drugName" : $("#drugNameText").val()
		});
	}
</script>


</body>
</html>