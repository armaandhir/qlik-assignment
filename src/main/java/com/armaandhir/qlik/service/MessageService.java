package com.armaandhir.qlik.service;

import java.util.Collection;

import com.armaandhir.qlik.model.Message;

/**
 * @author armaan
 *
 */
public interface MessageService {
	
	
	/**
	 * Lists all messages
	 * @return
	 */
	public Collection<Message> getAllMessages();
	
	/**
	 * Adds the posted message to Database and returns if successful
	 * @return Message	submitted/posted message
	 */
	public Message addMessage(Message message);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Message getMessage(Long id);
	
	public boolean deleteMessage(Long id);
}
