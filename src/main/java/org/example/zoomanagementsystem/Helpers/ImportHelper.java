package org.example.zoomanagementsystem.Helpers;
import org.example.zoomanagementsystem.Model.*;

/**
 * The {@code ImportHelper} class is a utility class responsible for
 * constructing and returning a preconfigured hierarchical structure
 * of animal enclosures and collections used in the zoo application demo.
 *
 * <p>This class uses the Composite Design Pattern by organizing
 * multiple {@link Enclosure} objects (leaf nodes) and
 * {@link CompositeEnclosureCollection} objects (composite nodes)
 * into a tree structure of animals grouped by type.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * CompositeEnclosureCollection zoo = ImportHelper.createAnimals();
 * zoo.displayInfo();
 * }</pre>
 *
 * @author Matthias Harte
 * @version 1.0
 */
public class ImportHelper {

    /**
     * Creates and returns a preconfigured {@link CompositeEnclosureCollection}
     * representing a zoo section called "Big Cats". This structure contains
     * lions, tigers (and cubs), and cougars, organized into individual
     * {@link Enclosure} instances and combined into composite collections.
     *
     * <p>The resulting hierarchy looks like this:</p>
     * <pre>
     * Big Cats
     * ├── Lions
     * │   ├── Simba (3 years)
     * │   ├── Mufasa (8 years)
     * │   └── Nala (3 years)
     * ├── Tigers
     * │   ├── Tigers Habitat
     * │   │   ├── Shere Kahn (3 years)
     * │   │   └── Rajah (3 years)
     * │   └── Tiger Cubs
     * │       ├── Tala (5 years)
     * │       ├── Ravi (0 years)
     * │       ├── Kali (0 years)
     * │       └── Indra (0 years)
     * └── Cougars
     *     ├── Sierra (3 years)
     *     ├── Rocky (3 years)
     *     ├── Luna (2 years)
     *     └── Lenny (1 year)
     * </pre>
     *
     * @return a fully populated {@link CompositeEnclosureCollection} representing
     *         the “Big Cats” section of the zoo
     */
    public static CompositeEnclosureCollection createAnimals() {
        // --- Lions enclosure ---
        Enclosure lions = new Enclosure("Lions");
        lions.addAnimal(new Lion("Simba", 3));
        lions.addAnimal(new Lion("Mufasa", 8));
        lions.addAnimal(new Lion("Nala", 3));

        // --- Tiger habitat enclosure ---
        Enclosure tigerHabitat = new Enclosure("Tigers Habitat");
        tigerHabitat.addAnimal(new Tiger("Shere Kahn", 3));
        tigerHabitat.addAnimal(new Tiger("Rajah", 3));

        // --- Tiger cubs enclosure ---
        Enclosure cubs = new Enclosure("Tiger Cubs");
        cubs.addAnimal(new Tiger("Tala", 5));
        cubs.addAnimal(new Tiger("Ravi", 0));
        cubs.addAnimal(new Tiger("Kali", 0));
        cubs.addAnimal(new Tiger("Indra", 0));

        // --- Composite for all tigers ---
        CompositeEnclosureCollection tigers = new CompositeEnclosureCollection("Tigers");
        tigers.addCollection(tigerHabitat);
        tigers.addCollection(cubs);

        // --- Cougars enclosure ---
        Enclosure cougarsEnclosure = new Enclosure("Cougars");
        cougarsEnclosure.addAnimal(new Cougar("Sierra", 3));
        cougarsEnclosure.addAnimal(new Cougar("Rocky", 3));
        cougarsEnclosure.addAnimal(new Cougar("Luna", 2));
        cougarsEnclosure.addAnimal(new Cougar("Lenny", 1));

        // --- Composite for all big cats ---
        CompositeEnclosureCollection bigCats = new CompositeEnclosureCollection("Big Cats");
        bigCats.addCollection(lions);
        bigCats.addCollection(tigers);
        bigCats.addCollection(cougarsEnclosure);

        // Return the complete composite structure
        return bigCats;
    }
}