
$(function () {
    $("#submit").click(function () {
        console.log("开始提交了");

        var formData = new FormData($("#form-student-import")[0]);

        $.ajax({
            url: "/import/student/info",
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
                    parent.$('#studentList').bootstrapTable('refresh');
                    layer_close();
                }
            }
        });
    })
})