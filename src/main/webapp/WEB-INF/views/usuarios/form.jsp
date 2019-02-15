<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<c:url value="/" var="contextPath" />

<tags:pageTemplate	titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">
	<link href="${contextPath}resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />
	
	<section id="index-section" class="container middle">
		<h1>Cadastro de usuários</h1>
		<form:form action="${s:mvcUrl('UC#gravar').build() }" cssStyle="padding-bottom: 40px;" commandName="usuario" >
			<div class="form-group">
				<form:label path="nome">Nome</form:label>
				<form:input path="nome" cssClass="form-control" />
				<form:errors path="nome" />
			</div>
			<div class="form-group">
				<form:label path="email" >E-mail</form:label>
				<form:input path="email" cssClass="form-control" />
				<form:errors path="email" />
			</div>
			<div class="form-group">
				<form:label path="senha" >Senha</form:label>
				<form:password path="senha" cssClass="form-control" />
				<form:errors path="senha" />
			</div>
			<div class="form-group">
				<label for="senhaRepetida">Senha repetida</label>
				<input type="password" id="senhaRepetida" name="senhaRepetida" class="form-control" />
				<form:errors>
					<c:out value="">
						${erros}
					</c:out>
				</form:errors>
			</div>
			<input type="submit" class="btn btn-primary" value="Cadastrar" />
		</form:form>
	</section>
</tags:pageTemplate>
