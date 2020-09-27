<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>药品出售明细信息管理</title>
    <link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.9.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.9.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.9.5/themes/color.css">
	<script type="text/javascript" src="../js/jquery-easyui-1.9.5/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery-easyui-1.9.5/jquery.easyui.min.js"></script>
</head>
<body>
<!-- 在页面中使用框架进行页面设计 -->
      <!-- css样式有三种使用方法：行内样式，内嵌样式，外部样式表 -->
      <!-- HTML布局设计主要有两种方法：table表格布局(tr行，th表头/列，td单元格/列)，  div块布局 (块级元素，行级元素) -->
      <!-- 在pssms中获取http://localhost:8080/JXCMS/user/userList.do的数据，这是跨域（名）请求;
                 系统数据在实际环境中需要避免发生跨域 -->
      <table id="saleDetail_dg" class="easyui-datagrid" title="药品出售明细信息管理" style="width:100%;height:500px"
            toolbar="#saleDetail_tb" 
			data-options="singleSelect:true,pagination:true,url:'http://localhost:8080/DurgShopSystem/sys/saledetail/list',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'saleDetailNo',width:100,align:'center'">   出售明细编号  </th>
				<th data-options="field:'saleNo',width:100,align:'center'">   出售编号  </th>
				<th formatter="saleNameFormatter" data-options="field:'saleName',width:100,align:'center'">   出售单号名称  </th>
				<th data-options="field:'drugNo',width:100,align:'center'">药品编号    </th>
				<th formatter="drugNameFormatter" data-options="field:'drugName',width:100,align:'center'">药品名称    </th>
				<th data-options="field:'saleQuantity',width:100,align:'center'">   出售数量</th>
				<th data-options="field:'price',width:100,align:'center'">    价格</th>	
			</tr>
		</thead>
	</table>
	
	<div id="saleDetail_tb">
	  <a href="#" class="easyui-linkbutton" onclick="newSaleDetail()" data-options="iconCls:'icon-add',plain:true">新增</a>
	  <a href="#" class="easyui-linkbutton" onclick="editSaleDetail()" data-options="iconCls:'icon-edit',plain:true">修改</a>
	  <a href="#" class="easyui-linkbutton" onclick="destroySaleDetail()" data-options="iconCls:'icon-remove',plain:true">删除</a>
		<div>
			&nbsp;出售编号： <input type="text" id="saleNoText2" size="20"
				onkeydown="if(event.keyCode == 13) searchsaleDetailInformation()" /> <a
				href="javascript:searchsaleDetailInformation()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<!-- datagrid- end -->
	
	
	<!-- add/edit diaglog start -->
	<div id="saleDetail_dlg" class="easyui-dialog" style="width:350px;height:270px;padding:20px 20px"
		closed="true" buttons="#saleDetail_dlg-buttons">
	<div class="ftitle"></div>
	<form id="saleDetail_fm" method="post">
		<div class="fitem">
			<label>销售单名:</label>
			<div style="float:right;margin-right:50px">
				<input id="sale_list" name="saleNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'saleNo',textField:'saleName',
						url:'../sys/saledetail/salelist'"/  required="true">
			</div>
		</div>
		<br>
		<div class="fitem">
			<label>药品名称：</label>
			<div style="float:right;margin-right:50px">
				<input id="drug_list" name="drugNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'drugNo',textField:'drugName',
						url:'../sys/saledetail/druglist'"/  required="true">
			</div>
		</div>
		<br>
		<div class="fitem">
			<label>出售数量:</label>
			<div style="float:right;margin-right:50px">
				<input name="saleQuantity" class="easyui-textbox" >
			</div>
		</div>
		<br>
		<div class="fitem">
			<label>价格:</label>
			<div style="float:right;margin-right:50px">
				<input name="price" class="easyui-textbox" />
			</div>
		</div>
	</form>
</div>
<center><div id="saleDetail_dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSaleDetail()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#saleDetail_dlg').dialog('close')">取消</a>
	</div></center>
<!-- add/edit diaglog end -->


<script type="text/javascript">

function saleNameFormatter(value,row,index){
	return row.sale.saleName;
}

function drugNameFormatter(value,row,index){
	return row.drug.drugName;
}

//搜索按钮
function searchsaleDetailInformation() {
	$("#saleDetail_dg").datagrid('load', {
		"saleNo" : $("#saleNoText2").val()
	});
}
    //点击新增按钮使调用，新增对话框
function newSaleDetail(){
	$('#saleDetail_dlg').dialog('open').dialog('setTitle','新增药品出售明细信息');
	$('#saleDetail_fm').form('clear');
	url = '../sys/saledetail/add';
}
    //修改用户
function editSaleDetail(){
		var row = $('#saleDetail_dg').datagrid('getSelected');
		if (row){
			$('#saleDetail_dlg').dialog('open').dialog('setTitle','编辑药品出售明细信息');
			$('#saleDetail_fm').form('load',row);
			url = '../sys/saledetail/edit?saleDetailNo='+row.saleDetailNo;
		}else{
			$.messager.show({
				title: 'Info',
				msg: "请选择一条数据！"
			});
		}
	}
	
	//保存
	function saveSaleDetail(){
	$('#saleDetail_fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			//完善用户操作体验，关闭新增对话框，刷新列表数据，提示操作信息
		$('#saleDetail_dlg').dialog('close');		// close the dialog
		$('#saleDetail_dg').datagrid('reload');	// reload the user data
		$.messager.show({
			title: 'Info',
			msg: result.errorMsg
		});
		}
	});
}
	//删除按钮动作
	function destroySaleDetail(){
	var row = $('#saleDetail_dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('确认','您确认要删除该药品出售明细信息吗?',function(r){
			if (r){
				$.post('../sys/saledetail/delete',{saleDetailNo:row.saleDetailNo},function(result){
					if (result.success){
						$('#saleDetail_dg').datagrid('reload');	// reload the user data
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
</script>


</body>
</html>