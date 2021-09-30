package clase_15;

import java.sql.*;

public class connectDB {
    Connection conn;
    Statement statement;


    public String getData(String id){
        String result = "Usuario no encontrado";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs_clase15", "root", "");
            Statement statement = conn.createStatement();
            
            //Execute Sql
            String query = "SELECT name FROM users WHERE id="+id;
            ResultSet resultSet = statement.executeQuery(query);

            //Read resultSet
            while (resultSet.next()){
                result = resultSet.getString("name");
            }
        } 
        catch (Exception e) {
            System.out.println("Error en la coneccion");
            e.printStackTrace();
        }
        return result;
    }
    public void insertUser(String userName){
        String query = "INSERT INTO users(name) VALUES ('"+userName+"')";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs_clase15", "root", "");
            Statement statement = conn.createStatement();
            
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }
    }
    public void deleteUser(String userId){
        String query = "DELETE FROM users WHERE id="+userId;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs_clase15", "root", "");
            Statement statement = conn.createStatement();
            
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error al eliminar");
            e.printStackTrace();
        }
    }
    public void updateUser(String userId, String newName){
        String query = "UPDATE users set name='"+newName+"'WHERE id="+userId;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hs_clase15", "root", "");
            Statement statement = conn.createStatement();
            
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error al eliminar");
            e.printStackTrace();
        }
    }
}
