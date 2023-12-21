package it.unicam.cs.ids2324.project;

import it.unicam.cs.ids2324.project.Controller.CreatoreContributorAut;

public class Main {
    public static void main(String[] args) {

        CreatoreContributorAut creatoreContributorAut = new CreatoreContributorAut();
        try {
            System.out.println(creatoreContributorAut.creaAttore("Michele", "michelepolenta02@gmail.com",
                    "Password@@@123", "Ancona", "29/09/2002"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}