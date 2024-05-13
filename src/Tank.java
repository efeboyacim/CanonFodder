public class Tank extends SpecialCharacter{
    public Tank(Shield tankBasicShield){
        super();
        this.setName("Tank");
        this.setStrength(super.randomNumber(1,5));
        this.setVitality(super.randomNumber(6,10));
        this.setIntelligence(super.randomNumber(3,7));
        this.setMaxHP((int) (Math.round((0.7 * this.getVitality() + 0.2 * this.getStrength() + 0.1 * this.getIntelligence()))));
        this.setMaxHP(this.getMaxHP()*10);
        this.setHp(this.getMaxHP());
        this.setWeaponType(tankBasicShield);
        this.getInventory().add(tankBasicShield);
    }


}
