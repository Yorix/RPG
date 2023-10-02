public class Trade implements Runnable {
    Unit buyer;
    Seller seller;

    public Trade(Unit buyer, Seller seller) {
        this.seller = seller;
        this.buyer = buyer;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Золота в кошельке: " + buyer.getGold());

            String input;
            do {
                System.out.println("""
                            1. Показать список доступных товаров
                            2. Вернуться в город
                        """);
                input = Utils.input();
                if (input.equals("2")) return;
                else if (!input.equals("1")) Utils.command(input, buyer instanceof Player ? buyer : seller);

            } while (!input.equals("1"));

            seller.showGoods();

            System.out.println("Укажите номер товара, который вы желаете купить. (0. Назад)");

            int index;
            try {
                input = Utils.input();
                index = Integer.parseInt(input);
                Item item = ((Unit) seller).getBag().getItem(index - 1);

                boolean purchased = seller.sell(buyer, item);

                if (purchased) System.out.println("Вы купили " + item);
                else System.out.println("Покупка не удалась (недостаточно золота)");
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                if (input.equals("0")) continue;
                Utils.command(input, buyer instanceof Player ? buyer : seller);
            }
        }
    }
}
