public class Cats {
    private String name;
    private int appetite;
    private boolean satiety = false;

    public Cats( String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plates plate) {
        if ( plate.isFoodEnough(appetite) ) {
            System.out.println("Cat " + name + " eat " + appetite + " units of food!");
            plate.decreaseFood(this, appetite);
            this.satiety = true;
        } else System.out.println("Not enough food units in a plate!( Cat " + name + " still hungry.");
    }

    public void info() {
        if ( satiety == true ) {
            System.out.println("Cat " + name + " is full and happy!");
        } else System.out.println("Cat " + name + " is hungry.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public boolean getSatiety() {
        return satiety;
    }

    public void setSatiety(boolean satiety) {
        this.satiety = satiety;
    }
}