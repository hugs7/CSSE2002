public class CoffeeCup {
    
    // state of the class as variables
    public double amountOfCoffee; // in millilitres
    public double amountOfMilk; // in millilitres
    public double amountOfWater; // in millilitres
    
    // constructor of the class
    public CoffeeCup() {
        amountOfCoffee = 0.0;
        amountOfMilk = 0.0;
        amountOfWater = 0.0;
    }
    
    // accessors - just return class variables

    public double getAmountOfCoffee() {
        return amountOfCoffee + amountOfMilk + amountOfWater;
    }

    public double getStrengthOfCoffee() {
        return amountOfCoffee / (amountOfCoffee + amountOfMilk + amountOfWater) * 100.0;
    }
    
    // a method that calculates amount of caffeine

    public double getEffectiveCaffeine() {
        // according to
        // https://www.home-barista.com/knockbox/what-is-proper-volume-of-single-shot-of-espresso-t6036.html
        // We can estimate as 100mg of caffeine per 25ml of espresso, so ...
        return amountOfCoffee / 25 * 100.0; // just amount of coffee on 25 ml
    }

    public void addToCup(CoffeeCup coffee) {
        amountOfCoffee += coffee.amountOfCoffee;
        amountOfMilk += coffee.amountOfMilk;
        amountOfWater += coffee.amountOfWater;
    }

    public CoffeeCup combineInNewCupWith(CoffeeCup coffee) {
        CoffeeCup newCup = new CoffeeCup();
        newCup.amountOfCoffee = amountOfCoffee + coffee.amountOfCoffee;
        newCup.amountOfMilk = amountOfMilk + coffee.amountOfMilk;
        newCup.amountOfWater = amountOfWater + coffee.amountOfWater;
        return newCup;
    }

    public void addEspressoToCup(int amountOfShots) {
        CoffeeCup coffee = new CoffeeCup();
        coffee.amountOfCoffee = amountOfShots * 25; // we add 25 ml shots
        coffee.amountOfMilk = 0.0;
        coffee.amountOfWater = 0.0;
        addToCup(coffee);
    }

    public void addWaterToCup(double amountOfWater) { // amount of water in millilitres
        CoffeeCup water = new CoffeeCup();
        water.amountOfCoffee = 0.0;
        water.amountOfWater = amountOfWater;
        water.amountOfMilk = amountOfMilk;
        addToCup(water);
    }

    public void addMilkToCup(double amountOfMilk) { // amount of milk in millilitres
        CoffeeCup milk = new CoffeeCup();
        milk.amountOfMilk = amountOfMilk;
        milk.amountOfCoffee = 0.0;
        milk.amountOfWater = 0.0;
        addToCup(milk);
    }
}
