$(function () {
    //页面加载数据
    if ($("#classId").val()!= null && "" != $("#classId").val()) {
        var data = {"classId": $("#classId").val()};

        $.AjaxHelper.PostRequest("/getClassPhotosByClassId?rdm="+Math.random(),data,function (b,data) {
            console.log("AjaxHelper");
            if (b) {
                console.log(data.data);
                appendContentHtml(data);
                $(".portfolio-area li").Huihover();
            }
        })
    }

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
            contentHtml += ' <div class="picbox"><a href="'+photoData.photoUrl+'" data-lightbox="gallery" data-title="'+'上传人：'+photoData.creator+'">';
            contentHtml += ' <img  src="'+photoData.photoUrl+'" /> ';
            contentHtml += ' </a></div>  ';
            contentHtml += ' <div class="textbox">'+'上传人：'+photoData.creator+' </div> ';
            contentHtml += ' </div> ';
            contentHtml += ' </li> ';
        }
        contentHtml += '</ul>';
        contentHtml += '</div>';
    }
    $("#photo-container").append(contentHtml);


}



