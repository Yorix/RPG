public abstract class Monster extends Unit implements Fighter {

    public Monster(Class<? extends Monster> cl) {
        super(cl);
    }

    public Monster(String name, Class<? extends Monster> cl) {
        super(name, cl);
    }

    public Monster(int level, Class<? extends Monster> cl) {
        super(level, cl);
    }
}
