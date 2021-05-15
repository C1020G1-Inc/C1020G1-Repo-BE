package com.auction_website.service.role;

import com.auction_website.model.Role;
import com.auction_website.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    /**
     * @author PhinNL
     * find role by id
     */
    @Override
    public Role findById(Integer id) {
        return roleRepository.findByRoleId(id);
    }
}
