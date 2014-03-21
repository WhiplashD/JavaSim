package javasim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
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
            plantArray.add(new SimPlant("iPlant " + i));
        }
        int h = Math.max(150, gen.nextInt(400));
        for (int i = 0; i < h; i++) {
            herbanimalArray.add(new HerbivoreAnimal("iHerbivoreAnimal " + i));
        }
        int c = Math.max(50, gen.nextInt(100));
        for (int i = 0; i < c; i++) {
            carnanimalArray.add(new CarnivoreAnimal("iCarnivoreAnimal " + i));
        }
        ConsoleLogger.Log(p + " plants have been initialized\n" + h + " herbivore animals have been initialized\n" + c + " carnivore animals have been initialized", 2);
    }

    public void carnivoreAnimalBreeder() {
        for (ListIterator<CarnivoreAnimal> i = carnanimalArray.listIterator(); i.hasNext();) {
            CarnivoreAnimal ca = i.next();
            carnanimnum++;
            randnum = gen.nextInt(240);
            if (randnum == carnanimnum | carnanimnum == 240) {
                if (!ca.isHungry()) {
                    i.add(ca.Breed());
                    carnanimnum = 0;
                }
            }
        }
    }

    public void carnivoreAnimalFeeder() {
        int eatnum = gen.nextInt(100);
        carnanimeat++;
        int decidernum;
        for (CarnivoreAnimal ca : carnanimalArray) {
            ca.decHunger();
            decidernum = gen.nextInt(4); // This random int will decide if the specific carnovire animal will eat a herbivore animal or another carnivore animal.
            if (ca.isHungry()) { // If hungry
                if (!ca.isDead()) { // Not dead
                    if (ca.getHunger() <= 0) { // Hunger is greater than 0
                        ca.setDead(true);
                    } else if (decidernum == 0) { // If decider num has decided carnivore selection
                        if (carnanimalArray.size() > 2) { // And carnivore array size is larger than 2
                            randnum = gen.nextInt(carnanimalArray.size());
                            if (ca == carnanimalArray.get(randnum)) { // And the carnivore is not trying to eat itself
                                ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " can't eat itself!", 1);
                            } else { // Eat another carnivore animal.
                                ca.Eat(carnanimalArray.get(randnum));
                                ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " has eaten " + carnanimalArray.get(randnum).getName(), 2);
                            }
                        } else if (!herbanimalArray.isEmpty()) { // If carnivore array size is 2 or less it will eat a herbivore animal instead.
                            randnum = gen.nextInt(herbanimalArray.size());
                            ca.Eat(herbanimalArray.get(randnum));
                            ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " has eaten " + herbanimalArray.get(randnum).getName(), 2);
                        }
                    } else if (!herbanimalArray.isEmpty()) { // If decidernum is anything but 0 it will eat a herbivore animal instead.
                        randnum = gen.nextInt(herbanimalArray.size());
                        ca.Eat(herbanimalArray.get(randnum));
                        ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " has eaten " + herbanimalArray.get(randnum).getName(), 2);
                    }
                } else {

                }
            } else if (eatnum == carnanimeat | carnanimeat == 100) { // Chance to repeat the process even if the animal is not hungry.
                if (!ca.isDead()) {
                    if (ca.getHunger() <= 0) {
                        ca.setDead(true);
                    } else if (decidernum == 0) {
                        if (carnanimalArray.size() > 2) {
                            randnum = gen.nextInt(carnanimalArray.size());
                            if (ca == carnanimalArray.get(randnum)) {
                                ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " can't eat itself!", 1);
                            } else { // Eat another carnivore animal.
                                ca.Eat(carnanimalArray.get(randnum));
                                ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " has eaten " + carnanimalArray.get(randnum).getName(), 2);
                                carnanimeat = 0;
                            }
                        } else if (!herbanimalArray.isEmpty()) {
                            randnum = gen.nextInt(herbanimalArray.size());
                            ca.Eat(herbanimalArray.get(randnum));
                            ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " has eaten " + herbanimalArray.get(randnum).getName(), 2);
                            carnanimeat = 0;
                        }
                    } else if (!herbanimalArray.isEmpty()) {
                        randnum = gen.nextInt(herbanimalArray.size());
                        ca.Eat(herbanimalArray.get(randnum));
                        ConsoleLogger.Log("Carnivore Animal " + ca.getName() + " has eaten " + herbanimalArray.get(randnum).getName(), 2);
                        carnanimeat = 0;
                    }
                } else {

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

    public void herbivoreAnimalBreeder() {
        for (ListIterator<HerbivoreAnimal> i = herbanimalArray.listIterator(); i.hasNext();) {
            HerbivoreAnimal ha = i.next();
            herbanimnum++;
            randnum = gen.nextInt(240);
            if (randnum == herbanimnum | herbanimnum == 240) {
                if (!ha.isHungry()) {
                    i.add(ha.Breed());
                    herbanimnum = 0;
                }
            }
        }
    }

    public void herbivoreAnimalFeeder() {
        int eatnum = gen.nextInt(100);
        herbanimeat++; // Increments the animal eat variable each time the method is called.
        for (HerbivoreAnimal ha : herbanimalArray) { // For each animal in the array
            ha.decHunger(); // call that animals decHunger method which decrements their hunger variable.
            if (!plantArray.isEmpty()) {
                randnum = gen.nextInt(plantArray.size());
                if (ha.isHungry()) {
                    if (!ha.isDead()) {
                        if (ha.getHunger() <= 0) {
                            ha.setDead(true);
                        } else {
                            ha.Eat(plantArray.get(randnum));
                            ConsoleLogger.Log("Herbivore Animal " + ha.getName() + " has eaten " + plantArray.get(randnum).getName(), 1);
                        }
                    }
                } else if (eatnum == herbanimeat | herbanimeat == 100) {
                    if (ha.isHungry()) {
                        if (!ha.isDead()) {
                            if (ha.getHunger() <= 0) {
                                ha.setDead(true);
                            } else {
                                ha.Eat(plantArray.get(randnum));
                                ConsoleLogger.Log("Herbivore Animal " + ha.getName() + " has eaten " + plantArray.get(randnum).getName(), 1);
                            }
                        }
                    }
                }
            }
        }
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
        if (!plantArray.isEmpty()) {
            if (randnum == plantspawnnum | plantspawnnum >= 4) {
                for (int i = 0; i < gen.nextInt(500); i++) {
                    plantArray.add(new SimPlant("nPlant " + plantnum));
                    ConsoleLogger.Log("New plant: " + plantArray.get(plantArray.size() - 1).getName() + " with NV of: " + plantArray.get(plantArray.size() - 1).getNutritionValue(), 1);
                    plantspawnnum = 0;
                    plantnum++;
                }
            }
        }
        ConsoleLogger.Log("plantSpawner numbers:" + randnum + " " + plantspawnnum + "\n" + "Plant array size is: " + plantArray.size(), 1);
    }

    public void plantDeSpawner() {
        if (!plantArray.isEmpty()) {
            for (Iterator<SimPlant> i = plantArray.iterator(); i.hasNext();) {
                SimPlant plant = i.next();
                if (plant.isDead()) {
                    ConsoleLogger.Log(plant.getName() + " has died.", 1);
                    i.remove();
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
        carnivoreAnimalBreeder();
        herbivoreAnimalBreeder();
        herbivoreAnimalFeeder();
        carnivoreAnimalFeeder();
        carnivoreAnimalDeSpawner();
        herbivoreAnimalDeSpawner();
        plantDeSpawner();
    }

}
