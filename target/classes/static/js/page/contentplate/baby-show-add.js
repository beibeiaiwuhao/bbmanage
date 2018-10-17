
var videoObj = [];

$(function () {
    //初始化视频模块
    initVideoView();

    //初始化select 模块
    initGartenAndClassSelect();


    $("#submit").click(function () {

            $.DialogHelper.Loading(true);

            var formData = new FormData($("#form-gartenVideo-add")[0]);

            $.ajax({
                url: "/garten/video/save",
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








function initGartenAndClassSelect() {
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
        }
    });


    //点击切换幼儿园的时候切换幼儿园下面的班级
    $("#garden-info").change(function () {
        $("#class-info").empty();
        $("#class-info").append("<option value=''>--请选择班级--</option>");
        loadClassesInfo($("#garden-info").val());
    })


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







function initVideoView() {
    //添加需要上传视频点击事件
    $('#addVideo').on('click', function(){
        $('#uploadVideos').find('li:first-child input').click();
    });

    //给第一个li绑定事件
    $('#uploadVideos').on('change', 'li:first-child input', function(e){
        //获取当前视频的数量
        var currLength = $('#uploadVideos').find("li").length-1;
        //保存
        $("#countvdo").val(currLength);

        var files = e.target.files;

        if(files.length >= 4 || files.length + parseInt($("#countvdo").val()) > 3){
            $.DialogHelper.Fail('请最多选择3个视频!');
            return;
        }

        for(var i=0; i<files.length; i++){

            var type = this.files[i].type;
            if(type.indexOf("video") == -1){
                $.DialogHelper.Fail("第"+(i+1)+"个文件格式不对!");
                continue;
            }
            var size = this.files[i].size/1024;
            if(size >= 1024 * 30){
                $.DialogHelper.Fail("第"+(i+1)+"个视频请选择文件大小小于30MB!");
                continue;
            }

            if(size <= 0){
                $.DialogHelper.Fail("第"+(i+1)+"个视频请选择显示正确的!");
                continue;
            }

            var url = getObjectURL(this.files[i]);
            if (url) {
                //
                var addLi = '<li style="overflow:visible;">' +
                    '<div><video src="' + url + '" height="150" width="100" preload="preload" controls="controls" title="'+this.files[0].name+'" alt="'+this.files[0].name+'" ></video></div>' +
                    '<span title="删除" class="btnVideoDel"><i class="Hui-iconfont Hui-iconfont-del2" style="float:right;cursor:pointer;" ></i></span>'+
                    '</li>';
                $('#addVideo').closest('ul').append(addLi);
                $('#uploadVideos').append('<li><input type="file" multiple="multiple" accept="video/*"/></li>');

                $("#countvdo").val(parseInt($("#countvdo").val())+1);
            }
            //将保存在第一个li中的视频对象循环添加到数组中
            videoObj.push($('#uploadVideos').find('li:first-child input')[0].files[i]);
        }
        console.log(videoObj);
    });

    //视频删除
    $('#addVideo').closest('ul').on('click','li .btnVideoDel', function(e) {
        //当前视频index
        var no = parseInt($(e.target).closest("li").index());

        $(e.target).closest("li").remove();

        //删除数组中元素
        videoObj.splice(no-1, 1);

        //删除uploadPictures中li
        $('#uploadVideos').find('li').eq(no).remove();

        //设置当前视频数量
        var currentLength = $('#uploadVideos').find("li").length-1;
        $("#countvdo").val(currentLength);
        console.log(videoObj);
        return;
    });
}


//获取选择视频的url
function getObjectURL(file) {
    var url = null ;
    if (window.createObjectURL!=undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}
