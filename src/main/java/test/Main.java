package test;

import pdfprinting.HelloWorld;
import pdfprinting.PremierLeague;
import pdfprinting.RickAstley;
import pdfprinting.States;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //HelloWorld.printHelloWorld();
        //RickAstley.printRickAstley();
        //States.printTable();
        PremierLeague.printPremierLeague();
    }
}
