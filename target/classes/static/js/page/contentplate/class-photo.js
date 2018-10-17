function class_photo_add(title,url) {
    layer_show(title,url,'','');
}

var setting = {
    view: {
        selectedMulti: false
    },
    async: {
        enable: true,
        url:"/garden/allInfo",
        // autoParam:["id", "name=n", "level=lv"],
        // otherParam:{"otherParam":"zTreeAsyncTest"},
        dataFilter: filter
    },
    callback: {
        beforeClick: beforeClick,
        beforeAsync: beforeAsync,
        onAsyncError: onAsyncError,
        onAsyncSuccess: onAsyncSuccess
    }
};


function filter(treeId, parentNode, childNodes) {

    if (!childNodes) return null;
    var zNodes = new Array();
    for (var i=0, l=childNodes.data.length; i<l; i++) {
        // if (i == 0) {
        //     firstId = childNodes.data[i].id;
        //     deleteId = firstId;
        // }
        zNodes[i]={id:childNodes.data[i].id, pId:0, name:childNodes.data[i].gardenName,isParent:0};
    }
    return zNodes;
}

function beforeClick(treeId, treeNode) {
    if (!treeNode.isParent) {
        /*当点击子类的时候，请求*/
        console.log(treeNode.id);
        // $("#panel-single-detail").attr("src", "/management/page/panel/add?id="+treeNode.id);
        // deleteId = treeNode.id;
        return false;
    } else {
        return true;
    }
}
var log, className = "dark";
function beforeAsync(treeId, treeNode) {
    className = (className === "dark" ? "":"dark");
    showLog("[ "+getTime()+" beforeAsync ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
    return true;
}
function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    showLog("[ "+getTime()+" onAsyncError ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
}
function onAsyncSuccess(event, treeId, treeNode, msg) {
    showLog("[ "+getTime()+" onAsyncSuccess ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
    // $("#panel-single-detail").attr("src", "/management/page/panel/add?id="+firstId);
}

function showLog(str) {
    if (!log) log = $("#log");
    log.append("<li class='"+className+"'>"+str+"</li>");
    if(log.children("li").length > 8) {
        log.get(0).removeChild(log.children("li")[0]);
    }
}



function getTime() {
    var now= new Date(),
        h=now.getHours(),
        m=now.getMinutes(),
        s=now.getSeconds(),
        ms=now.getMilliseconds();
    return (h+":"+m+":"+s+ " " +ms);
}

function refreshNode(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        type = e.data.type,
        silent = e.data.silent,
        nodes = zTree.getSelectedNodes();
    if (nodes.length == 0) {
        alert("请先选择一个父节点");
    }
    for (var i=0, l=nodes.length; i<l; i++) {
        zTree.reAsyncChildNodes(nodes[i], type, silent);
        if (!silent) zTree.selectNode(nodes[i]);
    }
}


$(function () {
    $.fn.zTree.init($("#treeDemo"), setting);
    $("#refreshNode").bind("click", {type:"refresh", silent:false}, refreshNode);
    $("#refreshNodeSilent").bind("click", {type:"refresh", silent:true}, refreshNode);
    $("#addNode").bind("click", {type:"add", silent:false}, refreshNode);
    $("#addNodeSilent").bind("click", {type:"add", silent:true}, refreshNode);
})



