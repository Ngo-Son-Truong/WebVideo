package edu.poly.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the Shares database table.
 * 
 */
@Entity
@Table(name = "Shares")
@NamedQuery(name = "Share.findAll", query = "SELECT s FROM Share s")
public class Share implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	private int id;

	@Column(name = "Emails")
	private String emails;

	@Column(name = "ShareDate")
	private Date shareDate;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;

	// bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name = "VideoId")
	private Video video;

	public Share() {
	}

	public Share(int id, String emails, Date shareDate) {
		super();
		this.id = id;
		this.emails = emails;
		this.shareDate = shareDate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmails() {
		return this.emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public Date getShareDate() {
		return this.shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}