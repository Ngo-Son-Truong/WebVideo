<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col mt-4">
	<div class="b-b">
		<div class="nav-active-border b-primary bottom">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item" role="presentation"><a class="nav-link active" id="videoEdition-tab" data-toggle="tab"
					href="#videoEdition" role="tab" aria-controls="videoEdition" aria-selected="true">VIDEO EDITION</a></li>
				<li class="nav-item" role="presentation"><a class="nav-link" id="videoList-tab" data-toggle="tab"
					href="#videoList" role="tab" aria-controls="videoList" aria-selected="false">VIDEO LIST</a></li>
			</ul>
		</div>
	</div>
	<div class="tab-content" id="myTabContent">
		<div class="tab-pane fade show active" id="videoEdition" role="tabpanel" aria-labelledby="videoEdition-tab">
			<form action="" method="post" enctype="multipart/form-data">
				<div class="card col-100">
					<div class="card-body">
						<jsp:include page="/common/inform.jsp"></jsp:include>
						<div class="row">
							<div class="col-3">
								<div class="border p-3">
									<img src="${video.poster !=null ?video.poster: 'images/doraemon.png'}" alt="" class="img-thumbnail">
									<div class="input-group mb-3 mt-3">
										<div class="input-group-prepend">
											<span class="input-group-text">Upload</span>
										</div>
										<div class="custom-file">
											<input type="file" class="custom-file-input" name="cover" /><label for="cover">Choose File</label>
										</div>
									</div>
								</div>
							</div>
							<div class="col-9">
								<div class="form-group">
									<label>Youtube ID</label> <input type="text" class="form-control" name="id" value="${video.id}"
										placeholder="Youtube ID">
								</div>
								<div class="form-group">
									<label>Video Title</label> <input type="text" class="form-control" name="title" value="${video.title}"
										placeholder="Video Title">
								</div>
								<div class="form-group">
									<label>View Count</label> <input type="text" name="views" id="viewCount" value="${video.views}"
										class="form-control" placeholder="View Count">
								</div>
								<div class="form-check form-check-inline">
									<div class="checkbox">
										<label class="ui-check"> <input type="radio" name="active" ${video.active? 'checked':''}> <i
											class="dark-white"></i> Active
										</label>
									</div>
									<div class="checkbox">
										<label class="ui-check pl-3"> <input type="radio" name="active" ${! video.active? 'checked':''}>
											<i class="dark-white"></i> Inactive
										</label>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<label>Description</label>
								<textarea name="description" cols="30" rows="5" class="form-control">${video.description}</textarea>
							</div>
							<div class="col">
								<label>Code</label> <input name="code" value="${video.code}" class="form-control"></input>
							</div>
						</div>
					</div>
					<div class="card-footer text-muted">
						<button class="btn btn-outline-primary" formaction="admin/video/create">Create</button>
						<button class="btn btn-outline-success" formaction="admin/video/update">Update</button>
						<button class="btn btn-outline-danger" formaction="admin/video/delete?videoId=${video.id}">Delete</button>
						<button class="btn btn-outline-warning" formaction="admin/video/reset">Reset</button>
					</div>
				</div>
			</form>
		</div>
		<div class="tab-pane fade" id="videoList" role="tabpanel" aria-labelledby="videoList-tab">
			<table class="table table-bordered">
				<tr style="color: blue; font-weight: bold;">
					<td>Video ID</td>
					<td>Video Title</td>
					<td>View Count</td>
					<td>Status</td>
					<td>&nbsp;</td>
				</tr>
				<c:forEach var="item" items="${videos}">
					<tr style="color: white;">
						<td>${item.id}</td>
						<td>${item.title}</td>
						<td>${item.views}</td>
						<td>${item.active? 'Active': 'Deactive'}</td>
						<td><a href="admin/video/edit?videoId=${item.id}" class="btn btn-icon btn-white"><i data-feather="edit"></i></a>
							<a href="admin/video/delete?videoId=${item.id}" class="btn btn-icon btn-white"><i data-feather="x"></i></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>