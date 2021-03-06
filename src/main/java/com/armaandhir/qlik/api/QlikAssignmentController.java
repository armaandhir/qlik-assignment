package com.armaandhir.qlik.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * The main controller class of the application service.
 * Request mappings are defined here and service layer methods are called to perform actions
 * MessageService is used
 * CrossOrigin annotation is used so that methods could be tested locally.
 * 
 * @author armaan
 *
 */
@RestController
public class QlikAssignmentController {
	
	/**
	 * All service level methods are performed by this class.
	 */
	@Autowired
	private MessageService messageService;
	

	@RequestMapping("/")
    public String index() {
        return "Qlik Assignment app";
    }
	
	
	/**
	 * Saves a message to the database
	 * HTTP POST
	 * 
	 * @param author	String. Cannot be greater that 15 characters.
	 * @param text		String. Cannot be greater that 250 characters
	 * @return ResponseEntity<Message>	Message that has been added or throws exception
	 */
	@CrossOrigin
	@JsonView(View.Summary.class)
	@RequestMapping(value="/qlik/api/message",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> createMessage(@RequestParam String author, @RequestParam String text) {
		if (author != "" && text != null && text.length() <= 250 && author.length() <= 15) {
			Message newMessage = new Message(author, text);
			Message createdMessage = messageService.addMessage(newMessage);
			// DO SOMEHING HERE
			if (createdMessage != null)
			{
				return new ResponseEntity<Message>(createdMessage, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<Message>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		// change this to custom return error
		else {
			throw new IllegalArgumentException("invalid data");
		}
    }
	
	/**
	 * Returns the list of all messages.
	 * HTTP GET
	 * 
	 * @return ResponseEntity<Collection<Message>>	A json list having all messages
	 */
	@CrossOrigin
	@JsonView(View.Summary.class)
	@RequestMapping(value="/qlik/api/message",
			method=RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Message>> getAllMessages() {
		// TRY USING COLLECTION
		Collection<Message> messageList = messageService.getAllMessages();
		if (messageList.isEmpty() || messageList == null) {
			return new ResponseEntity<Collection<Message>>(HttpStatus.NO_CONTENT);
		}
		else 
		{
			return new ResponseEntity<Collection<Message>>(messageList, HttpStatus.OK);
		}
	}
	
	/**
	 * Returns the message details requested by id.
	 * Also determines if it is a palindrome. 
	 * 'isPalindrome' property is added to the json response.
	 * The palindrome value can be evaluated at client side.
	 * HTTP GET
	 * 
	 * @param expenseId
	 * @return Message	message details with isPalindrome property added
	 */
	@CrossOrigin
	@RequestMapping(value="/qlik/api/message/{message_id}",
			method=RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> getSpecificMessage(@PathVariable("message_id") Long messageId) {
		// CHECK IF IT IS LONG
		if (messageId != null) {
			Message message = new Message();
			message = messageService.getMessage(messageId);
			if (message != null) {
				return new ResponseEntity<Message>(message, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
			}
		}
		// change this to custom return error
		else {
			throw new IllegalArgumentException("invalid data");
		}
	}
	
	/**
	 * Deletes the record from the database with specific message id
	 * HTTP DELETE
	 * 
	 * @param id	id of the message to be deleted
	 * @return String	deleted or IllegalArgumentException on invalid data
	 */
	@CrossOrigin
	@RequestMapping(
			value="/qlik/api/message/{id}",
			method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteAccount(@PathVariable("id") Long id) {
		boolean b = messageService.deleteMessage(id);
		if(b){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else{
			throw new IllegalArgumentException("invalid data");
		}
	}
	
}
