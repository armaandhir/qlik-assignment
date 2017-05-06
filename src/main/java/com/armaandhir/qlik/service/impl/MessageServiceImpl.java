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
			// add checks if it is null ?
			Message retrievedMessage = messageRepository.findOne(id);
			return retrievedMessage;
		}
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRES_NEW,
			readOnly = false)
	public void deleteMessage(Long id) {
		Message mess = messageRepository.findOne(id);
		messageRepository.delete(mess);
	}

}
