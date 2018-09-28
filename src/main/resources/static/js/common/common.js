(function($) {
	/**
	 * 系统通用方法
	 * 
	 * eg: $.SystemHelper.UrlRedirect()
	 *     $.SystemHelper.ReloadPage()
	 *     $.SystemHelper.ReloadParentPage()
	 *     $.SystemHelper.GetProjectName()
	 *     $.SystemHelper.GetLocalHostPath()
	 *     $.SystemHelper.IsNullOrEmpty()
	 *     $.SystemHelper.IsNotNullOrEmpty()
	 *     $.SystemHelper.TimePad()
	 *     
	 * @param $ (Jquery)
	 */
	$.SystemHelper = {
	   /**
	    * 页面跳转
	    * @param url
	    */
	   UrlRedirect: function(url) {
		   location.href = url;
		   return false;
	   },
	   /**
	    * 重新导入本页面
	    * @param url
	    */
	   ReloadPage: function(){
		   location.reload();
		   return false;
	   },
	   /**
	    * 重新导入父页面	    
	    */
	   ReloadParentPage: function() {
		   parent.location.reload();
	   },
	   /**
	    * 获取项目根路径
	    * eg: http://localhost:8083/project
	    * 
	    */
	   GetProjectName: function() {
		 var curPath = window.document.location.href;
	     var pathName = window.document.location.pathname;
	     var pos = curPath.indexOf(pathName);
	     var localhostPath = curPath.substring(0, pos);
	     var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);
	     return projectName;
	   },
	   /**
	    * 获取获取主机地址
	    * eg: http://localhost:8080
	    * 
	    */
	   GetLocalHostPath: function() {
		 var curPath = window.document.location.href;
	     var pathName = window.document.location.pathname;
	     var pos = curPath.indexOf(pathName);
	     var localHostPath = curPath.substring(0, pos);
	     return localHostPath;
	   },
	   /**
	    * 如果数字是一位，那么就在后面补零
	    * @param num 值
	    * @param n   补的位数
	    * eg:(400, 4) ==> 0400
	    */
	   TimePad: function(s) {
		   return s < 10 ? '0' + s: s;
	   },
	   SetBreadcrumbUrl: function(urlParam) {
		   var obj = $(".breadcrumb li.active a");
		   if(obj.length > 0) {
			   var href = $(obj).attr("href");
			   if(href.indexOf("?") != -1 ) {
				   $(obj).attr("href", ($(obj).attr("href") + "&" + urlParam)); 
			   }
			   else {
				   $(obj).attr("href", ($(obj).attr("href") + "?" + urlParam));  
			   }
		   }   
	   }	   
	};
}(jQuery));

