package com.edomex.biblioteca.ServDaoImpl;

import com.edomex.biblioteca.Dao.UserGenDao;
import com.edomex.biblioteca.Entity.AppUser;
import com.edomex.biblioteca.Entity.GenLiterario;
import com.edomex.biblioteca.Service.AppUserService;
import com.edomex.biblioteca.Service.UserGenServicee;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGenServImpll implements UserGenServicee {
    @Autowired
    private UserGenDao userGenDao;
    @Autowired
    private AppUserService appUserService;
    @Override
    public void grdUsergen(String datagenuser, String user) throws JSONException {
        AppUser usua=appUserService.userById(user);
        JSONArray arr=new JSONArray(datagenuser);
        for(int i=0;i<arr.length() && i<3;i++){
            int id=Integer.parseInt(arr.getJSONObject(i).getString("id"));
            GenLiterario litera=new GenLiterario();
            litera.setGCVEGEN(id);
            userGenDao.grdusergen(usua,litera);
        }
    }

    @Override
    public List<Integer> recomendaciones(String cveserv) {
        return userGenDao.MasLeidos(cveserv);
    }
}
