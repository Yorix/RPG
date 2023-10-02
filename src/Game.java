import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Player player;
    private final List<Trader> traders;

    public Game() {
        System.out.println("Введите своё имя...");
        String name = Utils.input();
        player = name.isBlank() ? new Player() : new Player(name);

        traders = new ArrayList<>();
        Trader trader = new Trader();
        traders.add(trader);
    }

    public void start() {
        System.out.println("Привет, " + player.getName() + "!\n");
        System.out.println(player);
        System.out.println("Получить помощь по командам - введите ? или --help");

        toVillage();
    }

    private void toVillage() {
        String input;

        while (true) {
            System.out.println("""
                    Куда вы хотите пойти?
                        1. К торговцу
                        2. В тёмный лес
                        3. На выход
                    """);

            input = Utils.input();
            switch (input) {
                case "1" -> toTrader(traders.get(0));
                case "2" -> toForest();
                case "3" -> System.exit(0);
                default -> Utils.command(input, player);
            }
        }
    }

    private void toTrader(Trader trader) {
        System.out.println(trader.getName() + ": Добро пожаловать в лавку!");

        Thread trade = new Thread(new Trade(player, trader));
        trade.start();

        try {
            trade.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void toForest() {
        while (true) {
            Monster monster = Forest.getMonster(50, player.getLevel());
            System.out.print("Вы вошли в лес и встретили ");

            String input;
            do {
                System.out.println(monster + """
                        Желаете вступить в бой?
                            1. Сражаться
                            2. В город
                        """);

                input = Utils.input();
                if (input.equals("2")) return;
                else if (!input.equals("1")) Utils.command(input, player);

            } while (!input.equals("1"));

            Thread battle = new Thread(new Battle(player, monster));
            battle.start();
            try {
                battle.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
