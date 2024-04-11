public class Monster extends Character{
    public Monster(int hp, int dmg) {
        super(hp, dmg);
    }

    static void printEnemyStats() {
        System.out.println("Enemy "+"\nhp: " + getPlayerhp() + "\ndmg: " + getPlayermeleedmg() + "\n");
    }
}
