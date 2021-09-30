package abm;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class UsersDB {
    private Connection conn;
    private Statement statement;
    private Date date;
    SimpleDateFormat dateFormat;

    public UsersDB(){
        date = new Date();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd H:m:s");
    }
    
    private void connectDB(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/supermercado", "root", "");
            statement = conn.createStatement();
        } catch (Exception e) {
            System.out.println("Error en la coneccion");
            e.printStackTrace();
        }
    } 

    public boolean checkUserLogin(String name, String password){
        connectDB();
        String query = "SELECT * FROM usuarios WHERE usuario='"+name+"' AND pass='"+password+"';";
        try {
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            
            if (rs.next()){
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        return false;
    }

    public String[] getUserById(Object id){
        String query = "SELECT id, usuario, pass FROM usuarios WHERE id="+id;
        String result[] = new String[1];
        try {
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                result = new String[]{
                    rs.getString("id"),
                    rs.getString("usuario"),
                    rs.getString("pass")
                };
            }
        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }
        return result;
     }
    public String[][] getAllUsers(){
        String usersData[][] = new String[1][6];
        
        try {
            connectDB();
            String query = "SELECT u.id, usuario, p.perfil, pass, u.fecha_insert, u.fecha_update FROM usuarios AS u LEFT JOIN perfiles AS p ON u.id_perfil = p.id;";
            ResultSet rs = statement.executeQuery(query);

            //count data rows
            int count = 0;
            while (rs.next()){                
                count ++;
            }

            //read data
            rs.beforeFirst();
            usersData = new String[count][6];
            count = 0;
            while (rs.next()){
                String rowData[] = new String[] {
                    rs.getString("id"),
                    rs.getString("usuario"),
                    rs.getString("pass"),
                    rs.getString("perfil"),
                    rs.getString("fecha_insert"),
                    rs.getString("fecha_update"),
                };
                usersData[count] = rowData;
                count ++;
            }
            return usersData;
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return usersData;
    } 
    public String[][] getAllProfiles(){
        String profilesData[][] = new String[1][2];
        
        try {
            connectDB();
            String query = "SELECT id, perfil FROM perfiles;";
            ResultSet rs = statement.executeQuery(query);

            //count data rows
            int count = 0;
            while (rs.next()){                
                count ++;
            }

            //read data
            rs.beforeFirst();
            profilesData = new String[count][2];
            count = 0;
            while (rs.next()){
                String rowData[] = new String[] {
                    rs.getString("id"),
                    rs.getString("perfil")
                };
                profilesData[count] = rowData;
                count ++;
            }
            return profilesData;
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return profilesData;
    } 
    public void insertUser(String[] userData){
       String query = "INSERT INTO usuarios (usuario, id_perfil, pass, usuario_insert, fecha_insert) VALUES ('"
       +userData[0]+"',"
       +userData[1]+",'"
       +userData[2]+"','"
       +userData[3]+"','"
       +dateFormat.format(date)+"') "; 
       try {
           System.out.println(query);
           statement.executeUpdate(query);

       } catch (Exception e) {
           System.out.println("Error al insertar");
           e.printStackTrace();
       }
    }
    public void modifyUser(String[] userData){
        String query = "UPDATE usuarios SET usuario= '"
        +userData[1]+
        "', id_perfil='"+userData[2]+
        "', pass='"+userData[3]+
        "', usuario_update='"+userData[4]+
        "', fecha_update= '"+dateFormat.format(date)+
        "' WHERE id="+userData[0];
        try {
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }
     }
    public void deleteUser(Object id){
        String query = "DELETE FROM usuarios WHERE id="+id;
        try {
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }
     }
  
}
