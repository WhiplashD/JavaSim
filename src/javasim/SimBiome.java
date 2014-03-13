package javasim;

import java.util.ArrayList;
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
    private int plantnum;

    ArrayList<SimPlant> plantArray = new ArrayList<>();
    ArrayList<HerbivoreAnimal> herbanimalArray;

    public void plantSpawner() {
        randnum = gen.nextInt(24);
        spawnnum++;
        if (randnum == spawnnum | spawnnum == 24 ) {
            plantArray.add(new SimPlant("Plant" + plantnum));
            ConsoleLogger.Log("New plant: " + plantArray.get(plantArray.size() - 1), 1);
            spawnnum = 0;
            plantnum++;
        }

        ConsoleLogger.Log("Plant spawner numbers:" + randnum + " " + spawnnum, 1);
        ConsoleLogger.Log("Plant array size is: " + plantArray.size(), 1);
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
    }

}
