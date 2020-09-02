$(init);

function init(){
	$("#jgxxGrid").jqGrid(
  	{
  		treeGrid:true,
        treeGridModel: "adjacency",
        url : ctx+"/common/extQuery?sqlid=jgxx",
  	    autowidth:true,
        height:(($(window.document).height())-150),
  	    datatype: "json",    
  	    ExpandColumn:"jgmc",
  		colNames : ['机构名称', '机构代码', '机构类型', '机构级别','维护人','操作','id','sjjgid','sjjgmc'],
		colModel : [ 
			{name : "jgmc",index : "jgmc",width : 120,sortable:false}, 
			{name : "jgdm",index : "jgdm",width : 55,sortable:false}, 			
			{name : "jglx",index : "jglx",width : 30,sortable:false,formatter:function(value){
				if(value=="1"){
					return "<span class='labelL'>生产</span>";
				}else if(value=="2"){
					return "<span class='labelM'>销售</span>";
				}else if(value=="3"){
					return "<span class='labelH'>生产+销售</span>";
				}
			}}, 				             
			{name : "jgjb",index : "jgjb",width : 30,align:"center",formatter:function(value){
				if(value=="1"){
					return "公司";
				}else if(value=="2"){
					return "部门";
				}
			}}, 
			{name : "lrr_mc",index : "lrr_mc",width : 30,sortable:false},
			{name : "cz",index : "jgmc",width : 40,sortable:false,align:"center",title:false,formatter:function(value,options,row){
				return "<span class='tdcz'>"+
				(addPer?"<a title='新增下级' class='td_btn td_btn_add' href=javascript:addChildJgxx('"+row.id+"','"+row.jgmc+"');></a>":"")+
				(modifyPer?"<a title='修改' class='td_btn td_btn_change' href=javascript:openModify('"+row.id+"');></a>":"")+
				(deletePer?"<a title='删除' class='td_btn td_btn_delete' href=javascript:deleteJGXX('"+row.id+"');></a>":"")+
				"</span>";
			}},
			{name : "id",index : "id",hidden:true},
			{name : "sjjgid",index : "sjjgid",hidden:true},
			{name : "sjjgmc",index : "sjjgmc",hidden:true}
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
		pager : "#jgxxPager",
  	    mtype: "POST",
		viewrecords : true,
		sortable:false
     });
     //初始化验证
     formValidate("jgxxForm");
     //自适应屏幕
     $(window).resize(function(){
     	$("#jgxxGrid").setGridWidth($(window).width()*0.99);
     });
}
	
//删除机构信息
function deleteJGXX(jgid){
	qyConfirm("确定执行删除操作吗？",function(value){
		if(value){
			var result = doService(ctx+"/xtwh/jggl/delete","jgid",jgid);
			if(result){
				qyAlert("删除成功！",function(){
					refreshJGXX();
				});
			}
		}
	});
}

//刷新机构信息
function refreshJGXX(){
	var data = $("#searchForm").getFormJson();
	$("#jgxxGrid").setGridParam({"postData":data}).trigger("reloadGrid"); 
}

//打开新增机构信息弹出框
function openAddJgxx(){
	formReset("jgxxForm");
	$("#sjjgid").val(ssjgid);
	$("#sjjgmc").val(ssjgmc);
	$("#jglx").val("3");
	$("#jgjb").val("1");
	openJGXX("新增机构");
}

//打开机构信息
function openJGXX(title){
	qyOpen(title,700,"jgxxContent");
}

//打开新增下级机构信息弹出框
function addChildJgxx(id,sjjgmc){
	formReset("jgxxForm");
	$("#sjjgid").val(id);
	$("#sjjgmc").val(sjjgmc);
	$("#jglx").val("1");
	$("#jgjb").val("2");
	openJGXX("新增机构");
}

//打开修改机构信息弹出框
function openModify(jgid){
	formReset("jgxxForm");
	var result = doService(ctx+"/xtwh/jggl/queryJGXX","jgid",jgid);
	if (result) {
		bindForm("jgxxForm",result);
		if (!result.sjjgid) {
			$("#sjjgid").val("");
			$("#sjjgmc").val("无上级机构");
		}
		openJGXX("修改机构");
	}
}

//保存或修改机构信息
function saveJGXX(){
	qyConfirm("确定执行保存操作吗？",function(value){
		if(value){
			if ($("#jgxxForm").valid()) {
				var url = ctx+"/xtwh/jggl/saveJGXX";
				if ($("#jgid").val()!="") {
					var url = ctx+"/xtwh/jggl/updateJGXX";
				}
				var result = doFormService(url,"jgxxForm");
				if(result){
					qyAlert("保存成功！",function(){
						qyClose();
						refreshJGXX();
					});
				}
			}
		}
	});	
}