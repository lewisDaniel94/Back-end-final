/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mgb.Controller;

import com.portafolio.mgb.Dto.dtoProyectos;
import com.portafolio.mgb.Entity.Proyectos;
import com.portafolio.mgb.Security.Controller.Mensaje;
import com.portafolio.mgb.Service.Sproyectos;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "https://portafolio-back-lws.web.app")

public class CProyectos {
    
@Autowired
Sproyectos sProyectos;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    
    }
          @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") int id){
        if(!sProyectos.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.BAD_REQUEST);
        }
        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
    
    
    
          @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sProyectos.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        }
        sProyectos.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
    
     @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyectos dtoproyectos){
    if(StringUtils.isBlank(dtoproyectos.getNombreP())){
        return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    }
     
    if(sProyectos.existsByNombreP(dtoproyectos.getNombreP())){
        return new ResponseEntity(new Mensaje ("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
    }
     Proyectos proyectos = new Proyectos(
     dtoproyectos.getNombreP(), dtoproyectos.getDescripcionP());
     
    sProyectos.save(proyectos);
    return new ResponseEntity(new Mensaje("Proyectos ya existen"),HttpStatus.OK);
    }
    
    
    
     @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody dtoProyectos dtoproyectos){
    if (!sProyectos.existsById(id)){
        return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
    }
  
    if(sProyectos.existsByNombreP(dtoproyectos.getNombreP()) && sProyectos.getByNombreP(dtoproyectos.getNombreP()).get().getId() !=id){
                return new ResponseEntity(new Mensaje("Esa nombre ya existe"), HttpStatus.BAD_REQUEST);

    }
     if(StringUtils.isBlank(dtoproyectos.getNombreP())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    }
       Proyectos proyectos = sProyectos.getOne(id).get();
        proyectos.setNombreP(dtoproyectos.getNombreP());
        proyectos.setDescripcionP((dtoproyectos.getDescripcionP()));
        
        sProyectos.save(proyectos);
        return new ResponseEntity(new Mensaje("Proyectos Actualizados"), HttpStatus.OK);
    }
    }
    

    
    
    
    

   

