package Arquitetura.Config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection () throws SQLException {

        try {
            // Busca as informações de application.properties
            Properties properties = new Properties();
            InputStream is = ConnectionFactory.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");
            properties.load(is);

            // Busca de cada valor específico
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String senha = properties.getProperty("db.senha");

            return DriverManager.getConnection(url, user, senha);
        }catch (Exception e)
        {
            throw new SQLException("Erro ao conectar ao banco", e);
        }

    }
}