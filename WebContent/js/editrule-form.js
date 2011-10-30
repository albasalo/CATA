$(document).ready(function() {
	function showSources() {
		$("#changeSource").hide();
		$("#selectSource").show();
	};
	
	$("#changeSource").click(function() {
		showSources();
		showTable();
		return false;
	});
	
	$('#academics').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"sPaginationType": "four_button"
	});
	$('#academics tbody tr').live('click', function() {
		var nTds = $('td', this);
		newID = $(nTds[0]).text();
		newSourceIDForm();
	});
	
	$('#books').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"sPaginationType": "four_button"
	});
	$('#books tbody tr').live('click', function() {
		var nTds = $('td', this);
		newID = $(nTds[0]).text();
		newSourceIDForm();
	});			
	
	var lastID = 0;
	var newID = 0;
				
	function newSourceIDForm() {
		if(lastID > 0) {
			var lastRowID = "#row" + lastID;
			$(lastRowID).removeClass("selected");
		}
		lastID = newID;
		var newRowID = "#row" + newID;
		$(newRowID).addClass("selected");			
		
		$("#selectedSourceID").val(newID);
	};
	
	$('#handbooks').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"sPaginationType": "four_button"
	});
	$('#handbooks tbody tr').live('click', function() {
		var nTds = $('td', this);
		newID = $(nTds[0]).text();
		newSourceIDForm();
	});	
	
	$('#urls').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"sPaginationType": "four_button"
	});
	$('#urls tbody tr').live('click', function() {
		var nTds = $('td', this);
		newID = $(nTds[0]).text();
		newSourceIDForm();
	});	
	
	$('#others').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"sPaginationType": "four_button"
	});
	$('#others tbody tr').live('click', function() {
		var nTds = $('td', this);
		newID = $(nTds[0]).text();
		newSourceIDForm();
	});
	
	$("[name='source.type']").change(showTable);
	
	function showTable() {
		if($("[name='source.type'] option:selected").val() == "ACADEMIC_PUBLISHING") {
			$("#booksDiv").hide();
			$("#handbooksDiv").hide();
			$("#urlsDiv").hide();
			$("#othersDiv").hide();
			$("#academicsDiv").show();
		}
		else if($("[name='source.type'] option:selected").val() == "BOOK") {
			$("#academicsDiv").hide();
			$("#handbooksDiv").hide();
			$("#urlsDiv").hide();
			$("#othersDiv").hide();
			$("#booksDiv").show();
		}
		else if($("[name='source.type'] option:selected").val() == "HANDBOOK") {
			$("#booksDiv").hide();
			$("#urlsDiv").hide();
			$("#othersDiv").hide();
			$("#academicsDiv").hide();
			$("#handbooksDiv").show();
		}
		else if($("[name='source.type'] option:selected").val() == "INTERNET") {
			$("#booksDiv").hide();
			$("#handbooksDiv").hide();
			$("#othersDiv").hide();
			$("#academicsDiv").hide();
			$("#urlsDiv").show();
		}
		else {
			$("#booksDiv").hide();
			$("#handbooksDiv").hide();
			$("#urlsDiv").hide();
			$("#academicsDiv").hide();
			$("#othersDiv").show();
		}
	};
	
	var counterLemma = $("#lemmasSize").val() - 1;
	var counter = $("#matchingsSize").val() - 1;
	
	$("#plus-lemma").click(function() {
		if(counterLemma < 200) {
			counterLemma = counterLemma + 1;
			$("#lemmasDiv").append('<span id="lemma' + counterLemma + '">' +
					'<label class="label">Padrão incorreto</label><br />' +
					'<input class="input_border width450" type="text" maxlength=200 name="lemmas[' + counterLemma + '].pattern"/><br/>' +
					'<label class="label">Sugestões</label><br />' +
					'<input class="input_border width450" type="text" maxlength=200 name="lemmas[' + counterLemma + '].suggestions"/><br/>' +
					'<br /></span>');
		}
	});
	
	$('#minus-lemma').click(function() {
		if(counterLemma != -1) {
			var lemma = "#lemma" + counterLemma;
			$(lemma).remove();
			counterLemma = counterLemma - 1;
		}
	});
	
	$("#plus-exact").click(function() {
		if(counter < 200) {
			counter = counter + 1;
			$("#exactMatchingsDiv").append('<span id="exactMatching' + counter + '">' +
					'<label class="label">Padrão incorreto</label><br />' +
					'<input class="input_border width450" type="text" maxlength=200 name="exactMatchings[' + counter + '].pattern"/><br/>' +
					'<label class="label">Sugestões</label><br />' +
					'<input class="input_border width450" type="text" maxlength=200 name="exactMatchings[' + counter + '].suggestions"/><br/>' +
					'<br /></span>');
		}
	});
	
	$('#minus-exact').click(function() {
		if(counter != -1) {
			var exactMatching = "#exactMatching" + counter;
			$(exactMatching).remove();
			counter = counter - 1;
		}
	});
});