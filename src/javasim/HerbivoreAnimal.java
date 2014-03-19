package javasim;

import java.util.Collection;
import java.util.Random;

/**
 *
 * @author Whiplash
 */
public class HerbivoreAnimal implements SimAnimal {

    private int maxHunger = 168;
    public int hunger;
    private int minHunger = 0;
    private boolean isDead;
    private String name;
    Random random = new Random();
    private int nutritionValue;
    private int namenum;

    public HerbivoreAnimal(String name) {
        this.name = name;
        nutritionValue = random.nextInt(150);
        hunger = Math.max(50, random.nextInt(maxHunger));
    }

    @Override
    public HerbivoreAnimal Breed() {
        HerbivoreAnimal result = new HerbivoreAnimal("bHerbivoreAnimal" + namenum);
        ConsoleLogger.Log("New Herbivore Animal " + result.getName() + " with NV of " + result.getNutritionValue(), 2);
        namenum++;
        return result;
    }

    @Override
    public void setNutritionValue(int value) {
        nutritionValue = value;
    }

    @Override
    public int getNutritionValue() {
        return nutritionValue;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void Eat(SimPlant plant) {
        hunger = Math.min(maxHunger, hunger + plant.getNutritionValue());
        plant.setDead(true);

    }

    @Override
    public void setDead(boolean setDead) {
        isDead = setDead;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public int getHunger() {
        return hunger;
    }

    @Override
    public void setHunger(int value) {
        hunger = Math.max(minHunger, Math.min(maxHunger, value));
    }

    public void decHunger() {
        hunger--;
    }

    @Override
    public boolean isHungry() {
        return hunger < 25;
    }
}
