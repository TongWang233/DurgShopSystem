/**
  使用方法：
     
     1.在页面加入以下代码：
     
     <div id="tab-tools">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-reload'" onclick="reload()"></a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-delete'" onclick="closecur()"></a>
		</div>

		<div id="mm" class="easyui-menu" style="width: 150px;">
			<div data-options="iconCls:'icon-reload'" id="refresh">刷新</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-delete0'" id="closecur">关闭当前</div>
			<div data-options="iconCls:'icon-delete0'" id="closeall">全部关闭</div>
			<div data-options="iconCls:'icon-delete0'" id="closeother">除此之外全部关闭</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-delete0'" id="closeright">当前页右侧全部关闭</div>
			<div data-options="iconCls:'icon-delete0'" id="closeleft">当前页左侧全部关闭</div>
		</div>
		
	2.在内容显示块上加入：data-options="tools:'#tab-tools'"
	
	  <div data-options="region:'center',border:false,plain:true">
			<div id="tt" class="easyui-tabs" data-options="fit:true"
				data-options="tools:'#tab-tools'">
				<div title="首页" style="text-align: center; font-size: 24px;">
					<p>欢迎来到XXX管理后台管理系统</p>
				</div>
			</div>
		</div>
		
	3.导入js
	 <script type="text/javascript" src="./js/context-menu.js"></script>	
	 
    4.绑定contextmenu
    
    <script type="text/javascript">
		$(function() {
			bindContextMenu('#tt');
		});
		
    5.注意事项：
       本例默认不关闭第一项
       
	</script>
 * @param tabID
 * @returns
 */

function bindContextMenu(tabID) {

	$(".tabs-header").bind('contextmenu', function(e) {
		e.preventDefault();
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	});

	// 刷新
	$("#refresh").bind("click", function() {
		// reload();
		RefreshTab(tabID);
	})

	// 关闭当前标签页
	$("#closecur").bind("click", function() {
		closecur(tabID);
	});
	// 关闭所有标签页
	$("#closeall").bind("click", function() {

		var tablist = $(tabID).tabs('tabs');
		
		for (var i = tablist.length - 1; i > 0; i--) {
			$(tabID).tabs('close', i);
		}
	});
	// 关闭非当前标签页（先关闭右侧，再关闭左侧）
	$("#closeother").bind("click", function() {
		var tablist = $(tabID).tabs('tabs');
		var tab = $(tabID).tabs('getSelected');
		var index = $(tabID).tabs('getTabIndex', tab);
		for (var i = tablist.length - 1; i > index; i--) {
			$(tabID).tabs('close', i);
		}
		var num = index - 1;
		for (var i = num; i > 0; i--) {
			$(tabID).tabs('close', 1);
		}
	});
	// 关闭当前标签页右侧标签页
	$("#closeright").bind("click", function() {
		var tablist = $(tabID).tabs('tabs');
		var tab = $(tabID).tabs('getSelected');
		var index = $(tabID).tabs('getTabIndex', tab);

		for (var i = tablist.length - 1; i > index; i--) {
			$(tabID).tabs('close', i);
		}
	});
	// 关闭当前标签页左侧标签页
	$("#closeleft").bind("click", function() {
		var tab = $(tabID).tabs('getSelected');
		var index = $(tabID).tabs('getTabIndex', tab);
		var num = index - 1;
		for (var i = 0; i < num; i++) {
			$(tabID).tabs('close', 1);
		}
	});
}

// 刷新当前标签Tabs
function RefreshTab(tabs) {
	var currentTab = $(tabs).tabs('getSelected');
	currentTab.panel('refresh');
}
// 刷新当前标签Tabs
function reload(tabs) {
	var currentTab = $(tabs).tabs('getSelected');
	var url = $(currentTab.panel('options')).attr('href');
	$(tabs)
			.tabs(
					'update',
					{
						tab : currentTab,
						options : {
							content : '<iframe scrolling="auto" frameborder="0"  style="width:100%;height:100%;" src="'
									+ url + '" ></iframe>'
						}
					})
}

function closecur(tabs) {

	var tab = $(tabs).tabs('getSelected');
	var index = $(tabs).tabs('getTabIndex', tab);
	if (index != 0) {// 不关闭第一项
		$(tabs).tabs('close', index);
	}
}
