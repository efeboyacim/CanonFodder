import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class SpecialCharacter extends Character implements ISpecialCharacterInterface{
    private ArrayList<Item> inventory;
    private boolean isWearingSingleArmor = true;
    private boolean isWieldingSingleWeapon = true;
    private boolean isSwordsSpecialAbilityUsed = false;
    public static int deathNumber=0;
    private boolean usedAttackOrSpecial = false;

    public SpecialCharacter() {
        inventory = new ArrayList<Item>();
        setArmor(new Clothing("Basic Armor",3,0.1));
        inventory.add(this.getArmor());
        Main.specialCharacterList.add(this);


    }

    @Override
    public void death(Character ch) {
        deathNumber++;
        Main.specialCharacterList.remove(ch);
        System.out.println(ch.getName()+" is dead.");
        if(deathNumber==3){
            Main.game=false;
            Main.round=false;
            System.out.println("Game over, your score: "+Main.level);
            System.out.println("Enter your name:");
            String name = Main.input.next();
            File highscores = new File("Highscores.txt");
            try{
                FileWriter myWriter = new FileWriter("Highscores.txt");
                myWriter.write(name+" - Score : "+Main.level);
                myWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void attack(SpecialCharacter character,EnemyCharacter defender) {
        character.getWeaponType().attack(character,defender);
        character.setUsedAttackOrSpecial(true);
    }


    @Override
    public void pick(SpecialCharacter ch,Item item) {
        int weightOfTheInventory=0;
        for(int i=0;i<ch.getInventory().size();i++){
            weightOfTheInventory+=ch.getInventory().get(i).getWeight();
        }
        if(ch.getStrength()>weightOfTheInventory) {
            ch.getInventory().add(item);
            System.out.println(ch.getName()+" picked up the item: "+item.getName());
        }else{
            System.out.println("Your inventory is full.");
        }
    }

    @Override
    public void examine(SpecialCharacter ch, Item item) {
        System.out.println(ch.getName()+" examining the item.");
        item.displayItem();
    }

    @Override
    public void wield(SpecialCharacter ch, Item item) {
        if(item.getName().endsWith("r")){
            ch.setArmor((Clothing) item);
        }else if(item.getName().endsWith("d")){
            ch.setWeaponType((Weapon) item);
        }
    }

    @Override
    public void listInventory(SpecialCharacter ch) {
        System.out.println("Inventory: ");
        for(Item item : ch.getInventory()){
            item.displayItem();
        }

    }

    public void startMessage(){
        System.out.println(this.getName()+" created with S: "+this.getStrength()+", V: "+this.getVitality()
        +", I: "+this.getIntelligence()+". The HP is: "+this.getMaxHP()+". "+this.getName()+" wields "+this.getWeaponType().getName()+", with " +
                this.getWeaponType().getValue()+" damage and "+this.getWeaponType().getWeight()+" units of weight.");
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public boolean isWearingSingleArmor() {
        return isWearingSingleArmor;
    }

    public void setWearingSingleArmor(boolean wearingSingleArmor) {
        isWearingSingleArmor = wearingSingleArmor;
    }

    public boolean isWieldingSingleWeapon() {
        return isWieldingSingleWeapon;
    }

    public void setWieldingSingleWeapon(boolean wieldingSingleWeapon) {
        isWieldingSingleWeapon = wieldingSingleWeapon;
    }

    public boolean isSwordsSpecialAbilityUsed() {
        return isSwordsSpecialAbilityUsed;
    }

    public void setSwordsSpecialAbilityUsed(boolean swordsSpecialAbilityUsed) {
        isSwordsSpecialAbilityUsed = swordsSpecialAbilityUsed;
    }

    public static int getDeathNumber() {
        return deathNumber;
    }

    public static void setDeathNumber(int deathNumber) {
        SpecialCharacter.deathNumber = deathNumber;
    }


    public boolean isUsedAttackOrSpecial() {
        return usedAttackOrSpecial;
    }

    public void setUsedAttackOrSpecial(boolean usedAttackOrSpecial) {
        this.usedAttackOrSpecial = usedAttackOrSpecial;
    }
}
