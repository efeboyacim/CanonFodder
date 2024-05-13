public class Sword extends Weapon{
    public Sword(String name, int weight, double value) {
        super(name, weight, value);
    }

    @Override
    public void attack(Character attacker, Character defender){
        double damage = attacker.getStrength()*attacker.getWeaponType().getValue();
        damage = damage;
        try {
            defender.setHp((int) (defender.getHp() + defender.getArmor().getValue() - damage));
        }catch (Exception e){
            defender.setHp((int) (defender.getHp()  - damage));
        }
        if(defender.getHp()<=0){
            defender.death(defender);
            System.out.println("-----------------------");
        }else{
            System.out.println(defender.getName()+"'s new HP: "+defender.getHp());
            System.out.println("-----------------------");
        }

    }

    @Override
    public void specialAbility(SpecialCharacter ch) {
        Main.swordSuperPowerCount = 2;
        System.out.println("Enemies can't attack for 2 turn.");
        for(SpecialCharacter character : Main.specialCharacterList){
            character.setProtection(true);
        }
        ch.setSwordsSpecialAbilityUsed(true);
    }

    @Override
    public void specialAbility(SpecialCharacter healer, SpecialCharacter healed) {

    }

}
