class Lab1 {
    private static boolean isAInValue(short a) {
        int[] values = { 4, 5, 9, 10, 12, 14, 17, 19, 20 };

        for (int i = 0; i < values.length; i++) {
            if (values[i] == a) {
                return true;
            }
        }
        return false;
    }

    private static double calculeteTask(short a, double x) {
        double val;

        if (a == 7) {
            val = Math.pow(Math.E, Math.cos(Math.cos(x)));
            // } else if (IntStream.of(values).anyMatch(r -> r == a)) {
        } else if (isAInValue(a)) {
            val = Math.asin(
                    1 / Math.pow(Math.E, Math.pow(Math.tan(Math.pow(Math.E, Math.pow(Math.E, x))), 2)));
        } else {
            val = 1 - (1 / 4 * Math.pow(Math.pow(Math.E, Math.tan(x)), 2));
        }

        return val;
    }

    public static void main(String[] args) {
        short[] a = new short[20 - 3 + 1];

        for (int i = 0; i < a.length; i++) {
            a[i] = (short) (i + 3);
        }

        double[] x = new double[10];

        for (int i = 0; i < x.length; i++) {
            x[i] = Math.random() * (4 - (-13)) - 13;
        }

        double[][] b = new double[18][10];

        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                b[i][j] = calculeteTask(a[i], x[j]);
                System.out.printf("%.3f ", b[i][j]);
            }
            System.out.println();
        }
    }
}