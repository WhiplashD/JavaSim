package javasim;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Whiplash
 */
public class SimBiome {

    private int temperature;
    private int precipitation;

    Random gen = new Random();
    private int randnum = gen.nextInt();

    ArrayList<SimPlant> plantArray;
    ArrayList<HerbivoreAnimal> herbanimalArray;

    public void plantSpawner() {

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

}
