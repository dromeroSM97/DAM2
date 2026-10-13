import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;

public class LlegirZoo {
    public static void main(String[] args) {
        try {
            // Carregar i parsejar el fitxer XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("zoo.xml"));

            // Obtenir la llista d'animals
            NodeList animals = doc.getElementsByTagName("animal");

            System.out.println("--- INFORMACIÓ DELS ANIMALS DEL ZOO ---");
            
            // Mostrar el contingut de tots els animals
            for (int i = 0; i < animals.getLength(); i++) {
                Element animal = (Element) animals.item(i);
                
                String id = animal.getAttribute("id");
                String nom = animal.getElementsByTagName("nom").item(0).getTextContent();
                String especie = animal.getElementsByTagName("especie").item(0).getTextContent();
                String aliment = animal.getElementsByTagName("aliment").item(0).getTextContent();
                String edat = animal.getElementsByTagName("edat").item(0).getTextContent();

                System.out.println("Animal #" + id);
                System.out.println("Nom: " + nom);
                System.out.println("Espècie: " + especie);
                System.out.println("Aliment: " + aliment);
                System.out.println("Edat: " + edat);
                System.out.println();
            }
            
            // Recompte total d'animals
            System.out.println("Total d'animals al zoo: " + animals.getLength());
            System.out.println();

            // Filtrar i mostrar només els animals que mengen carn
            System.out.println("--- ANIMALS CARNÍVORS ---");
            for (int i = 0; i < animals.getLength(); i++) {
                Element animal = (Element) animals.item(i);
                String aliment = animal.getElementsByTagName("aliment").item(0).getTextContent();

                if (aliment.equalsIgnoreCase("Carn")) {
                    String nom = animal.getElementsByTagName("nom").item(0).getTextContent();
                    System.out.println("- " + nom + " (Aliment: " + aliment + ")");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
