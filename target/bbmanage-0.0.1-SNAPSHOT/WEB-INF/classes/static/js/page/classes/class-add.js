$(function () {

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
        }
    });



    $("#submit").click(function () {


        $.DialogHelper.Loading(true);

        var formData = new FormData($("#form-gardenClass-add")[0]);

        $.ajax({
            url: "/classes/saveGartenClass",
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
                    // parent.$('#videoList').bootstrapTable('refresh');
                    layer_close();
                }
            }
        });

    })


})