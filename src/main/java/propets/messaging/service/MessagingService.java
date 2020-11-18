package propets.messaging.service;

import java.awt.print.Pageable;

import org.springframework.http.ResponseEntity;

import propets.messaging.dto.PostDto;
import propets.messaging.dto.PostResponseDto;
import propets.messaging.dto.PostsDto;

public interface MessagingService {
	
	ResponseEntity<PostResponseDto> createPost(String login, PostDto postDto);
	
	ResponseEntity<PostResponseDto> updatePost(String postId, PostDto postDto);
	
	ResponseEntity<PostResponseDto> deletePost(String postId);
	
	ResponseEntity<PostResponseDto> getPostById(String postId);
	
	Pageable getPosts(Integer itemsOnPage, Integer nPage);
		
	Iterable<PostResponseDto> getUserDate(PostsDto postsDto);
	
}