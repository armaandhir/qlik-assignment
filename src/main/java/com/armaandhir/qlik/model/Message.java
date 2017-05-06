package com.armaandhir.qlik.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Message() {
		
	}
	
	public Message(String author, String text) {
		this.author = author;
		this.text = text;
		this.isPalindrome = findPalindrome(text);
		// As it gets updated in the database as current timestamp automatically
		this.createdAt = null;
		//this.isDeleted = 0;
	}
	
	@Id
	@GeneratedValue
	@Column(name="id")
    @JsonView(View.Summary.class)
	private long id;
	
	@Column(name="author")
	@JsonView(View.Summary.class)
	private String author;
	
    /**
     * Max Limit is 250
     */
	@Column(name="text")
	@JsonView(View.Summary.class)
	private String text;
	
	@Column(name="created_at")
	@JsonView(View.Summary.class)
	private Date createdAt;
	
	private transient String isPalindrome;
	
	//private short isDeleted;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	/*
	public short getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(short isDeleted) {
		this.isDeleted = isDeleted;
	}
	*/

	public String getIsPalindrome() {
		if (isPalindrome == null) {
			isPalindrome = findPalindrome(text);
		}
		return isPalindrome;
	}
	
	/*
	public void setIsPalindrome(short isPalindrome) {
		this.isPalindrome = isPalindrome;
	}
	*/
	
	/**
	 * @param text		String to be checked for palindrome
	 * @return short	0 or 1
	 */
	private String findPalindrome(String text) {
		String string = text.replaceAll("[^a-zA-Z]+", "").trim().toLowerCase();
	    int len = string.length();
	    for (int i = 0; i < len / 2; ++i) {
	        if (string.charAt(i) != string.charAt(len - 1 - i)) {
	            return "no";
	        }
	    }
	    return "yes";
	}
	
}
