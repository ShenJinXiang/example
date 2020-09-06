$(function () {
    $("div.left").height(($(window.document).height()) - 85);
    initTree();
    initGrid();
});

function initTree() {
    var zNodes = doService(ctx+"/xtwh/bmgl/queryBmxxForTree");
    //资源树配置
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey:"id",
                pIdKey: 'pId',
                rootPId: ''
            }
        },
        view: {
            selectedMulti: false,
        },
        callback: {
            onClick: clickBmNode
        }
    };
    //初始资源树
    bmTree = $.fn.zTree.init($("#bmTree"), setting,zNodes);
}

function initGrid() {
    $("#tableGrid").jqGrid(
        {
            url: ctx + '/common/extQuery?sqlid=xtwh_ryxx',
            autowidth: true,
            height: (($(window.document).height()) - 160),
            postData: $("#searchForm").getFormJson(),
            datatype: 'local',
            colNames: ['用户编号', '用户名称', '所属部门', '联系电话', '角色信息', '状态', '操作', 'ryid', 'ssbmid'],
            colModel: [
                {name: 'rybh', index: 'rybh', width: 50, sortable: false},
                {name: 'rymc', index: 'rymc', width: 50, sortable: false},
                {name: 'ssbmmc', index: 'ssbmmc', width: 50, sortable: false},
                {name: 'lxdh', index: 'lxdh', width: 50, sortable: false},
                {name: 'jsxx', index: 'jsxx', width: 60, sortable: false},
                {name: 'yxbz', index: 'yxbz', width: 60, sortable: false, align:'center', formatter: function (value, options, row) {
                    if (value) {
                        return '<span class="green">正常</span>';
                    }
                    return "<span class='red'>冻结</span>"
                    }},
                {name: 'cz', index: 'jgmc', width: 80, sortable: false, align: "center", title: false, formatter: function (value, options, row) {
                        return "<span class='tdcz'>" +
                            "<a title='授权' class='td_btn td_btn_auto' href=javascript:openGrant('" + row.ryid + "','" + row.rymc + "');>授权</a>" +
                            "<a title='修改' class='td_btn td_btn_change' href=javascript:openModify('" + row.ryid + "');>修改</a>" +
                            "<a title='删除' class='td_btn td_btn_delete' href=javascript:deleteRyxx('" + row.ryid + "');>删除</a>" +
                            "<a title='密码初始化' class='td_btn td_btn_password' href=javascript:resetPwd('" + row.ryid + "');>重置密码</a>" +
                            // (grantPer?"<a title='授权' class='td_btn td_btn_auto' href=javascript:openGrant('" + row.rydm + "','" + row.rymc + "','"+row.ssjglx+"');></a>":"") +
                            // (modifyPer?"<a title='修改' class='td_btn td_btn_change' href=javascript:openModify('" + row.rydm + "');></a>":"") +
                            // (deletePer?"<a title='删除' class='td_btn td_btn_delete' href=javascript:deleteRYXX('" + row.rydm + "');></a>":"") +
                            // (resetPwdPer?"<a title='密码初始化' class='td_btn td_btn_password' href=javascript:resetPwd('" + row.rydm + "');></a>":"") +
                            "</span>";
                    }},
                {name: 'ryid', index: 'ryid', hidden: true},
                {name: 'ssbmid', index: 'ssbmid', hidden: true}
            ],
            prmNames: {
                rows: "limit"
            },
            jsonReader: {
                root: "message.list",
                page: "message.pageNumber",
                total: "message.totalPage",
                records: "message.totalRow"
            },
            treeReader: {
                level_field: "level",
                parent_id_field: "sjjgid",
                leaf_field: "leaf",
                expanded_field: "expanded"
            },
            loadtext: "<img src='" + ctx + "/static/images/loading.gif'>&nbsp;&nbsp;拼命加载中...",
            rowNum: 50,
            rowList: [50, 100, 150],
            pager: '#tablePager',
            mtype: "POST",
            viewrecords: true,
            sortable: false
        });
    //初始化验证
    formValidate("ryxxForm");
    //自适应屏幕
    $(window).resize(function(){
        $("#tableGrid").setGridWidth($(window).width()-250);
    });

}

