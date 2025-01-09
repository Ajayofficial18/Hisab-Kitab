package com.hisabKitab.springProject.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // Specify the table name for the entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	private String fullName;
	
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	@Column(unique = true)
	private String contactNo;
	

	@ManyToMany
	@JoinTable(name = "friendship", // Join table name
			joinColumns = @JoinColumn(name = "user_id"), // Foreign key for this user
			inverseJoinColumns = @JoinColumn(name = "friend_id") // Foreign key for the friend user
	)
	@JsonIgnore
	private List<UserEntity> friends = new ArrayList<>(); // Set of friends

	 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	 @JsonIgnore
	    private List<TransactionComment> comments;
	
	// Constructors, Getters, Setters

	// Default constructor
	public UserEntity() {
	}

	// Parameterized constructor
	public UserEntity(String fullName, String email, String password, String role, String contactNo) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contactNo = contactNo;
	}

	

	public UserEntity(String fullName, String email, String password, String contactNo) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.contactNo = contactNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public List<UserEntity> getFriends() {
		return friends;
	}

	public void setFriends(List<UserEntity> friends) {
		this.friends = friends;
	}

	// Convenience method to add a friend (and ensure bidirectional friendship)
	public void addFriend(UserEntity friend) {
		this.friends.add(friend);
		friend.getFriends().add(this); // Ensure mutual friendship
	}

	// Convenience method to remove a friend
	public void removeFriend(UserEntity friend) {
		this.friends.remove(friend);
		friend.getFriends().remove(this); // Ensure mutual removal
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<TransactionComment> getComments() {
		return comments;
	}

	public void setComments(List<TransactionComment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", password=" + password
				+ ", role=" + role + ", contactNo=" + contactNo + ", friends=" +  "abcd"+ "]";
	}

	
}
