public abstract class Unit {
    private String name;
    private int maxHealth;
    private int health;
    private int strength;
    private int dexterity;
    private int experience;
    private int level;
    private final Bag bag;

    public Unit(Class<? extends Unit> cl) {
        String unitClass = cl.getName().toLowerCase();
        this.name = Utils.getProperties().getProperty(unitClass + ".name", unitClass);
        this.maxHealth = Integer.parseInt(Utils.getProperties().getProperty(unitClass + ".max_health", "100"));
        this.health = Integer.parseInt(Utils.getProperties().getProperty(unitClass + ".health", "100"));
        this.strength = Integer.parseInt(Utils.getProperties().getProperty(unitClass + ".strength", "20"));
        this.dexterity = Integer.parseInt(Utils.getProperties().getProperty(unitClass + ".dexterity", "20"));
        this.experience = Integer.parseInt(Utils.getProperties().getProperty(unitClass + ".experience", "0"));
        this.level = Integer.parseInt(Utils.getProperties().getProperty(unitClass + ".level", "1"));
        this.bag = new Bag(this);
    }

    public Unit(String name, Class<? extends Unit> cl) {
        this(cl);
        this.name = name;
    }

    public Unit(int level, Class<? extends Unit> cl) {
        this(cl);
        setLevel(level);
    }

    public boolean use(Item item) {
        return bag.getItems().contains(item) && item.use(this);
    }

    public int setHealth(int health) {
        if (health > maxHealth) health = maxHealth;
        int delta = health - this.health;
        this.health = Math.max(health, 0);
        return delta;
    }

    public void setExperience(int experience) {
        this.experience = experience;
        int ratio = (int) Math.pow(level, 2) * 10 + 100;
        if (experience >= ratio)
            setLevel(experience / ratio + 1);
    }

    public void setLevel(int level) {
        int delta = level - this.level;
        if (delta <= 0) return;

        this.maxHealth = this.maxHealth + delta * 10;
        this.health = this.maxHealth;
        this.strength = this.strength + delta * 2;
        this.dexterity = this.dexterity + delta * 2;
        this.level = level;
    }

    public void setGold(int gold) throws ValueRangeException {
        this.bag.setGold(gold);
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public Bag getBag() {
        return bag;
    }

    public int getGold() {
        return bag.getGold();
    }

    @Override
    public String toString() {
        return String.format("%s, имя: %s, уровень: %d, здоровье: %d, сила: %d, ловкость: %d, золото: %d, предметы: %s\n",
                getClass().getName(),
                name,
                level,
                health,
                strength,
                dexterity,
                getGold(),
                bag);
    }
}
