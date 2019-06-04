package org.sideproject.simplestore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
//@Table(name = "Category", uniqueConstraints={@UniqueConstraint(columnNames={"category"})})
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false)
    private String category;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="category")
    private List<Listing> listings;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	} 
	
	public static class CategoryBuilder{
		Category category;
		
		public CategoryBuilder() {
			this.category = new Category();
		}
		
		public CategoryBuilder setCategory(String category) {
			this.category.setCategory(category);
			return this;
		}
		
		public CategoryBuilder setListing(List<Listing> listings) {
			this.category.setListings(listings);
			return this;
		}
		
		public Category build() {
			return this.category;
		}
	}
}
