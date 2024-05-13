public class Fighter extends SpecialCharacter{
    public Fighter(Sword fighterBasicWeapon) {
        super();
        this.setName("Fighter");
        this.setStrength(super.randomNumber(6,10));
        this.setVitality(super.randomNumber(3,7));
        this.setIntelligence(super.randomNumber(1,5));
        this.setMaxHP((int) (Math.round((0.7 * this.getVitality() + 0.2 * this.getStrength() + 0.1 * this.getIntelligence()))));
        this.setMaxHP(this.getMaxHP()*10);
        this.setHp(this.getMaxHP());
        this.setWeaponType(fighterBasicWeapon);
        this.getInventory().add(fighterBasicWeapon);
    }

}
