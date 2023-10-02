public class Player extends Unit implements Fighter, Seller {

    public Player() {
        super(Player.class);
    }

    public Player(String name) {
        super(name, Player.class);
    }

    @Override
    public boolean sell(Unit buyer, Item item) {
        return false;
    }

    @Override
    public void showGoods() {
        System.out.println("Предметы для продажи отсутствуют.");
    }
}
