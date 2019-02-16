<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<c:url value="/" var="contextPath" />

<tags:pageTemplate
	titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">
	<link href="${contextPath}resources/css/bootstrap.min.css"
		rel="stylesheet" type="text/css" media="all" />

	<section id="index-section" class="container middle">
		<h1>Permissões do usuário ${usuario.nome }</h1>
		
		<form:form commandName="usuario" method="POST" action="${s:mvcUrl('UC#gravarPermissoes').build()}">
			<form:hidden path="email"/>
			
			<div class="form-group">
				<form:checkboxes items="${permissoes}" path="roles" title="Permissões" cssClass="form-check-input" />	
			</div>
			<input type="submit" class="btn btn-primary" value="Gravar" />		
		</form:form>
	</section>
</tags:pageTemplate>