(function($) {
	/**
	 * 验证通用方法
	 * 
	 * eg: $.VerifyHelper.IsNullOrEmpty()
	 *     $.VerifyHelper.IsNotNullOrEmpty()
	 *     $.VerifyHelper.IsUndefined()
	 *     $.VerifyHelper.IsNotUndefinedOrNull()
	 * @param $ (Jquery)
	 */
	$.VerifyHelper = {
	   /**
	    * 是否为空或者Null判断
	    * @param value
	    * @return true:为空, false:不为空
	    */
	   IsNullOrEmpty: function(value) {
		   var isRet = false;
		   if($.trim(value) == null || $.trim(value) === "") {
			   isRet = true;
		   }
		   return isRet;
	   },
	   /**
	    * 是否为空或者Null判断
	    * @param value
	    * @return true:不为空, false:为空
	    */
	   IsNotNullOrEmpty: function(value) {
		   var isRet = false;
		   if($.trim(value) != null && $.trim(value) != "") {
			   isRet = true;
		   }
		   return isRet;
	   },
	   /**
	    * 是否为空或者Null判断
	    * @param value
	    * @return true:不为空, false:为空
	    */
	   IsUndefined: function(value) {
		   var isRet = false;
		   if(typeof(value) === "undefined") {
			   isRet = true;
		   }
		   return isRet;
	   },
	   /**
	    * 是否为空或者Null判断
	    * @param value
	    * @return true:不为空, false:为空
	    */
	   IsNotUndefinedOrNull: function(value) {
		   var isRet = false;
		   if(typeof(value) !== "undefined" && value != null) {
			   isRet = true;
		   }
		   return isRet;
	   },
	   WebUrlParamsIsChange: function(paramKey) {
		   var reg = new RegExp("(^|&)" + paramKey +"=([^&]*)(&|$)");
		   var r = window.location.search.substr(1).match(reg);
		   if (r != null) {
			   return unescape(r[2]); 
		   }
		   return null;
	   },
	   DataCurrentFormat: function(fmt) {
		   /** * 对Date的扩展，将 Date 转化为指定格式的String * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
		    可以用 1-2 个占位符 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) * eg: * (new
		    Date()).pattern("yyyy-MM-dd hh:mm:ss.S")==> 2006-07-02 08:09:04.423      
			 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
			 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
			 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
			 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
			 */
		   var date = new Date();
		   var o = {         
				    "M+" : date.getMonth()+1, //月份         
				    "d+" : date.getDate(), //日         
				    "h+" : date.getHours()%12 == 0 ? 12 : date.getHours()%12, //小时         
				    "H+" : date.getHours(), //小时         
				    "m+" : date.getMinutes(), //分         
				    "s+" : date.getSeconds(), //秒         
				    "q+" : Math.floor((date.getMonth()+3)/3), //季度         
				    "S" : date.getMilliseconds() //毫秒         
		   };         
		   var week = {         
				    "0" : "/u65e5",         
				    "1" : "/u4e00",         
				    "2" : "/u4e8c",         
				    "3" : "/u4e09",         
				    "4" : "/u56db",         
				    "5" : "/u4e94",         
				    "6" : "/u516d"        
		   };
		   
	       if(/(y+)/.test(fmt)){         
	          fmt = fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));         
	       }
	       
		   if(/(E+)/.test(fmt)){         
		        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[date.getDay()+""]);         
		   }
		   
		   for(var k in o){         
			   if(new RegExp("("+ k +")").test(fmt)){         
				   fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
			   }         
		   }
		   
		   return fmt;
	   },
	   CompareCurrentDateTime: function(value) {
		   var isRet = false;
		   var currentDataTime = new Date();
		   if(value != "") {
			   isRet = ((new Date(value.replace(/-/g,"\/"))) <= currentDataTime);
		   }
		   
		   return isRet;
	   },
	   CompareCurrentBetweenDateTime: function(startDateValue, endDateValue) {
		   var isRet = false;
		   var currentDataTime = new Date();
		   var strCrrentYmd = $.VerifyHelper.DataCurrentFormat("yyyy-MM-dd");
		       startDateValue = strCrrentYmd + " " + startDateValue;
		       endDateValue = strCrrentYmd + " " + endDateValue;
		   
		   if(typeof(startDateValue) !== "undefined" && startDateValue != null && 
			  typeof(endDateValue) !== "undefined" && endDateValue != null) {
			   if((new Date(startDateValue.replace(/-/g,"\/"))) <= currentDataTime && 
			       currentDataTime <= (new Date(startDateValue.replace(/-/g,"\/")))) {
				   isRet = true;
			   }
		   }
		   return isRet;
	   },
	   CompareParamBetweenDateTime: function(paramValue, startDateValue, endDateValue) {
		   var isRet = false;
		       paramValue = paramValue.replace(" 06:00:00", "").replace(" 18:00:00", "");
		   var currentDataTime = new Date(paramValue + " 00:00:00");
		       startDateValue = paramValue + " " + startDateValue;
		       endDateValue = paramValue + " " + endDateValue;
		   
		   if(typeof(startDateValue) !== "undefined" && startDateValue != null && 
			  typeof(endDateValue) !== "undefined" && endDateValue != null) {
			   if((new Date(startDateValue.replace(/-/g,"\/"))) <= currentDataTime && 
			       currentDataTime <= (new Date(startDateValue.replace(/-/g,"\/")))) {
				   isRet = true;
			   }
		   }
		   return isRet;
	   },
	   dateFormat: function(date, format) {
		   var t = new Date(date);
		   var tf = function(i) { return (i < 10 ? '0' : '') + i };
		   return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a) {
		        switch(a){
		            case 'yyyy':
		                return tf(t.getFullYear());
		                break;
		            case 'MM':
		                return tf(t.getMonth() + 1);
		                break;
		            case 'mm':
		                return tf(t.getMinutes());
		                break;
		            case 'dd':
		                return tf(t.getDate());
		                break;
		            case 'HH':
		                return tf(t.getHours());
		                break;
		            case 'ss':
		                return tf(t.getSeconds());
		                break;
		        }
		   });
	   }
	};
}(jQuery));

