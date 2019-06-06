package org.sideproject.simplestore.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Listing")
public class Listing {
	@Id
    @GeneratedValue
    private Integer id;
	
	@Column
	private String title;

	@Column
	private String description;

	@Column
	private double price; 

	@Column
	private String userName;

	@Column
	private LocalDate creationTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Category_id")
	private Category category;
	
	@ManyToOne
    @JoinColumn(name = "User_id")
	private User user;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDate getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDate creationTime) {
		this.creationTime = creationTime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public static class ListingBuilder{
		Listing listing;
		
		public ListingBuilder() {
			this.listing = new Listing(); 
		}
		
		public ListingBuilder setTitle(String title) {
			this.listing.setTitle(title);
			return this;
		}
		
		public ListingBuilder setDescription(String description) {
			this.listing.setDescription(description);
			return this;
		}
		
		public ListingBuilder setPrice(double price) {
			this.listing.setPrice(price);
			return this;
		}
		
		public ListingBuilder setUserName(String userName) {
			this.listing.setUserName(userName);
			return this;
		}
		
		public ListingBuilder setCreationTime(LocalDate creationTime) {
			this.listing.setCreationTime(creationTime);
			return this;
		}
		
		public ListingBuilder setCategory(Category category) {
			this.listing.setCategory(category);
			return this;
		}
		
		public ListingBuilder setUser(User user) {
			this.listing.setUser(user);
			return this;
		}
		
		public Listing build() {
			return this.listing;
		}
	}
}
