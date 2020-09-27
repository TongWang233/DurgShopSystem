<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>药品采购明细管理</title>
    <link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.9.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.9.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.9.5/themes/color.css">
	<script type="text/javascript" src="../js/jquery-easyui-1.9.5/jquery.min.js"></script>
	<script type="text/javascript" src="../js/jquery-easyui-1.9.5/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.9.5/themes/demo.css">

</head>
<body>
<!-- 在页面中使用框架进行页面设计 -->
      <!-- css样式有三种使用方法：行内样式，内嵌样式，外部样式表 -->
      <!-- HTML布局设计主要有两种方法：table表格布局(tr行，th表头/列，td单元格/列)，  div块布局 (块级元素，行级元素) -->
      <!-- 在pssms中获取http://localhost:8080/JXCMS/user/userList.do的数据，这是跨域（名）请求;
                 系统数据在实际环境中需要避免发生跨域 -->
      <table id="purchasingDetail_dg" class="easyui-datagrid" title="药品采购明细管理" style="width:100%;height:500px"
            toolbar="#purchasingDetail_tb" 
			data-options="singleSelect:true,pagination:true,url:'http://localhost:8080/DurgShopSystem/sys/purchasingdetail/list',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'purchasingDetailNo',width:110,align:'center'">   采购明细编号</th>
				<th data-options="field:'purchasingNo',width:110,align:'center'">   采购编号</th>
				<th formatter=" purchasingNameFormatter" data-options="field:'purchasingName',width:100,align:'center'">  采购单名 </th>
				<th data-options="field:'drugNo',width:110,align:'center'">   药品编号</th>
				<th formatter=" drugNameFormatter" data-options="field:'drugName',width:110,align:'center'">   药品名称</th>
				<th data-options="field:'purchasingDetailQuantity',width:100,align:'center'">   采购数量</th>
				<th data-options="field:'purchasingDetailPrice',width:100,align:'center'">   采购价格</th>
				<th data-options="field:'productDate',width:200,align:'center'"> 生产日期</th>
				
			</tr>
		</thead>
	</table>
	
	<div id="purchasingDetail_tb">
	  <a href="#" class="easyui-linkbutton" onclick="newPurchasingDtail()" data-options="iconCls:'icon-add',plain:true">新增</a>
	  <a href="#" class="easyui-linkbutton" onclick="editPurchasingDtail()" data-options="iconCls:'icon-edit',plain:true">修改</a>
	  <a href="#" class="easyui-linkbutton" onclick="destroyPurchasingDtail()" data-options="iconCls:'icon-remove',plain:true">删除</a>
		<div>
			&nbsp;采购编号： <input type="text" id="purchasingNoText" size="20"
				onkeydown="if(event.keyCode == 13) searchPurchasingInformation()" /> <a
				href="javascript:searchPurchasingInformation()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	
	
	</div>
	
	<!-- datagrid- end -->
	
	
	<!-- add/edit diaglog start -->
	<div id="purchasingDetail_dlg" class="easyui-dialog" style="width:300px;height:350px;padding:20px 20px"
		closed="true" buttons="#purchasingDetail_dlg-buttons">
	<div class="ftitle"></div>
	<form id="purchasingDetail_fm" method="post">
		<div class="fitem">
			<label>采购单名:</label>
			<input id="purchasing_list" name="purchasingNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'purchasingNo',textField:'purchasingName',
						url:'../sys/purchasingdetail/purchasinglist'"/  required="true">
		</div>
		<br>
		<div class="fitem">
			<label>药品名称:</label>
			<input id="drug_list" name="drugNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'drugNo',textField:'drugName',
						url:'../sys/saledetail/druglist'"/  required="true">
		</div>
		<br>
		<div class="fitem">
			<label>采购数量:</label>
			<input name="purchasingDetailQuantity" class="easyui-textbox"  required="true">
		</div>
		<br>
		<div class="fitem">
			<label>采购总价:</label>
			<input name="purchasingDetailPrice" class="easyui-textbox"  required="true">
		</div>
		<br>
		<div class="fitem">
			<label>生产日期:</label>
			<input class="easyui-datetimebox" id="productDate" name="productDate" data-options="showSeconds:true"/  required="true">
		</div>
		
</body>
	
		
		
	</form>
</div>
<center><div id="purchasingDetail_dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePurchasingDtail()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#purchasingDetail_dlg').dialog('close')">取消</a>
	</div></center>
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

    //点击新增按钮使调用，新增对话框
function drugNameFormatter(value,row,index){
	return row.drug.drugName;
}
function purchasingNameFormatter(value,row,index){
	return row.purchasing.purchasingName;
}
    
//搜索按钮
function searchPurchasingInformation() {
	$("#purchasingDetail_dg").datagrid('load', {
		"purchasingNo" : $("#purchasingNoText").val()
	});
}

    var url;
function newPurchasingDtail(){
	$('#purchasingDetail_dlg').dialog('open').dialog('setTitle','新增药品购买明细信息');
	$('#purchasingDetail_fm').form('clear');
	url = '../sys/purchasingdetail/add';
}
    //修改用户
function editPurchasingDtail(){
		var row = $('#purchasingDetail_dg').datagrid('getSelected');
		if (row){
			$('#purchasingDetail_dlg').dialog('open').dialog('setTitle','编辑药品购买明细信息');
			$('#purchasingDetail_fm').form('load',row);
			url = '../sys/purchasingdetail/edit?purchasingDetailNo='+row.purchasingDetailNo;
		}else{
			$.messager.show({
				title: 'Info',
				msg: "请选择一条数据！"
			});
		}
	}
	
	//保存
	function savePurchasingDtail(){
	$('#purchasingDetail_fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
				var result = eval('('+result+')');
				//完善用户操作体验，关闭新增对话框，刷新列表数据，提示操作信息
			$('#purchasingDetail_dlg').dialog('close');		// close the dialog
			$('#purchasingDetail_dg').datagrid('reload');	// reload the user data
			$.messager.show({
				title: 'Info',
				msg: result.errorMsg
			});
			}
		});
}
	
	/* function GoodsTypesave(){
 		$('#goodT_fm').form('submit',{
 			url: url,
 			onSubmit: function(){
 				return $(this).form('validate');
 			},
 			success: function(result){
 				var result = eval('('+result+')');
 				//完善用户操作体验，关闭新增对话框，刷新列表数据，提示操作信息
				$('#goodT_dlg').dialog('close');		// close the dialog
				$('#goodT_dg').datagrid('reload');	// reload the user data
				$.messager.show({
					title: 'Info',
					msg: result.errorMsg
				});
 			}
 		});
 	} */
	//删除按钮动作
	function destroyPurchasingDtail(){
	var row = $('#purchasingDetail_dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('确认','您确认要删除该药品购买明细信息吗?',function(r){
			if (r){
				$.post('../sys/purchasingdetail/delete',{purchasingDetailNo:row.purchasingDetailNo},function(result){
					if (result.success){
						$('#purchasingDetail_dg').datagrid('reload');	// reload the user data
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