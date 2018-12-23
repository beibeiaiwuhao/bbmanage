$(function () {

    //初始化幼儿园列表下拉框
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

            var clickDate = getRequest("clickDate");
            if (clickDate != null && clickDate.length != 0) {
                $("#recipeDate").val(clickDate);
            }

            //修改食谱 初始化页面
            if ($("#id").val() != null && $("#id").val().length != 0) {
                $.ajax({
                    url:"/farm/findRecipeById",
                    type:"POST",
                    data:{
                        id:$("#id").val()
                    },
                    async:true,
                    success:function (data) {
                        console.log($("#garden-info option[value='"+data.data.gartenId+"']"));
                        $("#garden-info option[value='"+data.data.gartenId+"']").attr('selected',true);
                        $("#recipeDate").val(data.data.recipeDate);
                        $("#mealName").val(data.data.mealName);
                        $("#mealDesc").val(data.data.mealDesc);
                        $("#delete").css("display","inline");

                    }
                })
            }

        }
    });






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

                    parent.location.reload();
                    layer_close();
                }


            }
        });
    });


    $("#delete").click(function () {
        layer.confirm("确定删除吗？",function() {
            $.ajax({
                    url:"/farm/deleteRecipedById",
                    type:"POST",
                    data:{
                        id:$("#id").val()
                    },
                    success:function (data) {
                        if (data.code = 200) {
                            parent.location.reload();
                            layer.msg("数据删除成功",{icon: 5,time:1000});
                            layer_close();
                        }
                    }
                }
            )
        })
    });
})

function getRequest(strParame) {
    var args = new Object();
    var query = location.search.substring(1);

    var pairs = query.split("&"); // Break at ampersand
    for (var i = 0; i < pairs.length; i++) {
        var pos = pairs[i].indexOf('=');
        if (pos == -1) continue;
        var argname = pairs[i].substring(0, pos);
        var value = pairs[i].substring(pos + 1);
        value = decodeURIComponent(value);
        args[argname] = value;
    }
    return args[strParame];
}