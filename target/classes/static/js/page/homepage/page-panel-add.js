var chooseFile = new ChooseFile({
    elId: 'chooseFile',
    targetElId: 'target',
    height: 100,
    width: 100,
    acceptType: 'image/png,image/jpg,image/jpeg',
    count: 1,
    size: 5000
});

$(function () {

    $('#mySwitch').on('switch-change', function (e, data) {
        if(data.value==true){
            $("#status").val(0);
        }else{
            $("#status").val(1);
        }
    });

    //这个是页面展示的时候，展示的内容
    if ($("#id").val()!= null && "" != $("#id").val()) {
        var data = {"id":$("#id").val()};
        $.ajax({
            url: "/page/panel/list",
            type: "POST",
            data: data,
            dataType: "json",
            async: true, // 异步
            error: function ()
            {

            },
            complete:function () {

            },
            success: function (data)
            {
                var model = data.data[0];
                var images = model.pictureUrl.split(",");
                $("#pageName").val(model.pageName);
                $("#sort").val(model.sort);
                $("#remark").val(model.remark);
                $("#status").val(model.status);
                if (model.status != 0) {
                    $('#mySwitch').bootstrapSwitch('toggleState',true);
                }
                chooseFile.show(images);
                $("#submit").text("修改提交");

            }
        });
    }

    $("#submit").click(function () {

        if ( chooseFile.getFiles().length == 0) {
            $.DialogHelper.Fail("请选择图片");
            return;
        }

        var formData = new FormData();

        var imgFileAll = chooseFile.getFiles();
        // 图片
        var imgUrl = '';
        for(var i in imgFileAll){
            if (isString(imgFileAll[i])) {
                if (imgUrl == '') {
                    imgUrl = imgFileAll[i];
                }else {
                    imgUrl = imgUrl + ',' + imgFileAll[i];
                }
            }else {
                formData.append('images',imgFileAll[i]);
            }
        }
        if (imgUrl != '') {
            formData.append("imgUrl",imgUrl);
        }

        $.each($("#form-panelContent-add").serializeArray(),function (index,data) {
            formData.append(data.name,data.value)
        });

        $.DialogHelper.Loading(true);

        $.ajax({
            url: "/page/panel/add",
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
                    parent.location.reload();
                }
            }
        });
    });



});
