<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- seo搜索引擎優化： -->
<!-- 前端三大技术标准：html页面布局控制，css页面美化，javascript动态效果或人机交互 -->
<title>入库详细单管理</title>
    <!-- 在页面中引入框架代码,本地安装,在线安装 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/color.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.easyui.min.js"></script>
	
</head>
<body>

      <table id="stockInDetail_dg" class="easyui-datagrid" title="入库管理" style="width:100%;height:500px"
            toolbar="#stockInDetail_tb"
			data-options="pagination:true,singleSelect:true,
			url:'${pageContext.request.contextPath}/sys/stockInDetail/stockInDetailList',
			method:'post',
			">
		<thead>
			<tr>
				<th data-options="field:'stockInDetailNo',width:100,align:'center'"  >入库详细编号</th>
				<th data-options="field:'stockInName',width:100,align:'center'" formatter="stockInNoF" >入库单</th>
				<th data-options="field:'drugName',width:100,align:'center'" formatter="drugNameF"  >药品名称</th>
				<th data-options="field:'drugSpecification',width:200,align:'center'" formatter="drugSpecification">药品规格</th>
				<th data-options="field:'stockInDetailQuantity',width:100,align:'center'" >入库数量</th> 
				<th data-options="field:'handler',width:100,align:'center'" formatter="HandlerF" >经手人</th> 
				<th data-options="field:'stockInTime',width:200,align:'center'" formatter="stockInTimeF" >创建时间</th> 
				
			</tr>
		</thead>
	</table>
	<div id="stockInDetail_tb">
		<div >
		  <a href="#" class="easyui-linkbutton" onclick="newStockInDetail()" data-options="iconCls:'icon-add',plain:true">新增</a>
		  <a href="#" class="easyui-linkbutton" onclick="editStockInDetail()" data-options="iconCls:'icon-edit',plain:true">修改</a>
		  <a href="#" class="easyui-linkbutton" onclick="destroyStockInDetail()" data-options="iconCls:'icon-remove',plain:true">删除</a>
		</div>
		<div>
			&nbsp;入库单： <input type="text" id="stockInNameText2" size="20"
			onkeydown="if(event.keyCode == 13) searchstockInNameInformation2()" /> <a
			href="javascript:searchstockInNameInformation2()"
			class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<!-- datagrid end  -->
	
	
	<!-- add/edit dialog  start  -->
	<div id="stockInDetail_dlg" class="easyui-dialog" style="width:360px;height:300px;padding:10px 20px"
		closed="true" buttons="#stockInDetail_dlg-buttons">
	<form id="stockInDetail_fm" method="post">
		<div class="fitem">
			<label>入库详细单号:&nbsp;&nbsp;&nbsp;</label>
			<input id="forstockInNo" name="stockIn.stockInNo" class="easyui-combobox" 
				data-options="panelHeight:'auto',editable:false,valueField:'stockInNo',textField:'stockInName',
				url:'${pageContext.request.contextPath}/sys/stockInDetail/stockInList'"/  required="true">
		</div> 
		<br>
		<div class="fitem">
			<label>药品名称:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<input id="drug_drugNo" name="drug.drugNo" class="easyui-combobox" 
				data-options="panelHeight:'auto',editable:false,valueField:'drugNo',textField:'drugName',
				url:'${pageContext.request.contextPath}/sys/stockInDetail/drugList'"/  required="true">
		</div> 
		<br>
		<div class="fitem">
				<label>入库数量:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
				<input  name="stockInDetailQuantity" class="easyui-textbox"  required="true">
		</div> 
		<br>
		<div class="fitem">
			<label>创建时间:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<input name="stockInTime" class="easyui-datebox">
		</div>  
	</form>
</div>
<div id="stockInDetail_dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveStockInDetail()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#stockInDetail_dlg').dialog('close')">取消</a>
</div>
<!-- add/edit dialog  end  -->
<style type="text/css">
 .fitem{
	   display: inline-block;
	   margin: 10px
	   }
</style>	


 <script type="text/javascript">
 
