<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="padding">
	<div class="heading py-2 d-flex ">
		<div>
			<div class="text-muted text-sm sr-item"></div>
			<h5 class="text-highlight sr-item">Featured</h5>
		</div>
		<span class="flex"></span>
	</div>
	<div class="row">
		<c:forEach var="item" items="${videos}">
			<div class="col-4 col-sm-4 col-md-3 pb-3" data-category="all">
				<div class="list-item">
					<div class="media media-16x9">
						<a href="detail?videoId=${item.id}" class="ajax media-content"
							style="background-image: url(${ empty item.poster ? 'images/laptop.jpg' : item.poster}"></a>
						<div class="media-action "></div>
					</div>
					<div class="list-content">
						<div class="list-body">
							<p style="display: flex; justify-content: space-between;">
								<span><a href="detail?videoId=${item.id}" class="list-title title ajax h-1x">${item.title}</a></span> <span>
									<c:choose>
										<c:when test="${favlist != null}">
											<c:set var="init" value="0" />
											<c:forEach var="videoFav" items="${favlist}">
												<c:if test="${item.id == videoFav.id}">
													<c:set var="init" value="${init+1}" />
												</c:if>
											</c:forEach>
											<c:choose>
												<c:when test="${init == '1'}">
													<a href="unlike?videoId=${item.id}"><i data-feather="thumbs-down"></i></a>&nbsp;&nbsp;&nbsp;
													<a href="sharevideo?videoId=${item.id}"><i data-feather="share-2"></i></a>
												</c:when>
												<c:otherwise>
													<a href="like?videoId=${item.id}"><i data-feather="thumbs-up"></i></a>&nbsp;&nbsp;&nbsp;
													<a href="sharevideo?videoId=${item.id}"><i data-feather="share-2"></i></a>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<a href="like?videoId=${item.id}"><i data-feather="thumbs-up"></i></a>&nbsp;&nbsp;&nbsp;
											<a href="sharevideo?videoId=${item.id}"><i data-feather="share-2"></i></a>
										</c:otherwise>
									</c:choose>
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
				<li class="page-item ${indexPage>1?'':'disabled'}"><a class="page-link" href="home?page=${indexPage-1}"
					aria-label="Previous"> <span>&laquo;</span> <span class="sr-only">Previous</span>
				</a></li>
				<c:forEach begin="1" end="${numberPage}" var="i">
					<li class="page-item ${indexPage==i?'active':''}"><a class="page-link" href="home?page=${i}">${i}</a></li>
				</c:forEach>
				<li class="page-item ${indexPage == numberPage?'disabled':''}"><a class="page-link"
					href="home?index=${indexPage+1}" aria-label="Next"> <span>&raquo;</span> <span class="sr-only">Next</span>
				</a></li>
			</ul>
		</nav>
	</div>
</div>
