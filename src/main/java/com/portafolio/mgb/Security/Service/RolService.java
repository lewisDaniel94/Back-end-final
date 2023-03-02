  package com.portafolio.mgb.Security.Service;

import com.portafolio.mgb.Security.Entity.Rol;
import com.portafolio.mgb.Security.Enums.RolNombre;
import com.portafolio.mgb.Security.Repository.iRolRespository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RolService {
    
    @Autowired
    iRolRespository irolRepository;
    
    public Optional<Rol>getByRolNombre(RolNombre rolNombre){
        
       return irolRepository.findByRolNombre(rolNombre);
    }
    
    
    public void save (Rol rol){
        irolRepository.save(rol);
    }
    
}