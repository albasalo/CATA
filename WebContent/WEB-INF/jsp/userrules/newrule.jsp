<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/user-menu.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/form.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-table.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/modal-window.css'/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value='/css/table.css'/>" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery.simplemodal.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/newrule-form.js'/>"></script>
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
		<script type="text/javascript">
			function showModal(ruleID) {
				var modal = "#source" + ruleID;
				if($(modal).length > 0) {
				    $(modal).fadeIn().css({ 'width': Number(550)}).prepend('<a href="#" class="close"><img src="<c:url value='/css/images/close_pop.png'/>" class="btn_close" title="Fechar" alt="Fechar" /></a>');
				
				    var popMargTop = ($(modal).height() + 80) / 2;
				    var popMargLeft = ($(modal).width() + 80) / 2;
				
				    $(modal).css({
				        'margin-top' : -popMargTop,
				        'margin-left' : -popMargLeft
				    });
				
				    $('body').append('<div id="fade"></div>');
				    $('#fade').css({'filter' : 'alpha(opacity=80)'}).fadeIn();
				
				    return false;
				}
			};
			
			$('a.close, #fade').live('click', function() {
				$('#fade , .popup_block').fadeOut( function() {
				    $('#fade, a.close').remove();
				});
				return false;
			});
		</script>
		
		<title>Cadastrar nova Regra de Estilo</title>
	</head>
	
	<body>
		<%@ include file="../shared/header.jsp"%>
		<%@ include file="../shared/messages.jsp"%>		
		<%@ include file="../shared/user-menu.jsp"%>
		
		<div id="page">
			<div id="content">
				<form id="custom_form" class="width800" action="<c:url value='/userrules/newrule'/>" method="post">
				<fieldset>
					<legend>Cadastrar nova Regra de Estilo</legend>
					
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
					<b>Lemas:</b>
					<div class="single_form_element">
						<div id="lemmasDiv" class="indent">
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
						<textarea id="explanation" style="width: 600px; height: 100px !important"
							class="input_border" maxlength=400 name="newRule.explanation"></textarea>
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
										<th>ID</th>
										<th>Título</th>
										<th>Autor(es)</th>
										<th>Mais informações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="academic" items="${academics}">
										<tr id="row${academic.sourceID}">
											<td><c:out value="${academic.sourceID}" /></td>
											<td><c:out value="${academic.title}" /></td>
											<td><c:out value="${academic.authors}" /></td>
											<td class="center">
												<img onclick="showModal(${academic.sourceID});" src="<c:url value='/css/images/plus-icon.png'/>">
												<div id="source${academic.sourceID}" class="popup_block" style="display:none">
													<div class="popup_content" style="text-align: left">
														<b>Mais informações sobre a referência selecionada</b><br />
														<br />
														<table class="modal-table">
															<tr>
																<td><b>Título:</b></td>
																<td><c:out value="${academic.title}" /></td>
															</tr>
															<tr>
																<td><b>Autor(es):</b></td>
																<td><c:out value="${academic.authors}" /></td>
															</tr>
															<c:if test = "${academic.institution != null}">
																<tr>
																	<td><b>Instituição:</b></td>
																	<td><c:out value="${academic.institution}" /></td>
																</tr>
															</c:if>
															<c:if test = "${academic.date != null}">
															<tr>
																<td><b>Data:</b></td>
																<td><c:out value="${academic.date}" /></td>
															</tr>
															</c:if>
															<c:if test = "${academic.moreInformation != null}">
															<tr>
																<td><b>Descrição:</b> </td>
																<td><c:out value="${academic.moreInformation}" /></td>
															</tr>
															</c:if>
															<tr>
																<td><b>Usuário:</b> </td>
																<td><i><c:out value="${academic.user.name}" /></i> cadastrou esta referência</td>
															</tr>
														</table>
													</div>	
												</div>
											</td>
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
										<th>ID</th>
										<th>Título</th>
										<th>Autor(es)</th>
										<th>Mais informações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="book" items="${books}">
										<tr id="row${book.sourceID}">
											<td><c:out value="${book.sourceID}" /></td>
											<td><c:out value="${book.title}" /></td>
											<td><c:out value="${book.authors}" /></td>
											<td class="center">
												<img onclick="showModal(${book.sourceID});" src="<c:url value='/css/images/plus-icon.png'/>">
												<div id="source${book.sourceID}" class="popup_block" style="display:none">
													<div class="popup_content" style="text-align: left">
														<b>Mais informações sobre a referência selecionada</b><br />
														<br />
														<table class="modal-table">
															<tr>
																<td><b>Título:</b></td>
																<td><c:out value="${book.title}" /></td>
															</tr>
															<tr>
																<td><b>Autor(es):</b></td>
																<td><c:out value="${book.authors}" /></td>
															</tr>
															<c:if test = "${book.publisher != null}">
																<tr>
																	<td><b>Editora:</b></td>
																	<td><c:out value="${book.publisher}" /></td>
																</tr>
															</c:if>
															<c:if test = "${book.date != null}">
															<tr>
																<td><b>Data:</b></td>
																<td><c:out value="${book.date}" /></td>
															</tr>
															</c:if>
															<c:if test = "${book.moreInformation != null}">
															<tr>
																<td><b>Descrição:</b> </td>
																<td><c:out value="${book.moreInformation}" /></td>
															</tr>
															</c:if>
															<tr>
																<td><b>Usuário:</b> </td>
																<td><i><c:out value="${book.user.name}" /></i> cadastrou esta referência</td>
															</tr>
														</table>
													</div>	
												</div>
											</td>
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
										<th>ID</th>
										<th>Título</th>
										<th>Autor(es)</th>
										<th>Mais informações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="handbook" items="${handbooks}">
										<tr id="row${handbook.sourceID}">
											<td><c:out value="${handbook.sourceID}" /></td>
											<td><c:out value="${handbook.title}" /></td>
											<td><c:out value="${handbook.authors}" /></td>
											<td class="center">
												<img onclick="showModal(${handbook.sourceID});" src="<c:url value='/css/images/plus-icon.png'/>">
												<div id="source${handbook.sourceID}" class="popup_block" style="display:none">
													<div class="popup_content" style="text-align: left">
														<b>Mais informações sobre a referência selecionada</b><br />
														<br />
														<table class="modal-table">
															<tr>
																<td><b>Título:</b></td>
																<td><c:out value="${handbook.title}" /></td>
															</tr>
															<tr>
																<td><b>Autor(es):</b></td>
																<td><c:out value="${handbook.authors}" /></td>
															</tr>
															<c:if test = "${handbook.publisher != null}">
																<tr>
																	<td><b>Editora:</b></td>
																	<td><c:out value="${handbook.publisher}" /></td>
																</tr>
															</c:if>
															<c:if test = "${handbook.date != null}">
															<tr>
																<td><b>Data:</b></td>
																<td><c:out value="${handbook.date}" /></td>
															</tr>
															</c:if>
															<c:if test = "${handbook.moreInformation != null}">
															<tr>
																<td><b>Descrição:</b> </td>
																<td><c:out value="${handbook.moreInformation}" /></td>
															</tr>
															</c:if>
															<tr>
																<td><b>Usuário:</b> </td>
																<td><i><c:out value="${handbook.user.name}" /></i> cadastrou esta referência</td>
															</tr>
														</table>
													</div>	
												</div>
											</td>
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
										<th>Disponível em</th>
										<th>Mais informações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="url" items="${urls}">
										<tr id="row${url.sourceID}">
											<td><c:out value="${url.sourceID}"/></td>
											<td><a href="<c:out value="${url.url}" />" target="_blank"><c:out value="${url.title}"/></a></td>
											<td class="center">
												<img onclick="showModal(${url.sourceID});" src="<c:url value='/css/images/plus-icon.png'/>">
												<div id="source${url.sourceID}" class="popup_block" style="display:none">
													<div class="popup_content" style="text-align: left">
														<b>Mais informações sobre a referência selecionada</b><br />
														<br />
														<table class="modal-table">
															<tr>
																<td><b>URL:</b></td>
																<td><a href="<c:out value="${url.url}" />" target="_blank"><c:out value="${url.title}"/></a></td>
															</tr>
															<c:if test = "${url.moreInformation != null}">
															<tr>
																<td><b>Descrição:</b> </td>
																<td><c:out value="${url.moreInformation}" /></td>
															</tr>
															</c:if>
															<tr>
																<td><b>Usuário:</b> </td>
																<td><i><c:out value="${url.user.name}" /></i> cadastrou esta referência</td>
															</tr>
														</table>
													</div>	
												</div>
											</td>
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
										<th>ID</th>
										<th>Descrição</th>
										<th>Mais informações</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="other" items="${others}">
										<tr id="row${other.sourceID}">
											<td><c:out value="${other.sourceID}" /></td>
											<td><c:out value="${other.moreInformation}" /></td>
											<td class="center">
												<img onclick="showModal(${other.sourceID});" src="<c:url value='/css/images/plus-icon.png'/>">
												<div id="source${other.sourceID}" class="popup_block" style="display:none">
													<div class="popup_content" style="text-align: left">
														<b>Mais informações sobre a referência selecionada</b><br />
														<br />
														<table class="modal-table">
															<tr>
																<td><b>Descrição:</b> </td>
																<td><c:out value="${other.moreInformation}" /></td>
															</tr>
														</table>
													</div>	
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="spacer"></div>
							</div>
						</div>
						
						<br />
						<div class="small">Não encontrou a referência para sua regra?
							<a href="<c:url value='/userrules/newsource'/>">Cadastre uma nova referência</a>.</div>
					</div>
					<br /><br />
				
					<input class="single_form_element button" type="submit" value="Cadastrar">
				</fieldset>
				</form>
			</div>
		</div>
		
<%@ include file="../shared/footer.jsp"%>