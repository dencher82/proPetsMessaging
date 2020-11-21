package propets.messaging.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import propets.messaging.dao.MessagingRepository;
import propets.messaging.dto.PostDto;
import propets.messaging.dto.PostResponseDto;
import propets.messaging.dto.PostsPageableDto;
import propets.messaging.exceptions.PostNotFoundException;
import propets.messaging.model.Post;

import static propets.messaging.configuration.Constants.*;

@Service
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	MessagingRepository repository;

	@Autowired
	ModelMapper mapper;

	@Override
	public ResponseEntity<PostResponseDto> createPost(String login, PostDto postDto) {
		Post post = new Post(login, postDto.getUserName(), postDto.getAvatar(), postDto.getText());
		post.setImages(postDto.getImages());
		repository.save(post);
		return createResponseEntity(post);
	}

	@Override
	public ResponseEntity<PostResponseDto> updatePost(String postId, PostDto postDto) {
		Post post = repository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
		if (postDto.getUserName() != null) {
			post.setUserName(postDto.getUserName());
		}
		if (postDto.getAvatar() != null) {
			post.setAvatar(postDto.getAvatar());
		}
		if (postDto.getText() != null) {
			post.setText(postDto.getText());
		}
		if (postDto.getImages().size() > 0) {
			post.setImages(postDto.getImages());
		}
		repository.save(post);
		return createResponseEntity(post);
	}

	@Override
	public ResponseEntity<PostResponseDto> deletePost(String postId) {
		Post post = repository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
		repository.deleteById(postId);
		return createResponseEntity(post);
	}

	@Override
	public ResponseEntity<PostResponseDto> getPostById(String postId) {
		Post post = repository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));
		return createResponseEntity(post);
	}

	@Override
	public PostsPageableDto getPosts(Integer itemsOnPage, Integer nPage) {
		Pageable pageable = PageRequest.of(nPage, itemsOnPage);
		Page<Post> page = repository.findAll(pageable);
		long itemsTotal = page.getTotalElements();
		List<PostResponseDto> posts = page.getContent().stream().map(post -> mapper.map(post, PostResponseDto.class)).collect(Collectors.toList());
		return new PostsPageableDto(itemsOnPage, nPage, itemsTotal, posts);
	}

	@Override
	public Iterable<PostResponseDto> getUserDate(String[] posts) {
		List<Post> postList	= new ArrayList<Post>();		
		Arrays.stream(posts).forEach(post -> postList.add(repository.findById(post).orElse(null)));
		return postList.stream().filter(post -> post != null).map(post -> mapper.map(post, PostResponseDto.class)).collect(Collectors.toList());
	}

	private ResponseEntity<PostResponseDto> createResponseEntity(Post post) {
		PostResponseDto postResponseDto = mapper.map(post, PostResponseDto.class);
		HttpHeaders headers = new HttpHeaders();
		headers.add(NAME_HEADER, post.getUserName());
		headers.add(AVATAR_HEADER, post.getAvatar());
		headers.add(LOGIN_HEADER, post.getUserLogin());
		return new ResponseEntity<PostResponseDto>(postResponseDto, headers, HttpStatus.OK);
	}

}
