package window.main.sorting.algorithms;

import processing.core.PApplet;
import window.main.sorting.SortingAlgorithm;
import window.main.sorting.colors.Color;
import window.main.sorting.colors.Colors;

public class RadixSort extends SortingAlgorithm {
    public RadixSort(PApplet pApplet, int[] vector, Color[] color) {
        super(pApplet, vector, color, "Radix Sort", "O(n*k/d)", "O(n+2^d)");
    }

    @Override
    public void run() {
        onAlgorithmStart();

        radixSort();

        onAlgorithmStops();
    }

    private void radixSort() {
        int m = getMax();

        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(exp);
        }
    }

    private void countSort(int exp) {
        int n = vector.length;

        int[] buffer = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            if (stop) {
                return;
            }

            animateIndex(i);

            count[(vector[i] / exp) % 10]++;
            arrayAccess++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            if (stop) {
                return;
            }

            animateIndex(i);

            int value = vector[i];

            buffer[count[(value / exp) % 10] - 1] = value;
            count[(value / exp) % 10]--;
            arrayAccess += 1;
        }

        for (int i = 0; i < n; i++) {
            if (stop) {
                return;
            }

            arrayAccess++;
            vector[i] = buffer[i];

            setColor(i, Colors.CURRENT_INDEX);
            playSound(i);

            sleep();
            stopSound();

            while (pause) {
                if (stop) {
                    return;
                }
                pauseSleep();
            }

            resetColor(i);
        }
    }

}
