$(function () {
    $('.popupConf').each(function() {
        var duration = 200;
        var hideDelay = 200;
        var hideDelayTimer = null;

        var beingDisplayed = false;
        var displayed = false;

        var trigger = $('.highlightedText', this);
        var conf = $('.popup', this);

        $([trigger.get(0), conf.get(0)])
		.mouseover(function() {
            if (hideDelayTimer)
				clearTimeout(hideDelayTimer);
            if (displayed || beingDisplayed) {
                return;
            }
			else {
                beingDisplayed = true;

				var t = $('.highlightedText');
				var position = t.position();
				var left = position.left;

                conf.css({
                    top: +20,
                    left: +left,
                    display: 'block'
                }).animate({
                    opacity: 1
                }, duration, 'linear', function() {
                    beingDisplayed = false;
                    displayed = true;
                });
            }

            return false;
    	})
		.mouseout(function () {
        	if (hideDelayTimer)
				clearTimeout(hideDelayTimer);
            hideDelayTimer = setTimeout(function () {
            	hideDelayTimer = null;
            	conf.animate({
                	opacity: 0
            	}, duration, 'linear', function () {
                	displayed = false;
                	conf.css('display', 'none');
            	});
            }, hideDelay);

            return false;
    	});
    });
});