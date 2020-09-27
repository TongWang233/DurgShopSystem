<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- seo搜索引擎優化： -->
<!-- 前端三大技术标准：html页面布局控制，css页面美化，javascript动态效果或人机交互 -->
<title>入库管理</title>
    <!-- 在页面中引入框架代码,本地安装,在线安装 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/color.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
	
</head>
<body>

      <table id="stockIn_dg" class="easyui-datagrid" title="入库管理" style="width:100%;height:500px"
            toolbar="#stockIn_tb"
			data-options="pagination:true,singleSelect:true,
			url:'${pageContext.request.contextPath}/sys/stockIn/stockInList',
			method:'post',
			">
		<thead>
			<tr>
				<th data-options="field:'stockInNo',width:200,align:'center'"  >入库编号</th>
				<th data-options="field:'stockInName',width:200,align:'center'"  >入库单</th>
				<th data-options="field:'handler',width:200,align:'center' " >负责人</th>
				<th data-options="field:'saleNo',width:300,align:'center'" hidden="true">销售编号</th>
				<th data-options="field:'stockInTime',width:250,align:'center'" >创建时间</th> 
			</tr>
		</thead>
	</table>
	<div id="stockIn_tb">
		<div >
		  <a href="#" class="easyui-linkbutton" onclick="newStockIn()" data-options="iconCls:'icon-add',plain:true">新增</a>
		  <a href="#" class="easyui-linkbutton" onclick="newStockDetail()" data-options="iconCls:'icon-add',plain:true">新增详情</a>
		  <a href="#" class="easyui-linkbutton" onclick="editStockIn()" data-options="iconCls:'icon-edit',plain:true">修改</a>
		  <a href="#" class="easyui-linkbutton" onclick="destroyStockIn()" data-options="iconCls:'icon-remove',plain:true">删除</a>
		</div>
		<div>
			&nbsp;入库单： <input type="text" id="stockInNameText" size="20"
				onkeydown="if(event.keyCode == 13) searchstockInNameInformation()" /> <a
				href="javascript:searchstockInNameInformation()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
		
	</div>
	<!-- datagrid end  -->
	
	
	<!-- add/edit dialog  start  -->
	<div id="stockIn_dlg" class="easyui-dialog" style="width:320px;height:260px;padding:10px 20px"
		closed="true" buttons="#stockIn_dlg-buttons">
	<form id="stockIn_fm" method="post">
		<div class="fitem">
			<label>入库单:&nbsp;&nbsp;&nbsp;</label>
			<div style="float:right;margin-right:10px">
				<input name="stockInName" class="easyui-textbox"  required="true"> 
			</div>
		</div> 
		<br>
		<div class="fitem">
			<label>经手人:&nbsp;&nbsp;&nbsp;</label> 
			<div style="float:right;margin-right:10px">
				<input  name="handler" class="easyui-textbox" >
			</div>
		</div>
		<br>
		<div class="fitem">
			<label>创建时间:</label>
			<div style="float:right;margin-right:10px">
				<input name="stockInTime" class="easyui-datebox">
			</div>
		</div>  
	</form>
</div>
<div id="stockIn_dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveStockIn()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#stockIn_dlg').dialog('close')">取消</a>
</div>
<!-- add/edit dialog  end  -->

<!-- 新增详细单 -->
	<div id="stockInDetailto_dlg" class="easyui-dialog" style="width:300px;height:300px;padding:10px 20px"
		closed="true" buttons="#stockInDetail-dlg-buttons">
	<form id="stockInDetailto_fm" method="post">
		<div class="fitem">
			<label>入库单:&nbsp;&nbsp;&nbsp;</label>
			<input id="forstockInNo" name="stockIn.stockInNo" class="easyui-combobox" 
				data-options="panelHeight:'auto',editable:false,valueField:'stockInNo',textField:'stockInName',
				url:'${pageContext.request.contextPath}/sys/stockInDetail/stockInList'"  required="true">
		</div> 
		<br>
		<div class="fitem">
			<label>药品名称:</label>
			<input id="drug_drugNo" name="drug.drugNo" class="easyui-combobox" 
				data-options="panelHeight:'auto',editable:false,valueField:'drugNo',textField:'drugName',
				url:'${pageContext.request.contextPath}/sys/stockInDetail/drugList'"  required="true">
		</div> 
		<br>
		<div class="fitem">
				<label>入库数量:</label> 
				<input  name="stockInDetailQuantity" class="easyui-textbox"  required="true">
		</div> 
		<br>
		 
	</form>
</div>
<div id="stockInDetail-dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveStockInDetail2()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#stockInDetailto_dlg').dialog('close')">取消</a>
</div>



<style type="text/css">
 .fitem{
	   display: inline-block;
	   margin: 10px
	   }
</style>	


 <script type="text/javascript">
 
