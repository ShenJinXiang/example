$(init);

function init(){
	$("#zyxxGrid").jqGrid(
  	{
  		treeGrid:true,
        treeGridModel: "adjacency",
        url : ctx+"/common/extQuery?sqlid=zyxx",
  	    autowidth:true,
        height:(($(window.document).height())-150),
  	    datatype: "json",    
  	    ExpandColumn:"zymc",
  		colNames : ['资源排序', '资源名称', '资源路径','资源类型','资源描述','操作','id','zytb','sjzyid','sjzymc'],
		colModel : [ 
			{name : "zypx",index : "zypx",width : 30,sortable:false}, 
			{name : "zymc",index : "zymc",width : 100,sortable:false}, 			
			{name : "zylj",index : "zylj",width : 100,sortable:false}, 			
			{name : "zylx",index : "zylx",width : 30,align:"center",formatter:function(value){
				if(value=="0"){
					return "<span class='labelH'>菜单</span>";
				}else if(value=="1"){
					return "<span class='labelM'>功能</span>";
				}
			}}, 
			{name : "zyms",index : "zyms",width : 100,sortable:false},
			{name : "cz",index : "cz",width : 60,sortable:false,align:"center",title:false,formatter:function(value,options,row){
				return "<span class='tdcz'>"+
				"<a title='新增下级' class='td_btn td_btn_add' href=javascript:addChildJgxx('"+row.id+"','"+row.zymc+"');></a>"+
				"<a title='修改' class='td_btn td_btn_change' href=javascript:openModify('"+row.id+"');></a>"+
				"<a title='删除' class='td_btn td_btn_delete' href=javascript:deleteZYXX('"+row.id+"');></a>"+
				"</span>";
			}},
			{name : "id",index : "id",hidden:true},
			{name : "zytb",index : "zytb",hidden:true},
			{name : "sjzyid",index : "sjzyid",hidden:true},
			{name : "sjzymc",index : "sjzymc",hidden:true}
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
  	   	  parent_id_field: "sjzyid", 
  	   	  leaf_field: "leaf",
  	   	  expanded_field: "expanded"
  		},
        loadtext:"<img src='"+ctx+"/static/images/loading.gif'>&nbsp;&nbsp;拼命加载中...",
  		rowNum : 50,
		rowList : [ 50, 100, 150 ],
		pager : "#zyxxPager",
  	    mtype: "POST",
		viewrecords : true,
		sortable:false
     });
     //初始化验证
     formValidate("zyxxForm");
     //自适应屏幕
     $(window).resize(function(){
     	$("#zyxxGrid").setGridWidth($(window).width()*0.99);
     });
}
	
//删除资源信息
function deleteZYXX(zyid){
	qyConfirm("确定执行删除操作吗？",function(value){
		if(value){
			var result = doService(ctx+"/xtwh/zygl/delete","zyid",zyid);
			if(result){
				qyAlert("删除成功！",function(){
					refreshZYXX();
				});
			}
		}
	});
}

//刷新资源信息
function refreshZYXX(){
	console.log('refresh');
	var data = $("#searchForm").getFormJson();
	$("#zyxxGrid").setGridParam({"postData":data}).trigger("reloadGrid"); 
}

//打开新增资源信息弹出框
function openAddZyxx(){
	formReset("zyxxForm");
	$("#sjzyid").val("");
	$("#sjzymc").val("无上级资源");
	openZYXX("新增资源");
}

//打开资源信息
function openZYXX(title){
	qyOpen(title,700,"zyxxContent");
}

//打开新增下级资源信息弹出框
function addChildJgxx(id,sjzymc){
	formReset("zyxxForm");
	$("#sjzyid").val(id);
	$("#sjzymc").val(sjzymc);
	openZYXX("新增资源");
}

//打开修改资源信息弹出框
function openModify(zyid){
	formReset("zyxxForm");
	var result = doService(ctx+"/xtwh/zygl/queryZYXX","zyid",zyid);
	if (result) {
		bindForm("zyxxForm",result);
		if (!result.sjzyid) {
			$("#sjzyid").val("");
			$("#sjzymc").val("无上级资源");
		}
		openZYXX("修改资源");
	}
}

//保存或修改资源信息
function saveZYXX(){
	qyConfirm("确定执行保存操作吗？",function(value){
		if(value){
			if ($("#zyxxForm").valid()) {
				var url = ctx+"/xtwh/zygl/saveZYXX";
				if ($("#zyid").val()!="") {
					var url = ctx+"/xtwh/zygl/updateZYXX";
				}
				var result = doFormService(url,"zyxxForm");
				if(result){
					qyAlert("保存成功！",function(){
						qyClose();
						refreshZYXX();
					});
				}
			}
		}
	});	
}