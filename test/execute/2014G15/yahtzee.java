class yahtzee {
    public static void main(String[] args) {
        System.out.println(new YahtzeeImpl().play(100));
    }
}

class YahtzeeImpl {
    Util util;

    public int play(int rounds) {
        int ret;
        ret = 0;
        util = new Util();
        while (0 < rounds) {
            ret = this.playSingle();
            if (0 < ret) {
                rounds = 0;
            } else {
                rounds = rounds - 1;
            }
            System.out.println(4711);
        }
        return ret;
    }

    public int playSingle() {
        int ret;
        int[] dice;
        int diceCount;
        int[] buckets;
        int[] beakers;
        int i;
        int j;
        int sum;

        ret = 0;

        diceCount = 5;
        dice = this.roll(diceCount);

        buckets = new int[6];
        i = 0;
        while (i < 6) {
            buckets[i] = 0;
            j = 0;
            while (j < diceCount) {
                if (util.equal(dice[j], i + 1)) {
                    buckets[i] = buckets[i] + 1;
                } else {
                }
                j = j + 1;
            }
            i = i + 1;
        }

        beakers = new int[diceCount];
        sum = 0;
        i = 0;
        while (i < diceCount) {
            beakers[i] = 0;
            sum = sum + dice[i];
            System.out.println(dice[i]);
            i = i + 1;
        }
        i = 0;
        while (i < 6) {
            if (0 < buckets[i])
                beakers[buckets[i] - 1] = beakers[buckets[i] - 1] + 1;
            else {
            }
            i = i + 1;
        }

        if (0 < beakers[4])
            // Yahtzee!
            System.out.println(50);
        else if (0 < beakers[3])
            // Four of a kind!
            System.out.println(sum);
        else if (0 < beakers[2] && 0 < beakers[1])
            // Full house!
            System.out.println(25);
        else if (0 < beakers[2])
            // Three of a kind!
            System.out.println(sum);
        else if (4 < beakers[0]
                && util.or(util.equal(buckets[0], 0), util.equal(buckets[5], 0)))
            // Straight!
            System.out.println(2 * sum);
        else
            // Nothing cool...
            System.out.println(0);

        return ret;
    }

    public int[] roll(int diceCount) {
        int[] dice;
        int i;

        dice = new int[diceCount];
        i = 0;
        while (i < diceCount) {
            dice[i] = util.mod(util.random(), 6) + 1;
            i = i + 1;
        }

        return dice;
    }
}

class Util {
    int a;

    /**
     * @return A not so random number.
     */
    public int random() {
        a = this.mod(a * 991 + 3, 997);
        return a;
    }

    public int mod(int a, int m) {
        if (0 < m) {
            while (m - 1 < a) {
                a = a - m;
            }
        } else {
        }
        return a;
    }

    public boolean equal(int a, int b) {
        boolean eq;
        if (!(a < b) && !(b < a)) {
            eq = true;
        } else {
            eq = false;
        }
        return eq;
    }

    public boolean or(boolean a, boolean b) {
        if (b)
            a = true;
        else {
        }
        return a;
    }
}