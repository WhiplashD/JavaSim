package javasim;

import java.util.Random;

/**
 *
 * @author Whiplash
 */
public class SimPlant {

    private int nutritionValue;
    private boolean isDead;
    private String name;
    Random random = new Random();

    public SimPlant(String name) {
this.name = name;
nutritionValue = random.nextInt(150);
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setNutritionValue(int value) {
        nutritionValue = value;
    }
    
    public int getNutritionValue() {
        return nutritionValue;
    }
    
    public void setDead(boolean state) {
        isDead = state;
    }
    
    public boolean isDead(){
        return isDead;
    }
}
