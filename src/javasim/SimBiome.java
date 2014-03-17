package javasim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Whiplash
 */
public class SimBiome implements Updateable {

    private int temperature; // Temp and precip are unused.
    private int precipitation;

    Random gen = new Random();
    private int randnum;
    private int plantspawnnum;
    private int herbanimspawnnum;
    private int plantdienum;
    private int plantnum;
    private int herbanimnum;
    private int herbanimeat;
    private int carnanimspawnnum;
    private int carnanimnum;
    private int carnanimeat;

    ArrayList<SimPlant> plantArray = new ArrayList<>();
    ArrayList<HerbivoreAnimal> herbanimalArray = new ArrayList<>();
    ArrayList<CarnivoreAnimal> carnanimalArray = new ArrayList<>();

    public void initializeBiome() { // Initializing the biome will generate between N and N plant and animal objects.
        int p = Math.max(1000, gen.nextInt(4000));
        for (int i = 0; i < p; i++) {
            plantArray.add(new SimPlant("sPlant " + i));
        }
        int h = Math.max(200, gen.nextInt(600));
        for (int i = 0; i < h; i++) {
            herbanimalArray.add(new HerbivoreAnimal("sHerbivoreAnimal " + i));
        }
        int c = Math.max(50, gen.nextInt(200));
        for (int i = 0; i < c; i++) {
            carnanimalArray.add(new CarnivoreAnimal("sCarnivoreAnimal " + i));
        }
        ConsoleLogger.Log(p + " plants have been initialized\n" + h + " herbivore animals have been initialized\n" + c + " carnivore animals have been initialized", 2);
    }

    public void carnivoreAnimalSpawner() {
        randnum = gen.nextInt(24);
        carnanimspawnnum++;
        int breedernum = carnanimalArray.size() / Math.max(2, gen.nextInt(8));
        int litternum = Math.max(1, gen.nextInt(3));
        if (randnum == carnanimspawnnum | carnanimspawnnum == 24) {
            if (carnanimalArray.size() > 1) {
                for (int i = 0; i < breedernum; i++) {
                    for (int j = 0; j < litternum; j++) {
                        carnanimalArray.add(new CarnivoreAnimal("nCarnivoreAnimal " + carnanimnum));
                        ConsoleLogger.Log("New Canivore Animal: " + carnanimalArray.get(carnanimalArray.size() - 1).getName() + " with NV of: " + carnanimalArray.get(carnanimalArray.size() - 1).getNutritionValue(), 2);
                        carnanimnum++;
                    }
                }
            }
            carnanimspawnnum = 0;
        }
        ConsoleLogger.Log("carnivoreAnimalSpawner numbers: " + randnum + " " + carnanimspawnnum + "\n" + "Carnivore animal array size is " + carnanimalArray.size(), 1);
    }

