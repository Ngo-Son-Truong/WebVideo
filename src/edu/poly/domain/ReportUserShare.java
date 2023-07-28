package edu.poly.domain;

import java.util.Date;

public class ReportUserShare {
	private String fullname;
	private String senderMail;
	private String email;
	private Date shareDate;

	public ReportUserShare() {

	}

	public ReportUserShare(String fullname, String senderMail, String email, Date shareDate) {
		super();
		this.fullname = fullname;
		this.senderMail = senderMail;
		this.email = email;
		this.shareDate = shareDate;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

}
