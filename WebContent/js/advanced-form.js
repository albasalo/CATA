$(document).ready(function() {
	$("[name='filter']").change(filterForm);
	
	function filterForm() {
		if($("[name='filter'] option:selected").val() == 0) {
			$("#filterForm").hide();
		}
		else {
			$("#filterForm").show();
		}
	}
	
	filterForm();
	
	$("#selectUser").multiselect();
});