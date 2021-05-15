package com.auction_website.service.comment;

import com.auction_website.model.Comment;
import com.auction_website.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Author : SonPH
     * create new comment
     *
     * @param comment
     */
    @Override
    @Transactional
    public void createComment(Comment comment) {
        commentRepository.createComment(comment.getContent(), comment.getImage(), comment.getProduct().getProductId(),
                comment.getAccount().getAccountId(), comment.getCommentTime());
    }

    /**
     * Author : SonPH
     * find comment by commentID
     *
     * @param commentId
     */
    @Override
    public Comment getCommentById(Integer commentId) {
        return commentRepository.getCommentById(commentId);
    }

    /**
     * Author : SonPH
     * find all comment by productID
     *
     * @param productId
     */
    @Override
    public List<Comment> getCommentByProductId(Integer productId) {
        return commentRepository.getAllCommentByProductId(productId);
    }

    /**
     * Author : SonPH
     * update comment
     *
     * @param comment
     */
    @Override
    @Transactional
    public void updateComment(Comment comment) {
        commentRepository.editComment(comment.getContent(), comment.getImage(), comment.getCommentId());
    }

    /**
     * Author : SonPH
     * delete comment
     *
     * @param commentId
     */
    @Override
    @Transactional
    public void deleteCommentById(Integer commentId) {
        commentRepository.removeComment(commentId);
    }

    /**
     * Author : SonPH
     * decode string
     *
     * @param string
     */
    @Override
    public String decodeString(String string) {
        String decodeString = null;
        try {
            decodeString = URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return decodeString;
        }
        return decodeString;
    }

    /**
     * Author : SonPH
     * encode string
     *
     * @param string
     */
    @Override
    public String encodeString(String string) {
        String encodeString = null;
        try {
            encodeString = URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return encodeString;
        }
        return encodeString;
    }

    /**
     * Author : SonPH
     * recent comment by productId
     *
     * @param productId
     */
    @Override
    public Comment getRecentComment(Integer productId) {
        return commentRepository.getRecentComment(productId);
    }
}
