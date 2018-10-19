

var TableInit;
TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#videoList').bootstrapTable('destroy');
        $('#videoList').bootstrapTable({
            url: "/garten/video/list?&rdm=" + Math.random(), //请求后台的URL（*）
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
                field: 'id',
                title: 'ID',
                align : 'center',
                valign : 'middle',
                sortable : true,
                // formatter: function (value, row, index) {
                //     return '<span class="label label-warning arrowed arrowed-right">' + row.id + '</span>';
                // }
            }  ,{
                field: 'gartenName',
                title: '园所名字',
                align : 'center',
                valign : 'middle',
                sortable : true,
            },{
                field: 'className',
                title: '班级名字',
                align : 'center',
                valign : 'middle',
                sortable : true,
            },{
                field: 'videoName',
                title: '视频名字',
                align : 'center',
                valign : 'middle',
                sortable : true,
            }
                ,{
                    field: 'videoUrl',
                    title: '视频',
                    align : 'center',
                    valign : 'middle',
                    sortable : true,
                    formatter:function (value,row,index) {
                        return '<video controls="controls" style="height: 150px;width: 100px;" src="'+value+'"></video>';
                    }
                },{
                    field: 'videoDesc',
                    title: '视频描述',
                    align : 'center',
                    valign : 'middle',
                    sortable : true,
                }
                ,{
                    field: 'createTime',
                    title: '创建日期',
                    align : 'center',
                    valign : 'middle',
                    sortable : true,
                },{
                    field: 'status',
                    title: '状态',
                    align : 'center',
                    valign : 'middle',
                    sortable : true,
                    formatter: function (value, row, index) {
                        var html = "";
                        if(value == 0) {
                            html = '<span class="label label-warning">启用</span>';
                        }else if(value == 1) {
                            html = '<span class="label label-warning">禁用</span>';
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
            gartenId:$("#garden-info").val()!=null&&'undefined' !=$("#garden-info").val()?$("#garden-info").val():0,
            classId:$("#class-info").val()!=null&&'undefined' !=$("#class-info").val()?$("#class-info").val():0
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


$(function () {




})

function testWebsocket() {
    $.AjaxHelper.PostRequest("/test/easyWEbSocket",{"msg":"test"},function (b,data) {

        if (b) {
            var socket;
            if(typeof(WebSocket) == "undefined") {
                console.log("您的浏览器不支持WebSocket");
            }else {
                console.log("您的浏览器支持WebSocket");

                //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
                //socket = new WebSocket("ws://localhost:9094/starManager/websocket/张三")
                socket = new WebSocket("ws://localhost:8082/websocket");
                //打开事件
                socket.onopen = function () {
                    console.log("Socket 已打开");
                    //socket.send("这是来自客户端的消息" + location.href + new Date());
                };
                //获得消息事件
                socket.onmessage = function (msg) {
                    console.log(msg.data);
                    //发现消息进入    调后台获取
                    console.log("发现消息进入    调后台获取")
                };
                //关闭事件
                socket.onclose = function () {
                    console.log("Socket已关闭");
                };
                //发生了错误事件
                socket.onerror = function () {
                    alert("Socket发生了错误");
                }
                $(window).unload(function () {
                    socket.close();
                });

            }

        }

    })

}