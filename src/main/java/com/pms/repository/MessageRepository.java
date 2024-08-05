package com.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	public List<Message> findByChatIdOrderByCreatedAtAsc(Long chatId);
	
}
