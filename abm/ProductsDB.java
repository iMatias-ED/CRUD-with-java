package abm;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class ProductsDB {
    private Connection conn;
    private Statement statement;
    private Date date;
    SimpleDateFormat dateFormat;

    public ProductsDB(){
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
    
    public String[] getProductById(Object id){
        connectDB();
        String query = "SELECT id, nombre_producto FROM productos WHERE id="+id;
        String result[] = new String[1];
        try {
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                result = new String[]{
                    rs.getString("id"),
                    rs.getString("nombre_producto"),
                };
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return result;
     }
    public String[][] getAllProducts(){
        String productsData[][] = new String[1][4];
        
        try {
            connectDB();
            String query = "SELECT * FROM productos;";
            ResultSet rs = statement.executeQuery(query);

            //count data rows
            int count = 0;
            while (rs.next()){                
                count ++;
            }

            //read data
            rs.beforeFirst();
            productsData = new String[count][4];
            count = 0;
            while (rs.next()){
                String rowData[] = new String[] {
                    rs.getString("id"),
                    rs.getString("nombre_producto"),
                    rs.getString("fecha_insert"),
                    rs.getString("fecha_update"),
                };
                productsData[count] = rowData;
                count ++;
           }
           conn.close();
           return productsData;
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return productsData;
    } 
    public void insertProduct(String[] productData){
        connectDB();
       String query = "INSERT INTO productos (nombre_producto, usuario_insert, fecha_insert) VALUES ('"
       +productData[0]+"','"//name
       +productData[1]+"','"//user Insert
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
    public void modifyProduct(String[] productData){
        connectDB();
        String query = "UPDATE productos SET nombre_producto= '"
        +productData[1]+
        "', usuario_update='"+productData[2]+
        "', fecha_update= '"+dateFormat.format(date)+
        "' WHERE id="+productData[0];
        try {
            System.out.println(query);
            statement.executeUpdate(query);
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al modificar");
            e.printStackTrace();
        }
     }
    public void deleteProduct(Object id){
        connectDB();
        String query = "DELETE FROM productos WHERE id="+id;
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
