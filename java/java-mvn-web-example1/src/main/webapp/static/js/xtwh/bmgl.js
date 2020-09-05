var gsmc = "公司";
$(function () {

    init();
    initGrid();
});

function init() {
    //初始化验证
    formValidate("bmxxForm");
}

function initGrid() {
    $("#tableGrid").jqGrid(
        {
            treeGrid:true,
            treeGridModel: "adjacency",
            url : ctx+"/common/extQuery?sqlid=xtwh_bmxx",
            autowidth:true,
            height:(($(window.document).height())-150),
            datatype: "json",
            ExpandColumn:"jgmc",
            colNames : ['部门编号', '部门名称', '上级部门', '描述', '录入人', '操作','id', 'bmid', 'sjbmid'],
            colModel : [
                {name : "bmbh",index : "bmbh",width : 55,sortable:false},
                {name : "bmmc",index : "bmmc",width : 120,sortable:false},
                {name : "sjbmmc",index : "sjbmmc",width : 120,sortable:false, formatter:function (value, options, row) {
                    if (!row.sjbmid) {
                        return gsmc;
                    }
                    return row.sjbmmc;
                    }},
                {name : "bmms",index : "bmms",width : 150,sortable:false},
                {name : "lrrmc",index : "lrrmc",width : 30,sortable:false},
                {name : "cz",index : "jgmc",width : 40,sortable:false,align:"center",title:false,formatter:function(value,options,row){
                        return "<span class='tdcz'>"+
                            "<a title='新增下级' class='td_btn td_btn_add' href=javascript:addChildBmxx('"+row.bmid+"','"+row.bmmc+"');></a>" +
                            "<a title='修改' class='td_btn td_btn_change' href=javascript:openModify('"+row.bmid+"');></a>" +
                            "<a title='删除' class='td_btn td_btn_delete' href=javascript:deleteBmxx('"+row.bmid+"');></a>" +
                            // (addPer?"<a title='新增下级' class='td_btn td_btn_add' href=javascript:addChildBmxx('"+row.bmid+"','"+row.jgmc+"');></a>":"")+
                            // (modifyPer?"<a title='修改' class='td_btn td_btn_change' href=javascript:openModify('"+row.bmid+"');></a>":"")+
                            // (deletePer?"<a title='删除' class='td_btn td_btn_delete' href=javascript:deleteBmxx('"+row.bmid+"');></a>":"")+
                            "</span>";
                    }},
                {name : "id",index : "id",hidden:true},
                {name : "bmid",index : "bmid",hidden:true},
                {name : "sjbmid",index : "sjbmid",hidden:true}
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
            treeReader : {
                level_field: "level",
                parent_id_field: "sjjgid",
                leaf_field: "leaf",
                expanded_field: "expanded"
            },
            loadtext:"<img src='"+ctx+"/static/images/loading.gif'>&nbsp;&nbsp;拼命加载中...",
            rowNum : 50,
            rowList : [ 50, 100, 150 ],
            pager : "#tablePager",
            mtype: "POST",
            viewrecords : true,
            sortable:false
        });
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

/**
 * 弹出新增部门
 */
function openAddBmxx() {
    formReset("bmxxForm");
    var bmbh = doService(ctx + "/xtwh/bmgl/queryBmbh");
    if (bmbh) {
        $("#bmbh").val(bmbh);
    }
    $("#sjbmid").val("");
    $("#sjbmmc").val(gsmc);
    // todo 设置录入人为当前登录用户

    $("#lrrq").val(timeKit.getDateTimeStr(new Date));
    openBMXX("新增部门");
}

/**
 * 新增下级部门
 * @param bmid
 * @param bmmc
 */
function addChildBmxx(bmid, bmmc) {
    formReset("bmxxForm");
    var bmbh = doService(ctx + "/xtwh/bmgl/queryBmbh");
    if (bmbh) {
        $("#bmbh").val(bmbh);
    }
    $("#sjbmid").val(bmid);
    $("#sjbmmc").val(bmmc);
    // todo 设置录入人为当前登录用户

    $("#lrrq").val(timeKit.getDateTimeStr(new Date));
    openBMXX("新增部门");

}


/**
 * 打开修改部门信息弹出框
 * @param bmid
 */
function openModify(bmid){
    formReset("bmxxForm");
    var result = doService(ctx+"/xtwh/bmgl/queryBmxx","bmid",bmid);
    if (result) {
        bindForm("bmxxForm",result);
        if (!result.sjbmid) {
            $("#sjbmid").val("");
            $("#sjbmmc").val(gsmc);
        }
        openBMXX("修改部门");
    }
}

//打开部门信息
function openBMXX(title){
    qyOpen(title,700,"bmxxContent");
}

function saveBMXX() {
    if ($("#bmxxForm").valid()) {
        qyConfirm("确定执行保存操作吗？",function(value){
            if(value){
                var url = ctx+"/xtwh/bmgl/saveBmxx";
                if ($("#bmid").val()!="") {
                    var url = ctx+"/xtwh/bmgl/updateBmxx";
                }
                var result = doFormService(url,"bmxxForm");
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

//删除机构信息
function deleteBmxx(bmid){
    qyConfirm("确定执行删除操作吗？",function(value){
        if(value){
            var result = doService(ctx+"/xtwh/bmgl/deleteBmxx","bmid",bmid);
            if(result){
                qyAlert("删除成功！",function(){
                    refreshGrid();
                });
            }
        }
    });
}