var zyTree;
$(function () {

    initGrid();

});

function initGrid() {
    $("#tableGrid").jqGrid( {
            url : ctx+"/common/extQuery?sqlid=xtwh_jsxx",
            autowidth:true,
            height:(($(window.document).height())-150),
            datatype: "json",
            colNames : ['角色编号', '角色名称', '角色描述','操作','jsid'],
            colModel : [
                {name : "jsbh",index : "jsbh",width : 80,sortable:false},
                {name : "jsmc",index : "jsmc",width : 80,sortable:false},
                {name : "jsms",index : "jsms",width : 80,sortable:false},
                {name : "cz",index : "jsmc",width : 50,sortable:false,align:"center",title:false,formatter:function(value,options,row){
                        return "<span class='tdcz'>"+
                            (sqPer ? "<a title='授权' class='td_btn' href=javascript:openGrant('"+row.jsid+"','"+row.jsmc+"');>授权</a>" : "") +
                            (modifyPer ? "<a title='修改' class='td_btn' href=javascript:openModify('"+row.jsid+"');>修改</a>" : "" ) +
                            (deletePer ? "<a title='删除' class='td_btn' href=javascript:deleteJsxx('"+row.jsid+"');>删除</a>" : "" ) +
                            // (sqPer ? "<a title='授权' class='td_btn td_btn_auto' href=javascript:openGrant('"+row.jsid+"','"+row.jsmc+"');>授权</a>" : "") +
                            // (modifyPer ? "<a title='修改' class='td_btn td_btn_change' href=javascript:openModify('"+row.jsid+"');>修改</a>" : "" ) +
                            // (deletePer ? "<a title='删除' class='td_btn td_btn_delete' href=javascript:deleteJsxx('"+row.jsid+"');>删除</a>" : "" ) +
                            "</span>";
                    }},
                {name : "jsid",index : "jsid",hidden:true}
            ],
            prmNames:{
                rows:"limit"
            },
            jsonReader :{
                root: "message.list",
                page: "message.pageNumber",
                total: "message.totalPage",
                records:"message.totalRow"
            },
            loadtext:"<img src='"+ctx+"/static/images/loading.gif'>&nbsp;&nbsp;拼命加载中...",
            rowNum : 50,
            rowList : [ 50, 100, 150 ],
            pager : "#tablePager",
            mtype: "POST",
            viewrecords : true,
            sortable:false
        });
    //初始化验证
    formValidate("jsxxForm");
    //自适应屏幕
    $(window).resize(function(){
        $("#tableGrid").setGridWidth($(window).width()*0.99);
    });
}

//刷新机构信息
function refreshGrid(){
    var data = $("#searchForm").getFormJson();
    $("#tableGrid").setGridParam({"postData":data}).trigger("reloadGrid");
}


function openAddJsxx() {
    formReset("jsxxForm");
    var jsbh = doService(ctx + "/xtwh/jsgl/queryJsbh");
    if (jsbh) {
        $("#jsbh").val(jsbh);
    }
    $("#lrrmc").val(currentRyxx.rymc);
    $("#lrrq").val(timeKit.getDateTimeStr(new Date));
    openJsxx("新增角色");
}

function openModify(jsid) {
    formReset("jsxxForm");
    var result = doService(ctx+"/xtwh/jsgl/queryJsxx","jsid",jsid);
    if (result) {
        bindForm("jsxxForm",result);
        openJsxx("修改角色");
    }
}

function openJsxx(title) {
    qyOpen(title,700,"jsxxContent");
}

function saveJsxx() {
    if ($("#jsxxForm").valid()) {
        qyConfirm("确定执行保存操作吗？",function(value){
            if(value){
                var url = ctx+"/xtwh/jsgl/saveJsxx";
                if ($("#jsid").val()) {
                    url = ctx+"/xtwh/jsgl/updateJsxx";
                }
                var result = doFormService(url,"jsxxForm");
                if(result){
                    qyAlert("保存成功！",function(){
                        qyClose();
                        refreshGrid();
                    });
                }
            }
        });
    }
}

function deleteJsxx(jsid) {
    qyConfirm("确定执行删除操作吗？",function(value){
        if(value){
            var result = doService(ctx+"/xtwh/jsgl/deleteJsxx","jsid",jsid);
            if(result){
                qyAlert("删除成功！",function(){
                    refreshGrid();
                });
            }
        }
    });
}

//初始化树
function initZyTree(jsid){
    var zNodes = doService(ctx+"/xtwh/zygl/queryZyxxForTree");
    //资源树配置
    var setting = {
        data: {
            simpleData: {
                idKey:"id",
                enable: true
            }
        },
        check: {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y": "ps", "N": "ps" }
        }
    };
    //初始资源树
    zyTree = $.fn.zTree.init($("#zyTree"), setting,zNodes);
}

function openGrant(jsid, jsmc) {
    if(!zyTree){
        initZyTree();
    }
    var result = doService(ctx+"/xtwh/zygl/queryJsZy","jsid",jsid);
    if(result){
        // this.currentJSID = jsid;
        $("#zy_jsid").val(jsid);
        //清除所有选中
        zyTree.checkAllNodes(false);
        //初始化已授权的资源选中状态
        if(result){
            result.forEach(function(item, arr) {
                var zyid = item.zyid;
                    var node = zyTree.getNodeByParam("id",zyid);
                    if(node){
                        zyTree.checkNode(node,true,false);
                    }
            });
        }
        qyOpen("授权（"+jsmc+"）",300,"zyDiv");
    }
}

function saveGrant() {
    var jsid = $("#zy_jsid").val();
    var nodes = zyTree.getCheckedNodes(true);
    var zyidArr = [];
    for(var i=0;i<nodes.length;i++){
        zyidArr.push(nodes[i].id);
    }
    var result = doService(ctx+"/xtwh/jsgl/saveJsZy","jsid",jsid,"zyids",zyidArr.join(","));
    if(result){
        qyConfirm("确定执行保存操作吗？",function(value){
            if(value){
                qyAlert("保存成功！",function(){
                    qyClose();
                });
            }
        });
    }
}