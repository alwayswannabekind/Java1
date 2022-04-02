public class Plates {
    private int foodUnits; // Количество еды

    public Plates (int foodUnits){
        this.foodUnits = foodUnits;
    }

    public void info() {
        System.out.println("Food units left in a plate: " + foodUnits );
    }

    public void decreaseFood(Cats cat, int foodAmountToDecrease) {
        this.foodUnits -= foodAmountToDecrease;
    }

    public void addFood(int foodUnitsToAdd) {
        this.foodUnits += foodUnitsToAdd;
        System.out.println();
        System.out.println("-----------------------");
        System.out.println("Food units are increased by " + foodUnitsToAdd);
        System.out.println("-----------------------");
        System.out.println();
    }

    public boolean isFoodEnough(int foodCount) {
        return this.foodUnits >= foodCount;
    }
}
