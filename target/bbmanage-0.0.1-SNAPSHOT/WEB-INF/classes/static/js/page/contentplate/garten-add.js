var chooseFile = new ChooseFile({
    elId: 'chooseFile',
    targetElId: 'target',
    height: 100,
    width: 100,
    acceptType: 'image/png,image/jpg,image/jpeg',
    count: 6,
    size: 5000
});

//地区的数据
var areaData;

$(function () {

    $.ajax({
        url: "/static/json/zh_map_json.json",
        type: "GET",
        async: true, // 异步
        error: function ()
        {

        },
        complete:function () {

        },
        success: function (data)
        {
            areaData = data;
           // console.log(data.length);
            for (var i = 0;i < data.length;i++) {
                $("#province").append("<option value='"+data[i].region+"'>"+data[i].region+"</option>");
            }
        }
    });

    $("#province").change(function () {
        console.log($("#province").val());

        $("#area").empty();
        $("#area").append("<option value=''>区</option>");

        for (var i = 0;i < areaData.length;i++) {

            if (areaData[i].region == $("#province").val()) {
                $("#city").empty();
                $("#city").append("<option value=''>市</option>");
                for (var j = 0; j < areaData[i].regionEntitys.length;j++) {
                    $("#city").append("<option value='"+areaData[i].regionEntitys[j].region+"'>"+areaData[i].regionEntitys[j].region+"</option>");
                }
            }
        }
    });
    $("#city").change(function () {
        console.log($("#city").val());
        for (var i = 0;i < areaData.length;i++) {
            if (areaData[i].region == $("#province").val()) {
                for (var j = 0; j < areaData[i].regionEntitys.length;j++) {
                    if (areaData[i].regionEntitys[j].region == $("#city").val()) {
                        $("#area").empty();
                        $("#area").append("<option value=''>区</option>");
                        for (var k = 0; k < areaData[i].regionEntitys[j].regionEntitys.length;k++) {
                            $("#area").append("<option value='"+areaData[i].regionEntitys[j].regionEntitys[k].region+"'>"+areaData[i].regionEntitys[j].regionEntitys[k].region+"</option>");
                        }
                    }


                }
            }
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

        $.each($("#form-gardenInfo-add").serializeArray(),function (index,data) {
            formData.append(data.name,data.value)
        });

        $.DialogHelper.Loading(true);

        $.ajax({
            url: "/garden/save",
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
                    parent.$('#gardenList').bootstrapTable('refresh');
                    layer_close();
                }
            }
        });
    });



})