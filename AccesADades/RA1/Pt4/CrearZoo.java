import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class CrearZoo {
    public static void main(String[] args) {
        try {
            // Crear el document XML buit
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Crear l'element arrel <zoo>
            Element zoo = doc.createElement("zoo");
            doc.appendChild(zoo);

            // --- Animal 1: Pingüí ---
            Element animal1 = doc.createElement("animal");
            animal1.setAttribute("id", "1");
            zoo.appendChild(animal1);

            Element nom1 = doc.createElement("nom");
            nom1.setTextContent("Pingüí");
            animal1.appendChild(nom1);

            Element especie1 = doc.createElement("especie");
            especie1.setTextContent("Aptenodytes forsteri");
            animal1.appendChild(especie1);

            Element aliment1 = doc.createElement("aliment");
            aliment1.setTextContent("Peix");
            animal1.appendChild(aliment1);

            Element edat1 = doc.createElement("edat");
            edat1.setTextContent("5");
            animal1.appendChild(edat1);

            // --- Animal 2: Lleó ---
            Element animal2 = doc.createElement("animal");
            animal2.setAttribute("id", "2");
            zoo.appendChild(animal2);

            Element nom2 = doc.createElement("nom");
            nom2.setTextContent("Lleó");
            animal2.appendChild(nom2);

            Element especie2 = doc.createElement("especie");
            especie2.setTextContent("Panthera leo");
            animal2.appendChild(especie2);

            Element aliment2 = doc.createElement("aliment");
            aliment2.setTextContent("Carn");
            animal2.appendChild(aliment2);

            Element edat2 = doc.createElement("edat");
            edat2.setTextContent("8");
            animal2.appendChild(edat2);

            // Convertir l'arbre DOM a fitxer XML amb Transformer
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(
                new DOMSource(doc),
                new StreamResult(new File("zoo.xml"))
            );

            System.out.println("Fitxer zoo.xml generat correctament!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
