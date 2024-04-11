import java.util.*;
public class Main {
    static Scanner scan = new Scanner(System.in);
    static dice die = new dice();
    public static String playerName;
    public static boolean fighting = false; //globals for player stats & enemy stats
    private static Monster buildEnemy(Warrior player) {
        switch (player.getLevel()) {
            case 1 -> {
                return new Monster(9, 1);
            }
            case 2 -> {
                return new Monster(19, 4);
            }
            case 3 -> {
                return new Monster(24, 6);
            }
            case 4 -> {
                return new Monster(32, 7);
            }
            case 5 -> {
                return new Monster(40, 9);
            }
            default -> {
                return new Monster(10, 0);
            }//initializes enemy stats based on player level
        }
    }
    private static void fight(Warrior player) {
        String action;
        player.printStats(player.getPlayerhp(), player.getPlayermeleedmg(),player.getXp());
        System.out.println("An enemy approaches");
        Monster monster = buildEnemy(player);
        player.printStats(player.getPlayerhp(), player.getPlayermeleedmg(),player.getXp());
        fighting = true;
        while(fighting){
            System.out.println("Press 'a' to attack\nPress 'i' for info");
            action = scan.nextLine();
            if(action.charAt(0) == 'a'){
                fighting = attack(player, monster);
                if(!fighting){
                    switch (player.getLevel()) {
                        case 1 -> player.setXp(player.getXp() + 4);
                        case 2 -> player.setXp(player.getXp() + 6);
                        case 3 -> player.setXp(player.getXp() + 9);
                        case 4 -> player.setXp(player.getXp() + 12);
                    }
                    System.out.println("You earned :" + player.getXp() + " xp");
                    checkLevelUp(player);
                    return;
                }
                enemyattack(player, monster);
            }else if(action.charAt(0) == 'i'){
                player.printStats(player.getPlayerhp(), player.getPlayermeleedmg(),player.getXp());
                monster.printEnemyStats();
            }
        }
    }

    private static void checkLevelUp(Warrior player) {
        if(player.getXp() >= 100 && player.getLevel() == 4){
            System.out.println("Level 5!");
            player.setLevel(player.getLevel()+1);
            player.setMaxhp(player.getMaxhp()+25);
            player.setPlayerhp(player.getMaxhp());
            player.setPlayermeleedmg(player.getPlayermeleedmg()+3);
            player.printStats(player.getPlayerhp(), player.getPlayermeleedmg(),player.getXp());
        }else
        if(player.getXp() >= 50 && player.getLevel() == 3){
            System.out.println("Level 4!");
            player.setLevel(player.getLevel()+1);
            player.setMaxhp(player.getMaxhp()+20);
            player.setPlayerhp(player.getMaxhp());
            player.setPlayermeleedmg(player.getPlayermeleedmg()+2);
            player.printStats(player.getPlayerhp(), player.getPlayermeleedmg(),player.getXp());
        }else
        if(player.getXp() >= 25 && player.getLevel() == 2){
            System.out.println("Level 3!");
            player.setLevel(player.getLevel()+1);
            player.setMaxhp(player.getMaxhp()+10);
            player.setPlayerhp(player.getMaxhp());
            player.setPlayermeleedmg(player.getPlayermeleedmg()+2);
            player.printStats(player.getPlayerhp(), player.getPlayermeleedmg(),player.getXp());
        }else
        if(player.getXp() >= 10 && player.getLevel() == 1){
            System.out.println("Level 2!");
            player.setLevel(player.getLevel()+1);
            player.setMaxhp(player.getMaxhp()+5);
            player.setPlayerhp(player.getMaxhp());
            player.setPlayermeleedmg(player.getPlayermeleedmg()+1);
            player.printStats(player.getPlayerhp(), player.getPlayermeleedmg(),player.getXp());
        }//increments player level and adds to stats with xp

    }
    private static void enemyattack(Warrior player, Monster monster) {
        if(die.roll6() > 2){
            System.out.println("Enemy hits!");
            player.setPlayerhp(player.getPlayerhp() - monster.getPlayermeleedmg());
            if(player.getPlayerhp() <= 0){
                gameover();
                System.exit(0);//game over if player health < 0
            }
        }else{
            System.out.println("Enemy Misses!");
        }
    }
    private static boolean attack(Warrior player, Monster monster) {
        if(die.roll6() > 2){
            System.out.println("You hit!");
            monster.setPlayerhp(monster.getPlayerhp() - player.getPlayermeleedmg());
            if(monster.getPlayerhp() <= 0){
                System.out.println("You Won!"); //prints if enemy health < 0
                return false;
            }
        }else{
            System.out.println("You miss!");
        }
        return true;
    }

    private static void gameover() {
        System.out.println(playerName + " Died!") ;
        System.out.println("GAME OVER!");
        System.exit(0); //terminates if lost
    }
    public static void main(String[] args) {
        while(true){
            System.out.println("Enter your Name: ");
            playerName = scan.nextLine();
            System.out.println("Welcome your Name: " + playerName);
            Warrior player = new Warrior();
            player.printStats(player.getPlayerhp(), player.getPlayermeleedmg(),player.getXp());
            while(player.getLevel() == 1){
                fight(player);
            }
            System.out.println("This area is clear... time to move on\n");
            while(player.getLevel() == 2){
                fight(player);
            }
            System.out.println("This area is clear... time to move on\n");
            while(player.getLevel() == 3){
                fight(player);
            }
            System.out.println("This area is clear... time to move on\n");
            while(player.getLevel() == 4){
                fight(player);
            }
            System.out.println("This area is clear... time to move on\n");
            while(player.getLevel() == 5){
                fight(player);
            }//keeps in area until levelUp
        }
    }
}
