package Clase_11;

import java.io.IOException;

public class test {
    public static void main (String[] args){
        Perro mi_perro = new Perro();
        System.out.println("Edad perro: "+ mi_perro.getEdad());
        mi_perro.setEdad(10);
        System.out.println("Edad perro: "+ mi_perro.getEdad());


        Gato mi_gato = new Gato();
        System.out.println("Edad gato: "+ mi_gato.getEdad());
        mi_gato.setEdad(5);
        System.out.println("Edad gato: "+ mi_gato.getEdad());

        mi_gato.maullar();

    }
}
