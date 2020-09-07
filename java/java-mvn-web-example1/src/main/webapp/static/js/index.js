
$(function(){
	headerMenu("#user");
	headerMenu("#handle");
	fold();
	nav();
	maxHeight();
	// if (dlsbZyid) {
	// 	initZyByZyid(dlsbZyid);
	// }
})

$(window).resize(function(){
	maxHeight();
})

function headerMenu(obj){
	
	$(obj).click(function(){
		
		if($(this).hasClass('active')){
			
			$(this).removeClass('active');
			
		}else{
			
			$(this).addClass('active');
			
		}
		
		
	});
	
	$(document).click(function(e){
		
		
		if( $(e.target).parents('#handle').length==0 ){
			$("#handle").removeClass();
		}
		if($(e.target).parents('#user').length ==0 ){
			$("#user").removeClass();
		}
	}); 
}


function fold(){
	$("#fold").click(function(){
		
		if($("nav").hasClass('active')){
			
			$("nav").removeClass('active');
			
		}else{
			$("nav").addClass('active');
			
		}
		
	})
}

function nav (){
	$("#nav a").click(function(){
		var href = $(this).attr('href');
		if(href&&href!=""&&href!="#"){ 
			$("#nav a").removeClass('on_active');
			$(this).addClass('on_active');
			var _text = $(this).find('.title').text();
			var _url = ctx+$(this).attr('href');
			var _zyid = $(this).attr('zyid');
			//打开新页签
			loadPage(_zyid,_text,_url);
		}else{    
			if($(this).parents('dt').length){
				if($(this).parents('dt').hasClass('active')){
//                                   $("#nav").children('dt').removeClass('active').nextUntil('dt').hide();
					$(this).parents('dt').removeClass('active').next('dd').hide();
				}else{
                                    $("#nav").children('dt').removeClass('active').nextUntil('dt').hide();
					$(this).parents('dt').addClass('active').next('dd').show();
				}
			}
		}
		return false;
	});
	$('.tabs_header').on('click','span',function(){
		var _index = $(this).index();
		$(this).addClass('active').siblings('span').removeClass('active');
		$('.tabs_container iframe').eq(_index).show().siblings().hide();
	});
	
	
	$('.tabs_header').on('click','i',function(){
		var _index = $(this).parent().index();
		var _activeIndex =  _index -1;
		$(this).parent().remove();
		$('.tabs_container iframe').eq(_index).remove();
		if(!$(".tabs_header .active").length){
			$('.tabs_header span').eq(_activeIndex).addClass('active');
			$('.tabs_container iframe').eq(_activeIndex).show();
		}
	});
	
	
	
}

function maxHeight(){
	$("#tabs").height($("#main").height());
}

/**
 * 修改用户密码
 */
function updateXGMM() {
	url = ctx + "/yygl/wdzl/updateXGMM?judge="+1;
	qyOpenPage("修改密码", 500, 280, url);
 
 
}

/**
 * 关闭页面
 */
function logOut(){
	closeWindows();
}

function closeWindows() {
	var explorer =navigator.userAgent ;
	//ie 
	if (explorer.indexOf("MSIE") >= 0) {
		  window.open('', '_self', '');
	        window.close();
	}
	//firefox 
	else if (explorer.indexOf("Firefox") >= 0) {
		window.open('','_parent','');window.close();
	}
	//Chrome
	else if(explorer.indexOf("Chrome") >= 0){
       window.opener=null;window.close();
	}
	//Opera
	else if(explorer.indexOf("Opera") >= 0){
		window.opener=null;
		window.open('','_self');
		window.close();
	}
	//Safari
	else if(explorer.indexOf("Safari") >= 0){
		window.opener=null;
		window.open('','_self');
		window.close();
	} 
	//Netscape
	else if(explorer.indexOf("Netscape")>= 0) { 
		window.opener=null;
		window.open('','_self');
		window.close();
	}else {
	    window.close();
	 }
}

