$(document).ready(function() {
	$("[name='filter']").change(filterForm);
	
	function filterForm() {
		if($("[name='filter'] option:selected").val() == 0) {
			$("#filterForm").hide();
		}
		else {
			$("#filterForm").show();
			showMultiselect();
		}
	}
	
	filterForm();
	
	$("#selectUser").multiselect({
		noneSelectedText: "Selecione os usuários"
	}).multiselectfilter();
	$("#selectSource").multiselect({
		noneSelectedText: "Selecione as referências bibliográficas"
	}).multiselectfilter();
	
	$("[name='selectedFilter']").change(showMultiselect);
	
	function showMultiselect() {
		if($("[name='selectedFilter'] option:selected").val() == 0) {
			$("#selectSourceDiv").hide();
			$("#selectUserDiv").show();
		}
		else {
			$("#selectUserDiv").hide();
			$("#selectSourceDiv").show();
		}
	}
});