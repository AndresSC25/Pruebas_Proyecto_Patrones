package com.laca.singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TODO:-Patron singleton aplicado por Andrés Segura Calderon-
public class DataBaseConnection {
    //TODO: Se instancia la clase DataBaseConnection que nos servira para solo tener una instancia y se exporta
    // sqlConnection
    private static DataBaseConnection instance;
    private Connection connection;
    public DataBaseConnection() throws SQLException {
        try {
            //TODO: Se declara la informacion de la conexión a la base de datos,se puede cambiar si es necesario
            //TODO: Pero es importante verificar bien los datos por que si no se puede llegar a caer
            Class.forName("org.mariadb.jdbc.Driver");
            String password = "159457896321";
            String username = "root";
            String url = "jdbc:mariadb://localhost:3306/proyecto_patrones";
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Ups... Hay algo mal con el String de la conexión: " + ex.getMessage());
        }
    }
    //TODO: con este metodo podemos obtener la direccion que se hace para tener la conexión.
    public Connection getConnection() throws SQLException {
        return connection;
    }
    //TODO: Con esto creamos la instancia de la clase que sea unica.
    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

}
