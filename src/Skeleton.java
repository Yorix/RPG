public class Skeleton extends Monster {

    public Skeleton() {
        super(Skeleton.class);
    }

    public Skeleton(String name) {
        super(name, Skeleton.class);
    }

    public Skeleton(int level) {
        super(level, Skeleton.class);
    }
}
