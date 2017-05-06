package com.armaandhir.qlik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.armaandhir.qlik.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
