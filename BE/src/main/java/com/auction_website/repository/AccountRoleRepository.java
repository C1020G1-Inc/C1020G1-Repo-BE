package com.auction_website.repository;

import com.auction_website.model.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer> {
    @Query(value = "select * from account_role where account_id = ?1",nativeQuery = true)
    List<AccountRole> findAllByAccountId(Integer accountId);
    @Modifying
    @Query(value = "insert into account_role(account_id,role_id) values(?1,?2)",nativeQuery = true)
    void save(Integer accountId, Integer roleId);
}
