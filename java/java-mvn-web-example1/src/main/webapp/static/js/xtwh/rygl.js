var isNewBean = false;
var jsTree = null;
var currentRYDM;
$(init);

function init() {
    $("div.left").height(($(window.document).height()) - 85);
    //人员树配置
    var setting = {
        async: {
            enable: true,
            url: ctx + "/xtwh/jggl/cshjgxx",
            autoParam: ["id=sjjgid"],
            dataFilter: function (treeId, parentNode, data) {
                return data.message;
            }
        },
        callback: {
            onClick: zTreeOnClick
        }
    };
    var zNodes = [
        {id: ssjgid, name: ssjgmc, open: false, isParent: true}
    ];
    //初始化人员树
    $.fn.zTree.init($("#orgTree"), setting, zNodes);

    $("#ryxxGrid").jqGrid(
            {
                url: ctx + '/common/extQuery?sqlid=ryxx',
                autowidth: true,
                height: (($(window.document).height()) - 160),
                datatype: 'json',
                colNames: ['人员代码', '人员名称', '所属机构', '联系电话', '角色信息', '操作', 'ssjgid', 'ssjglx'],
                colModel: [
                    {name: 'rydm', index: 'rydm', width: 50, sortable: false},
                    {name: 'rymc', index: 'rymc', width: 50, sortable: false},
                    {name: 'ssjgmc', index: 'ssjgmc', width: 50, sortable: false},
                    {name: 'dh', index: 'dh', width: 50, sortable: false},
                    {name: 'jsxx', index: 'jsxx', width: 60, sortable: false},
                    {name: 'cz', index: 'jgmc', width: 80, sortable: false, align: "center", title: false, formatter: function (value, options, row) {
                            return "<span class='tdcz'>" +
                                    (grantPer?"<a title='授权' class='td_btn td_btn_auto' href=javascript:openGrant('" + row.rydm + "','" + row.rymc + "','"+row.ssjglx+"');></a>":"") +
                                    (modifyPer?"<a title='修改' class='td_btn td_btn_change' href=javascript:openModify('" + row.rydm + "');></a>":"") +
                                    (deletePer?"<a title='删除' class='td_btn td_btn_delete' href=javascript:deleteRYXX('" + row.rydm + "');></a>":"") +
                                    (resetPwdPer?"<a title='密码初始化' class='td_btn td_btn_password' href=javascript:resetPwd('" + row.rydm + "');></a>":"") +
                                    "</span>";
                        }},
                    {name: 'ssjgid', index: 'ssjgid', hidden: true},
                    {name: 'ssjglx', index: 'ssjglx', hidden: true}
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
                pager: '#ryxxPager',
                mtype: "POST",
                viewrecords: true,
                sortable: false
            });
    //初始化验证
	formValidate("ryxxForm");
	 //自适应屏幕
	$(window).resize(function(){
		$("#ryxxGrid").setGridWidth($(window).width()-250);
	});
}

/**
 * 人员树点击事件
 * @param {type} event
 * @param {type} treeId
 * @param {type} treeNode
 * @returns {undefined}
 */
function zTreeOnClick(event, treeId, treeNode) {
    if (treeId == "orgTree") {
        var data = $("#searchForm").getFormJson();
        $("#ryxxGrid").setGridParam({postData: {"ssjgid": treeNode.id}}).trigger("reloadGrid", [{page: 1}]);
    } else if (treeId == "userTreeDemo") {
        $("#SSJGMC").val(treeNode.name);
        $("#SSJGID").val(treeNode.id);
    }
}
;

//删除人员信息
function deleteRYXX(rydm) {
    qyConfirm("确定执行删除操作吗？", function (value) {
        if (value) {
            var result = doService(ctx + "/xtwh/rygl/delete", "rydm", rydm);
            if (result) {
                qyAlert("删除成功！", function () {
                    refreshRYXX();
                });
            }
        }
    });
}

//刷新人员信息
function refreshRYXX() {
    var data = $("#searchForm").getFormJson();
    $("#ryxxGrid").setGridParam({"postData": data}).trigger("reloadGrid");
}

//打开新增人员信息弹出框
function openAddRyxx() {
    isNewBean = true;
    formReset("ryxxForm");
    $("#rydm").removeAttr("readonly").removeClass("inp_readonly");
    var orgTree = $.fn.zTree.getZTreeObj("orgTree");
    var nodes = orgTree.getSelectedNodes();
    if (nodes.length > 0) {
        var data = nodes[nodes.length - 1];
        $("#ssjgmc").val(data.name);
        $("#ssjgid").val(data.id);
    } else {
        qyAlertByTime("请先选择所属机构！",5000);
        return;
    } 
    openRYXX("新增人员");
}

//打开人员信息
function openRYXX(title){
	qyOpen(title,700,"ryxxContent");
}

//打开修改人员信息弹出框
function openModify(rydm) {
    var result = doService(ctx + "/xtwh/rygl/queryRYXX", "rydm", rydm);
    $("#rydm").attr("readonly","readonly").addClass("inp_readonly");
    if (result) {
        bindForm("ryxxForm", result);
        isNewBean = false;
        openRYXX("修改人员");
    }
}

//保存或修改人员信息
function saveRYXX() {
    qyConfirm("确定执行保存操作吗？", function (value) {
        if (value) {
            if ($("#ryxxForm").valid()) {
                var url = ctx + "/xtwh/rygl/saveRYXX";
                if (!isNewBean) {
                    var url = ctx + "/xtwh/rygl/updateRYXX";
                    qyAlert("更新");
                }
                var result = doFormService(url, "ryxxForm");
                if (result) {
                    qyAlert("保存成功！", function () {
                        qyClose();
                        refreshRYXX();
                    });
                }
            }
        }
    });
}

/**
 * 初始化密码
 * @param {type} ryid
 * @returns {undefined}
 */
function resetPwd(rydm){
	qyConfirm("密码将会初始化为：123456，确定继续吗？",function(value){
		if(value){
			var result = doService(ctx+"/xtwh/rygl/resetPwd","rydm",rydm);
			if(result){
				qyAlert("密码初始化成功！",function(){
				}); 
			}
		}
	});
}

//初始化树
function initJsTree(rydm,ssjglx){
	$.fn.zTree.destroy("jsTree");
	var zNodes = doService(ctx+"/xtwh/jsgl/queryJsForTree","rydm",rydm,"ssjglx",ssjglx);
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

//打开授权
function openGrant(rydm,rymc,ssjglx){
	initJsTree(rydm,ssjglx);
	this.currentRYDM = rydm;
	qyOpen("授权("+rymc+")",300,"grantContent");
}

//保存授权
function saveGrant(){
	var nodes = jsTree.getCheckedNodes(true);
	var jsids = "";
	for(var i=0;i<nodes.length;i++){
		jsids+=nodes[i].id;
		if(i<nodes.length-1){
			jsids+=",";
		}
	}
	var result = doService(ctx+"/xtwh/rygl/saveRyJs","rydm",currentRYDM,"jsids",jsids);
	if(result){
		qyConfirm("确定执行保存操作吗？",function(value){
			if(value){
				qyAlert("保存成功！",function(){
					qyClose();
					refreshRYXX();
				});
			}
		});
	}	
}