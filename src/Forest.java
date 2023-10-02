import java.util.Random;

public class Forest {
    public static Monster getMonster(int chancePercents, int playerLevel) {
        return new Random().nextInt(0, 100) <= chancePercents ? new Skeleton(playerLevel) : new Goblin(playerLevel);
    }
}
