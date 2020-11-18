package propets.messaging.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import propets.messaging.model.Post;

@Component
public interface MessagingRepository extends MongoRepository<Post, String> {

}
