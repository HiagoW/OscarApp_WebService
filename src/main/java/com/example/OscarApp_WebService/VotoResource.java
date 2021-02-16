package com.example.OscarApp_WebService;

import com.google.gson.Gson;
import dao.LoginDao;
import dao.VotoDao;
import model.Usuario;
import model.Voto;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;

@Path("/voto")
public class VotoResource {

    LoginDao loginDao = new LoginDao();
    VotoDao votoDao = new VotoDao();

    @POST
    @Path("/votar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response voto(String data) {
        Gson gson = new Gson();
        Voto voto = gson.fromJson(data, Voto.class);
        if(loginDao.validarToken(voto.getUsuario())){
            if(votoDao.voto(voto)){
                return Response.ok(voto).build();
            }else{
                return Response.serverError().build();
            }
        }else{
            return Response.status(401).build();
        }
    }

    @POST
    @Path("/buscarVoto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buscarVoto(String data) {
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(data, Usuario.class);
        Voto voto = votoDao.getVoto(usuario);
        return Response.ok(voto).build();
    }

}