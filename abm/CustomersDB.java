package abm;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class CustomersDB {
    private Connection conn;
    private Statement statement;
    private Date date;
    SimpleDateFormat dateFormat;

    public CustomersDB(){
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
    
    public String[] getCustomerById(Object id){
        connectDB();
        String query = "SELECT id, nombre_cliente, ruc FROM clientes WHERE id="+id;
        String result[] = new String[1];
        try {
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                result = new String[]{
                    rs.getString("id"),
                    rs.getString("nombre_cliente"),
                    rs.getString("ruc")
                };
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return result;
     }
    public String[][] getAllCustomers(){
        String customersData[][] = new String[1][5];
        
        try {
            connectDB();
            String query = "SELECT * FROM clientes;";
            ResultSet rs = statement.executeQuery(query);

            //count data rows
            int count = 0;
            while (rs.next()){                
                count ++;
            }

            //read data
            rs.beforeFirst();
            customersData = new String[count][5];
            count = 0;
            while (rs.next()){
                String rowData[] = new String[] {
                    rs.getString("id"),
                    rs.getString("nombre_cliente"),
                    rs.getString("ruc"),
                    rs.getString("fecha_insert"),
                    rs.getString("fecha_update"),
                };
                customersData[count] = rowData;
                count ++;
           }
           conn.close();
           return customersData;
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return customersData;
    } 
    public void insertCustomer(String[] customerData){
        connectDB();
       String query = "INSERT INTO clientes (nombre_cliente, ruc, usuario_insert, fecha_insert) VALUES ('"
       +customerData[0]+"',"//name
       +customerData[1]+",'"//ruc
       +customerData[2]+"','"//user Insert
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
    public void modifyCustomer(String[] customerData){
        connectDB();
        String query = "UPDATE clientes SET nombre_cliente= '"
        +customerData[1]+
        "', ruc='"+customerData[2]+
        "', usuario_update='"+customerData[3]+
        "', fecha_update= '"+dateFormat.format(date)+
        "' WHERE id="+customerData[0];
        try {
            System.out.println(query);
            statement.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al modificar");
            e.printStackTrace();
        }
     }
    public void deleteCustomer(Object id){
        connectDB();
        String query = "DELETE FROM clientes WHERE id="+id;
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
