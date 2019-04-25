package studies.project.tripadvisor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studies.project.tripadvisor.entity.Comment;
import studies.project.tripadvisor.entity.Place;
import studies.project.tripadvisor.exception.ElementNotFoundException;
import studies.project.tripadvisor.service.CommentService;
import studies.project.tripadvisor.service.PlaceService;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping("api")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PlaceService placeService;

    public void setCommentService(CommentService commentService, PlaceService placeService) {
        this.commentService = commentService;
        this.placeService = placeService;
    }

    @PostMapping("/places/{placeId}/comments")
    public ResponseEntity saveComment(@RequestBody Comment comment,
                                      @PathVariable(name = "placeId") Long placeId) throws ElementNotFoundException {
        Place place = placeService.getPlace(placeId);
        comment.setPlace(place);
        commentService.saveComment(comment);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable(name = "commentId") Long commentId) throws ElementNotFoundException {
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
