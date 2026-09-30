/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.e2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author usuari-tarda
 */
public class RA1_E2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Demanar la clau de xifrat a l'usuari per consola
        System.out.print("Introdueix la clau de desplaçament (número enter): ");
        int clau = scanner.nextInt();
        
        String fitxerEntrada = "entrada.txt";
        String fitxerXifrat = "xifrat.txt";
        String fitxerDesxifrat = "desxifrat.txt";
        
        // Creem un fitxer d'entrada d'exemple per comprovar el funcionament
        crearFitxerExemple(fitxerEntrada);
        
        // Procés de Xifrat
        xifrarFitxer(fitxerEntrada, fitxerXifrat, clau);
        
        // Procés de Desxifrat
        desxifrarFitxer(fitxerXifrat, fitxerDesxifrat, clau);
        
        scanner.close();
    }

    /**
     * Crea un fitxer d'entrada d'exemple si no existeix.
     */
    public static void crearFitxerExemple(String nomFitxer) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nomFitxer))) {
            pw.println("Hola mon!");
            pw.println("Aquesta es una prova de xifrat amb Java.");
            pw.println("Treballant amb BufferedReader i BufferedWriter.");
            System.out.println("-> Fitxer d'entrada creat correctament: " + nomFitxer);
        } catch (IOException e) {
            System.out.println("Error creant el fitxer d'exemple: " + e.getMessage());
        }
    }

    /**
     * Llegeix el fitxer d'entrada, inverteix cada línia, aplica Cèsar i l'escriu al fitxer xifrat.
     */
    public static void xifrarFitxer(String entrada, String sortida, int clau) {
        System.out.println("\n--- Iniciant procés de Xifrat ---");
        try (
            BufferedReader br = new BufferedReader(new FileReader(entrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(sortida))
        ) {
            String linia;
            while ((linia = br.readLine()) != null) {
                // Invertir la línia
                String invertida = new StringBuilder(linia).reverse().toString();
                
                // Aplicar xifrat Cèsar (desplaçament Unicode)
                String xifrada = aplicaCesar(invertida, clau);
                
                // Escriure al fitxer xifrat
                bw.write(xifrada);
                bw.newLine();
            }
            System.out.println("[OK] Fitxer xifrat correctament a: " + sortida);
        } catch (FileNotFoundException e) {
            System.out.println("[Error] Fitxer no trobat: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Error d'E/S] " + e.getMessage());
        }
    }

    /**
     * Llegeix el fitxer xifrat, aplica el desplaçament invers i inverteix la línia.
     */
    public static void desxifrarFitxer(String entrada, String sortida, int clau) {
        System.out.println("\n--- Iniciant procés de Desxifrat ---");
        try (
            BufferedReader br = new BufferedReader(new FileReader(entrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(sortida))
        ) {
            String linia;
            while ((linia = br.readLine()) != null) {
                // Aplicar desplaçament invers de la clau (-clau)
                String desplaçada = aplicaCesar(linia, -clau);
                
                // Tornar a invertir cada línia
                String original = new StringBuilder(desplaçada).reverse().toString();
                
                // Escriure al fitxer desxifrat
                bw.write(original);
                bw.newLine();
            }
            System.out.println("[OK] Fitxer desxifrat correctament a: " + sortida);
        } catch (FileNotFoundException e) {
            System.out.println("[Error] Fitxer no trobat: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Error d'E/S] " + e.getMessage());
        }
    }

    /**
     * Mètode auxiliar per aplicar el desplaçament Cèsar sobre els caràcters d'una cadena.
     */
    private static String aplicaCesar(String text, int clau) {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            resultat.append((char) (c + clau));
        }
        return resultat.toString();
    }
}
