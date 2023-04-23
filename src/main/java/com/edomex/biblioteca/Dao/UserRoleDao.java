package com.edomex.biblioteca.Dao;

import com.edomex.biblioteca.Entity.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<UserRole, Integer>{
        List<UserRole> findByRcveserv(String Rcveserv);

}
