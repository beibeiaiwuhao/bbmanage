/**
 * 选择上传文件组件
 * @aurhtor Murphy.Chang
 * @version 0.0.2
 */
(function(window, document){
	//默认参数
	var defaultSettings = {
		elId: null,//点击元素id
		targetElId: null,//生成图片元素id
		height: 100,//高(大于0小于500)
		width: 100,//宽(大于0小于500)
		acceptType: 'image/png,image/jpg,image/jpeg',//允许文件类型(使用逗号分割，默认图片类型)
		count: 6,//允许文件数量(大于0小于10)
		size: 1024,//允许单个文件大小(KB)(大于0KB小于102400KB即10MB)
		add: function(){},//选择图片的change事件回调函数
		remove: function(){}//删除图片回调函数
	};

	//构造方法
	function ChooseFile(opt){
		this.initial(opt);
	}

	//判断允许文件类型是否正确(image/png,image/jpg,image/jpeg,vedio/mp4)
	function isAcceptType(val){
		var str = val.split(",");
		for(var i in str){
			if(str[i]!='image/png' && str[i]!='image/jpg' && str[i]!='image/jpeg' && str[i]!='vedio/mp4'){
				return false;
			}
		}
		return true;
	}

	//判断是否为数值类型
	function isNumber(val){
	    if(val === "" || val ==null){
	        return false;
	    }
	    if(!isNaN(val)){
	        return true;
	    }else{
	        return false;
	    }
	}

	//获取选择图片的url
	function getObjectURL(file) {
		var url = null ;
		if (window.createObjectURL!=undefined) { // basic
			url = window.createObjectURL(file);
		} else if (window.URL!=undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file);
		} else if (window.webkitURL!=undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file);
		}
		return url;
	}

	//通过标签名获取子元素
	function getChildrenNodesByTagName(pEle, tagName){
		var nodeList = pEle.childNodes;
    	var ary = [];
    	for(var i=0;i<nodeList.length;i++){
        	var curNode = nodeList[i];
        	if(curNode.nodeType === 1 && curNode.nodeName.toLowerCase() === tagName.toLowerCase()){
            	ary.push(curNode);
            }
		}

    	return ary;
	}

	//显示隐藏方法(和jquery.toggle()同理)
	function toggle(ele){
		ele.style.display = ele.style.display == 'none'?'':'none';
	}

	//判断元素在数组中的索引index
	function inArray(ele, arr){
		for(var i=0;i<arr.length;i++){
			if(ele === arr[i]){
				return i;
			}
		}

		return -1;
	}

	ChooseFile.prototype = {
		//初始化方法
		initial: function(opt){
			//合并参数
			this.defaultSettings = Object.assign({},defaultSettings,opt,true);
			//选中文件
			this.files = [];
			//错误信息
			this.error = null;
			//判断参数是否正确
			if(this.defaultSettings && 
				this.defaultSettings.elId && 
				this.defaultSettings.targetElId && 
				isNumber(this.defaultSettings.height) && this.defaultSettings.height>0 && this.defaultSettings.height<=500 &&
				isNumber(this.defaultSettings.width) && this.defaultSettings.width>0 && this.defaultSettings.width<=500 && 
				typeof(this.defaultSettings.acceptType) === "string" && isAcceptType(this.defaultSettings.acceptType) && 
				isNumber(this.defaultSettings.count) && this.defaultSettings.count>0 && this.defaultSettings.count<=10 && 
				isNumber(this.defaultSettings.size) && this.defaultSettings.size>0 && this.defaultSettings.size<=102400
				){
				//调用展示元素方法
				this.createDom();
				//绑定事件
				this.eventFuc();
			}else{
				throw new Error("请传入正确参数");
			}
		},
		//创建元素
		createDom: function(){
			//被初始化的dom元素
			var elementDom = document.getElementById(this.defaultSettings.elId);
			
			//目标展示元素
			var targetDom = document.getElementById(this.defaultSettings.targetElId);
			
			//file类型的input框
			var inputDom = document.createElement("input");
			inputDom.style.cssText = "display:none;";
			inputDom.type = "file";
			inputDom.multiple = "multiple";
			inputDom.accept = this.defaultSettings.acceptType;

			//列表ul元素(用于展示图片)
			var ulDom = document.createElement("ul");
			ulDom.style.cssText = "display:flow-root;list-style:none;margin:0;padding:0;";
			
			//储存错误信息的元素
			var errorDom = document.createElement("input");
			errorDom.type = "hidden";
			errorDom.name = "error";
            console.log("++++++++++++");
			console.log(ulDom);
            console.log(inputDom);
            console.log(errorDom);

			//放入初始化的dom元素中
			targetDom.appendChild(ulDom);
			elementDom.appendChild(inputDom);
			elementDom.appendChild(errorDom);

			//放入全局变量的属性中
			this.elementDom = elementDom;
			this.targetDom = targetDom;
			this.inputDom = inputDom;
			this.ulDom = ulDom;
			this.errorDom = errorDom;
		},
		//更新
		update: function(){
			this.ulDom.innerHTML = "";
			//选中文件
			this.files = [];
			//错误信息
			this.error = null;
		},
		//展示添加的图片
		show: function(files){
			if(this.files.length + files.length <= this.defaultSettings.count){//数量判断
				for(var i=0;i<files.length;i++){
					if (isString(files[i])) {
                            //加入到files参数中
                            this.files.push(files[i]);
                            //创建img元素
                            var imgDom = document.createElement("img");
                            imgDom.src = files[i];
                            imgDom.style.cssText = "height:"+this.defaultSettings.height+"px;width:"+this.defaultSettings.width+"px;display:block;position:relative;";

                            //创建span元素(删除用遮罩层)
                            var spanDom = document.createElement("span");
                            spanDom.innerHTML = "点击删除";
                            spanDom.style.cssText = "position:absolute;float:left;height:"+this.defaultSettings.height+"px;width:"+this.defaultSettings.width+"px;text-align:center;margin-top:-"+this.defaultSettings.height+"px;background-color:rgba(0,0,0,0.5);color:white;font-size:small;cursor:pointer;display:none;";

                            //创建li元素，并将img元素放入其中
                            var liDom = document.createElement("li");
                            liDom.style.cssText = "float:left;display:block;position:relative;overflow:hidden;margin:1px;border:2px solid #333;";
                            liDom.appendChild(imgDom);
                            liDom.appendChild(spanDom);
                            //动态绑定li的事件
                            this.eventFucActive(liDom);

                            //放入ul元素中
                            this.ulDom.appendChild(liDom);

                            //将错误信息置为空
                            this.error = null;
					}else {
                        if(files[i].size/1024 <= this.defaultSettings.size){//文件大小判断
                            //加入到files参数中
                            this.files.push(files[i]);
                            //创建img元素
                            var imgDom = document.createElement("img");
                            imgDom.src = getObjectURL(files[i]);
                            imgDom.style.cssText = "height:"+this.defaultSettings.height+"px;width:"+this.defaultSettings.width+"px;display:block;position:relative;";

                            //创建span元素(删除用遮罩层)
                            var spanDom = document.createElement("span");
                            spanDom.innerHTML = "点击删除";
                            spanDom.style.cssText = "position:absolute;float:left;height:"+this.defaultSettings.height+"px;width:"+this.defaultSettings.width+"px;text-align:center;margin-top:-"+this.defaultSettings.height+"px;background-color:rgba(0,0,0,0.5);color:white;font-size:small;cursor:pointer;display:none;";

                            //创建li元素，并将img元素放入其中
                            var liDom = document.createElement("li");
                            liDom.style.cssText = "float:left;display:block;position:relative;overflow:hidden;margin:1px;border:2px solid #333;";
                            liDom.appendChild(imgDom);
                            liDom.appendChild(spanDom);
                            //动态绑定li的事件
                            this.eventFucActive(liDom);

                            //放入ul元素中
                            this.ulDom.appendChild(liDom);

                            //将错误信息置为空
                            this.error = null;
                        }else{
                            //保存错误信息
                            this.error = "文件大小不能超过"+this.defaultSettings.size+"KB";
                            //触发input事件
                            var event = new InputEvent('input');
                            this.errorDom.dispatchEvent(event);
                        }
					}

				}
				//将input框value置空(可以选择重复文件，调用change事件)
				this.inputDom.value = null;
			}else{
				//保存错误信息
				this.error = "文件数量不能超过"+this.defaultSettings.count+"个";
				//触发input事件
				var event = new InputEvent('input');
				this.errorDom.dispatchEvent(event);
			}
		},
		//获取选中的文件
		getFiles: function(){
			return this.files;
		},
		//获取错误信息
		getError: function(){
			return this.error;
		},
		//绑定事件
		eventFuc: function(){
			var _this = this;

			// file类型input的点击转移
			this.elementDom.addEventListener("click", function(){
				_this.inputDom.click();
			}, false);

			//选择文件
			this.inputDom.addEventListener("change", function(){
				_this.show(this.files);
				//选择文件的回调函数
				_this.defaultSettings.add();
			}, false);
		},
		//动态绑定li的事件
		eventFucActive: function(liDom){
			var _this = this;

			//显示关闭用遮罩层
			liDom.addEventListener("mouseenter", function(){
				var spanDom = getChildrenNodesByTagName(liDom, "span")[0];
				toggle(spanDom);
			}, false);

			//隐藏关闭用遮罩层
			liDom.addEventListener("mouseleave", function(){
				var spanDom = getChildrenNodesByTagName(liDom, "span")[0];
				toggle(spanDom);
			}, false);

			//点击删除事件
			liDom.addEventListener("click", function(){
				var index = inArray(liDom, getChildrenNodesByTagName(_this.ulDom, "li"));//获取li索引index
				_this.files.splice(index, 1);//删除图片
				liDom.parentNode.removeChild(liDom);//删除dom元素
				//删除图片的回调
				_this.defaultSettings.remove();
			}, false);
		}
	}

	//将对象放入window对象中(接口暴露给外部)
	window.ChooseFile = ChooseFile;
}(window, document));

/*判断对象是否是字符串*/
function isString(obj){
    return Object.prototype.toString.call(obj) === "[object String]";
}
