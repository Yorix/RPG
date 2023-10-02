import java.io.*;
import java.nio.file.Path;
import java.util.Properties;

public class Utils {
    private static final Properties PROPERTIES = new Properties();

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    static {
        try {
            PROPERTIES.load(new FileInputStream(Path.of("app.properties").toFile()));
        } catch (IOException e) {
            PROPERTIES.setProperty("goblin.bag.gold", "50");
            PROPERTIES.setProperty("skeleton.bag.gold", "50");
            PROPERTIES.setProperty("trader.bag.potion_small", "10");
            PROPERTIES.setProperty("trader.bag.potion_medium", "10");
            PROPERTIES.setProperty("trader.bag.potion_big", "10");
        }
    }

    public static Properties getProperties() {
        return PROPERTIES;
    }

    public static String input() {
        String input;
        try {
            input = READER.readLine();
        } catch (IOException e) {
            input = "";
        }
        return input;
    }

    public static void command(String input, Object o) {
        if (o instanceof Player player) {
            switch (input) {
                case "help", "?" -> System.out.println(getCommandList());
                case "player info" -> System.out.println(player);
                case "use item" -> command("", player.getBag());
                default -> System.out.println("Неверное значение. Повторите ввод.");
            }
        } else if (o instanceof Bag bag) {
            while (true) {
                System.out.println("Выберите предмет для использования. Для возврата введите 0. ");
                bag.getItems().forEach(item -> System.out.println("\t" + (bag.getItems().indexOf(item) + 1) + ". " + item));

                input = input();
                try {
                    int index = Integer.parseInt(input) - 1;
                    bag.getOwner().use(bag.getItem(index));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    if (input.equals("0")) break;
                    command(input, bag.getOwner());
                }
            }
        }
    }

    private static String getCommandList() {
        return """
                    help                Вызов этого меню.
                    player info         Показать информацию об игроке.
                    use item            Меню использования предметов инвентаря.
                """;
    }
}
