package com.pms.service;

import java.util.List;

import com.pms.model.Message;

public interface MessageService {

	public Message sendMessage(Long senderId, Long projectId, String content) throws Exception;
	
	public List<Message> getMessageByProjectId(Long projectId) throws Exception;
	
}
