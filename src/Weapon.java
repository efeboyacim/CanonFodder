public abstract class Weapon extends Item{
    public Weapon(String name, int weight, double value) {
        super(name, weight, value);
    }

    public abstract void attack(Character attacker, Character defender);



    public abstract void specialAbility(SpecialCharacter ch);

    public abstract void specialAbility(SpecialCharacter healer, SpecialCharacter healed);
}
