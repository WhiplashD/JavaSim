/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javasim;

/**
 *
 * @author Whiplash
 */
public class HerbivoreAnimal {
    private int maxHunger = 100;
    public int hunger = 100;
    private int minHunger = 0;
    private boolean isDead;

    public HerbivoreAnimal() {

    }

        public void Eat(SimPlant plant) {
         hunger = Math.min(maxHunger, hunger + plant.getNutritionValue());
plant.setDead(true);
    }
        
//        public void Sleep() {
//            try {
//                Thread.sleep(6000);
//            } catch (InterruptedException ex) {
//        }
//        }
        
        public HerbivoreAnimal Breed(HerbivoreAnimal animal) {
return new HerbivoreAnimal();
        }
    
        public void setDead(boolean setDead) {
            isDead = setDead;
        }
    
        public boolean getDead() {
            return isDead;
        }
        
        public int getHunger() {
            return hunger;
        }
        
        public void setHunger(int value) {
            hunger = Math.max(minHunger, Math.min(maxHunger, value));
        }
        
        public boolean isHungry() {
            return hunger < 25;
        }
}
