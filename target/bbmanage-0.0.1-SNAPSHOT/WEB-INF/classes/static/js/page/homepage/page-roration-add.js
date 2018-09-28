var chooseFile = new ChooseFile({
    elId: 'chooseFile',
    targetElId: 'target',
    height: 100,
    width: 100,
    acceptType: 'image/png,image/jpg,image/jpeg',
    count: 6,
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
            url: "/panelcontent/save",
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
                    parent.$('#rotationList').bootstrapTable('refresh');
                    layer_close();
                }
            }
        });
    });
});











