package com.auction_website.controller;

import com.auction_website.model.Comment;
import com.auction_website.model.Product;
import com.auction_website.service.comment.CommentService;
import com.auction_website.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductService productService;

    /**
     * Author : SonPH
     * find all comment by productID
     *
     * @param productId
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Comment>> getAllCommentByProductId(@PathVariable("productId") Integer productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Comment> commentList = commentService.getCommentByProductId(productId);

        if (commentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (Comment comment : commentList) {
                comment.setContent(commentService.decodeString(comment.getContent()));
            }
            return new ResponseEntity<>(commentList, HttpStatus.OK);
        }
    }

    /**
     * Author : SonPH
     * find comment by commentID
     *
     * @param commentId
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentByCommentId(@PathVariable("commentId") Integer commentId) {
        Comment comment = commentService.getCommentById(commentId);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            comment.setContent(commentService.decodeString(comment.getContent()));
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }

    /**
     * Author : SonPH
     * create new comment
     *
     * @param comment
     * @param bindingResult
     */
    @PostMapping("")
    public ResponseEntity<Comment> createComment(@Validated @RequestBody Comment comment, BindingResult bindingResult) {
        comment.setCommentTime(new Timestamp(System.currentTimeMillis()));
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            comment.setContent(commentService.encodeString(comment.getContent()));
            commentService.createComment(comment);
            Comment commentRecent = commentService.getRecentComment(comment.getProduct().getProductId());
            return new ResponseEntity<>(commentRecent, HttpStatus.CREATED);
        }
    }

    /**
     * Author : SonPH
     * update comment
     *
     * @param commentId
     * @param comment
     * @param bindingResult
     */
    @PutMapping("/edit/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("commentId") Integer commentId,
                                                 @Validated @RequestBody Comment comment, BindingResult bindingResult) {
        Comment commentTempt = commentService.getCommentById(commentId);
        if (commentTempt == null || bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            comment.setContent(commentService.encodeString(comment.getContent()));
            commentService.updateComment(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }

    /**
     * Author : SonPH
     * delete comment
     *
     * @param commentId
     */
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("commentId") Integer commentId) {
        Comment comment = commentService.getCommentById(commentId);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            commentService.deleteCommentById(commentId);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
    }
}
