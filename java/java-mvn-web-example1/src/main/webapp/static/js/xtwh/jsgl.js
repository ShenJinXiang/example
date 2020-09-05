$(function () {

    initGrid();

});

function initGrid() {
    $("#jsxxGrid").jqGrid( {
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
                            "<a title='授权' class='td_btn td_btn_auto' href=javascript:openGrant('"+row.jsid+"','"+row.jsmc+"');></a>"+
                            "<a title='修改' class='td_btn td_btn_change' href=javascript:openModify('"+row.jsid+"');></a>"+
                            "<a title='删除' class='td_btn td_btn_delete' href=javascript:deleteJSXX('"+row.jsid+"');></a>"+
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
            pager : "#jsxxPager",
            mtype: "POST",
            viewrecords : true,
            sortable:false
        });
    //初始化验证
    // formValidate("jsxxForm");
    //自适应屏幕
    $(window).resize(function(){
        $("#jsxxGrid").setGridWidth($(window).width()*0.99);
    });
}