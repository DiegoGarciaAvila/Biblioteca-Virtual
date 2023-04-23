package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.GenLiterario;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserGenServicee {
    public void grdUsergen(String datagenuser, String user) throws JSONException;

    public List<Integer> recomendaciones(String cveserv) ;
}
