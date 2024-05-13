public class Wand extends Weapon{
    public Wand(String name, int weight, double value) {
        super(name, weight, value);
    }

    @Override
    public void attack(Character attacker, Character defender){
        double damage = attacker.getIntelligence()*attacker.getWeaponType().getValue();
        damage = damage/3;
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

    }

    @Override
    public void specialAbility(SpecialCharacter healer, SpecialCharacter healed){
        double healValue = healer.getIntelligence()*healer.getWeaponType().getValue();
        healed.setHp((int) (healed.getHp()+healValue));
        if(healed.getHp()>healed.getMaxHP()){
            healed.setHp(healed.getMaxHP());
        }
        System.out.println(healer.getName()+" healed: "+healed.getName());
        System.out.println("New HP of the "+healed.getName()+" : "+healed.getHp());

    }


}
