package edu.poly.domain;

import java.util.Date;

public class ReportUser {
	private String id;
	private String fullname;
	private String email;
	private Date likeDate;

	public ReportUser() {

	}

	public ReportUser(String username, String fullname, String email, Date likeDate) {
		super();
		this.id = username;
		this.fullname = fullname;
		this.email = email;
		this.likeDate = likeDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

}
