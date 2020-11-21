package propets.messaging.service;

import org.springframework.http.ResponseEntity;

import propets.messaging.dto.PostDto;
import propets.messaging.dto.PostResponseDto;
import propets.messaging.dto.PostsPageableDto;

public interface MessagingService {
	
	ResponseEntity<PostResponseDto> createPost(String login, PostDto postDto);
	
	ResponseEntity<PostResponseDto> updatePost(String postId, PostDto postDto);
	
	ResponseEntity<PostResponseDto> deletePost(String postId);
	
	ResponseEntity<PostResponseDto> getPostById(String postId);
	
	PostsPageableDto getPosts(Integer itemsOnPage, Integer nPage);
		
	Iterable<PostResponseDto> getUserDate(String[] posts);
	
}