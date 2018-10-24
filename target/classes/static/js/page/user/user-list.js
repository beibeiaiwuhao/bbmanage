
var TableInit;
TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#user-list').bootstrapTable('destroy');
        $('#user-list').bootstrapTable({
            url: "/user/findAllUser?&rdm=" + Math.random(), //请求后台的URL（*）
            method: 'GET',                     //请求方式（*）
            dataType:'json',
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [5, 10, 15, 20],        //可供选择的每页的行数（*）
            smartDisplay:false,
            search: false,                      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: false,
            showColumns: false,                 //是否显示所有的列
            showRefresh: false,                 //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            width:$(window).width(),
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            showExport: false,
            exportDataType: 'all',
            responseHandler: oTableInit.responseHandler,
            onLoadSuccess:oTableInit.loadSuccess,
            columns: [ {
                field: 'userId',
                title: 'ID',
                align : 'center',
                valign : 'middle',
                sortable : true,
            }  ,{
                field: 'userName',
                title: '用户名',
                align : 'center',
                valign : 'middle',
                sortable : true,
            },{
                field: 'mobile',
                title: '联系电话',
                align : 'center',
                valign : 'middle',
                sortable : true,
            }
            ,{
                    field: 'wechat',
                    title: '微信',
                    align : 'center',
                    valign : 'middle',
                    sortable : true,
                },{
                    field: 'address',
                    title: '联系地址',
                    align : 'center',
                    valign : 'middle',
                    sortable : true,
                }
                , {
                    field: '',
                    title: '操作',
                    align : 'center',
                    valign : 'middle',
                    sortable : true,
                    formatter: function (value, row, index) {
                        return addProductEditBtHtml(row.id,row.status);
                    }
                }
            ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var param = {
            limit: params.limit,
            page: params.pageNumber - 1,
            userName:' ',
        };
        return param;
    };


    //用于server分页，表格数据量太大的话 不想一次查询所有数据，可以使用server分页查询
    oTableInit.responseHandler = function(res) {
        var resp = {"rows":[], "total":0};
        if (res != null && res.data != null ) {
            resp = {
                "rows" :  res.data.content,
                "total" : res.data.totalElements
            };
        }
        return resp;
    };

    //table加载完成之后
    oTableInit.loadSuccess = function(){

    }

    return oTableInit;
};


$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();



})


/**
 * 操作三个按钮
 * @param id
 * @param status
 * @returns {string}
 */
function addProductEditBtHtml(id,status) {
    var btnHtml = '';
    btnHtml += '	<a style="text-decoration:none" href="javascript:void(0);"  title=';
    if (status == 1) {
        btnHtml += '"启用" onclick="rotation_status_change('+id+','+'0'+','+status+')">';
    }else {
        btnHtml += '"禁用" onclick="rotation_status_change('+id+','+'1'+','+status+')">';
    }
    btnHtml += '		 <i class="Hui-iconfont">&#xe6de;</i>';
    btnHtml += '	</a>';
    btnHtml += '	<a style="text-decoration:none" class="ml-5" href="javascript:void(0);" onclick="item_add('+id+')" title="编辑">';
    btnHtml += '		 <i class="Hui-iconfont">&#xe6df;</i>';
    btnHtml += '	</a>';
    btnHtml += '	<a style="text-decoration:none" class="ml-5" href="javascript:void(0);" onclick="delete_rotation('+id+')" title="删除">';
    btnHtml += '		<i class="Hui-iconfont">&#xe6e2;</i>';
    btnHtml += '	</a>';
    return btnHtml;
}

