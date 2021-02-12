package dao;

import model.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDao {

    public Usuario login(){
        Statement st = null;
        Connection conn = Conexao.getConn();
        Usuario usuario = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuarios LIMIT 1;");
            while(rs.next()){
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setLogin(rs.getString(2));
                usuario.setSenha(rs.getString(3));
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return usuario;
    }
}
