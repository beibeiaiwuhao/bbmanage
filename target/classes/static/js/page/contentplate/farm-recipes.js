function recipes_add(title,url) {
    layer_show(title,url,'','');
}


$(function () {
    var initialLocaleCode = 'zh-cn';

    console.log("开始加载了");

    $('#calendar').fullCalendar({
        buttonText: {
            prev: '',
            next: ''
        },
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,basicWeek,basicDay'
        },
        locale: initialLocaleCode,
        buttonIcons: true, // show the prev/next text
        // weekNumbers: true,
        navLinks: true, // can click day/week names to navigate views
        editable: false,
        contentHeight:800,
        eventLimit: true, // allow "more" link when too many events
        timezone:'local',
        // timeFormat: 'H(:mm)',
        views:{
            agenda:{
                eventLimit:1
            }
        },
        events: function(start,end, timezone,callback) {
          $.ajax({
              url: '/farm/recipe/list',
              type:'POST',
              error:function () {
                console.log("加载园所食谱失败");

                },
              success:function (data) {
                  var events = [];
                  for (var i = 0; i < data.data.length;i++) {
                    var recipe = data.data[i];
                    var title = recipe.mealName + '\n' + recipe.mealDesc;
                    var start = new Date(Date.parse(recipe.recipeDate));
                    var end = new Date(new Date().setTime(start.getTime()+1000*60*30));
                    events.push({
                        title: title,
                        start: start,
                        end:end,
                        id:recipe.id,
                        allDay:false,
                    });
                  }
                  callback(events);
                  }
          });
        },
        drop: function(date, allDay) { // this function is called when something is dropped

            // retrieve the dropped element's stored Event Object
            var originalEventObject = $(this).data('eventObject');
            var $extraEventClass = $(this).attr('data-class');


            // we need to copy it, so that multiple events don't have a reference to the same object
            var copiedEventObject = $.extend({}, originalEventObject);

            // assign it the date that was reported
            copiedEventObject.start = date;
            copiedEventObject.allDay = allDay;
            if($extraEventClass) copiedEventObject['className'] = [$extraEventClass];

            // render the event on the calendar
            // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
            $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

            // is the "remove after drop" checkbox checked?
            if ($('#drop-remove').is(':checked')) {
                // if so, remove the element from the "Draggable Events" list
                $(this).remove();
            }

        },
        selectable: false,
        selectHelper: false,
        eventClick: function(event, jsEvent, view) {
            recipes_add("修改食谱",'/management/contentplate/farm/recipes/add.html?id='+event.id);
            console.log("点击了eventClick"+event.title);

        },
        dayClick: function(date, jsEvent, view) {
            recipes_add('添加食谱','/management/contentplate/farm/recipes/add.html?clickDate='+dateToString(date));
            console.log("点击了dayClick");
            console.log(dateToString(date));
        },
        eventMouseover:function (event, jsEvent, view) {
            var view1 = $('#calendar').fullCalendar('getView');
            console.log("The view's title is " + view1.name);
            if(view1.name == "month") {
                showEventDetail(view,"mydiv1");
                $("#showDate").html(dateToString(event.start)+'-'+dateToString(event.end));
                $("#showContent").html(event.title);
                // console.log("鼠标放置的事件是"+event.title+event.id);
                console.log("鼠标划过");
            }

        },
        eventMouseout:function (event, jsEvent, view) {
            var view1 = $('#calendar').fullCalendar('getView');
            console.log("The view's title is " + view1.name);
            if(view1.name == "month") {
                hide(view,"mydiv1");
                console.log("鼠标离开");
            }
        }
    });

    // build the locale selector's options
    $.each($.fullCalendar.locales, function(localeCode) {
        $('#locale-selector').append(
            $('<option/>')
                .attr('value', localeCode)
                .prop('selected', localeCode == initialLocaleCode)
                .text(localeCode)
        );
    });

    // when the selected option changes, dynamically change the calendar option
    $('#locale-selector').on('change', function() {
        if (this.value) {
            $('#calendar').fullCalendar('option', 'locale', this.value);
        }
    });




})

function dateToString(date){
    console.log(date.unix());
    var date = new Date(date);
    var year = date.getFullYear();
    var month =(date.getMonth() + 1).toString();
    var day = (date.getDate()).toString();
    var hour = (date.getHours()).toString();
    var minute = (date.getMinutes()).toString();
    var second = (date.getSeconds()).toString();

    if (month.length == 1) {
        month = "0" + month;
    }
    if (day.length == 1) {
        day = "0" + day;
    }
    if (hour.length == 1) {
        hour = "0" + hour;
    }
    if (minute.length == 1) {
        minute = "0" + minute;
    }
    if (second.length == 1) {
        second = "0" + second;
    }


    var dateTime = year + "-" + month + "-" + day+' '+hour+":"+minute+":"+second;
    return dateTime;
}

function showEventDetail(obj,id) {
    var objDiv =  $("#mydiv1");
    $(objDiv).css("display","block");
    $(objDiv).css("left", event.pageX+20);
    // console.log("移动距离"+event.pageX);
    // console.log("移动距离"+event.clientX);
    $(objDiv).css("top", event.pageY );
}

function hide(obj,id) {

    var objDiv = $("#mydiv1");
    $(objDiv).css("display", "none");

}
