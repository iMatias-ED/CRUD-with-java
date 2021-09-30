package abm;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class ProfilesDB {
    private Connection conn;
    private Statement statement;
    private Date date;
    SimpleDateFormat dateFormat;

    public ProfilesDB(){
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
    
    public String[] getProfileById(Object id){
        connectDB();
        String query = "SELECT id, perfil FROM perfiles WHERE id="+id;
        String result[] = new String[1];
        try {
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                result = new String[]{
                    rs.getString("id"),
                    rs.getString("perfil"),
                };
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return result;
     }
    public String[][] getAllProfiles(){
        String profilesData[][] = new String[1][4];
        
        try {
            connectDB();
            String query = "SELECT * FROM perfiles;";
            ResultSet rs = statement.executeQuery(query);

            //count data rows
            int count = 0;
            while (rs.next()){                
                count ++;
            }

            //read data
            rs.beforeFirst();
            profilesData = new String[count][5];
            count = 0;
            while (rs.next()){
                String rowData[] = new String[] {
                    rs.getString("id"),
                    rs.getString("perfil"),
                    rs.getString("fecha_insert"),
                    rs.getString("fecha_update"),
                };
                profilesData[count] = rowData;
                count ++;
           }
           conn.close();
           return profilesData;
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return profilesData;
    } 
    public void insertProfile(String[] profileData){
        connectDB();
       String query = "INSERT INTO perfiles (perfil, usuario_insert, fecha_insert) VALUES ('"
       +profileData[0]+"','"//name
       +profileData[1]+"','"//user Insert
       +dateFormat.format(date)+"') "; 
       try {
           System.out.println(query);
           statement.executeUpdate(query);
           conn.close();

       } catch (Exception e) {
           System.out.println("Error al insertar");
           e.printStackTrace();
       }
    }
    public void modifyProfile(String[] profileData){
        connectDB();
        String query = "UPDATE perfiles SET perfil= '"
        +profileData[1]+
        "', usuario_update='"+profileData[2]+
        "', fecha_update= '"+dateFormat.format(date)+
        "' WHERE id="+profileData[0];
        try {
            System.out.println(query);
            statement.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al modificar");
            e.printStackTrace();
        }
     }
    public void deleteProfile(Object id){
        connectDB();
        String query = "DELETE FROM perfiles WHERE id="+id;
        try {
            System.out.println(query);
            statement.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al eliminar");
            e.printStackTrace();
        }
     }
  
}
