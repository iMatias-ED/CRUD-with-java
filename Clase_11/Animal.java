package Clase_11;

public class Animal {
    private int edad = 0;
    private int altura = 0;
    private String color = "";
    private String pelaje = "";

    public String comer(){
        return "animal comiendo";
    }
    public String getPelaje (){
        return pelaje;
    }
    public void setPelaje(String pelaje){
        this.pelaje = pelaje;
    }
    public int getEdad(){
        return edad;
    }
    public void setEdad(int edad){
        this.edad = edad;
    }
}
