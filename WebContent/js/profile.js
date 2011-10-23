$(document).ready(function() {
	var lastID = 0;
	var newID = 0;
				
	function selectRule() {
		if(lastID > 0) {
			var lastRowID = "#row" + lastID;
			$(lastRowID).removeClass("selected");

			var lastSelectedSource = "#selectedRuleID" + lastID;
			$("#inputRuleID").remove(lastSelectedSource);
		}
		lastID = newID;
		var newRowID = "#row" + newID;
		$(newRowID).addClass("selected");					
		
		$("#inputRuleID").append('<input id="selectedRuleID' + newID + '" name="rule.ruleID" value="' + newID + '" style="display:none" />');
	};
	
	$('#rules tbody tr').live('click', function() {
		var nTds = $('td', this);
		newID = $(nTds[0]).text();
		selectRule();
	});
});