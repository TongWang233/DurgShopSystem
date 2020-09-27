<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- seo搜索引擎優化： -->
<!-- 前端三大技术标准：html页面布局控制，css页面美化，javascript动态效果或人机交互 -->
<title>出库详细单管理</title>
    <!-- 在页面中引入框架代码,本地安装,在线安装 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/color.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.easyui.min.js"></script>
	
</head>
<body>

      <table id="stockOutDetail_dg" class="easyui-datagrid" title="出库管理" style="width:100%;height:500px"
            toolbar="#stockOutDetail_tb"
			data-options="pagination:true,singleSelect:true,
			url:'${pageContext.request.contextPath}/sys/stockOutDetail/stockOutDetailList',
			method:'post',
			">
		<thead>
			<tr>
				<th data-options="field:'stockOutDetailNo',width:100,align:'center'"  >出库详细编号</th>
				<th data-options="field:'drugName',width:100,align:'center'" formatter="drugNameF">药品名称</th>
				<th data-options="field:'drugSpecification',width:200,align:'center'" formatter="drugSpecification">药品规格</th>
				<th data-options="field:'drugRemark',width:300,align:'center'" formatter="drugRemark">药品详情</th>
				<th data-options="field:'stockOutDetailQuantity',width:100,align:'center'" >出库数量</th> 
				<th data-options="field:'stockOutName',width:100,align:'center'" formatter="stockInNoF" >出库单</th>
				<th data-options="field:'handler',width:100,align:'center'" formatter="HandlerF" >经手人</th> 
				<th data-options="field:'stockOutTime',width:200,align:'center'" formatter="stockInTimeF" >创建时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="stockOutDetail_tb">
		<div >
		  <a href="#" class="easyui-linkbutton" onclick="newStockOutDetail()" data-options="iconCls:'icon-add',plain:true">新增</a>
		  <a href="#" class="easyui-linkbutton" onclick="editStockOutDetail()" data-options="iconCls:'icon-edit',plain:true">修改</a>
		  <a href="#" class="easyui-linkbutton" onclick="destroyStockOutDetail()" data-options="iconCls:'icon-remove',plain:true">删除</a>
		</div>
		<div> 
			&nbsp;出库详情编号： <input type="text" id="stockOutNoText" size="20"
			onkeydown="if(event.keyCode == 13) searchstockOutDetailNoInformation()" /> <a
			href="javascript:searchstockOutDetailNoInformation()"
			class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<!-- datagrid end  -->
	
	
	<!-- add/edit dialog  start  -->
	<div id="stockOutDetail_dlg" class="easyui-dialog" style="width:300px;height:290px;padding:10px 20px"
		closed="true" buttons="#stockOutDetail_dlg-buttons">
	<form id="stockOutDetail_fm" method="post">
		<div class="fitem">
			<label>出库单:&nbsp;&nbsp;&nbsp;</label>
			<input id="forstockOutNo" name="stockOut.stockOutNo" class="easyui-combobox" 
				data-options="panelHeight:'auto',editable:false,valueField:'stockOutNo',textField:'stockOutName',
				url:'${pageContext.request.contextPath}/sys/stockOutDetail/stockOutList'"/  required="true">
		</div> 
		<br>
		<div class="fitem">
			<label>药品名称:</label>
			<input id="drugOne_drugNo" name="drugOne.drugNo" class="easyui-combobox" 
				data-options="panelHeight:'auto',editable:false,valueField:'drugNo',textField:'drugName',
				url:'${pageContext.request.contextPath}/sys/stockInDetail/drugList'"/  required="true">
		</div> 
		<br>
		<div class="fitem">
				<label>出库数量:</label> 
				<input  name="stockOutDetailQuantity" class="easyui-textbox"  required="true">
		</div> 
		<br>
		<div class="fitem">
			<label>创建时间:</label>
			<input name="stockOutTime" class="easyui-datebox">
		</div>  
	</form>
