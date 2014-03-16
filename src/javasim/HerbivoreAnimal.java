package javasim;

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

    public HerbivoreAnimal(String name) {
        this.name = name;
        nutritionValue = random.nextInt(80);
        hunger = Math.max(80, random.nextInt(maxHunger));
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

    @Override
    public boolean isHungry() {
        hunger--;
        return hunger < 25;
    }
}
