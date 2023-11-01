public class CoffeeCup {

    // state of the class as variables
    public double amountOfCoffee; // in millilitres
    public double strengthOfCoffee; // percentage espresso, 0 is water, 100 is pure espresso
    
    // constructor of the class
    public CoffeeCup() {
        amountOfCoffee = 0.0;
        strengthOfCoffee = 0.0;
    }
    
    // accessors - just return class variables

    public double getAmountOfCoffee() {
        return amountOfCoffee;
    }

    public double getStrengthOfCoffee() {
        return strengthOfCoffee;
    }

    // a method that calculates amount of caffeine

    public double getEffectiveCaffeine() {
        // according to
        // https://www.home-barista.com/knockbox/what-is-proper-volume-of-single-shot-of-espresso-t6036.html
        // We can estimate as 100mg of caffeine per 25ml of espresso, so ...
        return amountOfCoffee / 25.0 * strengthOfCoffee; // we divide strength by 100, then times by 100 for 100mg, so cancels out
    }

    public void addToCup(CoffeeCup coffee) {
        double newAmount = amountOfCoffee + coffee.amountOfCoffee;
        double newStrength = (amountOfCoffee * strengthOfCoffee + coffee.amountOfCoffee * coffee.strengthOfCoffee) / newAmount;
        amountOfCoffee = newAmount;
        strengthOfCoffee = newStrength;
    }

    public CoffeeCup combineInNewCupWith(CoffeeCup coffee) {
        CoffeeCup newCup = new CoffeeCup();
        newCup.amountOfCoffee = amountOfCoffee + coffee.amountOfCoffee;
        newCup.strengthOfCoffee = (amountOfCoffee * strengthOfCoffee + coffee.amountOfCoffee * coffee.strengthOfCoffee) / newCup.amountOfCoffee;
        return newCup;
    }

    public void addEspressoToCup(int amountOfShots) {
        CoffeeCup coffee = new CoffeeCup();
        coffee.amountOfCoffee = 25 * amountOfShots; // we add 1 25 ml
        coffee.strengthOfCoffee = 100; // pure espresso
        addToCup(coffee);
    }

    public void addWaterToCup(double amountOfWater) { // amount of water in millilitres
        CoffeeCup water = new CoffeeCup();
        water.amountOfCoffee = amountOfWater;
        water.strengthOfCoffee = 0; // water is just 0 strength coffee
        addToCup(water);
    }

    public void addMilkToCup(double amountOfMilk) { // amount of milk in millilitres
        CoffeeCup milk = new CoffeeCup();
        milk.amountOfCoffee = amountOfMilk;
        milk.strengthOfCoffee = 0; // water is also just 0 strength coffee
        addToCup(milk);
    }
}
