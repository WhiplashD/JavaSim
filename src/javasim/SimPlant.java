package javasim;

/**
 *
 * @author Whiplash
 */
public class SimPlant {

    private int nutritionValue;
    private boolean isDead;

    public SimPlant() {

    }
    
    public void setNutritionValue(int value) {
        nutritionValue = value;
    }
    
    public int getNutritionValue() {
        return nutritionValue;
    }
    
    public void setDead(boolean setDead) {
        isDead = setDead;
    }
    
    public boolean isDead(){
        return isDead;
    }
}
