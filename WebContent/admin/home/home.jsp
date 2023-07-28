<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="padding">
	<div class="heading py-2 d-flex ">
		<div>
			<div class="text-muted text-sm sr-item"></div>
			<h2 class="text-highlight sr-item">All Videos</h2>
		</div>
		<span class="flex"></span>
	</div>
	<div class="row">
		<c:forEach var="item" items="${videos}">
			<div class="col-4 col-sm-4 col-md-3 pb-3" data-category="all">
				<div class="list-item">
					<div class="media media-16x9">
						<a href="admin/detail?videoId=${item.id}" class="ajax media-content"
							style="background-image: url(${empty item.poster ? 'images/laptop.jpg' : item.poster}"></a>
						<div class="media-action "></div>
					</div>
					<div class="list-content">
						<div class="list-body">
							<p style="display: flex; justify-content: space-between;">
								<span><a href="admin/detail?videoId=${item.id}" class="list-title title ajax h-1x">${item.title}</a></span> <span>
								</span>
							</p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<div class="row">
	<div class="col">
		<nav aria-label="Page navigation">
			<ul class="pagination justify-content-center">
				<li class="page-item ${indexPage>1?'':'disabled'}"><a class="page-link" href="admin/home?page=${indexPage-1}"
					aria-label="Previous"> <span>&laquo;</span> <span class="sr-only">Previous</span>
				</a></li>
				<c:forEach begin="1" end="${numberPage}" var="i">
					<li class="page-item ${indexPage==i?'active':''}"><a class="page-link" href="admin/home?page=${i}">${i}</a></li>
				</c:forEach>
				<li class="page-item ${indexPage == numberPage?'disabled':''}"><a class="page-link"
					href="admin/home?index=${indexPage+1}" aria-label="Next"> <span>&raquo;</span> <span class="sr-only">Next</span>
				</a></li>
			</ul>
		</nav>
	</div>
</div>