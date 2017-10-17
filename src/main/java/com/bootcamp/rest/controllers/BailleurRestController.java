/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.controllers;

import com.bootcamp.jpa.entities.Bailleur;
import com.bootcamp.jpa.enums.TypeBailleur;
import com.bootcamp.jpa.repositories.BailleurRepository;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author root
 */
@Path("/bailleurs")
public class BailleurRestController {

    BailleurRepository br = new BailleurRepository("punit-mysql");
    
    @GET
    @Path("/list")
    @Produces("application/json")
    public Response getList(){
        Bailleur bailleur = new Bailleur();
        bailleur.setTypeBailleur(TypeBailleur.PRIVE);
        return Response.status(200).entity(bailleur).build();
    }
    
    @GET
    @Path("/listsdebase")
    @Produces("application/json")
    public Response getListBaailleurFromDB(){
        
        return Response.status(200).entity(br.findAll()).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getBaailleurByIdFromDB(@PathParam("id") int id) throws SQLException{
        BailleurRepository br = new BailleurRepository("punit-mysql");
        Bailleur bailleur = br.findById(id);
        if(bailleur.getId()!=id){
            return Response.status(200).entity(bailleur).build();
        }
       if(bailleur != null) {
           return Response.status(200).entity(bailleur).build();
        }else return Response.status(404).build();
    }
    
    
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Bailleur bailleur) throws SQLException {
        br.create(bailleur);
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Bailleur bailleur) throws SQLException {
       br.update(bailleur);
    }
}