//刷新人员信息
function refreshGrid() {
    var bmTree = $.fn.zTree.getZTreeObj("bmTree");
    var nodes = bmTree.getSelectedNodes();
    if (nodes.length <= 0) {
        qyAlertByTime("请先选择所属部门！",5000);
        return;
    }
    var node = nodes[nodes.length - 1];
    var data = $("#searchForm").getFormJson();
    data.s_ssbmid = node.id;
    $("#tableGrid").setGridParam({"datatype": "json", "postData": data}).trigger("reloadGrid");
}

function clickBmNode(event, treeId, treeNode) {
    refreshGrid();
}

function openAddRyxx() {
    formReset("ryxxForm");
    var bmTree = $.fn.zTree.getZTreeObj("bmTree");
    var nodes = bmTree.getSelectedNodes();
    if (nodes.length > 0) {
        var data = nodes[nodes.length - 1];
        $("#ssbmmc").val(data.name);
        $("#ssbmid").val(data.id);
    } else {
        qyAlertByTime("请先选择所属部门！",5000);
        return;
    }
    var rybh = doService(ctx + "/xtwh/rygl/queryRybh");
    if (rybh) {
        $("#rybh").val(rybh);
    }
    // todo 设置录入人为当前登录用户

    $("#lrrq").val(timeKit.getDateTimeStr(new Date));
    openRyxx("新增用户");
}

//打开人员信息
function openRyxx(title){
    qyOpen(title,700,"ryxxContent");
}

//打开修改人员信息弹出框
function openModify(ryid) {
    var result = doService(ctx + "/xtwh/rygl/queryRyxx", "ryid", ryid);
    if (result) {
        bindForm("ryxxForm", result);
        openRyxx("修改用户");
    }
}

//保存或修改人员信息
function saveRyxx() {
    if ($("#ryxxForm").valid()) {
        qyConfirm("确定执行保存操作吗？", function (value) {
            if (value) {
                var url = ctx + "/xtwh/rygl/saveRyxx";
                if ($("#ryid").val()) {
                    url = ctx + "/xtwh/rygl/updateRyxx";
                }
                var result = doFormService(url, "ryxxForm");
                if (result) {
                    qyAlert("保存成功！", function () {
                        qyClose();
                        refreshGrid();
                    });
                }
            }
        });
    }
}

//删除人员信息
function deleteRyxx(ryid) {
    qyConfirm("确定执行删除操作吗？", function (value) {
        if (value) {
            var result = doService(ctx + "/xtwh/rygl/delRyxx", "ryid", ryid);
            if (result) {
                qyAlert("删除成功！", function () {
                    refreshGrid();
                });
            }
        }
    });
}

function resetPwd(ryid){
    qyConfirm("密码将会初始化为：123456，确定继续吗？",function(value){
        if(value){
            var result = doService(ctx+"/xtwh/rygl/resetPwd","ryid",ryid);
            if(result){
                qyAlert("密码初始化成功！",function(){
                });
            }
        }
    });
}

function initJsTree(ryid){
    $.fn.zTree.destroy("jsTree");
    var zNodes = doService(ctx+"/xtwh/rygl/queryJsForTree","ryid",ryid);
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
            chkboxType: { "Y": "", "N": "" }
        }
    };
    //初始资源树
    jsTree = $.fn.zTree.init($("#jsTree"), setting,zNodes);
}


function openGrant(ryid, rymc) {
    initJsTree(ryid);
    $("#js_ryid").val(ryid);
    qyOpen("授权("+rymc+")",300,"grantContent");
}

// 保存授权
function saveGrant(){
    var nodes = jsTree.getCheckedNodes(true);
    var jsids = [];
    for(var i=0;i<nodes.length;i++){
        jsids.push(nodes[i].id);
    }
    var ryid = $("#js_ryid").val();
    var result = doService(ctx+"/xtwh/rygl/saveRyJs","ryid",ryid,"jsids",jsids.join(','));
    if(result){
        qyConfirm("确定执行保存操作吗？",function(value){
            if(value){
                qyAlert("保存成功！",function(){
                    qyClose();
                    refreshGrid();
                });
            }
        });
    }
}