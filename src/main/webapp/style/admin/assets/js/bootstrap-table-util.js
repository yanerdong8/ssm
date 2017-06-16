/**
 * Bootstrap Table Chinese translation
 * Author: xinjun
 */
function initTable(tableConfigObj){
	var tableObj=$("#"+tableConfigObj.id);
	var searchForm=$("#"+tableConfigObj.searchFormId);
	//先销毁表格
	tableObj.bootstrapTable('destroy');
	//初始化表格,动态从服务器加载数据
	tableObj.bootstrapTable(
					{   singleSelect:tableConfigObj.singleSelect,
						method : "get", //使用get请求到服务器获取数据
						url : tableConfigObj.url, //获取数据的Servlet地址
						striped : true, //表格显示条纹
						pagination : true, //启动分页
						//pageSize : 10, //每页显示的记录数
						pageList: [10, 25, 50, 100],
						pageNumber : 1, //当前第几页
						sidePagination : 'server',//设置为服务器端分页 
						queryParamsType : "undefined",
						showPaginationSwitch:false,
						paginationDetailHAlign:"left",
						paginationHAlign:"right",
						 
						queryParams : function queryParams(params) { //设置查询参数 
							searchForm.prepend(
											"<input type=\"hidden\" name=\"page\" value=\""+params.pageNumber+"\">");
							searchForm.prepend(
											"<input type=\"hidden\" name=\"pageSize\" value=\""+params.pageSize+"\">");

							return searchForm.serializeArray();
						},
						onLoadSuccess : function() { //加载成功时执行
							// alert("ok");
						},
						onLoadError : function() { //加载失败时执行
							alert("error");
						}
					  ,
					columns:tableConfigObj.columns

					});
}
function operateFormatterDate(value, row, index) { 
	return [
              timeStamp2String(value)
	    ].join('');
} 
function operateFormatterDateDay(value, row, index) { 
	return [
              timeStampToDate(value)
	    ].join('');
} 
function getIdSelections($table) {
	return $.map($table.bootstrapTable('getSelections'), function(row) {
		return row.id
	});
}