package com.auction_website.repository;

import com.auction_website.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "SELECT * \n" +
            "FROM comment \n" +
            "WHERE product_id = ?1 ", nativeQuery = true)
    List<Comment> getAllCommentByProductId(Integer productId);

    @Query(value = "SELECT * \n" +
            "FROM comment \n" +
            "WHERE comment_id = ?1", nativeQuery = true)
    Comment getCommentById(Integer commentId);

    @Modifying
    @Query(value = "INSERT INTO comment (content, image, product_id, account_id, comment_time) \n" +
            "VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void createComment(String content, String image, Integer productId, Integer accountId, Timestamp commentTime);

    @Modifying
    @Query(value = "UPDATE comment \n" +
            "SET content = ?1, image = ?2 \n" +
            "WHERE comment_id = ?3", nativeQuery = true)
    void editComment(String content, String image, Integer commentId);

    @Modifying
    @Query(value = "DELETE FROM comment \n" +
            "WHERE comment_id = ?1", nativeQuery = true)
    void removeComment(Integer commentId);

    @Query(value = "SELECT * FROM comment WHERE product_id = :productId ORDER BY comment_id DESC LIMIT 1", nativeQuery = true)
    Comment getRecentComment(Integer productId);

}
