<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>药品采购管理</title>
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
      <table id="purchasing_dg" class="easyui-datagrid" title="药品采购管理" style="width:100%;height:500px"
            toolbar="#purchasing_tb" 
			data-options="singleSelect:true,pagination:true,url:'http://localhost:8080/DurgShopSystem/sys/purchasing/list',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'purchasingNo',width:100,align:'center'">  采购编号 </th>
				<th data-options="field:'purchasingName',width:100,align:'center'">  采购单名 </th>
				<th data-options="field:'handler',width:100,align:'center'">  采购人</th>
				<th data-options="field:'purchasingRemark',width:100,align:'center'">  采购备注</th>
				<th data-options="field:'purchasingDate',width:200,align:'center'"> 采购日期</th>
			</tr>
		</thead>
	</table>
	
	<div id="purchasing_tb">
	  <a href="#" class="easyui-linkbutton" onclick="newPurchasing()" data-options="iconCls:'icon-add',plain:true">新增</a>
	  <a href="#" class="easyui-linkbutton" onclick="editPurchasing()" data-options="iconCls:'icon-edit',plain:true">修改</a>
	  <a href="#" class="easyui-linkbutton" onclick="destroyPurchasing()" data-options="iconCls:'icon-remove',plain:true">删除</a>
		<div>
			&nbsp;采购编号： <input type="text" id="purchasingNoText2" size="20"
				onkeydown="if(event.keyCode == 13) searchPurchasingInformation2()" /> <a
				href="javascript:searchPurchasingInformation2()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	<!-- datagrid- end -->
	
	
	<!-- add/edit diaglog start -->
	<div id="purchasing_dlg" class="easyui-dialog" style="width:300px;height:300px;padding:20px 20px"
		closed="true" buttons="#purchasing_dlg-buttons">
	<div class="ftitle"></div>
	<form id="purchasing_fm" method="post">
		<div class="fitem">
			<label>采购单名:</label>
			<input name="purchasingName" class="easyui-textbox"  required="true">
		</div>
		<br>
		<div class="fitem">
			<label>采购人:&nbsp;&nbsp;&nbsp;</label>
			<input name="handler" class="easyui-textbox">
		</div>
		<br>
		<div class="fitem">
			<label>采购日期:</label>
			<input class="easyui-datetimebox" id="purchasingDate" name="purchasingDate" data-options="showSeconds:true"/>
		</div>
		<br>
		<div class="fitem">
			<label>采购备注:</label>
			<input name="purchasingRemark" class="easyui-textbox">
		</div>
		
	</form>
</div>
<center><div id="purchasing_dlg-buttons">
	<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePurchasing()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#purchasing_dlg').dialog('close')">取消</a>
	</div></center>
<!-- add/edit diaglog end -->


<script type="text/javascript">

$('#purchasing_dg').datagrid({
    view: detailview,
    detailFormatter:function(index,row){
        return '<div style="padding:2px"><table class="ddv"></table></div>';
    },
    onExpandRow: function(index,row){
        var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
       
        ddv.datagrid({
            url:'http://localhost:8080/DurgShopSystem/sys/purchasingdetail/list?purchasingNo='+row.purchasingNo,
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
		            field:'purchasingDetailQuantity',
		            title:'出售数量',
		            width:50,
		            formatter: function(value,row,index){
	                    if(row.drug!=undefined){
	                        return row.purchasingDetailQuantity;
	                    }
	                }
		        },
                {
			        field:'purchasingDetailPrice',
			        title:'药品价格',
			        width:50
                },
                {
			        field:'productDate',
			        title:'药品价格',
			        width:50
                }
           
            ]],
            onResize:function(){
                $('#purchasing_dg').datagrid('fixDetailRowHeight',index);
            },
           
            onLoadSuccess:function(){
                setTimeout(function(){
                    $('#purchasing_dg').datagrid('fixDetailRowHeight',index);
                },0);
            }
        });
        $('#purchasing_dg').datagrid('fixDetailRowHeight',index);
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


function drugNoFormatter(value,row,index){
	return row.drug.drugNo;
}

function purchasingDetailQuantityFormatter(value,row,index){
	return row.purchasingDetail.purchasingDetailQuantity;
}
function productDateFormatter(value,row,index){
	return row.purchasingDetail.productDate;
}

function searchPurchasingInformation() {
	$("#purchasing_dg").datagrid('load', {
		"purchasingNo" : $("#purchasingNoText2").val()
	});
}

      //搜索按钮
function searchPurchasingInformation2() {
	$("#purchasing_dg").datagrid('load', {
		"purchasingNo" : $("#purchasingNoText2").val()
	});
}

	var url;
    //点击新增按钮使调用，新增对话框
function newPurchasing(){
	$('#purchasing_dlg').dialog('open').dialog('setTitle','新增采购单');
	$('#purchasing_fm').form('clear');
	url = '../sys/purchasing/add';
}
    //修改用户
function editPurchasing(){
		var row = $('#purchasing_dg').datagrid('getSelected');
		if (row){
			$('#purchasing_dlg').dialog('open').dialog('setTitle','编辑采购单');
			$('#purchasing_fm').form('load',row);
			url = '../sys/purchasing/edit?purchasingNo='+row.purchasingNo;
		}else{
			$.messager.show({
				title: 'Info',
				msg: "请选择一条数据！"
			});
		}
	}
	
	//保存
	function savePurchasing(){
	$('#purchasing_fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			var result = eval('('+result+')');
			//完善用户操作体验，关闭新增对话框，刷新列表数据，提示操作信息
		$('#purchasing_dlg').dialog('close');		// close the dialog
		$('#purchasing_dg').datagrid('reload');	// reload the user data
		$.messager.show({
			title: 'Info',
			msg: result.errorMsg
		});
		}
	});
}
	//删除按钮动作
	function destroyPurchasing(){
	var row = $('#purchasing_dg').datagrid('getSelected');
	if (row){
		$.messager.confirm('确认','您确认要删除该采购单吗?',function(r){
			if (r){
				$.post('../sys/purchasing/delete',{purchasingNo:row.purchasingNo},function(result){
					if (result.success){
						$('#purchasing_dg').datagrid('reload');	// reload the user data
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