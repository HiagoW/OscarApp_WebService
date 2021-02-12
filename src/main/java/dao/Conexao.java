package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static Connection conn = null;

    public static Connection getConn() {
        if (conn == null) {
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/oscarapp",
                                "postgres", "159951");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("Opened database successfully");
        }
            return conn;
    }

}
