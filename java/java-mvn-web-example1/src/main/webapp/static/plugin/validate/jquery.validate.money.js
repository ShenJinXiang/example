/*****************************************************************
                  jQuery Validate (金额验证)      
*****************************************************************/


$(function(){
	
	$(document).on('keyup','.money',function(){
		var reg =  /^[1-9]\d{0,19}\.\d{1,2}|[1-9][0-9]{0,19}\.|[1-9]\d{0,19}|0\.\d{1,2}|0\.|0$/;
		var $val = $(this).val();
		if(reg.test($val)){
			$(this).val($val.match(reg));
		}else{
			$(this).val("");
		}
	});
	
	/** 
	 * 2014-12-10  添加人Hjs
	 * 小数点后面6位
	 */
	$(document).on('keyup','.money_point_six',function(){
		var reg =  /^[1-9]\d{0,19}\.\d{1,6}|[1-9][0-9]{0,19}\.|[1-9]\d{0,19}|0\.\d{1,6}|0\.|0$/;
		var $val = $(this).val();
		if(reg.test($val)){
			$(this).val($val.match(reg));
		}else{
			$(this).val("");
		}
	});
	
});