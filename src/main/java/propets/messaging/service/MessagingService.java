package propets.messaging.service;

import org.springframework.http.ResponseEntity;

import propets.messaging.dto.PostDto;
import propets.messaging.dto.PostResponseDto;

public interface MessagingService {
	
	ResponseEntity<PostResponseDto> createPost(String login, PostDto postDto);
	
	ResponseEntity<PostResponseDto> updatePost(String postId, PostDto postDto);
	
	ResponseEntity<PostResponseDto> deletePost(String postId);
	
	ResponseEntity<PostResponseDto> getPostById(String postId);
	
	Iterable<PostResponseDto> getPosts(Integer itemsOnPage, Integer nPage);
		
	Iterable<PostResponseDto> getUserDate(String[] posts);
	
}