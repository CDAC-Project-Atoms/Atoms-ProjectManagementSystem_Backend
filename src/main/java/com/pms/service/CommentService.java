package com.pms.service;

import java.util.List;

import com.pms.model.Comment;

public interface CommentService {
	
	public Comment createComment(Long issueId, Long userId, String content) throws Exception;
	
	public void deleteComment(Long commentId, Long userId) throws Exception;
	
	public List<Comment> findCommentByIssueId(Long issueId) throws Exception;
 
}
