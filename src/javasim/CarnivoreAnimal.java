/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasim;

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

    public CarnivoreAnimal(String name) {
        this.name = name;
        nutritionValue = random.nextInt(80);
        hunger = Math.max(40, random.nextInt(maxHunger));
    }

    public void Eat(SimAnimal animal) {
        hunger = Math.min(maxHunger, hunger + animal.getNutritionValue());
        animal.setDead(true);

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
        hunger = value;
    }

    @Override
    public int getHunger() {
        return hunger;
    }

    @Override
    public boolean isHungry() {
        hunger--;
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
