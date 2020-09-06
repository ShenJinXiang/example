/**
 * 发送AJAX请求
 * @param serviceName: action的方法,格式为 package.class.method
 * @param 其后的参数以一对对“参数名-参数值”的方式
 * @example doService("admin.Main.changePwd", "oldpwd",1, "newpwd",2)
 */
function doService() {
	var params = doService.arguments;
	if(params.length%2 == 0) {
		alert('参数错误!');
		return;
	}
	var serviceName = params[0];
	var urlParams = '';
	urlParams = arrayToUrl(urlParams, params, 1);
	return postService(serviceName, urlParams);
}

/**
 * 发送AJAX请求
 * @private
 * @param serviceName: action的方法,格式为 package.class.method
 * @param urlParams: ajax请求的URL参数
 * @example postService("admin.Main.changePwd", "oldpwd=1&newpwd=2")
 */
function postService(serviceName, urlParams) 
{
	var jsonObject;
	$.ajax({
		type: "POST",
		url: serviceName,
		data: urlParams,
		dataType: "json",
		async: false,  
		success: function(json)
		{
			if(json.success){
				jsonObject = json.message;
			}else{
				//执行失败
				qyError(json.message);
				jsonObject = null;
			} 
			
		}
	});
	return jsonObject;
}

function qyOpen(title,width,contentId){
	layer.open({
		title:title,
	    type: 1,
	    shadeClose:true,
	    area: [width+"px", "auto"], //宽高
	    content: $("#"+contentId)
	});
}

function qyOpenPage(title,width,pagePath){
	layer.open({
		title:title,
	    type: 2,
	    shadeClose:true,
	    area: [width+"px", "auto"], //宽高
	    content: pagePath
	});
}


function qyOpenPage(title,width,height,pagePath){
	layer.open({
		title:title,
	    type: 2,
	    shadeClose:true,
	    area: [width+"px", height+"px"], //宽高
	    content: pagePath
	});
}

function qyClose(){
	layer.closeAll();
}

/**
 * 发送AJAX请求
 * @param serviceName: action的方法,格式为 package.class.method
 * @param formId: Form的Id
 * @param 其后的参数以一对对“参数名-参数值”的方式
 * @example doFormService("admin.Main.changePwd", "userForm", "oldpwd",1, "newpwd",2)
 */
function doFormService() {
	var params = doFormService.arguments;
	if(params.length < 2 || params.length%2 != 0) {
		alert('参数错误!');
		return;
	}
	var serviceName = params[0];
	var formId = params[1];
	var urlParams = '';
//	urlParams = formToUrl(urlParams, formId);
	urlParams = arrayToUrl(urlParams, params, 2);
	return postFormService(formId,serviceName, urlParams);
}

/**
 * 把Form的所有项生成name1=value1&name2=value2&...的URL参数字符串
 * @private
 * @param urlParams: 传入字符串
 * @param formId: Form的Id
 */
function formToUrl(urlParams, formId) 
{
	var els = document.getElementById(formId).elements;
	for(var i = 0, max = els.length; i < max; i++) 
	{
		var el = els[i];
		var id = el.id;
		var value = el.value;
		if (!id) continue;
		if (urlParams != '')
			urlParams += "&";
		urlParams += id + "=" + value;
	}
	//alert(urlParams);
	return urlParams;
	
}

/**
 * 把数组的所有项生成name1=value1&name2=value2&...的URL参数字符串
 * @private
 * @param urlParams: 传入字符串
 * @param params: 函数所有参数的数组
 * @param start: 从第几个参数开始截取
 */
function arrayToUrl(urlParams, params, start) 
{
	for (var i = start; i < params.length - 1; i = i + 2) 
	{
		if (urlParams != '')
			urlParams += "&";
		urlParams += params[i] + "=" + params[i+1];
	}
	return urlParams;
}

/**
 * 绑定Form
 * @param formId: Form的Id
 * @param data: Json数据
 * @example bindForm("orgForm", jsonObject)
 */
