package com.armaandhir.qlik.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.armaandhir.qlik.model.Message;
import com.armaandhir.qlik.model.View;
import com.armaandhir.qlik.service.MessageService;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author armaan
 *
 */
@RestController
public class QlikAssignmentController {
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/")
    public String index() {
        return "Qlik Assignment app";
    }
	
	/**
	 * @param author
	 * @param text
	 * @return
	 */
	@JsonView(View.Summary.class)
	@RequestMapping(value="/qlik/api/post",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMessage(@RequestParam String author, @RequestParam String text) {
		if (author != null && text != null) {
			Message newMessage = new Message(author, text);
			Message createdMessage = messageService.addMessage(newMessage);
			// DO SOMEHING HERE
			if (createdMessage != null)
			{
				return new ResponseEntity<Message>(createdMessage, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		// change this to custom return error
		else {
			return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	/**
	 * @return
	 */
	@JsonView(View.Summary.class)
	@RequestMapping(value="/qlik/api/message",
			method=RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllMessages() {
		// TRY USING COLLECTION
		Collection<Message> messageList = messageService.getAllMessages();
		if (messageList != null) {
			return new ResponseEntity<Collection<Message>>(messageList, HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @param expenseId
	 * @return
	 */
	@RequestMapping(value="/qlik/api/message/{message_id}",
			method=RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSpecificMessage(@PathVariable("message_id") Long messageId) {
		// CHECK IF IT IS LONG
		if (messageId != null) {
			Message message = messageService.getMessage(messageId);
			if (message != null) {
				return new ResponseEntity<Message>(message, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		// change this to custom return error
		else {
			return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(
			value="/qlik/api/delete/{id}",
			method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id) {
		// NEEDS ATTENTION
		try {
			messageService.deleteMessage(id);
		}
		catch(Exception ex) {
			System.out.println("Error deleting record");
		}
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
}
