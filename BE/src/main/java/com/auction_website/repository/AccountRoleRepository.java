package com.auction_website.repository;

import com.auction_website.model.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer> {
    @Query(value = "select * from account_role where account_id = ?1",nativeQuery = true)
    List<AccountRole> findAllByAccountId(Integer accountId);
}
