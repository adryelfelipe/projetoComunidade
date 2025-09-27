package Arquitetura.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String DB_URL = "jdbc:mysql://avnadmin:AVNS_vLbsjNpv_2MvexmoRSL@mysql-2902ce13-gustavo-4c3e.f.aivencloud.com:25606/dbProjetoComunidade?ssl-mode=REQUIRED";
    private static final String DB_USER = "avnadmin";
    private static final String DB_PASSWORD = "AVNS_vLbsjNpv_2MvexmoRSL";

    public static Connection getConnection () throws SQLException {

        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco.", e);
        }
    }
}