(function($) {
	/**
	 * 弹框通用方法
	 * 
	 * eg: $.DialogHelper.Loading(b)
	 *     
	 *    
	 * @param $ (Jquery)
	 */
	$.DialogHelper = {
	   /**
	    * 遮罩层
	    * @param b
	    * @return true:打开层, false:关闭层
	    */
	   Loading: function(b) {
		   if(b) {
			   //layer.msg('正在拼了命为您加载…', {icon: 16, time:30 * 1000});
			   layer.load(0, {shade: [0.5,'#fff'], time: 30 * 1000});
		   }
		   else {
			   layer.closeAll('loading');
		   }
	   },
	   Alert: function(msg, callback) {
		   if($.VerifyHelper.IsNotUndefinedOrNull(callback)) {
			   layer.msg(msg, {icon: 1}, callback);
		   }
		   else {
			   layer.msg(msg, {icon: 1});
		   }
	   },
	   Success: function(msg, callback) {
		   if($.VerifyHelper.IsNotUndefinedOrNull(callback)) {
			   layer.msg(msg, {icon: 1}, callback);
		   }
		   else {
			   layer.msg(msg, {icon: 1});
		   }
	   },
	   Fail: function(msg, callback) {
		   if($.VerifyHelper.IsNotUndefinedOrNull(callback)) {
			   layer.msg(msg, {icon: 5}, callback);
		   }
		   else {
			   layer.msg(msg, {icon: 5});
		   }
	   },
	   SysErr: function() {
		   layer.msg("对不起,系统出了一点问题,请重试！", {icon: 16});
	   }
	};
}(jQuery));

