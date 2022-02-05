package model;

import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {


        /*//Atividade 1

        ClienteDAO.getInstance().create("Matheus","Rua Alzevides Gonçalves Pereira",
                "08775476","m241689@dac.unicamp.br", "11974827485");

        ClienteDAO.getInstance().create("Carlos","Rua São Pedro",
                "08775476","c221133@dac.unicamp.br","11924327485");

        ClienteDAO.getInstance().create("Carlos","Rua São Pedro",
                "08775476","c221133@dac.unicamp.br", "11924327485");

        ClienteDAO.getInstance().create("Vanessa","Rua Garopaba",
                "1123213","v11579@dac.unicamp.br","212321323");

        System.out.println(ClienteDAO.getInstance().retrieveAll());




        AnimalDAO.getInstance().create("Rubens", 2002,0,1, 1);
        AnimalDAO.getInstance().create("Lili", 2010,1,1, 4);
        AnimalDAO.getInstance().create("Juan", 2004,0,1, 1);
        AnimalDAO.getInstance().create("Mel", 1999,1,2, 4);
        AnimalDAO.getInstance().create("Rubens", 2002,0,2, 1);

        */




        /* Atividade 2  */
/*
        EspecieDAO.getInstance().create("Cachorro");
        EspecieDAO.getInstance().create("Gato");
        VeterinarioDAO.getInstance().create("Vanessa","v11579@dac.unicamp.br" , "11939239459" );
        TratamentoDAO.getInstance().create(1,"Tramento de cancer" , "23/05/2021", "23/08/2021", 1);
        ConsultaDAO.getInstance().create("22/05/2021", 7 ,"Cachorrinho doente", 3, 1, 2);
        ExameDAO.getInstance().create("Raio X", 2);
*/

        Animal animal = AnimalDAO.getInstance().retrieveById(11);
        animal.setIdCliente(3);
        AnimalDAO.getInstance().update(animal);
        System.out.println(ConsultaDAO.getInstance().retrieveAll());
        System.out.println(EspecieDAO.getInstance().retrieveAll());
        System.out.println(ExameDAO.getInstance().retrieveAll());
        System.out.println(TratamentoDAO.getInstance().retrieveAll());
        System.out.println(VeterinarioDAO.getInstance().retrieveAll());


    }
}
