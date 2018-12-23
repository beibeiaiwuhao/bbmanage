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

            $("#class-info").empty();
            $("#class-info").append("<option value=''>--请选择班级--</option>");

            $("#user-info").empty();
            $("#user-info").append("<option value=''>--请选择上传人--</option>");
        }
    });

    //点击切换幼儿园的时候切换幼儿园下面的班级
    $("#garden-info").change(function () {
        $("#class-info").empty();
        $("#class-info").append("<option value=''>--请选择班级--</option>");
        loadClassesInfo($("#garden-info").val());
    })
    
    $("#class-info").change(function () {
        $("#user-info").empty();
        $("#user-info").append("<option value=''>--请选择上传人--</option>");
        loadTeacherInfo($("#class-info").val());
    })


    $("#submit").click(function () {
        $.DialogHelper.Loading(true);

        var formData = new FormData($("#form-gartenPhoto-add")[0]);
        // 图片
        var imgFileAll = chooseFile.getFiles();
        var imgUrl = '';
        for(var i in imgFileAll){
            if (isString(imgFileAll[i])) {
                if (imgUrl == '') {
                    imgUrl = imgFileAll[i];
                }else {
                    imgUrl = imgUrl + ',' + imgFileAll[i];
                }
            }else {
                formData.append('imgFile',imgFileAll[i]);
            }
        }
        if (imgUrl != '') {
            formData.append("imgFile",imgUrl);
        }
        $.ajax({
            url: "/garten/photo/save",
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
            // console.log(classesData.length);
            for (var i = 0;i < classesData.length;i++) {
                $("#class-info").append("<option value='"+classesData[i].id+"'>"+classesData[i].className+"</option>");
            }

        }
    });
}

function loadTeacherInfo(classId) {

    $.ajax({
        url:"/getTeacherListByClassId",
        type:'GET',
        data:{
            classId:classId
        },
        error:function () {
            },
        complete:function () {

        },
        success:function (data) {
            var userData = data.data;
            for (var i = 0;i < userData.length;i++) {
                $("#user-info").append("<option value='"+userData[i].teacherName+"'>"+userData[i].teacherName+"</option>");
            }
        }

    })

}