</div>
<div id="stockOutDetail_dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveStockOutDetail()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#stockOutDetail_dlg').dialog('close')">取消</a>
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
 function searchstockOutDetailNoInformation() {
 	$("#stockOutDetail_dg").datagrid('load', {
 		"stockOutDetailNo" : $("#stockOutNoText").val()
 	});
 }
 
 
 function drugNameF(value,row,index){
	return row.drugOne.drugName;
}
 function drugSpecification(value,row,index){
		return row.drugOne.drugSpecification;
	}
 function drugRemark(value,row,index){
		return row.drugOne.drugRemark;
	}
	
function HandlerF(value,row,index){
		return row.stockOut.handler;
	}
	
function stockInNoF(value,row,index){
		return row.stockOut.stockOutName;
		}
		
function stockInTimeF(value,row,index){
		return row.stockOut.stockOutTime;
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

	   //下拉列表反选 - 商品类型
	   function StockOutNoSuccess(value) {
	    	var data = $("#forstockOutNo").combobox("getData");
	    	if (data && data.length > 0) {
	    		if (value == 0) {
	    			$("#forstockOutNo").combobox("setValue", data[0].stockInNo);
	    		} else {
	    			$("#forstockOutNo").combobox("setValue", value);
	    		}
	    	}
	    }

	   //下拉列表反选 - 供应商
	   function DrugOneNoSuccess(value) {
	    	var data = $("#drugOne_drugNo").combobox("getData");
	    	if (data && data.length > 0) {
	    		if (value == 0) {
	    			$("#drugOne_drugNo").combobox("setValue", data[0].drugNo);
	    		} else {
	    			$("#drugOne_drugNo").combobox("setValue", value);
	    		}
	    	}
	    } 

	
 
 	//点击新增按钮时调用，弹出新增对话框
    function newStockOutDetail(){
		$('#stockOutDetail_dlg').dialog('open').dialog('setTitle','新增出库详情单');
		$('#stockOutDetail_fm').form('clear');
		//新增数据的控制器地址
		url = '${pageContext.request.contextPath}/sys/stockOutDetail/addStockOutDetail';

		StockOutNoSuccess(0);
		DrugOneNoSuccess(0);
	}
 	
 	//修改用户数据对话框，实现自动加载选中的数据
 	function editStockOutDetail(){
 		var row = $('#stockOutDetail_dg').datagrid('getSelected');
 		if (row){
 			$('#stockOutDetail_dlg').dialog('open').dialog('setTitle','编辑出库详情单');
 			$('#stockOutDetail_fm').form('load',row);
 			url = '${pageContext.request.contextPath}/sys/stockOutDetail/editStockOutDetail?stockOutDetailNo='+row.stockOutDetailNo;

 			StockOutNoSuccess(row.stockOut.stockOutNo);
 			DrugOneNoSuccess(row.drugOne.drugNo);
 		}else{
 			$.messager.show({
					title: 'Info',
					msg: "请选择一条数据！"
				});
 		}
 	}
 	
 	//保存按钮
 	function saveStockOutDetail(){
 		$('#stockOutDetail_fm').form('submit',{
 			url: url,
 			onSubmit: function(){
 				return $(this).form('validate');
 			},
 			success: function(result){
 				var result = eval('('+result+')');
 				//完善用户操作体验，关闭新增对话框，刷新列表数据，提示操作信息
				$('#stockOutDetail_dlg').dialog('close');		// close the dialog
				$('#stockOutDetail_dg').datagrid('reload');	// reload the user data
				$.messager.show({
					title: 'Info',
					msg: result.errorMsg
				});
 			}
 		});
 	}
 	
 	//删除按钮的动作
 	function destroyStockOutDetail(){
 		var row = $('#stockOutDetail_dg').datagrid('getSelected');
 		if (row){
 			$.messager.confirm('Confirm','确认删除?误删除要加班！',function(r){
 				if (r){
 					$.post('${pageContext.request.contextPath}/sys/stockOutDetail/deleteStockOutDetail',{stockOutDetailNo:row.stockOutDetailNo},function(result){
 						
 						if (result.success){
 							$.messager.show({	// show error message
 								title: 'info',
 								msg: "删除成功"
 							});
 							$('#stockOutDetail_dg').datagrid('reload');	// reload the user data
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