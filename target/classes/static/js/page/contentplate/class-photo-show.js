$(function () {
    //页面加载数据
    if ($("#id").val()!= null && "" != $("#id").val()) {
        var data = {"gartenId": $("#id").val()};
        $.ajax({
            url:"/getGartenClassPhotosCountByGartenId",
            type:"POST",
            async: true, // 异步
            data:data,
            error:function(){

            },
            success:function(data) {
                console.log(data.data);
                appendContentHtml(data);
            }
        })
    }
})


//盒子里面的数据填充
function appendContentHtml(data) {
    var contentDate = data.data;
    var contentHtml = '';
    for (var i = 0;  i < contentDate.length ; i++) {
        var tmpName = "'"+contentDate[i].className+"'";
        contentHtml += ' <div onclick="classPhotoDetail('+tmpName+','+contentDate[i].classId+')" class="box" id="box" > <img src="'+contentDate[i].photoUrl+'" /> ';
        contentHtml += ' <span>'+contentDate[i].className+'('+contentDate[i].imgCount+')'+'</span>  </div>';
    }
    $("#class-container").append(contentHtml);

}


function classPhotoDetail(className,classId) {
    picture_edit('相册','/management/contentplate/class/photoList/show?classId='+classId,'');
}


/*图片展示界面*/
function picture_edit(title,url,id) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);

}


