<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="flex">
	<div class="w-xll w-auto-sm mx-auto py-5">
		<div class="p-4 d-flex flex-column h-100">
			<!-- brand -->
			<a href="home" class="navbar-brand align-self-center"> <svg width="32" height="32" viewBox="0 0 512 512"
					xmlns="http://www.w3.org/2000/svg" fill="currentColor">
                    <g class="loading-spin" style="transform-origin: 256px 256px">
                        <path
						d="M200.043 106.067c-40.631 15.171-73.434 46.382-90.717 85.933H256l-55.957-85.933zM412.797 288A160.723 160.723 0 0 0 416 256c0-36.624-12.314-70.367-33.016-97.334L311 288h101.797zM359.973 134.395C332.007 110.461 295.694 96 256 96c-7.966 0-15.794.591-23.448 1.715L310.852 224l49.121-89.605zM99.204 224A160.65 160.65 0 0 0 96 256c0 36.639 12.324 70.394 33.041 97.366L201 224H99.204zM311.959 405.932c40.631-15.171 73.433-46.382 90.715-85.932H256l55.959 85.932zM152.046 377.621C180.009 401.545 216.314 416 256 416c7.969 0 15.799-.592 23.456-1.716L201.164 288l-49.118 89.621z">
                        </path>
                    </g>
                </svg> <span class="hidden-folded d-inline l-s-n-1x align-self-center">NGHIENPHIM</span>
			</a>
			<!-- / brand -->
		</div>
		<div class="card">
			<div id="content-body">
				<div class="p-3 p-md-5">
					<p>
						<small class="text-muted">Enter your email below and we will send you a new password in your inbox</small>
					</p>
					<form action="forgot" method="post" role="form">
						<div class="row row-sm">
							<div class="col-sm-12">
								<div class="md-form-group float-label">
									<input class="md-input" type="text" name="username" required> <label>Username</label>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="md-form-group">
									<input class="md-input" type="email" name="email" required> <label>Email</label>
								</div>
							</div>
						</div>
						<jsp:include page="/common/inform.jsp"></jsp:include>
						<button type="submit" class="btn btn-outline-success mb-4">Retrieve</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>