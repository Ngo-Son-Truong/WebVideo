package edu.poly.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the Favorites database table.
 * 
 */
@Entity
@Table(name = "Favorites")
@NamedQuery(name = "Favorite.findAll", query = "SELECT f FROM Favorite f")
public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	private int id;

	@Column(name = "LikeDate")
	private Date likeDate;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;

	// bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name = "VideoId")
	private Video video;

	public Favorite() {
	}

	public Favorite(int id, Date likeDate) {
		super();
		this.id = id;
		this.likeDate = likeDate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLikeDate() {
		return this.likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
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