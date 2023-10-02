import java.util.Random;

public interface Fighter {

    default int attack() {
        int power = ((Unit) this).getStrength();
        Random rnd = new Random();
        if (rnd.nextInt(0, 100) > 70) power <<= 1;
        return (((Unit) this).getDexterity() * 3 > rnd.nextInt(0, 100)) ? power : 0;
    }
}
