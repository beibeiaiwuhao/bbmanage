var chooseFile = new ChooseFile({
    elId: 'chooseFile',
    targetElId: 'target',
    height: 100,
    width: 100,
    acceptType: 'image/png,image/jpg,image/jpeg',
    count: 10,
    size: 5000
});

$(function () {
    $("#submit").click(function () {
        $.DialogHelper.Loading(true);

        var formData = new FormData($("#form-activityPhoto-add")[0]);
        // 图片
        var imgFileAll = chooseFile.getFiles();
        for(var i in imgFileAll){
            formData.append('imgFile',imgFileAll[i]);
        }

        $.ajax({
            url: "/activity/photo/save",
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
                    parent.$('#videoList').bootstrapTable('refresh');
                    layer_close();
                }
            }
        });
    })

})