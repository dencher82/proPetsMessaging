package propets.messaging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import propets.messaging.dto.PostDto;
import propets.messaging.dto.PostResponseDto;
import propets.messaging.service.MessagingService;

@RestController
@RequestMapping("/message/en/v1")
public class MessagingController {
	
	@Autowired
	MessagingService messagingService;
	
	@PostMapping("/{login}")
	public ResponseEntity<PostResponseDto> createPost(@PathVariable String login, @RequestBody PostDto postDto) {
		return messagingService.createPost(login, postDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostResponseDto> updatePost(@PathVariable(value = "id") String postId, @RequestBody PostDto postDto) {
		return messagingService.updatePost(postId, postDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PostResponseDto> deletePost(@PathVariable(value = "id") String postId) {
		return messagingService.deletePost(postId);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostResponseDto> getPostById(@PathVariable(value = "id") String postId) {
		return messagingService.getPostById(postId);
	}
	
	@GetMapping("/view")
	public Iterable<PostResponseDto> getPosts(@RequestParam Integer itemsOnPage, @RequestParam(value = "currentPage") Integer nPage) {
		return messagingService.getPosts(itemsOnPage, nPage);
	}
	
	@PostMapping("/userdata")
	public 	Iterable<PostResponseDto> getUserDate(@RequestBody String[] posts) {
		return messagingService.getUserDate(posts);
	}
	
}
