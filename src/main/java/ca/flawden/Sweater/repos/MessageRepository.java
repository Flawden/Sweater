package ca.flawden.Sweater.repos;

import ca.flawden.Sweater.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByTag(String tag);

    List<Message> findByTextContaining(String contain);

}
