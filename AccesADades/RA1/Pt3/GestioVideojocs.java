import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestioVideojocs {

    private static final String FITXER = "videojocs.dat";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Videojoc> cataleg = carregarFitxer();
        boolean sortir = false;

        while (!sortir) {
            mostrarMenu();
            int opcio = llegirEnter("Selecciona una opcio: ");

            switch (opcio) {
                case 1:
                    afegirVideojoc(cataleg);
                    guardarFitxer(cataleg);
                    break;
                case 2:
                    llistarVideojocs(cataleg);
                    break;
                case 3:
                    cercarVideojocs(cataleg);
                    break;
                case 4:
                    actualitzarVideojoc(cataleg);
                    guardarFitxer(cataleg);
                    break;
                case 5:
                    eliminarVideojoc(cataleg);
                    guardarFitxer(cataleg);
                    break;
                case 6:
                    guardarFitxer(cataleg);
                    System.out.println("Tancant programa...");
                    sortir = true;
                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta-ho de nou.");
            }
            System.out.println();
        }
    }

    private static void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("      GESTOR DE CATALEG DE VIDEOJOCS    ");
        System.out.println("========================================");
        System.out.println("1. Afegir videojoc");
        System.out.println("2. Llistar tots els videojocs");
        System.out.println("3. Cercar videojocs per títol");
        System.out.println("4. Actualitzar un videojoc");
        System.out.println("5. Eliminar un videojoc");
        System.out.println("6. Sortir del programa");
        System.out.println("========================================");
    }


    private static void afegirVideojoc(ArrayList<Videojoc> cataleg) {
        System.out.println("\n--- AFEGIR NOU VIDEOJOC ---");
        System.out.print("Títol: ");
        String titol = scanner.nextLine().trim();

        System.out.print("Gènere: ");
        String genere = scanner.nextLine().trim();

        int any = llegirEnter("Any de llançament: ");

        System.out.print("Plataforma: ");
        String plataforma = scanner.nextLine().trim();

        double preu = llegirDouble("Preu (€): ");

        Videojoc nouJoc = new Videojoc(titol, genere, any, plataforma, preu);
        cataleg.add(nouJoc);
        System.out.println("Videojoc afegit correctament.");
    }

    private static void llistarVideojocs(ArrayList<Videojoc> cataleg) {
        System.out.println("\n--- LLISTAT DE VIDEOJOCS ---");
        if (cataleg.isEmpty()) {
            System.out.println("El catàleg està buit.");
            return;
        }

        for (int i = 0; i < cataleg.size(); i++) {
            System.out.printf("[%d] %s\n", (i + 1), cataleg.get(i));
        }
    }

    private static void cercarVideojocs(ArrayList<Videojoc> cataleg) {
        System.out.println("\n--- CERCAR VIDEOJOC ---");
        System.out.print("Introdueix el text a cercar en el títol: ");
        String cerca = scanner.nextLine().toLowerCase().trim();

        boolean trobat = false;
        for (Videojoc j : cataleg) {
            if (j.getTitol().toLowerCase().contains(cerca)) {
                System.out.println(j);
                trobat = true;
            }
        }

        if (!trobat) {
            System.out.println("No s'ha trobat cap videojoc que coincideixi amb el text.");
        }
    }

    private static void actualitzarVideojoc(ArrayList<Videojoc> cataleg) {
        System.out.println("\n--- ACTUALITZAR VIDEOJOC ---");
        if (cataleg.isEmpty()) {
            System.out.println("El catàleg està buit.");
            return;
        }

        llistarVideojocs(cataleg);
        int index = llegirEnter("Introdueix el número del videojoc a modificar: ") - 1;

        if (index < 0 || index >= cataleg.size()) {
            System.out.println("Índex no vàlid.");
            return;
        }

        Videojoc joc = cataleg.get(index);
        System.out.println("Modificant: " + joc.getTitol());

        System.out.print("Nou títol (deixa en blanc per mantenir '" + joc.getTitol() + "'): ");
        String titol = scanner.nextLine().trim();
        if (!titol.isEmpty()) joc.setTitol(titol);

        System.out.print("Nou gènere (deixa en blanc per mantenir '" + joc.getGenere() + "'): ");
        String genere = scanner.nextLine().trim();
        if (!genere.isEmpty()) joc.setGenere(genere);

        System.out.print("Nou any (prem Enter per mantenir '" + joc.getAnyLlancament() + "'): ");
        String anyStr = scanner.nextLine().trim();
        if (!anyStr.isEmpty()) {
            try {
                joc.setAnyLlancament(Integer.parseInt(anyStr));
            } catch (NumberFormatException e) {
                System.out.println("Any no vàlid. Es manté l'actual.");
            }
        }

        System.out.print("Nova plataforma (deixa en blanc per mantenir '" + joc.getPlataforma() + "'): ");
        String plataforma = scanner.nextLine().trim();
        if (!plataforma.isEmpty()) joc.setPlataforma(plataforma);

        System.out.print("Nou preu (€) (prem Enter per mantenir '" + joc.getPreu() + "'): ");
        String preuStr = scanner.nextLine().trim();
        if (!preuStr.isEmpty()) {
            try {
                joc.setPreu(Double.parseDouble(preuStr));
            } catch (NumberFormatException e) {
                System.out.println("Preu no vàlid. Es manté l'actual.");
            }
        }

        System.out.println("Videojoc actualitzat correctament.");
    }

    private static void eliminarVideojoc(ArrayList<Videojoc> cataleg) {
        System.out.println("\n--- ELIMINAR VIDEOJOC ---");
        if (cataleg.isEmpty()) {
            System.out.println("El catàleg està buit.");
            return;
        }

        llistarVideojocs(cataleg);
        int index = llegirEnter("Introdueix el número del videojoc a eliminar: ") - 1;

        if (index >= 0 && index < cataleg.size()) {
            Videojoc eliminat = cataleg.remove(index);
            System.out.println("S'ha eliminat el videojoc '" + eliminat.getTitol() + "'.");
        } else {
            System.out.println("Índex no vàlid.");
        }
    }


    @SuppressWarnings("unchecked")
    private static ArrayList<Videojoc> carregarFitxer() {
        File f = new File(FITXER);
        if (!f.exists()) {
            System.out.println("S'ha creat un nou catàleg buit.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            ArrayList<Videojoc> llista = (ArrayList<Videojoc>) ois.readObject();
            System.out.println("Dades carregades correctament des de " + FITXER);
            return llista;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("S'ha produït un error en carregar el fitxer: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void guardarFitxer(ArrayList<Videojoc> cataleg) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FITXER))) {
            oos.writeObject(cataleg);
            System.out.println("Canvis desats correctament al fitxer.");
        } catch (IOException e) {
            System.out.println("Error en desar les dades: " + e.getMessage());
        }
    }


    private static int llegirEnter(String missatge) {
        while (true) {
            System.out.print(missatge);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introdueix un número enter vàlid.");
            }
        }
    }

    private static double llegirDouble(String missatge) {
        while (true) {
            System.out.print(missatge);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introdueix un número decimal vàlid.");
            }
        }
    }
}
