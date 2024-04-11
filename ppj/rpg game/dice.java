import java.util.Random;
public class dice {
    public int roll(int max) {
        return 1 + new Random().nextInt(max);
    }

    public int roll6() {
        return roll(6);
    }

    public int roll10() {
        return roll(10);
    }
}
