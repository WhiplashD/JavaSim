package javasim;

import java.util.Collection;

/**
 *
 * @author Whiplash
 */
public interface SimAnimal {

//    void Eat();

    SimAnimal Breed();
    
    void setName(String name);
    
    String getName();

    void setDead(boolean state);

    boolean isDead();

    void setHunger(int value);

    int getHunger();
    
    boolean isHungry();

    void setNutritionValue(int value);
    
    int getNutritionValue();
}