(function($) {
	/**
	 * Ajax请求
	 * 
	 * eg: $.AjaxHelper.GetRequest
	 *     $.AjaxHelper.PostRequest
	 *     $.AjaxHelper.AsyncGetRequest
	 *     $.AjaxHelper.AsyncPostRequest
	 *    
	 * @param $ (Jquery)
	 */
	$.AjaxHelper = {
		/**
	     * 异步get提交
	     * @param url      地址
	     * @param param {} 参数
	     * @param callBack 回调函数
	     */
		GetRequest: function(url, param, callBack) {
			$.DialogHelper.Loading(true);
			$.ajax({
 		       url: url,
 		       type: "GET",
 		       data: param,
 		       dataType: "json",
 		       cache: false,
 		       success: function (res) {
 		    	   var b = false;
 		    	   if (res != null && res.data != null && res.code == 200) {
 					   b = true;
 				   }
 				   else {
 					   $.DialogHelper.Fail(res.msg);
 				   }
 		    	   callBack(b, res);
 		       },
 		       error: function(XMLHttpRequest, textStatus, errorThrown) {
 		    	   $.DialogHelper.Loading(false);
				   if($.VerifyHelper.IsNotUndefinedOrNull(XMLHttpRequest.responseJSON)) {
					   $.DialogHelper.Fail(XMLHttpRequest.responseJSON.msg);
				   }
				   else {
					   $.DialogHelper.SysErr();  
				   }
			   },
			   complete : function(XMLHttpRequest, textStatus) {
				    $.DialogHelper.Loading(false);
					var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
					if (sessionstatus == "timeout") {
						//如果超时就处理 ，指定要跳转的页面
						window.location.replace("/login");
					}
			   }
 		   });
	    },
	    /**
	     * 异步post提交
	     * @param url      地址
	     * @param param {} 参数
	     * @param callBack 回调函数
	     */
		PostRequest: function(url, param, callBack) {
			$.DialogHelper.Loading(true);
			$.ajax({
 		       url: url,
 		       type: "POST",
 		       data: param,
 		       dataType: "json",
 		       cache: false,
 		       success: function (res) {
 		    	   var b = false;
		    	   if (res != null && res.data != null && res.code == 200) {
					   b = true;
				   }
				   else {
					   $.DialogHelper.Fail(res.msg);
				   }
		    	   callBack(b, res);
 		       },
 		       error: function(XMLHttpRequest, textStatus, errorThrown) {
 		    	   $.DialogHelper.Loading(false);
				   if($.VerifyHelper.IsNotUndefinedOrNull(XMLHttpRequest.responseJSON)) {
					   $.DialogHelper.Fail(XMLHttpRequest.responseJSON.msg);
				   }
				   else {
					   $.DialogHelper.SysErr();  
				   }
			   },
			   complete : function(XMLHttpRequest, textStatus) {
				    $.DialogHelper.Loading(false);
					var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
					if (sessionstatus == "timeout") {
						//如果超时就处理 ，指定要跳转的页面
						window.location.replace("/login");
					}
			   }
 		   });
	    },
		/**
	     * 同步get提交
	     * @param url      地址
	     * @param param {} 参数
	     * @param callBack 回调函数
	     */
		AsyncGetRequest: function(url, param, callBack) {
			$.DialogHelper.Loading(true);
			$.ajax({
 		       url: url,
 		       type: "GET",
 		       data: param,
 		       dataType: "json",
 		       cache: false,
 		       async: false,
 		       success: function (res) {
 		    	   var b = false;
		    	   if (res != null && res.data != null && res.code == 200) {
					   b = true;
				   }
				   else {
					   $.DialogHelper.Fail(res.msg);
				   }
		    	   callBack(b, res);
 		       },
 		       error: function(XMLHttpRequest, textStatus, errorThrown) {
 		    	   $.DialogHelper.Loading(false);
				   if($.VerifyHelper.IsNotUndefinedOrNull(XMLHttpRequest.responseJSON)) {
					   $.DialogHelper.Fail(XMLHttpRequest.responseJSON.msg);
				   }
				   else {
					   $.DialogHelper.SysErr();  
				   }
			   },
			   complete : function(XMLHttpRequest, textStatus) {
				    $.DialogHelper.Loading(false);
					var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
					if (sessionstatus == "timeout") {
						//如果超时就处理 ，指定要跳转的页面
						window.location.replace("/login");
					}
			   }
 		   });
	    },
	    /**
	     * 同步post提交
	     * @param url      地址
	     * @param param {} 参数
	     * @param callBack 回调函数
	     */
		AsyncPostRequest: function(url, param, callBack) {
			$.DialogHelper.Loading(true);
			$.ajax({
 		       url: url,
 		       type: "POST",
 		       data: param,
 		       dataType: "json",
 		       cache: false,
 		       async: false,
 		       success: function (res) {
 		    	   var b = false;
		    	   if (res != null && res.data != null && res.code == 200) {
					   b = true;
				   }
				   else {
					   $.DialogHelper.Fail(res.msg);
				   }
		    	   callBack(b, res);
 		       },
 		       error: function(XMLHttpRequest, textStatus, errorThrown) {
 		    	   $.DialogHelper.Loading(false);
				   if($.VerifyHelper.IsNotUndefinedOrNull(XMLHttpRequest.responseJSON)) {
					   $.DialogHelper.Fail(XMLHttpRequest.responseJSON.msg);
				   }
				   else {
					   $.DialogHelper.SysErr();  
				   }
			   },
			   complete : function(XMLHttpRequest, textStatus) {
				    $.DialogHelper.Loading(false);
					var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
					if (sessionstatus == "timeout") {
						//如果超时就处理 ，指定要跳转的页面
						window.location.replace("/login");
					}
			   }
 		   });
	    },
	    
	    
	    /**
	     * 图片同步post提交
	     * @param url      地址
	     * @param param {} 参数
	     * @param callBack 回调函数
	     */
	    ImgAsyncPostRequest: function(url, param, callBack) {
			$.DialogHelper.Loading(true);
			$.ajax({
 		       url: url,
 		       type: "POST",
 		       data: param,
 		       dataType: "json",
 		       async: false,
 		       processData: false,
 	           contentType: false,
 		       cache: false,
 		       success: function (res) {
 		    	   var b = false;
		    	   if (res != null && res.data != null && res.code == 200) {
					   b = true;
				   }
				   else {
					   $.DialogHelper.Fail(res.msg);
				   }
		    	   callBack(b, res);
 		       },
 		       error: function(XMLHttpRequest, textStatus, errorThrown) {
 		    	   $.DialogHelper.Loading(false);
				   if($.VerifyHelper.IsNotUndefinedOrNull(XMLHttpRequest.responseJSON)) {
					   $.DialogHelper.Fail(XMLHttpRequest.responseJSON.msg);
				   }
				   else {
					   $.DialogHelper.SysErr();  
				   }
			   },
			   complete : function(XMLHttpRequest, textStatus) {
				    $.DialogHelper.Loading(false);
					var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
					if (sessionstatus == "timeout") {
						//如果超时就处理 ，指定要跳转的页面
						window.location.replace("/web/login/login.html?invalid");
					}
			   }
 		   });
	    },
	    
	    /**
	     * 图片异步post提交
	     * @param url      地址
	     * @param param {} 参数
	     * @param callBack 回调函数
	     */
	    ImgPostRequest: function(url, param, callBack) {
			$.DialogHelper.Loading(true);
			$.ajax({
 		       url: url,
 		       type: "POST",
 		       data: param,
 		       dataType: "json",
 		       processData: false,
 	           contentType: false,
 		       cache: false,
 		       success: function (res) {
 		    	   var b = false;
		    	   if (res != null && res.data != null && res.code == 200) {
					   b = true;
				   }
				   else {
					   $.DialogHelper.Fail(res.msg);
				   }
		    	   callBack(b, res);
 		       },
 		       error: function(XMLHttpRequest, textStatus, errorThrown) {
 		    	   $.DialogHelper.Loading(false);
				   if($.VerifyHelper.IsNotUndefinedOrNull(XMLHttpRequest.responseJSON)) {
					   $.DialogHelper.Fail(XMLHttpRequest.responseJSON.msg);
				   }
				   else {
					   $.DialogHelper.SysErr();  
				   }
			   },
			   complete : function(XMLHttpRequest, textStatus) {
				    $.DialogHelper.Loading(false);
					var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
					if (sessionstatus == "timeout") {
						//如果超时就处理 ，指定要跳转的页面
						window.location.replace("/login");
					}
			   }
 		   });
	    },
	    /**
	     * 文件异步post提交
	     * @param url      地址
	     * @param param {} 参数
	     * @param callBack 回调函数
	     */
		FileUploadPostRequest: function(url, param, fileElementId, callBack) {
			$.DialogHelper.Loading(true);
			$.ajaxFileUpload({
				url: url, 
				type: "POST",
				secureuri: false, 
				fileElementId: fileElementId,
				dataType: 'json', 
				data: param,
				success: function (res, status){
				   var b = false;
		    	   if (res != null && res.data != null && res.code == 200) {
					   b = true;
				   }
				   else {
					   $.DialogHelper.Fail(res.msg);
				   }
		    	   callBack(b, res);
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
 		    	   $.DialogHelper.Loading(false);
				   if($.VerifyHelper.IsNotUndefinedOrNull(XMLHttpRequest.responseJSON)) {
					   $.DialogHelper.Fail(XMLHttpRequest.responseJSON.msg);
				   }
				   else {
					   $.DialogHelper.SysErr();  
				   }
			   },
			   complete : function(XMLHttpRequest, textStatus) {
				    $.DialogHelper.Loading(false);
			   }
			});
	    }
	};
}(jQuery));

