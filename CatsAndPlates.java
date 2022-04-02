public class CatsAndPlates {
    public static void main(String[] args) {

        Plates plate1 = new Plates(100);

        Cats[] cats = new Cats[] {
                new Cats("Matreshka", 33),
                new Cats("Vorchun", 33),
                new Cats("Bandit", 33)
        };

        for (Cats cat : cats) {
            cat.info();
            cat.eat(plate1);
            cat.info();
            System.out.println("-----------------------");
            plate1.info();
            System.out.println("-----------------------");
        }

        plate1.addFood(99);
        plate1.info();

    }
}
