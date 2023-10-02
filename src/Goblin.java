public class Goblin extends Monster {

    public Goblin() {
        super(Goblin.class);
    }

    public Goblin(String name) {
        super(name, Goblin.class);
    }

    public Goblin(int level) {
        super(level, Goblin.class);
    }
}
