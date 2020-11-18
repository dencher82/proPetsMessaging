package propets.messaging.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostDto {
	String userName;
	String avatar;
	String text;
	List<String> images;
	
}
