<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="padding">
	<div class="heading py-2 d-flex ">
		<div>
			<div class="text-muted text-sm sr-item"></div>
			<h5 class="text-highlight sr-item">My Favorite</h5>
		</div>
		<span class="flex"></span>
	</div>
	<div class="row">
		<c:forEach var="item" items="${listVideo}">
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
								<span><a href="detail?videoId=${item.id}" class="list-title title ajax h-1x">${item.title}</a></span>
								<span> <a href="unlike?videoId=${item.id}"><i data-feather="thumbs-down"></i></a>&nbsp;&nbsp;&nbsp;
									<a href="sharevideo?videoId=${item.id}"><i data-feather="share-2"></i></a>
								</span>
							</p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>