package com.pms.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.Chat;
import com.pms.model.Message;
import com.pms.model.User;
import com.pms.repository.MessageRepository;
import com.pms.repository.UserRepository;

@Service
public class MessageServiceImpl  implements MessageService{

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectService projectService;
	
//	Method to send the message in the particular project
	@Override
	public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
		User sender = userRepository.findById(senderId)
						.orElseThrow(() -> new Exception("User not found with id: "+ senderId));

//		Get the chat of the particular project
		Chat chat = projectService.getProjectById(projectId).getChat();
		
//		create the message for the chat
		Message message = new Message();
		message.setContent(content);
		message.setSender(sender);
		message.setCreatedAt(LocalDateTime.now());
		message.setChat(chat);
		
//		save the message
		Message savedMessage = messageRepository.save(message);
		
		chat.getMessages().add(savedMessage);
		
		return savedMessage;
	}

//	Method to get messages in the project chat
	@Override
	public List<Message> getMessageByProjectId(Long projectId) throws Exception {
		
		Chat chat = projectService.getChatByProjectId(projectId);
//		Display the messages in the chat in ascending order of creation
		List<Message> findByChatIdOrderByCreatedAtAsc = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
		
		return findByChatIdOrderByCreatedAtAsc;
	}

}
