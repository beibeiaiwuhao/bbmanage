$(function () {

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

            $("#user-info").empty();
            $("#user-info").append("<option value=''>--请选择负责人--</option>");
        }
    });

    //点击切换幼儿园的时候切换幼儿园下面的班级
    $("#garden-info").change(function () {
        $("#user-info").empty();
        $("#user-info").append("<option value=''>--请选择负责人--</option>");
        loadUserInfo($("#garden-info").val());
    })


    $("#submit").click(function () {
        $.DialogHelper.Loading(true);

        var formData = new FormData($("#form-activity-add")[0]);

        $.ajax({
            url: "/activity/save",
            type: "POST",
            data: formData,
            dataType: "json",
            cache: false,
            processData : false,
            contentType : false,
            async: true, // 异步
            error: function (data)
            {
                console.log(data);
            },
            complete:function () {

            },
            success: function (data)
            {
                console.log(data);
                if (data.code == 200) {
                    parent.$('#activityList').bootstrapTable('refresh');
                    layer_close();
                }
            }
        });
    })


})

function loadUserInfo(id) {
    $.ajax({
        url:'/getTeacherListByGartenId?gartenId='+id,
        type: "GET",
        async: true, // 异步
        success:function (data) {
            console.log(data);
            var userData = data.data;
            for (var i = 0;i<userData.length;i++) {
                $("#user-info").append("<option value='"+userData[i].teacherName+"'>"+userData[i].teacherName+"</option>");
            }
        },
        fail:function () {
        }
    })

}