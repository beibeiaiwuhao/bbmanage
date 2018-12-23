
var TableInit;
TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#announcementList').bootstrapTable('destroy');
        $('#announcementList').bootstrapTable({
            url: "/garten/notice/list?&rdm=" + Math.random(), //请求后台的URL（*）
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
            columns: [{
                field: '',
                title: '<input id="allSelect"  type="checkbox" value="" />',
                align : 'center',
                valign : 'middle',
                sortable : true,
                formatter: function (value, row, index) {
                    return '<input name="isSelect" type="checkbox" value="'+row.id+'" />';
                }
            }, {
                field: 'noticeId',
                title: 'ID',
                align : 'center',
                valign : 'middle',
                sortable : true,
                // formatter: function (value, row, index) {
                //     return '<span class="label label-warning arrowed arrowed-right">' + row.id + '</span>';
                // }gardenName
            },{
                field: 'gardenName',
                title: '所属幼儿园',
                align : 'center',
                valign : 'middle',
                sortable : true,
            }  ,{
                field: 'title',
                title: '公告标题',
                align : 'center',
                valign : 'middle',
                sortable : true,
            },{
                field: 'content',
                title: '公告内容',
                align : 'center',
                valign : 'middle',
                sortable : true,

            },{
                field: 'createTime',
                title: '创建日期',
                align : 'center',
                valign : 'middle',
                sortable : true,

            },{
                field: 'publicStatus',
                title: '状态',
                align : 'center',
                valign : 'middle',
                sortable : true,
                formatter: function (value, row, index) {
                    var html = "";
                    if(value == 1) {
                        html = '<span class="label label-warning">发布</span>';
                    }else if(value == 1) {
                        html = '<span class="label label-warning">未发布</span>';
                    }
                    return html;
                }
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
            size: params.limit,
            page: params.pageNumber - 1,
            minDate:$("#logmin").val(),
            maxDate:$("#logmax").val(),
            gartenId:($("#gartenId").val() !=null&&'undefined' != $("#gartenId").val())?$("#gartenId"):0
        };
        console.log(param);
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
        //全选/全不选
        $("#allSelect").on('change', function(){
            var checked = $(this).is(':checked');
            $("input[name='isSelect']").prop("checked",checked);//全选
            console.log($("input[name='isSelect']"));
        });

        //是否需要全选
        $("input[name='isSelect']").on('change', function(){
            var  isNeedChecked = $("input[name='isSelect']:checked").length == $("input[name='isSelect']").length;
            $("#allSelect").prop("checked",isNeedChecked);
        });
    }


    return oTableInit;
};

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




$(function(){

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //查询
    $("#serchInput").click(function() {
        // $.DialogHelper.Loading(true);
        $('#onlineBookList').bootstrapTable('refresh');
    });


    $.ajax({
        url: "/garden/allInfo",
        type: "GET",
        async: true, // 异步
        error: function ()
        {

        },
        complete:function () {

        },
        success: function (data)
        {
            var gardenData = data.data;
            console.log(gardenData.length);
            for (var i = 0;i < gardenData.length;i++) {
                $("#garden-info").append("<option value='"+gardenData[i].id+"'>"+gardenData[i].gardenName+"</option>");
            }
        }
    });

});


function announcement_add(title,url) {
    layer_show(title,url,'',450);
}

