<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
<title>${page.title}</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<base href="/WebVideo/" />
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="./assets/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="./assets/css/theme.css" type="text/css" />
</head>
<body>
	<div class="container-fluid">
		<nav class="row">
			<nav class="navbar navbar-expand-sm navbar-dark bg-light col">
				<!-- brand -->
				<a href="admin/home" class="navbar-brand w w-auto-sm"> <svg width="32" height="32" viewBox="0 0 512 512"
						xmlns="http://www.w3.org/2000/svg" fill="currentColor">
                    <g class="loading-spin" style="transform-origin: 256px 256px">
                        <path
							d="M200.043 106.067c-40.631 15.171-73.434 46.382-90.717 85.933H256l-55.957-85.933zM412.797 288A160.723 160.723 0 0 0 416 256c0-36.624-12.314-70.367-33.016-97.334L311 288h101.797zM359.973 134.395C332.007 110.461 295.694 96 256 96c-7.966 0-15.794.591-23.448 1.715L310.852 224l49.121-89.605zM99.204 224A160.65 160.65 0 0 0 96 256c0 36.639 12.324 70.394 33.041 97.366L201 224H99.204zM311.959 405.932c40.631-15.171 73.433-46.382 90.715-85.932H256l55.959 85.932zM152.046 377.621C180.009 401.545 216.314 416 256 416c7.969 0 15.799-.592 23.456-1.716L201.164 288l-49.118 89.621z">
                        </path>
                    </g>
                </svg><span class="hidden-folded d-inline l-s-n-1x w w-auto-sm">NGHIENPHIM</span>
				</a>
				<!-- / brand -->
				<!-- Navbar collapse -->
				<div class="collapse navbar-collapse order-2 order-lg-1" id="navbarToggler">
					<form class="input-group m-2 my-lg-0 ">
						<div class="input-group-prepend">
							<button type="button" class="btn no-shadow no-bg px-0 text-inherit">
								<i data-feather="search"></i>
							</button>
						</div>
						<input type="text" class="form-control no-border no-shadow no-bg typeahead" placeholder="Tìm kiếm phim..."
							data-plugin="typeahead">
					</form>
				</div>
				<ul class="nav navbar-menu order-1 order-lg-2">
					<li class="nav-item d-none d-sm-block"><a class="nav-link px-2" data-toggle="fullscreen"
						data-plugin="fullscreen" onclick="openFullscreen();"> <i data-feather="maximize"></i>
					</a></li>
					<li class="nav-item dropdown"><a class="nav-link px-2" data-toggle="dropdown"> <i data-feather="settings"></i>
					</a> <!-- ############ Setting START-->
						<div class="dropdown-menu dropdown-menu-center mt-3 w-mn animate fadeIn">
							<div class="setting px-3">
								<div class="mb-2 text-muted">
									<strong>Color:</strong>
								</div>
								<div class="mb-2">
									<label class="radio radio-inline ui-check ui-check-md"> <input type="radio" name="bg" value="">
										<i></i>
									</label> <label class="radio radio-inline ui-check ui-check-color ui-check-md"> <input type="radio" name="bg"
										value="bg-dark"> <i class="bg-dark"></i>
									</label>
								</div>
							</div>
						</div> <!-- ############ Setting END--></li>
					<!-- User dropdown menu -->
					<li class="nav-item dropdown"><a data-toggle="dropdown"
						class="nav-link d-flex align-items-center px-2 text-color"> <span class="avatar w-24 mr-5"
							style="margin: -2px; margin-top: 2px;"><img src="./assets/img/user.png"></span>
					</a>
						<div class="dropdown-menu dropdown-menu-right w mt-3 animate fadeIn">
							<c:if test="${not isLogin}">
								<a class="dropdown-item" href="login"><span>Login</span></a>
								<a class="dropdown-item" href="register"><span>Registration</span></a>
								<a class="dropdown-item" href="forgot"> <span>Forgot Password</span></a>
							</c:if>
							<c:if test="${isLogin}">
								<a class="dropdown-item" href="edit-profile"><span>Edit Profile</span></a>
								<a class="dropdown-item" href="change-password"><span>Change Password</span></a>
								<a class="dropdown-item" href="logout">Logout</a>
							</c:if>
						</div></li>
					<!-- Navarbar toggle btn -->
					<li class="nav-item d-lg-none"><a href="#" class="nav-link px-2" data-toggle="collapse" data-toggle-class
						data-target="#navbarToggler"> <i data-feather="search"></i>
					</a></li>
					<li class="nav-item d-lg-none"><a class="nav-link px-1" data-toggle="modal" data-target="#aside"> <i
							data-feather="menu"></i>
					</a></li>
				</ul>
			</nav>
		</nav>
		<div id="main" class="layout-row flex">
			<!-- ############ Aside START-->
			<div id="aside" class="page-sidenav no-shrink bg-white b-r nav-dropdown fade">
				<div class="sidenav h-100 modal-dialog bg-white b-r">
					<!-- sidenav top -->
					<!-- Flex nav content -->
					<div class="flex scrollable hover">
						<div class="nav-border b-primary" data-nav>
							<ul class="nav bg">
								<li class="nav-header hidden-folded"><span class="text-muted">Management</span></li>
								<li><a href="home" class="nav-text"><span class="nav-icon"><i data-feather="home"></i></span>Home</a></li>
								<li><a href="admin/video" class="nav-text"><span class="nav-icon"><i data-feather="film"></i></span>Video
										Management</a></li>
								<li><a href="admin/users" class="nav-text"><span class="nav-icon"><i data-feather="user"></i></span>User
										Management</a></li>
								<li><a href="admin/reports" class="nav-text"><span class="nav-icon"><i data-feather="flag"></i></span>Report
										Management</a></li>
								<div class="dropdown-divider"></div>
								<li><a href="logout" class="nav-text"><span class="nav-icon"><i data-feather="log-out"></i></span>Logout</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="flex">
				<!-- ############ Content START-->
				<div id="content" class="">
					<!-- ############ Main START-->
					<section>
						<jsp:include page="${page.contentUrl}"></jsp:include>
					</section>
				</div>
				<!-- ############ Content END-->
				<footer id="footer" class="page-footer b-t">
					<div class="page-container">
						<div class="padding">
							<div class="py-5 text-sm text-muted">
								<div class="row">
									<div class="col-12 col-sm-3"></div>
									<div class="col-12 col-sm-9">Copyright &copy; 2022 NGHIENPHIM. All rights reserved.</div>
								</div>
							</div>
						</div>
					</div>
				</footer>
			</div>
		</div>
	</div>
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="./assets/js/jquery.min.js"></script>
	<script src="./assets/js/popper.min.js"></script>
	<script src="./assets/js/bootstrap.min.js"></script>
	<!-- ajax page -->
	<script src="./assets/js/pjax.min.js"></script>
	<script src="./assets/js/ajax.js"></script>
	<!-- lazyload plugin -->
	<script src="./assets/js/lazyload.config.js"></script>
	<script src="./assets/js/lazyload.js"></script>
	<script src="./assets/js/plugin.js"></script>
	<!-- scrollreveal -->
	<script src="./assets/js/scrollreveal.min.js"></script>
	<!-- feathericon -->
	<script src="./assets/js/feather.min.js"></script>
	<script src="./assets/js/plugins/feathericon.js"></script>
	<!-- theme -->
	<script src="./assets/js/theme.js"></script>
	<script src="./assets/js/utils.js"></script>
	<c:if test="${not empty page.scriptUrl }">
		<jsp:include page="${page.scriptUrl }"></jsp:include>
	</c:if>
	<script>
		$('#myTab a').on('click', function(event) {
			event.preventDefault()
			$(this).tab('show')
		})
	</script>
</body>
</html>