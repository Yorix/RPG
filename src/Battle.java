import java.util.List;
import java.util.stream.Collectors;

public class Battle implements Runnable {
    private final Unit fighter1;
    private final Unit fighter2;

    public Battle(Fighter fighter1, Fighter fighter2) {
        this.fighter1 = (Unit) fighter1;
        this.fighter2 = (Unit) fighter2;
    }

    @Override
    public void run() {
        System.out.println("Fight!");

        Unit attacker = fighter1;
        Unit attacked = fighter2;

        while (fighter1.getHealth() > 0 && fighter2.getHealth() > 0) {
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                return;
            }

            int hit = ((Fighter) attacker).attack();

            if (hit > attacker.getStrength()) System.out.println("Критический удар!");

            attacked.setHealth(attacked.getHealth() - hit);

            System.out.println(hit != 0
                    ? attacker.getName() + " бьёт " + attacked.getName() + " с силой " + hit
                    : attacker.getName() + " промахивается");
            System.out.printf("%s : %d hp | %s : %d hp\n\n",
                    fighter1.getName(), fighter1.getHealth(), fighter2.getName(), fighter2.getHealth());

            Unit buffer = attacker;
            attacker = attacked;
            attacked = buffer;
        }

        System.out.println(attacked.getName() + " победил!");

        result(attacked, attacker);

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void result(Unit winner, Unit loser) {
        if (winner instanceof Player) {
            int gold = loser.getGold();
            Bag bag = loser.getBag();
            int experience = loser.getLevel() * 10 + 100;

            try {
                winner.setGold(winner.getGold() + gold);
            } catch (ValueRangeException e) {
                System.out.println(e.getMessage());
            }

            List<Item> droppedItems = bag.getItems().stream()
                    .skip((long) (Math.random() * bag.getItems().size()))
                    .limit((long) (Math.random() * bag.getItems().size()))
                    .collect(Collectors.toList());

            winner.getBag().addItems(droppedItems);

            int oldLevel = winner.getLevel();
            winner.setExperience(winner.getExperience() + experience);

            String droppedItemsString = droppedItems.isEmpty()
                    ? "" : ", " + droppedItems.toString().substring(1, droppedItems.toString().length() - 1);
            System.out.printf("%s получает %d золота%s и %d опыта\n\n", winner.getName(), gold, droppedItemsString, experience);
            if (winner.getLevel() > oldLevel)
                System.out.println("Поздравляем! Вы повысили уровень!\n" + winner);
        } else {
            System.out.println("Вы погибли, игра окончена.");
            System.exit(0);
        }
    }
}
