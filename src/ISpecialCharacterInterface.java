public interface ISpecialCharacterInterface {
    public void attack(SpecialCharacter character,EnemyCharacter defender);
    public void pick(SpecialCharacter ch,Item item);
    public void examine(SpecialCharacter ch, Item item);
    public void wield(SpecialCharacter ch, Item item);
    public void listInventory(SpecialCharacter ch);
}
