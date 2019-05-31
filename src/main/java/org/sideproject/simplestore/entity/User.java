package org.sideproject.simplestore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false)
    private String userName;
    
    @OneToMany(mappedBy="user")
    private List<Listing> listings = new ArrayList<Listing>();
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	} 
	
	public static class UserBuilder{
		User user;
		
		public UserBuilder() {
			this.user = new User(); 
		}
		
		public UserBuilder setUserName(String userName) {
			this.user.setUserName(userName);
			return this;
		}
		
		public UserBuilder setListings(List<Listing> listings) {
			this.user.setListings(listings);
			return this;
		}
		
		public User build() {
			return this.user;
		}
	}
}
