var zyTree=null;
var currentJSID = "";

$(init);

function init(){
	$("#jsxxGrid").jqGrid(
  	{
		grouping:true,
		groupingView : {
		  groupField : ['jslx'],
		  groupColumnShow : [false]
		},
		altRows:false,
        url : ctx+"/common/extQuery?sqlid=jsxx",
  	    autowidth:true,
        height:(($(window.document).height())-150),
  	    datatype: "json",    
  		colNames : ['角色名称', '角色描述', '角色类型', '数据权限','操作','jsid'],
		colModel : [ 
			{name : "jsmc",index : "jsmc",width : 120,sortable:false}, 
			{name : "jsms",index : "jsms",width : 120,sortable:false}, 			
			{name : "jslx",index : "jslx",width : 50,sortable:false,align:"center",formatter:function(value){
				if(value=="0"){
					return "<font style='font-size:14px;font-weight:bold;'>平台</font>";
				}else if(value=="1"){
					return "<font style='font-size:14px;font-weight:bold;'>生产</font>";
				}else if(value=="2"){
					return "<font style='font-size:14px;font-weight:bold;'>销售</font>";
				}
			}}, 				             
			{name : "sjqx",index : "sjqx",width : 50,align:"center",formatter:function(value){
				if(value=="1"){
					return "公司数据";
				}else if(value=="2"){
					return "部门数据";
				}else if(value=="3"){
					return "个人数据";
				}
			}}, 
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
     formValidate("jsxxForm");
     //自适应屏幕
     $(window).resize(function(){
     	$("#jsxxGrid").setGridWidth($(window).width()*0.99);
     });
}
	
//删除角色信息
function deleteJSXX(jsid){
	qyConfirm("确定执行删除操作吗？",function(value){
		if(value){
			var result = doService(ctx+"/xtwh/jsgl/delete","jsid",jsid);
			if(result){
				qyAlert("删除成功！",function(){
					refreshJSXX();
				});
			}
		}
	});
}

//刷新角色信息
function refreshJSXX(){
	var data = $("#searchForm").getFormJson();
	$("#jsxxGrid").setGridParam({"postData":data}).trigger("reloadGrid"); 
}

//打开新增角色信息弹出框
function openAddJsxx(){
	formReset("jsxxForm");
	openJSXX("新增角色");
}

//打开角色信息
function openJSXX(title){
	qyOpen(title,700,"jsxxContent");
}

//打开新增下级角色信息弹出框
function addChildJsxx(id,sjjsmc){
	formReset("jsxxForm");
	$("#sjjsid").val(id);
	$("#sjjsmc").val(sjjsmc);
	$("#jslx").val("1");
	$("#jsjb").val("2");
	openJSXX("新增角色");
}

//打开修改角色信息弹出框
function openModify(jsid){
	formReset("jsxxForm");
	var result = doService(ctx+"/xtwh/jsgl/queryJSXX","jsid",jsid);
	if (result) {
		bindForm("jsxxForm",result);
		if (!result.sjjsid) {
			$("#sjjsid").val("");
			$("#sjjsmc").val("无上级角色");
		}
		openJSXX("修改角色");
	}
}

//保存或修改角色信息
function saveJSXX(){
	qyConfirm("确定执行保存操作吗？",function(value){
		if(value){
			if ($("#jsxxForm").valid()) {
				var url = ctx+"/xtwh/jsgl/saveJSXX";
				if ($("#jsid").val()!="") {
					var url = ctx+"/xtwh/jsgl/updateJSXX";
				}
				var result = doFormService(url,"jsxxForm");
				if(result){
					qyAlert("保存成功！",function(){
						qyClose();
						refreshJSXX();
					});
				}
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
			chkboxType: { "Y": "ps", "N": "s" }
		}
	};	
	//初始资源树
	zyTree = $.fn.zTree.init($("#zyTree"), setting,zNodes);
}

//打开搜权窗口
function openGrant(jsid,jsmc){
	if(!zyTree){
		initZyTree();
	}
	var result = doService(ctx+"/xtwh/zygl/queryJsZy","jsid",jsid);
	if(result){
		this.currentJSID = jsid;
		//清除所有选中
		zyTree.checkAllNodes(false);
		//初始化已授权的资源选中状态
		if(result){
			var arrZyids = result.split(",");
			for(var i=0;i<arrZyids.length;i++){
				var node = zyTree.getNodeByParam("id",arrZyids[i]);
				if(node){
					zyTree.checkNode(node,true,false);
				}
			}
		}
		qyOpen("授权（"+jsmc+"）",300,"zyDiv");
	}
}

//保存授权
function saveGrant(){
	var nodes = zyTree.getCheckedNodes(true);
	var zyids = "";
	for(var i=0;i<nodes.length;i++){
		zyids+=nodes[i].id;
		if(i<nodes.length-1){
			zyids+=",";
		}
	}
	var result = doService(ctx+"/xtwh/jsgl/saveJsZy","jsid",currentJSID,"zyids",zyids);
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