package com.armaandhir.qlik.service;

import java.util.Collection;

import com.armaandhir.qlik.model.Message;

/**
 * The Service interface that defines the service level methods.
 * Uses Message modal to return data.
 * @author armaan
 *
 */
public interface MessageService {
	
	
	/**
	 * Lists all messages
	 * @return Collection<Message>	the collection of all messages
	 */
	public Collection<Message> getAllMessages();
	
	/**
	 * Adds the posted message to Database and returns if successful
	 * @return Message	submitted/posted message
	 */
	public Message addMessage(Message message);
	
	/**
	 * 
	 * @param id	Long
	 * @return Message	returns the message requested for with extra information
	 */
	public Message getMessage(Long id);
	
	/**
	 * @param id	Long
	 * @return boolean	true or false
	 */
	public boolean deleteMessage(Long id);
}
