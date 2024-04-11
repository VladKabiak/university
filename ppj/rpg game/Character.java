public class Character {
    // Instance Variables
    static int maxhp;
    static int playerhp;
    static int playermeleedmg;
    static int xp;
    static int level;

    // Constructor Declaration of Class
    public Character(int maxhp, int playerhp, int playermeleedmg, int xp, int level)
    {
        Character.maxhp = maxhp;
        Character.playerhp = playerhp;
        Character.playermeleedmg = playermeleedmg;
        Character.xp = xp;
        Character.level = level;
    }

    public Character(int hp,int dmg)
    {
        playerhp = hp;
        playermeleedmg = dmg;
    }

    // method 1
    public static int getMaxhp()
    {
        return maxhp;
    }

    // method 2
    public static int getPlayerhp()
    {
        return playerhp;
    }

    // method 3
    public static int getPlayermeleedmg()
    {
        return playermeleedmg;
    }

    // method 4
    public static int getXp()
    {
        return xp;
    }

    // method 4
    public static int getLevel()
    {
        return level;
    }

    public static void setMaxhp(int val)
    {
        maxhp = val;
    }

    public static void setPlayerhp(int val)
    {
        maxhp = val;
    }

    public static void setPlayermeleedmg(int val)
    {
        maxhp = val;
    }

    public static void setXp(int val)
    {
        maxhp = val;
    }

    public static void setLevel(int val)
    {
        maxhp = val;
    }

    public static void printStats(int playerhp, int playermeleedmg, int xp) {
        System.out.println("Your stats is:" + "\nhp: " + playerhp + "\ndamage: " + playermeleedmg + "\nxp: " + xp + "\n");
    }
}
