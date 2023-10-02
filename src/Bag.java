import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bag {
    private Unit owner;
    private int gold;
    private final List<Item> items;

    Bag(Unit owner) {
        this.owner = owner;
        String prefix = owner.getClass().getName().toLowerCase().concat(".").concat(Bag.class.getName().toLowerCase());

        items = new ArrayList<>();
        this.gold = Integer.parseInt(Utils.getProperties().getProperty(prefix.concat(".gold"), "0"));

        int potionSmallNumber = Integer.parseInt(Utils.getProperties().getProperty(prefix.concat(".potion_small"), "0"));
        int potionMediumNumber = Integer.parseInt(Utils.getProperties().getProperty(prefix.concat(".potion_medium"), "0"));
        int potionBigNumber = Integer.parseInt(Utils.getProperties().getProperty(prefix.concat(".potion_big"), "0"));
        int potionSmallPower = Integer.parseInt(Utils.getProperties().getProperty("potion_small.power", "10"));
        int potionMediumPower = Integer.parseInt(Utils.getProperties().getProperty("potion_medium.power", "50"));
        int potionBigPower = Integer.parseInt(Utils.getProperties().getProperty("potion_big.power", "100"));
        int potionSmallPrice = Integer.parseInt(Utils.getProperties().getProperty("potion_small.price", "10"));
        int potionMediumPrice = Integer.parseInt(Utils.getProperties().getProperty("potion_medium.price", "50"));
        int potionBigPrice = Integer.parseInt(Utils.getProperties().getProperty("potion_big.price", "100"));

        for (int i = 0; i < potionSmallNumber; i++)
            items.add(new Potion("small", potionSmallPower, potionSmallPrice));
        for (int i = 0; i < potionMediumNumber; i++)
            items.add(new Potion("medium", potionMediumPower, potionMediumPrice));
        for (int i = 0; i < potionBigNumber; i++)
            items.add(new Potion("big", potionBigPower, potionBigPrice));
    }

    public Unit getOwner() {
        return owner;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) throws ValueRangeException {
        if (gold < 0)
            throw Utils.getProperties().getProperty("app.logs", "false").equals("true")
                    ? new ValueRangeException("Can't be less than 0.")
                    : new ValueRangeException("");

        this.gold = gold;
    }

    List<Item> getItems() {
        return new ArrayList<>(items);
    }

    Item getItem(int i) {
        return items.get(i);
    }

    void addItem(Item item) {
        items.add(item);
    }

    void addItems(Collection<Item> collection) {
        items.addAll(collection);
    }

    boolean removeItem(Item item) {
        return items.remove(item);
    }

    @Override
    public String toString() {
        return items.isEmpty() ? "сумка пуста" : items.toString();
    }
}
