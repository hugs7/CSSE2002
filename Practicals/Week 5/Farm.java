public class Farm {
    private String[] chickens;
    private String[] cows;
    private String[] sheep;
    private int[] corn;

    public Farm() {
        chickens = new String[]{"Henry", "Bianca", "Jenny"};
        cows = new String[]{"Daisy", "Bertha"};
        sheep = new String[]{"Lincoln", "Shaun", "Billy", "Dolly"};
        corn = new int[]{1,2,3,4};
    }

    public Farm(String[] chickens, String[] cows, String[] sheep, int[] corn) {
        this.chickens = chickens.clone();
        this.cows = cows.clone();
        this.sheep = sheep.clone();
        this.corn = corn.clone();
    }

    public String greetChickens() {
        StringBuilder ret = new StringBuilder();
        for (String name : chickens) {
            ret.append("Hi " + name + "!\n");
        }
        return ret.toString();
    }

    public String patCows() {
        StringBuilder ret = new StringBuilder();
        for (String name : cows) {
            ret.append("Mornin' " + name + "\n");
        }
        return ret.toString();
    }

    public String countSheep() throws NoSheepException {
        if (sheep.length <= 0) {
            throw new NoSheepException();
        }
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < sheep.length; i++) {
            ret.append(sheep[i] + " " + Integer.toString(i + 1) + " sheep\n");
        }
        return ret.toString();
    }

    public int harvest() {
        int yield = 0;
        for (int i = 0; i < corn.length; i++) {
            yield += corn[i];
            corn[i] = 0;
        }
        return yield;
    }
}
