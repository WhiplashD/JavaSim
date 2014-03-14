package javasim;

/**
 *
 * @author Whiplash
 */
public interface SimAnimal {

//    void Eat();

//    Object Breed(Object animal);
    
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
