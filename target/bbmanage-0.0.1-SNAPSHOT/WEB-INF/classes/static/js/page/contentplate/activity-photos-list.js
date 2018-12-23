function activity_photo_add(title,url) {
    layer_show(title,url+'?id='+$("#activityId").val(),'','');
}

$(function () {

    console.log("测试activity相册");
    var data = {
      "activityId":$("#activityId").val()
    };

    $.AjaxHelper.PostRequest("/getActivityPhotoByActivityId?rdm="+Math.random(),data,function (b,data) {
        if (b) {
            console.log(data.data);
            appendContentHtml(data);
            $(".portfolio-area li").Huihover();
        }
    });


})


function appendContentHtml(data) {

    var contentHtml = '';
    for (var i = 0;  i< data.data.length; i++) {
        var contentData = data.data[i];

        contentHtml += '<div><span>'+contentData["time"]+'</span>';
        contentHtml += '<ul class="cl portfolio-area" >';
        var photoList = contentData.photoList;
        for (var j = 0;  j< photoList.length; j++) {
            var photoData = photoList[j];
            contentHtml += ' <li class="item"> ';
            contentHtml += ' <div class="portfoliobox"> ';
            contentHtml += ' <input class="checkbox" name="" type="checkbox" value="" /> ';
            contentHtml += ' <div class="picbox"><a href="'+photoData.fileUrl+'" data-lightbox="gallery" data-title="'+'活动名称：'+photoData.activityName+'">';
            contentHtml += ' <img  src="'+photoData.fileUrl+'" /> ';
            contentHtml += ' </a></div>  ';
            // contentHtml += ' <div class="textbox">'+'活动名称：'+photoData.activityName+' </div> ';
            contentHtml += ' </div> ';
            contentHtml += ' </li> ';
        }
        contentHtml += '</ul>';
        contentHtml += '</div>';
    }
    $("#photo-container").append(contentHtml);


}

