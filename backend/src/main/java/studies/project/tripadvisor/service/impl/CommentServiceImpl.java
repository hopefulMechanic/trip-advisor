package studies.project.tripadvisor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studies.project.tripadvisor.entity.Comment;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.repository.CommentRepository;
import studies.project.tripadvisor.service.CommentService;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void saveComment(Comment comment) {
        log.info("CommentService: saveComment");
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        log.info("CommentService: deleteComment");
        if (!commentRepository.existsById(commentId))
            throw new ElementNotFoundException();
        commentRepository.deleteById(commentId);
    }

    public void updateComment(Comment comment) {
        log.info("PlaceService: updatePlace");
        commentRepository.save(comment);
    }

}
