package com.pms.request;

import lombok.Data;

@Data
public class CreateMessageRequest {
	
	private Long senderid;
	private String content;
	private Long projectId;

}
