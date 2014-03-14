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
    private int spawnnum;
    private int dienum;
    private int plantnum;

    ArrayList<SimPlant> plantArray = new ArrayList<>();
    ArrayList<HerbivoreAnimal> herbanimalArray = new ArrayList<>();
    
    public void startingPlants() {
        for (int i = 0; i < Math.min(100, gen.nextInt(1000)); i++) {
           plantArray.add(new SimPlant("sPlant " + i));
        }
    }

    public void plantSpawner() {
        randnum = gen.nextInt(24);
        spawnnum++;
        if (randnum == spawnnum | spawnnum >= 24) {
            plantArray.add(new SimPlant("nPlant " + plantnum));
            ConsoleLogger.Log("New plant: " + plantArray.get(plantArray.size() - 1).getName(), 1);
            spawnnum = 0;
            plantnum++;
        }

        ConsoleLogger.Log("Plant spawner numbers:" + randnum + " " + spawnnum, 1);
        ConsoleLogger.Log("Plant array size is: " + plantArray.size(), 1);
    }

    public void plantDeSpawner() {
        randnum = gen.nextInt(plantArray.size());
        dienum++;
        if (randnum == dienum | dienum == plantArray.size()) {
            ConsoleLogger.Log(plantArray.get(randnum).getName() + " has died.", 2);
            plantArray.remove(randnum);
            dienum = 0;
        }
        else {
        for (Iterator<SimPlant> i = plantArray.iterator(); i.hasNext();) {
            SimPlant plant = i.next();
            if (plant.isDead()) {
ConsoleLogger.Log(plant.getName() + " has died.", 2);
i.remove();
            }
        }
    }
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
        plantDeSpawner();
    }

}