    public void carnivoreAnimalFeeder() {
        int eatnum = gen.nextInt(48);
        carnanimeat++;
        int decidernum;
        for (CarnivoreAnimal ca : carnanimalArray) {
            ca.isHungry();
            decidernum = gen.nextInt(3); // This random int will decide if the specific carnovire animal will eat a herbivore animal or another carnivore animal.
            if (decidernum == 0) {
                if (!carnanimalArray.isEmpty()) {
                    randnum = gen.nextInt(carnanimalArray.size());
                    if (eatnum == carnanimeat | carnanimeat >= 48) {
                        if (ca == carnanimalArray.get(randnum)) {
                            ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " can't eat itself!", 1);
                        } else {
                            ca.Eat(carnanimalArray.get(randnum));
                            carnanimeat = 0;
                            ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " has eaten " + carnanimalArray.get(randnum).getName(), 2);
                        }
                    } else {
                        if (!ca.isDead()) {
                            if (ca.isHungry()) {
                                if (ca == carnanimalArray.get(randnum)) {
                                    ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " can't eat itself!", 1);
                                } else {
                                    ca.Eat(carnanimalArray.get(randnum));
                                    ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " has eaten " + carnanimalArray.get(randnum).getName(), 2);
                                }
                            }
                        }
                    }
                } else {
                    if (ca.getHunger() <= 0) {
                        ca.setDead(true);
                    }
                    ConsoleLogger.Log("There are no carnivore animals to eat!", 3);
                }
            } else {
                if (!herbanimalArray.isEmpty()) {
                    randnum = gen.nextInt(herbanimalArray.size());
                    if (eatnum == carnanimeat | carnanimeat >= 48) {
                        ca.Eat(herbanimalArray.get(randnum));
                        carnanimeat = 0;
                        ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " has eaten " + herbanimalArray.get(randnum).getName(), 2);
                    } else {
                        if (!ca.isDead()) {
                            if (ca.isHungry()) {
                                ca.Eat(herbanimalArray.get(randnum));
                                ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " has eaten " + herbanimalArray.get(randnum).getName(), 2);
                            }
                        }
                    }
                } else {
                    if (ca.getHunger() <= 0) {
                        ca.setDead(true);
                    }
                    ConsoleLogger.Log("There are no herbivore animals to eat!", 3);
                }

            }
        }

    }

    public void carnivoreAnimalDeSpawner() {
        for (Iterator<CarnivoreAnimal> i = carnanimalArray.iterator(); i.hasNext();) {
            CarnivoreAnimal ca = i.next();
            if (ca.isDead()) {
                ConsoleLogger.Log(ca.getName() + " has died.", 2);
                i.remove();
            }
        }

    }

    public void herbivoreAnimalSpawner() {
        randnum = gen.nextInt(24);
        herbanimnum++;
        int breedernum = herbanimalArray.size() / Math.max(2, gen.nextInt(8));
        int litternum = Math.max(1, gen.nextInt(3));
        if (randnum == herbanimspawnnum | herbanimspawnnum == 24) {
            if (herbanimalArray.size() > 1) {
                for (int i = 0; i < breedernum; i++) {
                    for (int j = 0; j < litternum; j++) {
                        herbanimalArray.add(new HerbivoreAnimal("nHerbivoreAnimal " + herbanimnum));
                        ConsoleLogger.Log("New Herbivore Animal: " + herbanimalArray.get(herbanimalArray.size() - 1).getName() + " with NV of: " + herbanimalArray.get(herbanimalArray.size() - 1).getNutritionValue(), 2);
                        herbanimnum++;
                    }
                }
            }
            herbanimspawnnum = 0;
        }
        ConsoleLogger.Log("herbivoreAnimalSpawner numbers: " + randnum + " " + herbanimspawnnum + "\n" + "Herbivore animal array size is: " + herbanimalArray.size(), 1);
    }

    public void herbivoreAnimalFeeder() {
        int eatnum = gen.nextInt(48);
        herbanimeat++; // Increments the animal eat variable each time the method is called.
        for (HerbivoreAnimal ha : herbanimalArray) { // For each animal in the array
            ha.isHungry(); // call that animals isHungry method which decrements their hunger variable.
            if (!plantArray.isEmpty()) { // Must check to see if the array is empty, if it is we skip eating.
                randnum = gen.nextInt(plantArray.size()); // Generates a random number to select which plant will be eaten.
                if (eatnum == herbanimeat | herbanimeat >= 48) { // If eatnum and the animal eat variable are equal, or if the animal eat variable is higher than 24
                    ha.Eat(plantArray.get(randnum)); // then the animal eats the random plant
                    herbanimeat = 0; // and the eat variable is reset to 0. 
                    ConsoleLogger.Log("Herbivore Animal " + ha.getName() + " has eaten " + plantArray.get(randnum).getName(), 2);
                } else { // If eatnum and animal eat variable do not match however
                    if (!ha.isDead()) { // and the animal is not already set to dead
                        if (ha.isHungry()) { // and that live animal is hungry
                            ha.Eat(plantArray.get(randnum)); // it will eat the random plant.
                            ConsoleLogger.Log("Herbivore Animal " + ha.getName() + " has eaten " + plantArray.get(randnum).getName(), 2);
                        }
                    }
                }
            } else {
                if (ha.getHunger() <= 0) { // If the animals hunger is 0 or below 0                  
                    ha.setDead(true); // mark it for death.                  
                }
                ConsoleLogger.Log("There are no more plants to eat!", 1);
            }
        }
        ConsoleLogger.Log("herbivoreAnimalFeeder numbers: " + eatnum + " " + herbanimeat, 1);
    }

    public void herbivoreAnimalDeSpawner() {
        for (Iterator<HerbivoreAnimal> i = herbanimalArray.iterator(); i.hasNext();) {
            HerbivoreAnimal ha = i.next();
            if (ha.isDead()) {
                ConsoleLogger.Log(ha.getName() + " has died.", 2);
                i.remove();
            }
        }
    }

    public void plantSpawner() {
        randnum = gen.nextInt(4);
        plantspawnnum++;
        if (plantArray.size() >= 2) {
            if (randnum == plantspawnnum | plantspawnnum >= 4) {
                plantArray.add(new SimPlant("nPlant " + plantnum));
                ConsoleLogger.Log("New plant: " + plantArray.get(plantArray.size() - 1).getName() + " with NV of: " + plantArray.get(plantArray.size() - 1).getNutritionValue(), 2);
                plantspawnnum = 0;
                plantnum++;
            }
        }
        ConsoleLogger.Log("plantSpawner numbers:" + randnum + " " + plantspawnnum + "\n" + "Plant array size is: " + plantArray.size(), 1);
    }

    public void plantDeSpawner() {
        if (!plantArray.isEmpty()) {
            randnum = gen.nextInt(plantArray.size());
            plantdienum++;
            if (randnum == plantdienum | plantdienum == plantArray.size()) {
                ConsoleLogger.Log(plantArray.get(randnum).getName() + " has died.", 2);
                plantArray.remove(randnum);
                plantdienum = 0;
            } else {
                for (Iterator<SimPlant> i = plantArray.iterator(); i.hasNext();) {
                    SimPlant plant = i.next();
                    if (plant.isDead()) {
                        ConsoleLogger.Log(plant.getName() + " has died.", 2);
                        i.remove();
                    }
                }
            }
        } else {
            ConsoleLogger.Log("There are no more plants to despawn!", 1);
        }
        ConsoleLogger.Log("plantDeSpawner numbers: " + randnum + " " + plantdienum, 1);
    }

    public void setTemp(int temp) {
        temperature = temp;
    }

    public int getTemp() {
        return temperature;
    }

    public void setPrecip(int precip) {
        precipitation = precip;
    }

    public int getPrecip() {
        return precipitation;
    }

    @Override
    public void Update() {
        plantSpawner();
        carnivoreAnimalSpawner();
        herbivoreAnimalSpawner();
        herbivoreAnimalFeeder();
        carnivoreAnimalFeeder();
        carnivoreAnimalDeSpawner();
        herbivoreAnimalDeSpawner();
        plantDeSpawner();
    }

}
