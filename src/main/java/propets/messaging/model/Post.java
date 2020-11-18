package propets.messaging.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Document(collection = "posts")
public class Post implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9018905046223162291L;
	
	String id;
	String userLogin;
	String userName;
	String avatar;
	LocalDateTime datePost;
	String text;
	List<String> images = new ArrayList<>();
	
	public Post() {
		this.datePost = LocalDateTime.now();	
	}
	
	public Post(String userLogin, String userName, String avatar, String text) {
		this.userLogin = userLogin;
		this.userName = userName;
		this.avatar = avatar;
		this.text = text;
		this.datePost = LocalDateTime.now();
	}
	
}
