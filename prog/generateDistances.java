private double generateDistances(int n, double baseDistance,
        double[][] distances) {
    double maxDistance = n / 2 * baseDistance;

    for (int i = 0; i < n; i++) {
        int k = 0;
        boolean increasing = true;
        for (int j = i; j < n; j++) {
            distances[i][j] = k;

            if (k == maxDistance && increasing) {
                increasing = false;
                if (n % 2 == 0) {
                    k -= baseDistance;
                }
            } else if (increasing) {
                k += baseDistance;
            } else {
                k -= baseDistance;
            }
        }
        for (int j = 0; j < i; j++) {
            distances[i][j] = k;

            if (k == maxDistance && increasing) {
                increasing = false;
                if (n % 2 == 0) {
                    k -= baseDistance;
                }
            } else if (increasing) {
                k += baseDistance;
            } else {
                k -= baseDistance;
            }
        }
    }

    return maxDistance;
}