public class Trader extends Unit implements Seller {

    public Trader() {
        super(Trader.class);
    }

    public Trader(String name) {
        super(name, Trader.class);
    }

    @Override
    public void showGoods() {
        getBag().getItems().forEach(item ->
                System.out.printf("\t%d. %s - цена: %d золота\n", getBag().getItems().indexOf(item) + 1, item, item.getPrice()));
    }

    @Override
    public boolean sell(Unit buyer, Item item) {
        try {
            buyer.setGold(buyer.getGold() - item.getPrice());
            this.setGold(this.getGold() + item.getPrice());
        } catch (ValueRangeException e) {
                System.err.println(e.getMessage());
            return false;
        }
        this.getBag().removeItem(item);
        buyer.getBag().addItem(item);
        return true;
    }
}
