<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>药品出售信息管理</title>
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
      <table id="sale_dg" class="easyui-datagrid" title="药品出售信息管理" style="width:100%;height:500px"
            toolbar="#sale_tb" 
			data-options="singleSelect:true,pagination:true,url:'http://localhost:8080/DurgShopSystem/sys/sale/list',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'saleNo',width:100,align:'center'">出售编号  </th>
				<th data-options="field:'saleName',width:100,align:'center'">出售单名称  </th>
				<th data-options="field:'patientNo',width:100,align:'center'">患者编号    </th>
				<th formatter="patientNameFormatter"data-options="field:'patientName',width:100,align:'center'">患者名称    </th>
				<th data-options="field:'doctorNo',width:150,align:'center'">开方医生编号</th>
				<th formatter="doctorNameFormatter" data-options="field:'doctorName',width:150,align:'center'">开方医生名字</th>
				<th data-options="field:'saleDate',width:200,align:'center'">出售时间</th>
			</tr>
		</thead>
	</table>
	
	<div id="sale_tb">
	  <a href="#" class="easyui-linkbutton" onclick="newSale()" data-options="iconCls:'icon-add',plain:true">新增</a>
	  <a href="#" class="easyui-linkbutton" onclick="editSale()" data-options="iconCls:'icon-edit',plain:true">修改</a>
	  <a href="#" class="easyui-linkbutton" onclick="destroySale()" data-options="iconCls:'icon-remove',plain:true">删除</a>
	  <div>
			&nbsp;出售编号： <input type="text" id="saleNoText" size="20"
				onkeydown="if(event.keyCode == 13) searchSaleInformation()" /> <a
				href="javascript:searchSaleInformation()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<!-- datagrid- end -->
	
	
	<!-- add/edit diaglog start -->
	<div id="sale_dlg" class="easyui-dialog" style="width:350px;height:270px;padding:20px 20px"
		closed="true" buttons="#sale_dlg-buttons">
	<div class="ftitle"></div>
	<form id="sale_fm" method="post">
		<div class="fitem">
			<label>销售单名称:</label>
			<div style="float:right;margin-right:20px">
				<input name="saleName" class="easyui-textbox" /  required="true">
			</div>
		</div>
		<br>
		<div class="fitem">
			<label>患者编号:</label>
			<div style="float:right;margin-right:20px">
				<input id="patientNo_list" name="patientNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'patientNo',textField:'patientNo',
							url:'../sys/sale/patientlist'"/  required="true">
			</div>
		</div>
		<br>
		<div class="fitem">
			<label>开方医生工号:</label>
			<div style="float:right;margin-right:20px">
				<input id="doctorNo_list" name="doctorNo" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'doctorNo',textField:'doctorNo',
						url:'../sys/sale/doctorlist'"/  required="true">
			</div>
		</div>
		<br>
		<div class="fitem">
			<label>销售时间:</label>
			<div style="float:right;margin-right:20px">
				<input class="easyui-datetimebox" id="saleDate" name="saleDate" data-options="showSeconds:true"/>
				</div>
		</div>

	</form>
</div>
<center><div id="sale_dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSale()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#sale_dlg').dialog('close')">取消</a>
	</div></center>
<!-- add/edit diaglog end -->


<script type="text/javascript">

$('#sale_dg').datagrid({
    view: detailview,
    detailFormatter:function(index,row){
        return '<div style="padding:2px"><table class="ddv"></table></div>';
    },
    onExpandRow: function(index,row){
        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
       
        ddv.datagrid({
            url:'http://localhost:8080/DurgShopSystem/sys/saledetail/list?saleNo='+row.saleNo,
            fitColumns:true,
            singleSelect:true,
            rownumbers:true,
            loadMsg:'',
            height:'auto',
            columns:[[    
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
		            field:'saleQuantity',
		            title:'出售数量',
		            width:50,
		            formatter: function(value,row,index){
	                    if(row.drug!=undefined){
	                        return row.saleQuantity;
	                    }
	                }
		        },
                {
			        field:'price',
			        title:'药品价格',
			        width:50
                }
           
            ]],
            onResize:function(){
                $('#sale_dg').datagrid('fixDetailRowHeight',index);
            },
           
            onLoadSuccess:function(){
                setTimeout(function(){
                    $('#sale_dg').datagrid('fixDetailRowHeight',index);
                },0);
            }
        });
        $('#sale_dg').datagrid('fixDetailRowHeight',index);
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

    //点击新增按钮使调用，新增对话框
    
function doctorNameFormatter(value,row,index){
	return row.doctor.doctorName;
}
  
function drugNoFormatter(value,row,index){
	return row.saleDetail.drugNo;
}

function saleQuantityFormatter(value,row,index){
	return row.saleDetail.saleQuantity;
}

function patientNameFormatter(value,row,index){
	return row.patient.patientName;
}

//搜索按钮
function searchSaleInformation() {
	$("#sale_dg").datagrid('load', {
		"saleNo" : $("#saleNoText").val()
	});
}


function newSale(){
	$('#sale_dlg').dialog('open').dialog('setTitle','新增药品出售信息');
	$('#sale_fm').form('clear');
	url = '../sys/sale/add';
}
    //修改用户
function editSale(){
		var row = $('#sale_dg').datagrid('getSelected');
		if (row){
			$('#sale_dlg').dialog('open').dialog('setTitle','编辑药品出售信息');
			$('#sale_fm').form('load',row);
			url = '../sys/sale/edit?saleNo='+row.saleNo;
		}else{
			$.messager.show({
				title: 'Info',
				msg: "请选择一条数据！"
			});
		}
	}
	
	//保存
	function saveSale(){
	$('#sale_fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			//完善用户操作体验，关闭新增对话框，刷新列表数据，提示操作信息
		$('#sale_dlg').dialog('close');		// close the dialog
		$('#sale_dg').datagrid('reload');	// reload the user data
		$.messager.show({
			title: 'Info',
			msg: result.errorMsg
		});
		}
	});
}
	//删除按钮动作
	function destroySale(){
	var row = $('#sale_dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('确认','您确认要删除该药品出售信息吗?',function(r){
			if (r){
				$.post('../sys/sale/delete',{saleNo:row.saleNo},function(result){
					if (result.success){
						$('#sale_dg').datagrid('reload');	// reload the user data
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