(function(n) {
	"use strict";
    n.fn.getWebControls = function() {
        var i = "";
        return n(this).find("input,password,radio,select,textarea,.uploadify,.webUploader").each(function() {
            var r = n(this).attr("id"), u = n(this).attr("type"), m = n(this).attr("name"), t;
            if(r != undefined && m != "" && m != undefined) {
            	switch (u) {
		            case "checkbox":
		                i += n("#" + r).is(":checked") ? '"' + m + '":"1",': '"' + r + '":"0",';
		                break;
		            case "radio":
		                t = n("input[name='"+m+"']:checked").val();
		                i += '"' + m + '":"' + n.trim(t) + '",';
		                break;
		            case "select":
		                t = n("#" + r).val();
		                i += '"' + m + '":"' + n.trim(t) + '",';
		                break;
		            case "webUploader":
		            case "uploadify":
		                t = n("#" + r).val(); (t == "" || t == undefined);
		                i += '"' + m + '":"' + n.trim(t) + '",';
		                break;
		            default:
		                t = n("#" + r).val();
		                i += '"' + m + '":"' + n.trim(t) + '",'
	            }
            }
        }),
        i = i.substr(0, i.length - 1),
        i = i.replace(/\\/g, "\\\\"),
        i = i.replace(/\n/g, "\\n"),
        jQuery.parseJSON("{" + i + "}");
    }
}(jQuery));

(function(n) {
	n.fn.GetWebControls = function() {
        return n(this).getWebControls();
    };
}(jQuery));