//搜索按钮
 function searchstockInNameInformation2() {
 	$("#stockInDetail_dg").datagrid('load', {
 		"stockInDetailNo" : $("#stockInNameText2").val()
 	});
 }
 
 

 function drugNameF(value,row,index){
	return row.drug.drugName;
}
 function drugSpecification(value,row,index){
		return row.drug.drugSpecification;
	}
 function drugRemark(value,row,index){
		return row.drug.drugRemark;
	}
function HandlerF(value,row,index){
		return row.stockIn.handler;
	}
function stockInNoF(value,row,index){
			return row.stockIn.stockInName;
		}
function stockInTimeF(value,row,index){
			return row.stockIn.stockInTime;
		}
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

	   //下拉列表反选
	   function StcokInDetailNoSuccess(value) {
	    	var data = $("#forstockInNo").combobox("getData");
	    	if (data && data.length > 0) {
	    		if (value == 0) {
	    			$("#forstockInNo").combobox("setValue", data[0].stockInNo);
	    		} else {
	    			$("#forstockInNo").combobox("setValue", value);
	    		}
	    	}
	    }

	   //下拉列表反选
	   function DrugNoSuccess(value) {
	    	var data = $("#drug_drugNo").combobox("getData");
	    	if (data && data.length > 0) {
	    		if (value == 0) {
	    			$("#drug_drugNo").combobox("setValue", data[0].drugNo);
	    		} else {
	    			$("#drug_drugNo").combobox("setValue", value);
	    		}
	    	}
	    } 

	
 
 	//点击新增按钮时调用，弹出新增对话框
    function newStockInDetail(){
		$('#stockInDetail_dlg').dialog('open').dialog('setTitle','新增用户');
		$('#stockInDetail_fm').form('clear');
		//新增数据的控制器地址
		url = '${pageContext.request.contextPath}/sys/stockInDetail/addStockInDetail';

		StcokInDetailNoSuccess(0);
		DrugNoSuccess(0);
	}
 	
 	//修改用户数据对话框，实现自动加载选中的数据
 	function editStockInDetail(){
 		var row = $('#stockInDetail_dg').datagrid('getSelected');
 		if (row){
 			$('#stockInDetail_dlg').dialog('open').dialog('setTitle','编辑用户');
 			$('#stockInDetail_fm').form('load',row);
 			url = '${pageContext.request.contextPath}/sys/stockInDetail/editStockInDetail?stockInDetailNo='+row.stockInDetailNo;

 			StcokInDetailNoSuccess(row.stockIn.stockInNo);
 			DrugNoSuccess(row.drug.drugNo);
 		}else{
 			$.messager.show({
					title: 'Info',
					msg: "请选择一条数据！"
				});
 		}
 	}
 	
 	//保存按钮
 	function saveStockInDetail(){
 		$('#stockInDetail_fm').form('submit',{
 			url: url,
 			onSubmit: function(){
 				return $(this).form('validate');
 			},
 			success: function(result){
 				var result = eval('('+result+')');
 				//完善用户操作体验，关闭新增对话框，刷新列表数据，提示操作信息
				$('#stockInDetail_dlg').dialog('close');		// close the dialog
				$('#stockInDetail_dg').datagrid('reload');	// reload the user data
				$.messager.show({
					title: 'Info',
					msg: result.errorMsg
				});
 			}
 		});
 	}
 	
 	//删除按钮的动作
 	function destroyStockInDetail(){
 		var row = $('#stockInDetail_dg').datagrid('getSelected');
 		if (row){
 			$.messager.confirm('Confirm','确认删除?误删除要加班！',function(r){
 				if (r){
 					$.post('${pageContext.request.contextPath}/sys/stockInDetail/deleteStockInDetail',{stockInDetailNo:row.stockInDetailNo},function(result){
 						
 						if (result.success){
 							$.messager.show({	// show error message
 								title: 'info',
 								msg: "删除成功"
 							});
 							$('#stockInDetail_dg').datagrid('reload');	// reload the user data
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
				msg: "请选择要删除的数据！"
			});
		}
 	}
 	
 </script>     

</body>
</html>