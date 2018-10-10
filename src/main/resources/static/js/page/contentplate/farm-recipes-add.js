$(function () {
    $("#submit").click(function () {

        var formData = new FormData();


        $.each($("#form-recipe-add").serializeArray(),function (index,data) {
            formData.append(data.name,data.value)
        });

        $.DialogHelper.Loading(true);

        console.log("开始提交了");
        $.ajax({
            url: "/farm/recipe/save",
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
                    parent.$('#studentList').bootstrapTable('refresh');
                    layer_close();
                }
            }
        });
    });
})