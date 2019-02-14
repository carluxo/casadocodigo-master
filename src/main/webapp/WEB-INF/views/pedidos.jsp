<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate
	titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">
	<section id="index-section" class="container middle">
		<h1>Lista de pedidos atuais</h1>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Valor</th>
					<th>Data Pedido</th>
					<th>Títulos</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pedidos}" var="pedido">
					<tr>
						<td>${pedido.id}</td>
						<td>
						<fmt:setLocale value="pt_br" />${pedido.valor }
						<td><fmt:formatDate value="${pedido.data}" pattern="dd/MM/yyyy" /></td>
						<td>
							<c:forEach items="${pedido.produtos}" var="produto" varStatus="loop">
								<c:out value="${produto.titulo } ${!loop.last ? ', ' : '' }" />
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</tags:pageTemplate>
