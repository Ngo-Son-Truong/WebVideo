<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="padding">
	<div class="plyrist plyrist_video card" id="plyrist_video" data-plugin="plyr">
		<div class="d-md-flex">
			<div class="r-l flex bg-black">
				<div class="plyr__video-embed" id="player">
					<iframe width="100%" height="700" src="${video.code}" frameborder="0" allow="autoplay; picture-in-picture"
						allowfullscreen></iframe>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="d-md-flex mb-3">
				<h1 class="h3 my-3 flex">${video.title}<br /> <span style="font-size: 14px">${video.views} views</span>
				</h1>
			</div>
			<div class="card">
				<div class="card-header">
					<strong>Description</strong>
				</div>
				<div class="flex p-md-2">${video.description}</div>
			</div>
		</div>
	</div>
</div>