function bindForm(formId, data) {
	var form = document.getElementById(formId);
	for (var i = 0; i < form.elements.length;i++) {
		var element = form.elements[i];
		//if (!data[element.id]) continue;
		if (data[element.id] == undefined) continue;
		var val = data[element.id];
		switch (element.type) {
		case "textarea": ;
		case "text": ;
		case "hidden": ;
		case "password": element.value = val; break;
		case "radio" : 
		case "checkbox" : 
			if (val instanceof Array) element.checked = (val.indexOf(element.value) > -1);
			else element.checked = (element.value ==val);
			break;
		case "select-one" : 
		case "select-multiple" : 
			for (var j = 0; j < element.options.length; j++) {
				var option = element.options[j];
				if (val instanceof Array) {
					option.selected = (val.indexOf(option.value) > -1);
				} else {
					option.selected = (option.value == val);
				}
			}
			break;
		}
	}
}

//ajax提交form表单
function postFormService(formId,serviceName, urlParams) 
{
	var jsonObject;
 	 $('#'+formId).ajaxSubmit({
		type: "POST",
		url: serviceName.replace(/\./g, "/") + "?"+urlParams,
		dataType: "json",
		async: false,
		success: function(json)
		{
			if(json.success){
				jsonObject = json.message;
			}else{
				//执行失败
				qyError(json.message);
				jsonObject = null;
			} 
			
		}
	});
	return jsonObject;
}

/**
 * 单元格渲染tpl
 * @param {} value
 * @param {} meta
 * @return {}
 */
function renderTpl(value,meta){
	meta.tdAttr = 'data-qtip="'+value+'"';
	return value;
}

/**
 * 
 * @param {} msg
 * @param {} animateTarget
 * @param {} fn
 */
function qyConfirm(msg,func){
	layer.msg(msg, {
	    time: 0 //不自动关闭
	    ,btn: ['确定', '取消']
	    ,yes: function(index){
	        layer.close(index);
	        func(true);
	    }
	    ,no:function(index){
	    	func(false);
	    }
	});
}

/**
 * 
 * @param {} msg
 * @param {} fn
 */
function qyAlert(msg,fn){
	layer.msg(msg, {
		time:1000
	},fn);
}

/**
 * 
 * @param {} msg
 * @param {} time
 * @param {} fn
 */
function qyAlertByTime(msg,time,fn){
	layer.msg(msg, {
		time:time
	},fn);
}

/**
 * 
 * @param {} msg
 * @param {} fn
 */
function qyError(msg,fn){
	layer.msg(msg, {
		time:2000,
		shift:6
	},fn);
}

/**
 * 
 * @param {} msg
 * @param {} time
 * @param {} fn
 */
function qyErrorByTime(msg,time,fn){
	layer.msg(msg, {
		time:time,
		shift:6
	},fn);
}

/**
 * 导出excel
 * @param grid
 * @param sqlid
 * @param type ['single' or 'all']
 */
function exportExcel(grid,sqlid,type,excelName){
	if(grid.getStore().getTotalCount()<=0){
		qyError("没有数据",function(){});
		return;
	}
	var dataIndexes = "";		//数据集
	var columnNames = "";		//表头集
	var columns = grid.columns;
	var column = null;
	for(var i=0;i<columns.length;i++){
		column = columns[i];
		if(column.dataIndex==""){
			continue;
		}
		dataIndexes += column.dataIndex+",";
		columnNames += column.text+",";
	}
	dataIndexes = dataIndexes.substring(0,dataIndexes.length-1);
	columnNames = columnNames.substring(0,columnNames.length-1);
	//参数
	var params = Ext.clone(grid.getStore().proxy.extraParams);
	params.sqlid = sqlid;
	params.page = grid.getStore().currentPage;	//当前页
	params.limit = grid.getStore().pageSize;	//每页条数
	params.dataIndexes = dataIndexes;
	params.columnNames = columnNames;
	params.type = type;
	var result = postService(ctx+"/common/exportExcel",params);
	if(result){
		excelName = excelName==null?"":excelName;
		window.location.href = ctx+"/common/downLoadFile?targetName="+result+"&excelName="+excelName;
	}
}

/**
 * 四舍五入
 * @param {} numberRound 原数字
 * @param {} roundDigit 小数位数
 * @return {} 处理后的数字
 */
