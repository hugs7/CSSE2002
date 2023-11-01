public class CoffeeCupTest {

public static void main(String[] args) {
        CoffeeCup empty = new CoffeeCup();

        CoffeeCup shortBlack = new CoffeeCup();
        shortBlack.addEspressoToCup(1); // single espresso shot

        System.out.println("Short black --");
        System.out.println("  Amount of coffee: " + shortBlack.getAmountOfCoffee() + " ml"); // 25 ml
        System.out.println("  Strength of coffee: " + shortBlack.getStrengthOfCoffee() + "%"); // 100% espresso
        System.out.println("  Effective caffeine: " + shortBlack.getEffectiveCaffeine() + " mg"); // 100 mg of coffee in a standard espresso shot


        CoffeeCup americano = new CoffeeCup();
        americano.addEspressoToCup(1); // start with an espresso shot
        americano.addWaterToCup(75); // add 75ml of water

        System.out.println("Americano --");
        System.out.println("  Amount of coffee: " + americano.getAmountOfCoffee() + " ml"); // 25 ml
        System.out.println("  Strength of coffee: " + americano.getStrengthOfCoffee() + "%"); // 100% espresso
        System.out.println("  Effective caffeine: " + americano.getEffectiveCaffeine() + " mg"); // 100 mg of coffee in a standard espresso shot


        CoffeeCup tallFlatWhite = new CoffeeCup();
        tallFlatWhite.addEspressoToCup(2); // two espresso shots
        tallFlatWhite.addMilkToCup(200); // add 200ml milk

        System.out.println("Tall flat white --");
        System.out.println("  Amount of coffee: " + tallFlatWhite.getAmountOfCoffee() + " ml"); // 25 ml
        System.out.println("  Strength of coffee: " + tallFlatWhite.getStrengthOfCoffee() + "%"); // 100% espresso
        System.out.println("  Effective caffeine: " + tallFlatWhite.getEffectiveCaffeine() + " mg"); // 100 mg of coffee in a standard espresso shot


        // I tip my tall flat white and my americano (my short long black) into another cup
        CoffeeCup newTypeOfCoffee = tallFlatWhite.combineInNewCupWith(americano);

        System.out.println("Adding a tall flat white to a short long black");
        System.out.println("  Amount of coffee: " + newTypeOfCoffee.getAmountOfCoffee() + " ml"); // 25 ml
        System.out.println("  Strength of coffee: " + newTypeOfCoffee.getStrengthOfCoffee() + "%"); // 100% espresso
        System.out.println("  Effective caffeine: " + newTypeOfCoffee.getEffectiveCaffeine() + " mg"); // 100 mg of coffee in a standard espresso shot

    }
}
