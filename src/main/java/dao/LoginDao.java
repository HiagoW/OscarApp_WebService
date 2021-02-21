package dao;

import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDao {

    public boolean login(Usuario usuario){
        PreparedStatement st = null;
        Connection conn = Conexao.getConn();
        try {
            st = conn.prepareStatement("SELECT * FROM usuarios u WHERE u.login = ? and u.senha = ? LIMIT 1;");
            st.setString(1,usuario.getLogin());
            st.setString(2,usuario.getSenha());
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                usuario.setVotou(rs.getBoolean("votou"));
                usuario.setId(rs.getInt("id"));
                st = conn.prepareStatement("UPDATE usuarios SET token = ? WHERE id = ?; ");
                st.setInt(1,usuario.getToken());
                st.setInt(2, usuario.getId());
                st.executeUpdate();
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return false;
    }

    public boolean validarToken(Usuario usuario){
        PreparedStatement st = null;
        Connection conn = Conexao.getConn();
        try {
            st = conn.prepareStatement("SELECT * FROM usuarios u WHERE u.id = ? and u.token = ? LIMIT 1;");
            st.setInt(1,usuario.getId());
            st.setInt(2,usuario.getToken());
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return false;
    }
}
