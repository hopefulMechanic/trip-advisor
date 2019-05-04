package studies.project.tripadvisor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.entity.Comment;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.entity.User;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.service.CommentService;
import studies.project.tripadvisor.service.PlaceService;
import studies.project.tripadvisor.service.UserService;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("api")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private UserService userService;

    public void setCommentService(CommentService commentService, PlaceService placeService, UserService userService) {
        this.commentService = commentService;
        this.placeService = placeService;
        this.userService = userService;
    }

    @PostMapping("/places/{placeId}/comments/user/{userId}")
    public ResponseEntity saveComment(@RequestBody Comment comment,
                                      @PathVariable(name = "placeId") Long placeId,
                                      @PathVariable(name = "userId") Long userId) throws ElementNotFoundException {
        Place p = placeService.getPlace(placeId);
        User u = userService.getUser(userId);
        comment.setPlace(p);
        comment.setUser(u);
        commentService.saveComment(comment);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable(name = "commentId") Long commentId) throws ElementNotFoundException {
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity updateComment(@RequestBody Comment comment, @PathVariable(name = "commentId") Long commentId) throws ElementNotFoundException {
        Comment c = commentService.getComment(commentId);
        c.setText(comment.getText());
        commentService.updateComment(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

}
