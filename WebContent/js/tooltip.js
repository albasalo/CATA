$(document).ready(function() {
	var i = 0;
	while(true) {
		var mistake = "#mistake" + i;
		if($(mistake).length > 0) {
			var content = "#tooltipcontent" + i;
			$(mistake).qtip({
	    		content: $(content).html(),
	    		show: 'mouseover',
	    		hide: {
	    	          fixed: true,
	    	          delay: 200
	    	    },
	    		position: {
		    		corner: {
		    	    	target: 'bottomMiddle',
		    	        tooltip: 'topMiddle'
		    	    }
	    		},
	    		style: {
	    			border: {
	                     width: 5,
	                     radius: 10,
	                     color:'#999999'
	                },
	                width: 420,
	                padding: 8,
	    			tip: true,
                	name: 'light'
	    		}
    		});
		}
		else {
			break;
		}
		i = i + 1;
	}
});