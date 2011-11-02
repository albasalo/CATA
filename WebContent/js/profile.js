$(document).ready(function() {
	var lastID = 0;
	var newID = 0;
	var isSelected = false;
				
	function selectRule() {
		isSelected = true;
		if(lastID > 0) {
			var lastRowID = "#row" + lastID;
			$(lastRowID).removeClass("selected");
		}
		lastID = newID;
		var newRowID = "#row" + newID;
		$(newRowID).addClass("selected");					
		
		$("#selectedRuleID").val(newID);
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
	
	$("#deleteRule").click(function() {
		if(isSelected) {
			$("#deleterule-form").append('<input id="selectedRuleID' + newID + '" name="ruleToBeDeleted.ruleID" value="' + newID + '" style="display:none" />');
			$("#deleterule-form").submit();
			return false;
		}
		else {
			showDeleteModal();
			return false;
		}
	});
});