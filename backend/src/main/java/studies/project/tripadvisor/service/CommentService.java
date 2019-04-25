package studies.project.tripadvisor.service;

import studies.project.tripadvisor.entity.Comment;

public interface CommentService {

    void saveComment(Comment comment);

    void deleteComment(Long commentId);

    void updateComment(Comment comment);

    Comment getComment(Long commentId);

}
