public abstract class Character{
    private int strength;
    private int vitality;
    private int intelligence;
    private int maxHP;
    private int hp;
    private boolean isStunned = false;
    private Weapon weaponType;
    private String name;
    private Clothing armor;
    private boolean protection;

    public Character(){

    }

    public Character(String name){
        this.name =name;
    }

    public Character(int strength, int vitality, int intelligence) {
        this.strength = strength;
        this.vitality = vitality;
        this.intelligence = intelligence;
        this.maxHP = (int)(Math.round((0.7*this.vitality+0.2*this.strength+0.1*this.intelligence)));
        this.hp = maxHP;
    }

    public abstract void death(Character ch);

    public static int randomNumber(int min, int max){
        return (int) Math.floor(Math.random()*(max-min+1)+min);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public boolean isProtection() {
        return protection;
    }

    public void setProtection(boolean protection) {
        this.protection = protection;
    }

    public Clothing getArmor() {
        return armor;
    }

    public void setArmor(Clothing armor) {
        this.armor = armor;
    }
}
