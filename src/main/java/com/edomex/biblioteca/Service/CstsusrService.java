package com.edomex.biblioteca.Service;

import com.edomex.biblioteca.Entity.Cstsusr;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CstsusrService {
    public List<Cstsusr> cstslista();
}
