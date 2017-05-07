package com.armaandhir.qlik.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.armaandhir.qlik.model.Message;
import com.armaandhir.qlik.repository.MessageRepository;
import com.armaandhir.qlik.service.MessageService;

@Service
@Transactional(
		propagation = Propagation.SUPPORTS,
		readOnly = true)
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public Collection<Message> getAllMessages() {
		// might need to override as we need messages sorted by date: latest ones
		Collection<Message> messages = messageRepository.findAll();
		return messages;
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			readOnly = false)
	public Message addMessage(Message message) {
		if(message == null) {
			return null;
		}
		else {
			Message addedMessage = messageRepository.save(message);
			return addedMessage;
		}
	}

	@Override
	public Message getMessage(Long id) {
		if (id == null) {
			return null;
		}
		else {
			try {
				Message retrievedMessage = messageRepository.findOne(id);
				System.out.println(retrievedMessage);
				return retrievedMessage;
			}
			catch (Exception ex) {
				System.out.println("Error in getMessage(): " + ex);
				return null;
			}
		}
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			readOnly = false)
	public boolean deleteMessage(Long id) {
		try {
			Message mess = messageRepository.findOne(id);
			if(mess != null) {
				messageRepository.delete(mess);
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
	}

}
