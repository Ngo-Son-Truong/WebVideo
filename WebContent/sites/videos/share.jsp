<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="offset-3 col-6">
	<form action="sharevideo" method="post">
		<input type="hidden" name="videoId" value="${videoId}" />
		<div class="card mt-5">
			<div class="card-header">Send Video To Your Friends</div>
			<div class="card-body">
				<jsp:include page="/common/inform.jsp"></jsp:include>
				<div class="row">
					<div class="col">
						<div class="form-group">
							<label>Your Friend's emails:</label> <input type="text" class="form-control" name="email" placeholder="Emails">
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer">
				<button class="btn btn-success">Send</button>
			</div>
		</div>
	</form>
</div>