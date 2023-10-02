public class Potion extends Item {
    private final int power;

    public Potion(String name, int power, int price) {
        this.setName(name);
        this.power = power;
        this.setPrice(price);
    }

    @Override
    public boolean use(Unit user) {
        if (user.getBag().removeItem(this)) {
            int recovered = user.setHealth(user.getHealth() + this.power);
            System.out.println(user.getName() + " восстанавливает " + recovered + " здоровья. " + this + " исчезло.");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Зелье лечения (" + power + " восст.)";
    }

}
