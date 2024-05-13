public class EnemyCharacter extends Character implements IEnemyCharacterInterface{
    private Item drop;

    public EnemyCharacter(Item drop){
        super("Enemy"+(Main.enemyCharacterList.size()+1));
        Main.enemyCharacterList.add(this);
        int tempNumber = super.randomNumber(1,10);
        this.setStrength(super.randomNumber(1,5));
        this.setVitality(super.randomNumber(1,5));
        this.setIntelligence(super.randomNumber(1,5));
        this.setMaxHP((int) (Math.round((0.7 * this.getVitality() + 0.2 * this.getStrength() + 0.1 * this.getIntelligence()))));
        this.setHp(this.getMaxHP()*3);
        if(tempNumber<=8){
            this.setWeaponType(new Sword("Basic Sword",10,1));
        }else if(tempNumber==9){
            this.setWeaponType(new Shield("Basic Shield",10,1));
        }else if(tempNumber==10){
            this.setWeaponType(new Wand("Basic Wand",10,1));
        }
        this.drop=drop;
    }

    public static void target(Fighter fighter, Healer healer , Tank tank,EnemyCharacter enemyCharacter){
        if(!enemyCharacter.isStunned()){
            System.out.println("Enemy is attacking..");
            if(tank.getHp()>0){
                enemyCharacter.attack(tank,enemyCharacter);
            }else if(fighter.getHp()>0){
                enemyCharacter.attack(fighter,enemyCharacter);
            }else{
                enemyCharacter.attack(healer,enemyCharacter);
            }
        }else{
            System.out.println("Enemy is stunned, they can't attack right now.");
            enemyCharacter.setStunned(false);
        }

    }

    @Override
    public void attack(SpecialCharacter c1,EnemyCharacter enemyCharacter) {
        if(!c1.isProtection()) {
            enemyCharacter.getWeaponType().attack(enemyCharacter, c1);
        }else {
            System.out.println("Enemy tried to attack " + c1.getName() + " but character used Sword's special power.");
            Main.swordSuperPowerCount-=1;
            if(Main.swordSuperPowerCount==0){
                for(SpecialCharacter character : Main.specialCharacterList){
                    character.setSwordsSpecialAbilityUsed(false);
                    character.setStunned(false);
                    character.setProtection(false);
                }
            }
        }
    }



    @Override
    public void death(Character ch) {
        System.out.println(this.getName()+" is dead and dropped "+this.getDrop().getName()+".");
        Main.enemyCharacterList.remove(this);
        Main.itemDropList.add(this.getDrop());
    }


    public Item getDrop() {
        return drop;
    }

    public void setDrop(Item drop) {
        this.drop = drop;
    }
}
