package com.example.OscarApp_WebService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.LoginDao;
import model.Usuario;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.Random;

@Path("/login")
public class LoginResource {

    LoginDao loginDao = new LoginDao();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String data) {
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(data,Usuario.class);
        Random rand = new Random();
        usuario.setToken(rand.nextInt(100));
        if(loginDao.login(usuario)){
            return Response.ok(gson.toJson(usuario)).build();
        }else{
            return Response.status(401).build();
        }
    }
}