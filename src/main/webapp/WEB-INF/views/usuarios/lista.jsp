<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">
	<c:url value="/resources/imagens/" var="imagesPath" />
	
	<section id="index-section" class="container middle">
		<h1><a href="${s:mvcUrl('UC#form').build() }">Novo Usuário</a></h1>
		<h1>Lista de usuários</h1>		
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>		
		<table>
			<thead>
				<tr>
					<th>Nome</th>
					<th>E-mail</th>
					<th>Permissões</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="usuario">
					<tr>
						<td>${usuario.nome }</td>
						<td>${usuario.email }</td>
						<td>${usuario.roles }</td>
						<td>
							<a href="#"><img src="${imagesPath }roles.png" alt="Permissões" /></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</tags:pageTemplate>
