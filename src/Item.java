public abstract class Item {
    private String name;
    private int Weight;
    private double value;

    public Item(String name, int weight, double value) {
        this.name = name;
        Weight = weight;
        this.value = value;
    }


    public static Item randomItem(){
        int number =(int) Math.floor(Math.random()*(12)+1);
        switch(number){
            case 1:
                return new Sword("Basic Sword", 1, 1);
            case 2:
                return new Sword("Long Sword", 2, 1.1);
            case 3:
                return new Sword("Golden Sword", 3, 1.2);
            case 4:
                return new Sword("Basic Wand", 1, 1);
            case 5:
                return new Sword("Golden Wand", 3, 1.1);
            case 6:
                return new Sword("Magic Wand", 2, 1.2);
            case 7:
                return new Sword("Basic Shield", 1, 1.1);
            case 8:
                return new Sword("Heavy Shield", 2, 1.2);
            case 9:
                return new Sword("Golden Shield", 3, 1.3);
            case 10:
                return new Clothing("Basic Armor",3,0.1);
            case 11:
                return new Clothing("Light Armor",4,0.3);
            case 12:
                return new Clothing("Heavy Armor",5,0.5);

        }
        return null;
    }



    public void displayItem(){
        System.out.printf("Name: %s -- Value: %f -- Weight: %d%n",this.getName(),this.getValue(),this.getWeight());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
