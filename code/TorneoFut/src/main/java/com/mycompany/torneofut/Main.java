package com.mycompany.torneofut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * LINKS http://zetcode.com/db/mysqljava/ 
 * https://mvnrepository.com/
 *
 * @author angelrg
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/TALLER?useSSL=false";
        String user = "taller_user";
        String password = "Admin12345.";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            /*Ejemplo basico - Verificar conexion*/
//            ejemploBasico(connection);

            /*Creamos un cliente*/
//            crearCliente(connection);

            /*Actualizar Cliente*/
//            actualizarCliente(connection);

            /*Creacion avanzada, crear reparacion*/
//            agregarReparacion(connection);

            /*Busqueda*/
//            obtenerCliente(connection);
            buscarCliente(connection);

            connection.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Verificamos si existe conexion con al base de datos MySQL
     *
     * @param connection
     */
    public static void ejemploBasico(Connection connection) {
        String query = "SELECT VERSION()";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Creamos un cliente
     *
     * @param connection
     */
    public static void crearCliente(Connection connection) {
        String query = "INSERT INTO CLIENTE VALUES (?,?,?,?)";
        String NIT = "1234";
        String nombre = "Marianna";
        String apellido = "Alvarado";
        String telefono = "25836923";

        try (PreparedStatement preSt = connection.prepareStatement(query)) {

            preSt.setString(1, NIT);
            preSt.setString(2, nombre);
            preSt.setString(3, apellido);
            preSt.setString(4, telefono);

            preSt.executeUpdate();

            System.out.println("Cliente agregado");

            preSt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Creamos un cliente
     *
     * @param connection
     */
    public static void agregarReparacion(Connection connection) {
        String query = "INSERT INTO REPARACION (descripcion, fecha, costo, matricula_vehiculo, DPI_mecanico) VALUES (?,?,?,?,?)";
        String descripcion = "Descripcion agregada desde JAVA";
        String fecha = "2020-08-18";
        double costo = 300;
        String matricula = "P 101ABC";
        String DPI = "45810901";

        try (PreparedStatement preSt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preSt.setString(1, descripcion);
            preSt.setString(2, fecha);
            preSt.setDouble(3, costo);
            preSt.setString(4, matricula);
            preSt.setString(5, DPI);

            preSt.executeUpdate();

            try (ResultSet result = preSt.getGeneratedKeys()) {

                if (result.first()) {
                    System.out.println("ID Reparacion: " + result.getLong(1));
                }
                result.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            preSt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Creamos un cliente
     *
     * @param connection
     */
    public static void actualizarCliente(Connection connection) {
        String query = "UPDATE CLIENTE SET nombre = ? WHERE NIT = ?";
        String NIT = "2222";
        String nombre = "ORLANDO";

        try (PreparedStatement preSt = connection.prepareStatement(query)) {

            preSt.setString(1, nombre);
            preSt.setString(2, NIT);

            preSt.executeUpdate();

            System.out.println("Cliente actualizado");

            preSt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Mandamos a obtener a todos los clientes de la base de datos
     *
     * @param connection
     */
    public static void obtenerCliente(Connection connection) {
        String query = "SELECT * FROM CLIENTE";

        try (PreparedStatement preSt = connection.prepareStatement(query);
                ResultSet result = preSt.executeQuery()) {

            System.out.println("\n>>>>>>>>>>CLIENTE<<<<<<<<<<");
            while (result.next()) {
                System.out.println("NIT: " + result.getString(1)
                        + ",\tNombre: " + result.getString(2)
                        + ",\tApellido: " + result.getString("apellido")
                        + ",\tTelefono: " + result.getString("telefono"));
            }

            result.close();
            preSt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Mandamos a obtener a los clientes de la base de datos en base al nombre
     *
     * @param connection
     */
    public static void buscarCliente(Connection connection) {
        String query = "SELECT NIT, nombre, apellido, telefono FROM CLIENTE WHERE nombre LIKE ?";
        String nombreBusqueda = "an";

        try (PreparedStatement preSt = connection.prepareStatement(query)) {

            preSt.setString(1, "%" + nombreBusqueda + "%");
            ResultSet result = preSt.executeQuery();

            ResultSetMetaData meta = result.getMetaData();

            System.out.println("\n>>>>>>>>>>CLIENTE<<<<<<<<<<");
            System.out.println(meta.getColumnName(1) + "\t" + meta.getColumnName(2) + " y " + meta.getColumnName(3) + "\t" + meta.getColumnName(4) + "\t");

            while (result.next()) {
                System.out.println(result.getString(1)
                        + "\t" + result.getString(2)
                        + ", " + result.getString("apellido")
                        + "\t" + result.getString("telefono"));
            }

            result.close();
            preSt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
