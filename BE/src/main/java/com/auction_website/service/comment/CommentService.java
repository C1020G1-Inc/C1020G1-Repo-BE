package com.auction_website.service.comment;

import com.auction_website.model.Comment;

import java.util.List;

public interface CommentService {

    /**
     * Author : SonPH
     * create new comment
     *
     * @param comment
     */
    void createComment(Comment comment);

    /**
     * Author : SonPH
     * find comment by commentID
     *
     * @param commentId
     */
    Comment getCommentById(Integer commentId);

    /**
     * Author : SonPH
     * find all comment by productID
     *
     * @param productId
     */
    List<Comment> getCommentByProductId(Integer productId);

    /**
     * Author : SonPH
     * update comment
     *
     * @param comment
     */
    void updateComment(Comment comment);

    /**
     * Author : SonPH
     * delete comment
     *
     * @param commentId
     */
    void deleteCommentById(Integer commentId);

    /**
     * Author : SonPH
     * decode string
     *
     * @param string
     */
    String decodeString(String string);

    /**
     * Author : SonPH
     * encode string
     *
     * @param string
     */
    String encodeString(String string);

    /**
     * Author : SonPH
     * recent comment by productId
     *
     * @param productId
     */
    Comment getRecentComment(Integer productId);
}