//搜索按钮
 function searchstockInNameInformation() {
 	$("#stockIn_dg").datagrid('load', {
 		"stockInName" : $("#stockInNameText").val()
 	});
 }
 
 $('#stockIn_dg').datagrid({
	    view: detailview,
	    detailFormatter:function(index,row){
	        return '<div style="padding:2px"><table class="ddv"></table></div>';
	    },
	    onExpandRow: function(index,row){
	        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
	       
	        ddv.datagrid({
	            url:'${pageContext.request.contextPath}/sys/stockInDetail/stockInDetailandDrugList?stockInNo='+row.stockInNo,
	            fitColumns:true,
	            singleSelect:true,
	            rownumbers:true,
	            loadMsg:'',
	            height:'auto',
	            columns:[[
	                {
		                field:'stockInDetailNo',
		                title:'入库详情编号',
		                width:50


			        },
	                {
		                field:'drugNo',
		                title:'药品编号',
		                width:50,
		                formatter: function(value,row,index){
		                    if(row.drug!=undefined){
		                        return row.drug.drugNo;
		                    }
		                }

		                
		            },
	                {
		                field:'drugName',
		                title:'药品名称',
		                width:50,
		                formatter: function(value,row,index){
		                    if(row.drug!=undefined){
		                        return row.drug.drugName;
		                    }
		                }
		                
		            },
	                {
			            field:'drugSpecification',
			            title:'规格',
			            width:50,
			            formatter: function(value,row,index){
		                    if(row.drug!=undefined){
		                        return row.drug.drugSpecification;
		                    }
		                }
			        },
	                {
				        field:'stockInDetailQuantity',
				        title:'入库数量',
				        width:50
	                }
	           
	            ]],
	            onResize:function(){
	                $('#stockIn_dg').datagrid('fixDetailRowHeight',index);
	            },
	           
	            onLoadSuccess:function(){
	                setTimeout(function(){
	                    $('#stockIn_dg').datagrid('fixDetailRowHeight',index);
	                },0);
	            }
	        });
	        $('#stockIn_dg').datagrid('fixDetailRowHeight',index);
	    }
	});


 
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
	   
	   function newStockDetail(){
		   
		   
		   	var row = $('#stockIn_dg').datagrid('getSelected');
			$('#stockInDetailto_dlg').dialog('open').dialog('setTitle','新增详情单');
			$('#stockInDetailto_fm').form('clear');
			//新增数据的控制器地址
			url = '${pageContext.request.contextPath}/sys/stockInDetail/addStockInDetail';
			
			$('#forstockInNo').combobox({
			    url:'${pageContext.request.contextPath}/sys/stockInDetail/stockInList?t='+new Date().getTime(),
			    valueField:'stockInNo',
			    textField:'stockInName'
			    	
			});
			StcokInDetailNoSuccess(0),
			DrugNoSuccess(0)
		}  
	
	
 
 	//点击新增按钮时调用，弹出新增对话框
    function newStockIn(){
		$('#stockIn_dlg').dialog('open').dialog('setTitle','新增入库单');
		$('#stockIn_fm').form('clear');
		//新增数据的控制器地址
		url = '${pageContext.request.contextPath}/sys/stockIn/addStockIn';

	}
	    
 	//修改用户数据对话框，实现自动加载选中的数据
 	function editStockIn(){
 		var row = $('#stockIn_dg').datagrid('getSelected');
 		if (row){
 			$('#stockIn_dlg').dialog('open').dialog('setTitle','编辑入库单');
 			$('#stockIn_fm').form('load',row);
 			url = '${pageContext.request.contextPath}/sys/stockIn/editStockIn?stockInNo='+row.stockInNo;
 		}else{
 			$.messager.show({
					title: 'Info',
					msg: "请选择一条数据！"
				});
 		}
 	}
 	
 	//保存按钮
 	function saveStockIn(){
 		$('#stockIn_fm').form('submit',{
 			url: url,
 			onSubmit: function(){
 				return $(this).form('validate');
 			},
 			success: function(result){
 				var result = eval('('+result+')');
 				//完善用户操作体验，关闭新增对话框，刷新列表数据，提示操作信息
 				
				$('#stockIn_dlg').dialog('close');		// close the dialog
				$('#stockIn_dg').datagrid('reload');	// reload the user data
				
				$.messager.show({
					title: 'Info',
					msg: result.errorMsg
				});
 			}
 		});
 	}
 	//详情保存
 	function saveStockInDetail2(){
 		
 		$('#stockInDetailto_fm').form('submit',{
 			url: url,
 			onSubmit: function(){
 				return $(this).form('validate');
 			},
 			success: function(result){
 				var result = eval('('+result+')');
 				//完善用户操作体验，关闭新增对话框，刷新列表数据，提示操作信息
				$('#stockInDetailto_dlg').dialog('close');		// close the dialog
				$('#stockIn_dg').datagrid('reload');	// reload the user data
				$.messager.show({
					title: 'Info',
					msg: result.errorMsg
				});
 			}
 		});
 	}
 	
 	//删除按钮的动作
 	function destroyStockIn(){
 		var row = $('#stockIn_dg').datagrid('getSelected');
 		if (row){
 			$.messager.confirm('Confirm','确认删除?',function(r){
 				if (r){
 					$.post('${pageContext.request.contextPath}/sys/stockIn/deleteStockOut',{stockInNo:row.stockInNo},function(result){
 						
 						if (result.success){
 							$.messager.show({	// show error message
 								title: 'info',
 								msg: "删除成功"
 							});
 							$('#stockIn_dg').datagrid('reload');	// reload the user data
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