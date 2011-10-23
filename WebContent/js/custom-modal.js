function showCustomModal(modal, close) {
	if($(modal).length > 0) {
	    $(modal).fadeIn().css({ 'width': Number(550)}).prepend(close);
	
	    var popMargTop = ($(modal).height() + 80) / 2;
	    var popMargLeft = ($(modal).width() + 80) / 2;
	
	    $(modal).css({
	        'margin-top' : -popMargTop,
	        'margin-left' : -popMargLeft
	    });
	
	    $('body').append('<div id="fade"></div>');
	    $('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn();
	    
	    $('a.close, #fade').live('click', function() {
			$('#fade , .popup_block').fadeOut( function() {
			    $('#fade, a.close').remove();
			});
			return false;
		});
	
	    return false;
	}
};