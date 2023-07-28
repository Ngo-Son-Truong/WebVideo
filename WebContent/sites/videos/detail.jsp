<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="padding">
	<div class="plyrist plyrist_video card" id="plyrist_video" data-plugin="plyr">
		<div class="d-md-flex">
			<div class="r-l flex bg-black">
				<div class="plyr__video-embed" id="player">
					<iframe width="100%" height="700" src="${video.code}" frameborder="0"
						allow="autoplay; picture-in-picture" allowfullscreen></iframe>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-8">
			<div class="d-md-flex mb-3">
				<h1 class="h3 my-3 flex">${video.title}<br /> <span style="font-size: 14px">${video.views}
						views</span>
				</h1>
				<form method="post">
					<input type="hidden" name="videoId" value="${video.id}">
					<div class="card-footer" style="text-align: right;">
						<c:choose>
							<c:when test="${favorite != null}">
								<button formaction="detail/unlike?videoId=${video.id}" class="btn btn-light" style="">
									<i data-feather="thumbs-down"></i>
								</button>&nbsp;&nbsp;&nbsp;
							</c:when>
							<c:otherwise>
								<button formaction="detail/like?videoId=${video.id}" class="btn btn-light">
									<i data-feather="thumbs-up"></i>
								</button>&nbsp;&nbsp;&nbsp;
							</c:otherwise>
						</c:choose>
						<button formaction="detail/share?videoId=${video.id}" class="btn btn-light">
							<i data-feather="share-2"></i>
						</button>
					</div>
				</form>
			</div>
			<div class="card">
				<div class="card-header">
					<strong>Description</strong>
				</div>
				<div class="flex p-md-2">${video.description}</div>
			</div>
			<div class="card" id="comment-">
				<div class="card-header">
					<strong>Comments</strong>
				</div>
				<div class="powr-comments" id="1e35c0c3_1645292565"></div>
				<script src="https://www.powr.io/powr.js?platform=blogger"></script>
			</div>
		</div>
		<div class="col-md-4">
			<div class="card">
				<div class="card-header">Recommended</div>
				<div class="list list-row">
					<c:forEach var="item" items="${videos}">
						<div class="list-item" data-id="5">
							<div>
								<a class="media media-16x9 r" href="detail?videoId=${item.id}"> <span
									class="media-content" style="background-image: url(${item.poster})"> </span>
								</a>
							</div>
							<div class="flex">
								<a href="detail?videoId=${item.id}" class="item-author text-color ">${item.title}</a>
								<div class="item-except text-muted text-sm h-1x">${item.description}</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>