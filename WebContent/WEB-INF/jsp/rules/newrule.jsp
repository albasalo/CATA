<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/table.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#academics').dataTable({
					"aaSorting": [[ 0, "asc" ]]
				});
				
				$('#books').dataTable({
					"aaSorting": [[ 0, "asc" ]]
				});
				
				$('#handbooks').dataTable({
					"aaSorting": [[ 0, "asc" ]]
				});
				
				$('#urls').dataTable({
					"aaSorting": [[ 0, "asc" ]]
				});
				$('#urls tbody tr').live('click', function () {
					var nTds = $('td', this);
					var urlID = $(nTds[0]).text();
					$("#inputSourceID").append('<input name="source.sourceID" value="' + urlID + '" style="display:none" />');
				});
				
				$('#others').dataTable({
					"aaSorting": [[ 0, "asc" ]]
				});
			});
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				var counterLemma = -1;
				var counter = -1;
				
				$("#plus-lemma").click(function() {
					if(counterLemma < 200) {
						counterLemma = counterLemma + 1;
						$("#lemmasDiv").append('<span id="lemma' + counterLemma + '">' +
								'<br /><label class="label">Padrão incorreto</label><br />' +
								'<input class="input_border width450" type="text" maxlength=200 name="lemmas[' + counterLemma + '].pattern"/><br/>' +
								'<label class="label">Sugestão</label><br />' +
								'<input class="input_border width450" type="text" maxlength=200 name="lemmas[' + counterLemma + '].suggestion"/><br/>' +
								'</span>');
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
								'<br /><label class="label">Padrão incorreto</label><br />' +
								'<input class="input_border width450" type="text" maxlength=200 name="exactMatchings[' + counter + '].pattern"/><br/>' +
								'<label class="label">Sugestão</label><br />' +
								'<input class="input_border width450" type="text" maxlength=200 name="exactMatchings[' + counter + '].suggestion"/><br/>' +
								'</span>');
					}
				});
				
				$('#minus-exact').click(function() {
					if(counter != -1) {
						var exactMatching = "#exactMatching" + counter;
						$(exactMatching).remove();
						counter = counter - 1;
					}
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
				
				showTable();
			});
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				if($('#modal').length > 0) {
				    $('#modal').fadeIn().css({ 'width': Number(500)})
				    	.prepend('<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
				
				    var popMargTop = ($('#modal').height() + 80) / 2;
				    var popMargLeft = ($('#modal').width() + 80) / 2;
				
				    $('#modal').css({
				        'margin-top' : -popMargTop,
				        'margin-left' : -popMargLeft
				    });
				
				    $('body').append('<div id="fade"></div>');
				    $('#fade').css({ 'filter': 'alpha(opacity=80)'}).fadeIn(); 
				
				    return false;
				}
			});
			
			$('a.close, #fade').live('click', function() {
				$('#fade , .popup_block').fadeOut( function() {
				    $('#fade, a.close').remove();
				});
				return false;
			});
		</script>
		<title>Cadastrar nova Regra de estilo</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/messages.jsp"%>
		
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<form id="custom_form" class="width650" action="<c:url value='/rules/newrule'/>" method="post">
				<fieldset>
					<legend>Cadastrar nova Regra de estilo</legend>
					
					<div class="single_form_element">
						<label class="label" for="name">Categoria*</label>
						<br />
						<select id="selectCategory" name="newRule.category" class="input_border width250">
							<c:forEach var="category" items="${ruleCategories}"  >
								<option value="${category}">${category.categoryDescription}</option>								
							</c:forEach>
						</select>
					</div>
					
					<div class="single_form_element">
						<label class="label" for="name">Tipo*</label>
						<br />
						<select id="selectType" name="newRule.type" class="input_border width250">
							<c:forEach var="type" items="${typesOfRules}"  >
									<option value="${type}">${type.typeDescription}</option>									
							</c:forEach>
						</select>
					</div>
					<br />
					<b>Lemas:</b><br />
					<div class="single_form_element">
						<div id="lemmasDiv" class="indent">
							<label class="label" for="lemmaPattern">Padrão incorreto</label>
							<br />
							<input id="lemmaPattern" class="input_border width450" type="text" maxlength=200 name="newRule.lemmaElement.pattern" />
							<br />
							<label class="label" for="lemmaSuggestion">Sugestão</label>
							<br />
							<input id="lemmaSuggestion" class="input_border width450" type="text" maxlength=200 name="newRule.lemmaElement.suggestion" />
							<br />
						</div>
					</div>
					<div class="single_form_element">
						<div class="indent">
							<span id="plus-lemma" class="change_form_element">[+]</span>
							<span id="minus-lemma" class="change_form_element">[-]</span>
						</div>
					</div>
					<br />
					<b>Expressões exatas:</b><br />
					<div class="single_form_element">
						<div id="exactMatchingsDiv" class="indent">
							<label class="label" for="exactPattern">Padrão incorreto</label>
							<br />
							<input id="exactPattern" class="input_border width450" type="text" maxlength=200 name="newRule.exactMatchingElement.pattern"/>
							<br />
							<label class="label" for="exactSuggestion">Sugestão</label>
							<br />
							<input id="exactSuggestion" class="input_border width450" type="text" maxlength=200 name="newRule.exactMatchingElement.suggestion"/>
							<br />
						</div>
					</div>
					<div class="single_form_element">
						<div class="indent">
							<span id="plus-exact" class="change_form_element">[+]</span>
							<span id="minus-exact" class="change_form_element">[-]</span>
						</div>
					</div>
					<br />
					<div class="single_form_element">
						<label class="label" for="explanation">Explicação</label>
						<br />
						<textarea id="explanation" style="width: 600px; height: 100px !important" class="input_border" maxlength=400
							name="newRule.explanation"></textarea>
						<br />
					</div>
					<br />
									
					<div class="single_form_element">
						<label class="label" for="source"><a name="source">Referência*</a></label>
						<div id="inputSourceID"></div>
					</div>
					<div class="indent">	
						<div class="single_form_element">
							Tipo: 
							<select id="selectType" name="source.type" class="input_border width250">
								<c:forEach var="type" items="${typesOfSources}"  >
									<option value="${type}">${type.typeDescription}</option>								
								</c:forEach>
							</select>
						</div>
						
						<div id="academicsDiv" style="display: none">
							<div class="grid">
							<table cellpadding="0" cellspacing="0" border="0" class="display" id="academics">
								<thead>
									<tr>
										<th>Título</th>
										<th>Autor</th>
										<th>Mais informações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="academic" items="${academics}">
										<tr>
											<td><c:out value="${academic.title}" /></td>
											<td><c:out value="${academic.authors}" /></td>
											<td class="center"><a href="<c:url value='/rules/viewsource/${academic.sourceID}'/>"><img src="<c:url value='/css/images/plus-icon.png'/>"></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="spacer"></div>
							</div>
						</div>
						
						<div id="booksDiv" style="display: none">
							<div class="grid">
							<table cellpadding="0" cellspacing="0" border="0" class="display" id="books">
								<thead>
									<tr>
										<th>Título</th>
										<th>Autor</th>
										<th>Mais informações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="book" items="${books}">
										<tr>
											<td><c:out value="${book.title}" /></td>
											<td><c:out value="${book.authors}" /></td>
											<td class="center"><a href="<c:url value='/rules/viewsource/${book.sourceID}'/>"><img src="<c:url value='/css/images/plus-icon.png'/>"></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="spacer"></div>
							</div>
						</div>
						
						<div id="handbooksDiv" style="display: none">
							<div class="grid">
							<table cellpadding="0" cellspacing="0" border="0" class="display" id="handbooks">
								<thead>
									<tr>
										<th>Título</th>
										<th>Autor</th>
										<th>Mais informações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="handbook" items="${handbooks}">
										<tr>
											<td><c:out value="${handbook.title}" /></td>
											<td><c:out value="${handbook.authors}" /></td>
											<td class="center"><a href="<c:url value='/rules/viewsource/${handbook.sourceID}'/>"><img src="<c:url value='/css/images/plus-icon.png'/>"></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="spacer"></div>
							</div>
						</div>
						
						<div id="urlsDiv" style="display: none">
							<div class="grid">
							<table cellpadding="0" cellspacing="0" border="0" class="display" id="urls">
								<thead>
									<tr>
										<th>ID</th>
										<th>URL</th>
										<th>Mais informações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="url" items="${urls}">
										<tr>
											<td><c:out value="${url.sourceID}"/></td>
											<td><a href="${url.url}"><c:out value="${url.title}"/></a></td>
											<td class="center"><a href="<c:url value='/rules/viewsource/${url.sourceID}'/>"><img src="<c:url value='/css/images/plus-icon.png'/>"></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="spacer"></div>
							</div>
						</div>
						
						<div id="othersDiv" style="display: none">
							<div class="grid">
							<table cellpadding="0" cellspacing="0" border="0" class="display" id="others">
								<thead>
									<tr>
										<th>Descrição</th>
										<th>Mais informações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="other" items="${others}">
										<tr>
											<td><c:out value="${other.moreInformation}" /></td>
											<td class="center"><a href="<c:url value='/rules/viewsource/${other.sourceID}'/>"><img src="<c:url value='/css/images/plus-icon.png'/>"></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="spacer"></div>
							</div>
						</div>
						
						<br />
						<div class="small">Não encontrou a referência para sua regra?
							<a href="<c:url value='/rules/newsource'/>">Cadastre uma nova referência</a>.</div>
					</div>
					<br /><br />
				
					<input class="single_form_element button" type="submit" value="Cadastrar">
				</fieldset>
				</form>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>