package com.example.OscarApp_WebService;

import com.google.gson.Gson;
import dao.LoginDao;
import model.Usuario;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Path("/login")
public class LoginResource {

    LoginDao loginDao = new LoginDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        Usuario usuario = loginDao.login();
        Gson gson = new Gson();
        return Response.ok(gson.toJson(usuario)).build();
    }

    private class Teste implements Serializable {
        int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}