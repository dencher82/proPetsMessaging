package propets.messaging.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostsPageableDto {
	Integer itemsOnPage;
	Integer currentPage;
	Long itemsTotal;
	List<PostResponseDto> posts;
	
}
