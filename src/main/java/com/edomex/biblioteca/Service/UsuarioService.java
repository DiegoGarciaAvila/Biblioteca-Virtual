package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Dao.UserRoleDao;
import com.edomex.biblioteca.Dao.UsuarioDao;
import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.UserRole;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService{

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private UserRoleDao userRoleDao;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String uacveserv) throws UsernameNotFoundException {
        AppUser usuario = usuarioDao.findByUacveserv(uacveserv);
        List<UserRole> rls=userRoleDao.findByRcveserv(uacveserv);
        
        if(usuario == null){
            throw new UsernameNotFoundException(uacveserv);
        }
        
        var roles = new ArrayList<GrantedAuthority>();
        
        for(UserRole rol: rls){
            roles.add(new SimpleGrantedAuthority(rol.getRcverol().getSdesrol()));
            System.out.println(""+rol.getRcverol().getSdesrol());
        }
        System.out.println(""+usuario.getUacveserv()+ usuario.getUapassword());
        return new User(usuario.getUacveserv(), usuario.getUapassword(), roles);
    }
    
}
