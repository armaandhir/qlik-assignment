package com.armaandhir.qlik.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * The entity class for Message.
 * It will persist to the table named as Message which is the name of the class.
 * @author armaan
 *
 */
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
		this.createdAt = new Date();
		//this.isDeleted = 0;
	}
	
	/**
	 * ID of the message and is the primary key
	 */
	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
    @JsonView(View.Summary.class)
	private long id;
	
	/**
	 * Stores the author/creator name of the message
	 */
	@Column(name="author", columnDefinition="varchar(16) NOT NULL")
	@JsonView(View.Summary.class)
	private String author;
	
    /**
     * The message placeholder.
     * Max Limit is 250 characters.
     */
	@Column(name="text", columnDefinition="varchar(251) NOT NULL")
	@JsonView(View.Summary.class)
	private String text;
	
	/**
	 * Stores the time of creation of the message.
	 */
	@Column(name="created_at", columnDefinition="TIMESTAMP NOT NULL")
	@JsonView(View.Summary.class)
	private Date createdAt;
	
	/**
	 * This won't be persisted. Just used for determining palindrome
	 * This field is added as a property in json response when a specific message is requested.
	 */
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
	 * Determines if a string is palindrome or not.
	 * @param text		String to be checked for palindrome
	 * @return short	yes or no
	 */
	private String findPalindrome(String text) {
		String string = text.replaceAll("[^a-zA-Z]+", "").trim().toLowerCase();
	    // Could have also used string reverse function and then checked values.
		int len = string.length();
	    for (int i = 0; i < len / 2; ++i) {
	        if (string.charAt(i) != string.charAt(len - 1 - i)) {
	            return "no";
	        }
	    }
	    return "yes";
	}
	
}