function roundFun(numberRound,roundDigit){  
    var	digit;  
    digit=1;  
    digit=Math.pow(10,roundDigit)  
    return	(Math.round(numberRound*digit)/digit);  
} 

function formValidate(formid)
{
	$("#"+formid).validate({
		meta:"validate",
	    invalidHandler: function(form, validator) {
	        $.each(validator.invalid,function(key,value){
            	$("#"+key).focus();
            	$("#"+key).select();
            	layer.tips(value, "#"+key);
	        	return false;
	        }); //这里循环错误map，只报错第一个
	    },
	    errorPlacement:function(error, element) {
	    },
	    onkeyup: false,
	    onfocusout:false
	});
}

function formReset(formId){
	var $form = $("#"+formId);
	$form.get(0).reset();
	$form.find("input[type='hidden']").val("");
}

$.fn.getFormJson = function()  
{  
   var o = {};  
   var a = this.serializeArray();  
   $.each(a, function() {  
       if (o[this.name]) {  
           if (!o[this.name].push) {  
               o[this.name] = [o[this.name]];  
           }  
           o[this.name].push(this.value || '');  
       } else {  
           o[this.name] = this.value || '';  
       }  
   });  
   return o;  
};

//打开新页签
function loadPage(_zyid, _text, _url,isRefresh) {
	var tabs = window.parent.parent.parent.$("#tabs");
	var header = tabs.children(".tabs_div").children(".tabs_header");
	var container = tabs.children(".tabs_container");
	if (!tabs.find(".tabs_header span[zyid='" + _zyid + "']").length) {
		var _header = "<span zyid='" + _zyid + "' class='active'>" + _text + "<i></i></span>";
		var _container = "<iframe zyid='" + _zyid + "' src='" + _url + "'></iframe>";
		$(header).children().removeClass('active').end().append(_header);
		$(container).children().hide().end().append(_container);
		bindContextMenu(_zyid);
	} else {
		tabs.find(".tabs_header span[zyid='" + _zyid + "']:eq(0)").trigger("click");
		if(isRefresh){
			tabs.find(".tabs_header span[zyid='" + _zyid + "']:eq(0)").trigger("click");
			tabs.find(".tabs_container iframe[zyid='" + _zyid + "']").attr("src",_url);
		}
	}
}

function bindContextMenu(zyid) {
	var tabs = window.parent.parent.parent.$("#tabs");
	tabs.find(".tabs_header span[zyid='" + zyid + "']:eq(0)").contextMenu('myMenu1', {
		menuStyle: {
		},
		itemStyle: {
			'border': '0px',
			'font': "12px/20px '微软雅黑'"
		},
		itemHoverStyle: {
			'border': '0px',
			'font': "12px/20px '微软雅黑'"
		},
		bindings: {
			'closeCurrent': function (t) {
				tabs.find(".tabs_header span[zyid='" + zyid + "'] i:eq(0)").trigger('click');
			},
			'closeOther': function (t) {
				tabs.find(".tabs_header span[zyid!='" + zyid + "'] i").trigger('click');
			},
			'closeAll': function (t) {
				tabs.find(".tabs_header span[zyid!='00'] i").trigger('click');
			},
			'refreshCurrent': function (t) {
				tabs.find(".tabs_container iframe[zyid='" + zyid + "']").get(0).contentWindow.location.reload(true);
			}
		}
	});
}



var timeKit = {
	getDateTimeStr: function (date) {
		var year = date.getFullYear(),
			month = date.getMonth() + 1,
			d = date.getDate(),
			hour = date.getHours(),
			minite = date.getMinutes(),
			second = date.getSeconds();
		monthStr = (month > 9) ? ('' + month) : ('0' + month);
		dStr = (d > 9) ? ('' + d) : ('0' + d);
		hourStr = (hour > 9) ? ('' + hour) : ('0' + hour);
		miniteStr = (minite > 9) ? ('' + minite) : ('0' + minite);
		secondStr = (second > 9) ? ('' + second) : ('0' + second);
		return year + "-" + monthStr + "-" + dStr + " " + hourStr + ":" + miniteStr + ":" + secondStr;
	}

};