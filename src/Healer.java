public class Healer extends SpecialCharacter{
    public Healer(Wand healerBasicWand){
        super();
        this.setName("Healer");
        this.setStrength(super.randomNumber(3,7));
        this.setVitality(super.randomNumber(1,5));
        this.setIntelligence(super.randomNumber(6,10));
        this.setMaxHP((int) (Math.round((0.7 * this.getVitality() + 0.2 * this.getStrength() + 0.1 * this.getIntelligence()))));
        this.setMaxHP(this.getMaxHP()*10);
        this.setHp(this.getMaxHP());
        this.setWeaponType(healerBasicWand);
        this.getInventory().add(healerBasicWand);
    }

}
