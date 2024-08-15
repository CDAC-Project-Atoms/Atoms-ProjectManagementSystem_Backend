package com.pms.service;

import java.util.List;

import com.pms.exception.ChatException;
import com.pms.exception.ProjectException;
import com.pms.exception.UserException;
import com.pms.model.Message;

public interface MessageService {

    Message sendMessage(Long senderId, Long chatId, String content) throws UserException, ChatException, ProjectException;

    List<Message> getMessagesByProjectId(Long projectId) throws ProjectException, ChatException;
}

