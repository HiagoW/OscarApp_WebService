package dao;

import model.Usuario;
import model.Voto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VotoDao {

    public boolean voto(Voto voto){
        PreparedStatement st = null;
        Connection conn = Conexao.getConn();
        try {
            try {
                st = conn.prepareStatement("INSERT INTO votos VALUES (?,?,?);");
                st.setInt(1, voto.getUsuario().getId());
                st.setInt(2, voto.getIdFilme());
                st.setInt(3, voto.getIdDiretor());
                st.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println( e.getClass().getName()+": "+ e.getMessage() );
                System.exit(0);
            }
            st = conn.prepareStatement("UPDATE usuarios u SET votou = true WHERE u.id = ? ;");
            st.setInt(1, voto.getUsuario().getId());
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return true;
    }

    public Voto getVoto(Usuario usuario) {
        PreparedStatement st = null;
        Connection conn = Conexao.getConn();
        Voto voto = new Voto();
        voto.setUsuario(usuario);
        try {
            st = conn.prepareStatement("SELECT * FROM votos v WHERE v.usuario = ? ;");
            st.setInt(1, usuario.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                voto.setIdFilme(rs.getInt("filme"));
                voto.setIdFilme(rs.getInt("diretor"));
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return voto;
    }
}
