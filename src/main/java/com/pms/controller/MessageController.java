package com.pms.controller;

import java.util.List;

import org.jboss.logging.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.model.Chat;
import com.pms.model.Message;
import com.pms.model.User;
import com.pms.request.CreateMessageRequest;
import com.pms.service.MessageService;
import com.pms.service.ProjectService;
import com.pms.service.UserService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;
	
//	Method send the message
	@PostMapping("/send")
	public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) throws Exception {
		
		User user = userService.findUserById(request.getSenderid());
//		get the user
		if(user==null) 
			throw new Exception("User not found with id: " + request.getSenderid());
//		Get the all previous chats
		Chat chats = projectService.getProjectById(request.getProjectId()).getChat();
		if(chats==null) 
			throw new Exception("Chats not found ");
//		Send the message to the chat with senderId
		Message sentMessage = messageService.sendMessage(request.getSenderid(), request.getProjectId(), request.getContent());	
		return ResponseEntity.ok(sentMessage);
	}
	
//	Get the messages of the chat
	@GetMapping("/chat/{projectId}")
	public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
		
		List<Message> messages = messageService.getMessageByProjectId(projectId);
		return ResponseEntity.ok(messages);
	}
	
	
	

}
