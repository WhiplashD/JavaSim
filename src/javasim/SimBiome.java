package javasim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Whiplash
 */
public class SimBiome implements Updateable {

    private int temperature;
    private int precipitation;

    Random gen = new Random();
    private int randnum;
    private int plantspawnnum;
    private int herbanimspawnnum;
    private int plantdienum;
    private int plantnum;
    private int herbanimnum;
    private int herbanimeat;

    ArrayList<SimPlant> plantArray = new ArrayList<>();
    ArrayList<HerbivoreAnimal> herbanimalArray = new ArrayList<>();

    public void initializeBiome() {
        int p = Math.max(500, gen.nextInt(2000));
        for (int i = 0; i < p; i++) {
            plantArray.add(new SimPlant("sPlant " + i));
        }
        ConsoleLogger.Log(p + " plants have been initalized", 2);
        int h = Math.max(10, gen.nextInt(200));
        for (int i = 0; i < h; i++) {
            herbanimalArray.add(new HerbivoreAnimal("sHerbivoreAnimal " + i));
        }
        ConsoleLogger.Log(h + " herbivore animals have been initalized", 2);
    }

    public void herbivoreAnimalSpawner() {
        randnum = gen.nextInt(24);
        herbanimspawnnum++;
        if (randnum == herbanimspawnnum | herbanimspawnnum >= 24) {
            herbanimalArray.add(new HerbivoreAnimal("nHerbivoreAnimal " + herbanimnum));
            ConsoleLogger.Log("New Herbivore Animal: " + herbanimalArray.get(herbanimalArray.size() - 1).getName() + " with NV of: " + herbanimalArray.get(herbanimalArray.size() - 1).getNutritionValue(), 2);
            herbanimspawnnum = 0;
            herbanimnum++;
        }
        ConsoleLogger.Log("herbivoreAnimalSpawner numbers: " + randnum + " " + herbanimspawnnum + "\n" + "Herbivore animal array size is: " + herbanimalArray.size(), 1);
    }

    public void herbivoreAnimalFeeder() {
        int eatnum = gen.nextInt(24);
        herbanimeat++;
        for (Iterator<HerbivoreAnimal> i = herbanimalArray.iterator(); i.hasNext();) {
            HerbivoreAnimal ha = i.next();
            ha.isHungry();
            if (!plantArray.isEmpty()) {
                randnum = gen.nextInt(plantArray.size());
                if (eatnum == herbanimeat | herbanimeat >= 24) {
                    ha.Eat(plantArray.get(randnum));
                    herbanimeat = 0;
                } else {
                    if (!ha.isDead()) {
                        if (ha.isHungry()) {

                            ha.Eat(plantArray.get(randnum));
                            ConsoleLogger.Log("Herbivore Animal " + ha.getName() + " has eaten " + plantArray.get(randnum).getName(), 2);
                        }
                    }
                }
            } else {
                ha.setDead(true);
                ConsoleLogger.Log("There are no more plants to eat!", 3);
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
        randnum = gen.nextInt(12);
        plantspawnnum++;
        if (randnum == plantspawnnum | plantspawnnum >= 12) {
            plantArray.add(new SimPlant("nPlant " + plantnum));
            ConsoleLogger.Log("New plant: " + plantArray.get(plantArray.size() - 1).getName() + " with NV of: " + plantArray.get(plantArray.size() - 1).getNutritionValue(), 2);
            plantspawnnum = 0;
            plantnum++;
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
            ConsoleLogger.Log("There are no more plants to despawn!", 3);
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
        herbivoreAnimalSpawner();
        herbivoreAnimalFeeder();
        herbivoreAnimalDeSpawner();
        plantDeSpawner();
    }

}
