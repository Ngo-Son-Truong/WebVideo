<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col">
		<c:if test="${not empty message}">
			<div class="alert alert-success font-weight-bold">${message}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger font-weight-bold">${error}</div>
		</c:if>
	</div>
</div>