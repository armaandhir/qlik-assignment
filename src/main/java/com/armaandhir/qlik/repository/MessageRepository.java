package com.armaandhir.qlik.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.armaandhir.qlik.model.Message;

/**
 * The MessageRepositry class handles connection to database and executes queries.
 * Extends JpaRepository which provides few CRUD operations by default.
 * @author armaan
 *
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	/**
	 * Gets all messages ordered by created date in Ascending order
	 * @return Collection<Message>	list of returned messages
	 */
	public Collection<Message> findAllByOrderByCreatedAtAsc(); 
}
