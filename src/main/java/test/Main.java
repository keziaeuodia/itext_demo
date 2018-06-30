package test;

import pdfprinting.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //HelloWorld.printHelloWorld();
        //RickAstley.printRickAstley();
        //States.printTable();
        //PremierLeague.printPremierLeague();
        Films films = new Films();
        films.printTable();
    }
}
