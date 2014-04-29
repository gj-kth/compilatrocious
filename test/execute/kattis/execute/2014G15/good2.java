class good2 {
    public static void main(String[] args) {
        System.out.println(new Good().main());
    }
}

class Good {
    public int main() {
        int ret;
        ret = 0;

        // Bucket sort.
        ret = this.testBucketSort();

        // Expression stuff.
        if (1 + 4 * 4 < 18 && !false && 1 < 2) {
            ret = ret;
        } else {
            ret = 1;
        }

        return ret;
    }

    public int testBucketSort() {
        int[] numbers;
        int[] buckets;
        int i;
        int j;

        numbers = new int[30];
        i = 0;
        numbers[i] = 4;
        i = i + 1;
        numbers[i] = 5;
        i = i + 1;
        numbers[i] = 3;
        i = i + 1;
        numbers[i] = 5;
        i = i + 1;
        numbers[i] = 1;
        i = i + 1;
        numbers[i] = 3;
        i = i + 1;
        numbers[i] = 3;
        i = i + 1;
        numbers[i] = 5;
        i = i + 1;
        numbers[i] = 1;
        i = i + 1;
        numbers[i] = 2;
        i = i + 1;
        numbers[i] = 5;
        i = i + 1;
        numbers[i] = 1;
        i = i + 1;
        numbers[i] = 4;
        i = i + 1;
        numbers[i] = 3;
        i = i + 1;
        numbers[i] = 3;
        i = i + 1;
        numbers[i] = 5;
        i = i + 1;
        numbers[i] = 2;
        i = i + 1;
        numbers[i] = 1;
        i = i + 1;
        numbers[i] = 2;
        i = i + 1;
        numbers[i] = 3;
        i = i + 1;
        numbers[i] = 4;
        i = i + 1;
        numbers[i] = 4;
        i = i + 1;
        numbers[i] = 2;
        i = i + 1;
        numbers[i] = 3;
        i = i + 1;
        numbers[i] = 4;
        i = i + 1;
        numbers[i] = 1;
        i = i + 1;
        numbers[i] = 2;
        i = i + 1;
        numbers[i] = 4;
        i = i + 1;
        numbers[i] = 4;
        i = i + 1;
        numbers[i] = 3;

        buckets = this.bucketSort(numbers);

        System.out.println(4711);
        i = 0;
        while (i < buckets.length) {
            System.out.println(buckets[i]);
            i = i + 1;
        }
        System.out.println(4712);
        i = 0;
        while (i < buckets.length) {
            j = 0;
            while (j < buckets[i]) {
                System.out.println(i);
                j = j + 1;
            }
            i = i + 1;
        }
        System.out.println(4713);

        return 0;
    }

    /**
     * Bucket sort numbers.
     * @param numbers    Numbers
     * @return  Buckets
     */
    public int[] bucketSort(int[] numbers) {
        int[] buckets;
        int i;
        int max;

        max = 0;
        i = 0;
        while (i < numbers.length) {
            if (max < numbers[i]) {
                max = numbers[i];
            } else {
            }
            i = i + 1;
        }

        buckets = new int[max + 1];
        i = 0;
        while (i < buckets.length) {
            buckets[i] = 0;
            i = i + 1;
        }

        i = 0;
        while (i < numbers.length) {
            buckets[numbers[i]] = buckets[numbers[i]] + 1;
            i = i + 1;
        }

        return buckets;
    }
}
