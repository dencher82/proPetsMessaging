package propets.messaging.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostResponseDto {
	String id;
	String userLogin;
	String userName;
	String avatar;
	LocalDateTime datePost;
	String text;
	List<String> images;

}
