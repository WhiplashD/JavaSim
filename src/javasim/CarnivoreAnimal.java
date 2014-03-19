/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasim;

import java.util.Collection;
import java.util.Random;

/**
 *
 * @author Whiplash
 */
public class CarnivoreAnimal implements SimAnimal {

    private int maxHunger = 168;
    public int hunger;
    private int minHunger = 0;
    private boolean isDead;
    private String name;
    Random random = new Random();
    private int nutritionValue;
    private int namenum;

    public CarnivoreAnimal(String name) {
        this.name = name;
        nutritionValue = random.nextInt(40);
        hunger = Math.max(50, random.nextInt(maxHunger));
    }

    public void Eat(SimAnimal animal) {
        hunger = Math.min(maxHunger, hunger + animal.getNutritionValue());
        animal.setDead(true);

    }

    @Override
    public CarnivoreAnimal Breed() {
        CarnivoreAnimal result = new CarnivoreAnimal("bCarnivoreAnimal" + namenum);
        ConsoleLogger.Log("New Carnivore Animal " + result.getName() + " with NV of " + result.getNutritionValue(), 2);
        namenum++;
        return result;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setDead(boolean state) {
        isDead = state;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void setHunger(int value) {
        hunger = Math.max(minHunger, Math.min(maxHunger, value));
    }

    @Override
    public int getHunger() {
        return hunger;
    }

    public void decHunger() {
        hunger--;
    }

    @Override
    public boolean isHungry() {
        return hunger < 25;
    }

    @Override
    public void setNutritionValue(int value) {
        nutritionValue = value;
    }

    @Override
    public int getNutritionValue() {
        return nutritionValue;
    }

}
