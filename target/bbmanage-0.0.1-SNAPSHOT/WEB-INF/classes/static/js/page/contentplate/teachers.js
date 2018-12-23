function teacher_add(title,url) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

function student_import(title,url) {
    layer_show(title,url,'','');
}


/**
 * 选项卡的下标
 */
var tabIndex = 0;

/**
 * 老师列表数据展示
 * @type {number}
 */
var TableInit = 0;
TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#teacherList').bootstrapTable('destroy');
        $('#teacherList').bootstrapTable({
            url: "/teacher/list?&rdm=" + Math.random(), //请求后台的URL（*）
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
            },{
                field: 'teacherName',
                title: '老师名字',
                align : 'center',
                valign : 'middle',
                sortable : true,
            }  ,{
                field: 'teacherClasses',
                title: '所属班级',
                align : 'center',
                valign : 'middle',
                sortable : true,
            },{
                field: 'positionName',
                title: '上岗职位',
                align : 'center',
                valign : 'middle',
                sortable : true,
            },{
                    field: 'gender',
                    title: '性别',
                    align : 'center',
                    valign : 'middle',
                    sortable : true,
                    formatter: function (value, row, index) {
                        var html = "";
                        if(value == 1) {
                            html = '<span >男</span>';
                        }else if(value == 0) {
                            html = '<span >女</span>';
                        }
                        return html;
                    }
                },{
                field: 'avatarImgUrl',
                title: '老师头像',
                align : 'center',
                valign : 'middle',
                sortable : true,
                formatter:function (value, row, index) {
                    return showteacherAvatarImg(row);
                }
            },{
                field: 'wechat',
                title: '老师微信',
                align : 'center',
                valign : 'middle',
                sortable : true,

            },{
                field: 'teacherMobile',
                title: '老师联系方式',
                align : 'center',
                valign : 'middle',
                sortable : true,

            },{
                field: 'teacherDesc',
                title: '老师特长',
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
                    if(value == 1) {
                        html = '<span class="label label-warning">离职</span>';
                    }else if(value == 0) {
                        html = '<span class="label label-warning">在职</span>';
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
            courseId:$("#course-info").val()!=null&&'undefined' !=$("#course-info").val()?$("#course-info").val():0,
            gartenId:$("#garden-info").val()!=null&&'undefined' !=$("#garden-info").val()?$("#garden-info").val():0,
            classId:$("#class-info").val()!=null&&'undefined' !=$("#class-info").val()?$("#class-info").val():0
        };
        console.log(param);
        return param;
    };

    // ($("#gartenId").val() !=null&&'undefined' != $("#gartenId").val())?$("#gartenId"):0


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

/**
 * 展示老师头像
 * @param row
 *
 */
function showteacherAvatarImg(row) {
    var imghtml = '';
    imghtml = '<div class="portfoliobox">';
    imghtml +=  '<div class="picbox">';
    imghtml +=  '<a href="'+row.avatarImgUrl+'" data-lightbox="gallery" data-title="'+row.teacherName+'">';
    imghtml +=  '<img width="100" height="100" class="picture-thumb" src="'+row.avatarImgUrl+'"  />';
    imghtml += '</a></div></div>';

    return imghtml;
}

/**
 * 学生列表的数据展示
 * @type {number}
 */
var TableInit1 = 0;
TableInit1 = function () {
    var oTableInit1 = new Object();
    //初始化Table
    oTableInit1.Init = function () {
        $('#studentList').bootstrapTable('destroy');
        $('#studentList').bootstrapTable({
            url: "/student/list?&rdm=" + Math.random(), //请求后台的URL（*）
            method: 'GET',                     //请求方式（*）
            dataType:'json',
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                    //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit1.queryParams,//传递参数（*）
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
            responseHandler: oTableInit1.responseHandler,
            onLoadSuccess:oTableInit1.loadSuccess,
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
            },{
                field: 'studentName',
                title: '学生名字',
                align : 'center',
                valign : 'middle',
                sortable : true,
            }  ,{
                field: 'studentAge',
                title: '学生年龄',
                align : 'center',
                valign : 'middle',
                sortable : true,
            },{
                field: 'userName',
                title: '家长名字',
                align : 'center',
                valign : 'middle',
                sortable : true,
            },{
                field: 'studentGender',
                title: '性别',
                align : 'center',
                valign : 'middle',
                sortable : true,
                formatter: function (value, row, index) {
                    var html = "";
                    if(value == 1) {
                        html = '<span >男</span>';
                    }else if(value == 0) {
                        html = '<span >女</span>';
                    }
                    return html;
                }
            },{
                field: 'avatarImgUrl',
                title: '学生头像',
                align : 'center',
                valign : 'middle',
                sortable : true,
                formatter:function (value, row, index) {
                    return showteacherAvatarImg(row);
                }
            },{
                field: 'studentDesc',
                title: '学生特长',
                align : 'center',
                valign : 'middle',
                sortable : true,

            },{
                field: 'courseName',
                title: '报选课程',
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
                    if(value == 1) {
                        html = '<span class="label label-warning">在校</span>';
                    }else if(value == 0) {
                        html = '<span class="label label-warning">毕业</span>';
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
    oTableInit1.queryParams = function (params) {
        var param = {
            size: params.limit,
            page: params.pageNumber - 1,
            courseId:$("#course-info").val()!=null&&'undefined' !=$("#course-info").val()?$("#course-info").val():0,
            gartenId:$("#garden-info").val()!=null&&'undefined' !=$("#garden-info").val()?$("#garden-info").val():0,
            classId:$("#class-info").val()!=null&&'undefined' !=$("#class-info").val()?$("#class-info").val():0
        };
        console.log(param);
        return param;
    };

    // ($("#gartenId").val() !=null&&'undefined' != $("#gartenId").val())?$("#gartenId"):0


    //用于server分页，表格数据量太大的话 不想一次查询所有数据，可以使用server分页查询
    oTableInit1.responseHandler = function(res) {
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
    oTableInit1.loadSuccess = function(){
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


    return oTableInit1;
};

$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    var oTable1 = new TableInit1();
    oTable1.Init();

    //初始化tabCon
    $("#tab-system").Huitab({
        index: 0
    });

    //初始化页面
    $.ajax({
        url: "/teacher/initAddGartenTeacherData",
        type: "GET",
        async: true, // 异步
        error: function ()
        {

        },
        complete:function () {

        },
        success: function (data)
        {
            var gardenData = data.data.gartenInfo;
            $("#garden-info").append("<option value=''>--请选择幼儿园--</option>");
            for (var i = 0;i < gardenData.length;i++) {
                $("#garden-info").append("<option value='"+gardenData[i].id+"'>"+gardenData[i].gardenName+"</option>");
            }

            var courseData = data.data.courseInfo;
            $("#course-info").append("<option value=''>--请选择课程--</option>");
            console.log(courseData.length);
            for (var i = 0;i < courseData.length;i++) {
                $("#course-info").append("<option value='"+courseData[i].id+"'>"+courseData[i].courseName+"</option>");
            }
            $("#class-info").empty();
            $("#class-info").append("<option value=''>--请选择班级--</option>");
        }
    });

    //点击切换幼儿园的时候切换幼儿园下面的班级
    $("#garden-info").change(function () {
        $("#class-info").empty();
        $("#class-info").append("<option value=''>--请选择班级--</option>");
        loadClassesInfo($("#garden-info").val());

    })

    //查询
    $("#serchInput").click(function() {
        // $.DialogHelper.Loading(true);
        if (tabIndex == 0) {
            $('#teacherList').bootstrapTable('refresh');
        }else if (tabIndex == 1) {
            $('#studentList').bootstrapTable('refresh');
        }
    });
});



/**
 * 切换幼儿园的时候，更新班级
 * @param gardenId
 */
function loadClassesInfo(gardenId) {
    $.ajax({
        url: "/classes/getGartenClass",
        type: "GET",
        data:{
            gartenId: gardenId
        },
        async: true, // 异步
        error: function ()
        {

        },
        complete:function () {

        },
        success: function (data)
        {
            var classesData = data.data;
            console.log(classesData.length);
            for (var i = 0;i < classesData.length;i++) {
                $("#class-info").append("<option value='"+classesData[i].id+"'>"+classesData[i].className+"</option>");
            }

        }
    });
}


function getIndex(obj) {
    var index = $(obj).attr("value");
    tabIndex = index;
    if (index == 0) {
        $("#screenLabel").text("所教课程");
    }else if (index == 1) {
        $("#screenLabel").text("所上课程");
    }
}












