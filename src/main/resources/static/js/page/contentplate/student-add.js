//学生头像
var chooseFile = new ChooseFile({
    elId: 'chooseFile',
    targetElId: 'target',
    height: 100,
    width: 100,
    acceptType: 'image/png,image/jpg,image/jpeg',
    count: 1,
    size: 5000
});

//家长头像
var chooseFile1 = new ChooseFile({
    elId: 'chooseFile1',
    targetElId: 'target1',
    height: 100,
    width: 100,
    acceptType: 'image/png,image/jpg,image/jpeg',
    count: 1,
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

            var courseData = data.data.courseInfo;
            $("#course-info").append("<option value=''>--请选择课程--</option>");
            console.log(courseData.length);
            for (var i = 0;i < courseData.length;i++) {
                $("#course-info").append("<option value='"+courseData[i].courseName+"'>"+courseData[i].courseName+"</option>");
            }
            $("#class-info").empty();
            $("#class-info").append("<option value=''>--请选择班级--</option>");
        }
    });


    //点击切换幼儿园的时候切换幼儿园下面的班级
    $("#garden-info").change(function () {
        $("#class-info").empty();
        $("#class-info").append("<option value=''>--请选择班级--</option>");
        loadClassesInfo($("#garden-info").val());
    })


    $("#submit").click(function () {

        console.log($("#class-info").find("option:selected").text());

        // if ( chooseFile.getFiles().length == 0) {
        //     $.DialogHelper.Fail("请选择图片");
        //     return;
        // }
        //
        // if ($("input[name='gender']:checked").val() == null && 'undefined' == $("input[name='gender']:checked").val()) {
        //     $.DialogHelper.Fail("请选择性别");
        //     return;
        // }

        var formData = new FormData();
        //学生头像
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
                formData.append('studentImage',imgFileAll[i]);
            }
        }
        if (imgUrl != '') {
            formData.append("studentImage",imgUrl);
        }

        //家长头像
        var imgFileAll1 = chooseFile1.getFiles();
        // 图片
        var imgUrl1 = '';
        for(var i in imgFileAll1){
            if (isString(imgFileAll1[i])) {
                if (imgUrl1 == '') {
                    imgUrl1 = imgFileAll1[i];
                }else {
                    imgUrl1 = imgUrl1 + ',' + imgFileAll1[i];
                }
            }else {
                formData.append('parentImage',imgFileAll1[i]);
            }
        }
        if (imgUrl != '') {
            formData.append("parentImage",imgUrl);
        }

        $.each($("#form-student-add").serializeArray(),function (index,data) {
            console.log("key值为"+data.name+"value值为"+data.value);
            formData.append(data.name,data.value)
        });

        // formData.append("isExist",$("input[name='isExist']:checked").val());
        // formData.append("studentGender",$("input[name='studentGender']:checked").val());

        $.DialogHelper.Loading(true);

        console.log("开始提交了");
        $.ajax({
            url: "/student/save",
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




function showExistInfo(isShow) {
    //切换是否存在家长信息
    if(isShow==0) {
        $("#parents-search").css("display","none");
    }else {
        $("#parents-search").css("display","block");
    }
    // formData.append("gender",$("input[name='gender']:checked").val());
}


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
            console.log(classesData.length);
            for (var i = 0;i < classesData.length;i++) {
                $("#class-info").append("<option value='"+classesData[i].id+"'>"+classesData[i].className+"</option>");
            }

        }
    });
}




