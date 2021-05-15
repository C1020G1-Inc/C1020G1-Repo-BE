package com.auction_website.repository;

import com.auction_website.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select * from role where role_id = ?1",nativeQuery = true)
    Role findByRoleId(Integer roleId);
}
