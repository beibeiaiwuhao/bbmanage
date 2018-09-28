$(function () {

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

    $("#submit").click(function () {

        if ($("#title").val().length == 0) {
            $.DialogHelper.Fail("请填写公告标题");
            return;
        }

        if ($("#content").val().length == 0) {
            $.DialogHelper.Fail("请填写公告标题");
            return;
        }

        var formData = new FormData();

        $.each($("#form-gardenInfo-add").serializeArray(),function (index,data) {
            formData.append(data.name,data.value)
        });

        $.DialogHelper.Loading(true);

        $.ajax({
            url: "/garten/notice/save",
            type: "POST",
            data: formData,
            dataType: "json",
            cache: false,
            processData : false,
            contentType : false,
            async: true, // 异步
            error: function ()
            {

            },
            complete:function () {

            },
            success: function (data)
            {
                console.log(data);
                if (data.code == 200) {
                    parent.$('#announcementList').bootstrapTable('refresh');
                    layer_close();
                }
            }
        });
    });





})