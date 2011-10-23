$(document).ready(function() {
	var lastID = 0;
	var newID = 0;
	var isSelected = false;
				
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
		
		$("#inputRuleID").append('<input id="selectedRuleID' + newID + '" name="ruleToBeUpdated.ruleID" value="' + newID + '" style="display:none" />');
	};
	
	$('#rules tbody tr').live('click', function() {
		isSelected = true;
		var nTds = $('td', this);
		newID = $(nTds[0]).text();
		selectRule();
	});
	
	$("#editrule-form").submit(function() {
		if(isSelected) {
			return true;
		}
		else {
			showEditModal();
			return false;
		}
	});
});