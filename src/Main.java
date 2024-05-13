import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<EnemyCharacter> enemyCharacterList = new ArrayList<>();
    static ArrayList<SpecialCharacter> specialCharacterList = new ArrayList<>();
    static ArrayList<Item> itemDropList = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static int level = 0;
    static boolean game = true;
    static boolean round = true;
    static boolean side = true;
    static boolean gameplay = true;
    static boolean checkLevel = true;
    static int swordSuperPowerCount = 0;

    public static void main(String[] args) throws IOException {
        Weapon s1 = new Sword("Basic Sword", 1, 1);
        Weapon w1 = new Wand("Basic Wand", 1, 1);
        Weapon sh1 = new Shield("Basic Shield", 1, 1);
        Fighter fighter = new Fighter((Sword) s1);
        Healer healer = new Healer((Wand) w1);
        Tank tank = new Tank((Shield) sh1);
        for (int i = 0; i < specialCharacterList.size(); i++) {
            specialCharacterList.get(i).startMessage();
        }
        while (game) {
            startGame();
            gameplay = true;
            round = true;
            side = true;
            while (round) {
                if (side == true) {
                    gameplay = true;
                    while (gameplay) {
                        menuMsg(fighter, healer, tank);
                        checkLevel(fighter, healer, tank);
                    }
                    side = false;
                } else {
                    EnemyCharacter.target(fighter, healer, tank, enemyCharacterList.get(SpecialCharacter.randomNumber(0, enemyCharacterList.size() - 1)));
                    side = true;
                }
            }
        }


    }

    public static void startGame() {
        int number = (int) Math.pow(2, Main.level);
        for (int i = 0; i < number; i++) {
            new EnemyCharacter(Item.randomItem());
        }
        System.out.println("Creating Level " + Main.level + ", with " + Main.enemyCharacterList.size() + " enemy soldier.");
        System.out.print("Entering Level " + Main.level + ";");
        Main.itemDropList.clear();
        for (int i = 0; i < (3 - SpecialCharacter.deathNumber); i++) {
            System.out.print(Main.specialCharacterList.get(i).getName() + " enters.");
        }
        System.out.println("");
    }




    public static int menuMsg(Fighter fighter, Healer healer, Tank tank) {
        System.out.println();
        System.out.print("Choose a character to use: ");
        for (int i = 0; i < specialCharacterList.size(); i++) {
            System.out.print((i + 1) + ". " + specialCharacterList.get(i).getName() + " ");
        }

        int number = input.nextInt();

        if (specialCharacterList.get(number - 1).getHp() <= 0 || specialCharacterList.get(number - 1).isSwordsSpecialAbilityUsed()) {
            System.out.println("This character is unavailable, try again.");
            menuMsg(fighter, healer, tank);
        } else {
            SpecialCharacter tempChar = specialCharacterList.get(number - 1);
            switch (motion()) {
                case 1:
                    System.out.print("Choose an enemy to attack: ");
                    for (int i = 0; i < enemyCharacterList.size(); i++) {
                        System.out.print((i + 1) + ") - " + enemyCharacterList.get(i).getName() + " ");
                    }
                    int tempNumb = input.nextInt();
                    tempChar.attack(tempChar, enemyCharacterList.get(tempNumb - 1));
                    break;
                case 2:
                    System.out.println("Using super power...");
                    if (tempChar.getName().equals("Healer")) {
                        switch (healMenu()) {
                            case 1:
                                tempChar.getWeaponType().specialAbility(tempChar, fighter);
                                break;
                            case 2:
                                tempChar.getWeaponType().specialAbility(tempChar, tempChar);
                                break;
                            case 3:
                                tempChar.getWeaponType().specialAbility(tempChar, tank);
                                break;
                        }
                        tempChar.setUsedAttackOrSpecial(true);
                        System.out.println("----------------------");
                    } else {
                        tempChar.getWeaponType().specialAbility(tempChar);
                        tempChar.setUsedAttackOrSpecial(true);
                        System.out.println("----------------------");
                    }
                    break;


            }
            return number;
        }
        return 0;
    }

    public static int motion() {
        System.out.print("Motions: ");
        System.out.print("1)Attack - 2)Special Move\n");
        int numberMotion = input.nextInt();
        return numberMotion;
    }

    public static void checkLevel(Fighter fighter, Healer healer, Tank tank) throws IOException {
        if (enemyCharacterList.isEmpty()) {
            level++;
            Main.checkLevel=true;
            while(checkLevel){
                System.out.print("1) Next round 2) Examine - Wield - Inventory\n");
                if (next()) {
                    Main.round = false;
                    Main.gameplay = false;
                    Main.side = true;
                    Main.checkLevel=false;
                    for (SpecialCharacter ch : specialCharacterList) {
                        ch.setSwordsSpecialAbilityUsed(false);
                        ch.setUsedAttackOrSpecial(false);
                    }

                } else {
                    Main.side = true;
                    gameplay = true;
                    System.out.print("Choose a character to use: ");
                    for (int i = 0; i < specialCharacterList.size(); i++) {
                        System.out.print((i + 1) + ". " + specialCharacterList.get(i).getName() + " ");
                    }
                    int number = input.nextInt();
                    number = number - 1;
                    menuTwo(specialCharacterList.get(number));
                }

            }

        } else {
            side = true;
            gameplay = false;
        }
        if (specialCharacterList.isEmpty()) {
            System.out.println("Game over, your score: "+level);
            System.out.println("Enter your name:");
            String name = input.nextLine();
            File highscores = new File("Highscores.txt");
            FileWriter writer = new FileWriter("Highscores.txt");
            writer.write(name+"= Score = "+level+"\n");
            writer.close();
            Main.game = false;
            Main.round = false;
        }
    }

    public static int healMenu() {
        int tempNumber = 0;
        if (SpecialCharacter.deathNumber == 1) {
            System.out.print("Choose a teammate to heal: 1) Fighter 2)Healer\n");
        } else if (SpecialCharacter.deathNumber == 2) {
            System.out.print("Choose a teammate to heal: 1) Fighter\n");
        } else if (SpecialCharacter.deathNumber == 0) {
            System.out.print("Choose a teammate to heal: 1)Fighter 2)Healer 3)Tank\n");
        }
        tempNumber = input.nextInt();
        return tempNumber;
    }

    public static boolean next() {
        int next = 0;
        next = input.nextInt();
        if (next == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void pick(SpecialCharacter character) {
        System.out.print("Choose an item to pick: \n");
        for (int i = 0; i < Main.itemDropList.size(); i++) {
            Main.itemDropList.get(i).displayItem();
        }
    }

    public static void menuTwo(SpecialCharacter ch) {
        System.out.print("1) Examine 2) Wield 3) Pick 4) Inventory\n");
        int menuChoice = input.nextInt();
        int itemChoice = 0;
        int i=1;
        switch (menuChoice) {
            case 1:
                for (Item item : itemDropList) {
                    System.out.print(i+". "+item.getName() + " - ");
                    i+=1;
                }
                i=1;
                System.out.println();
                System.out.print("Choose an item to examine:\n");
                itemChoice = input.nextInt();
                if (itemDropList.get(itemChoice - 1) != null) {
                    ch.examine(ch, itemDropList.get(itemChoice - 1));
                }
                break;
            case 2:
                ArrayList<Item> wieldList = new ArrayList<>();
                wieldList.addAll(ch.getInventory());
                wieldList.remove(ch.getWeaponType());
                wieldList.remove(ch.getArmor());
                for (Item item : wieldList) {
                    System.out.print(i+". "+item.getName() + " - ");
                    i+=1;
                }
                i=1;
                System.out.println();
                System.out.print("Choose an item to wield.\n");
                itemChoice = input.nextInt();
                ch.wield(ch,ch.getInventory().get(itemChoice - 1));
                System.out.println(ch.getName() + " wields " + wieldList.get(itemChoice - 1).getName() + ".");
                break;
            case 3:
                for (Item item : itemDropList) {
                    System.out.print(i+". "+item.getName() + " - ");
                    i+=1;
                }
                i=1;
                System.out.println();
                System.out.print("Choose an item to pick:\n");
                itemChoice = input.nextInt();
                if (itemDropList.get(itemChoice - 1) != null) {
                    ch.pick(ch, itemDropList.get(itemChoice - 1));
                    System.out.println(ch.getName() + " picks " + itemDropList.get(itemChoice - 1).getName() + ".");
                    itemDropList.remove(itemChoice-1);
                }
                break;
            case 4:
                System.out.println(ch.getName() + "'s inventory: ");
                for (Item item : ch.getInventory()) {
                    item.displayItem();
                }
                break;
        }
    }

    }

