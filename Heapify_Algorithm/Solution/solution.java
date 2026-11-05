import java.io.*;
import java.util.*;

public class solution {
    static void minHeapify(int[] arr, int n, int i) {
        int smallest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l] < arr[smallest]) smallest = l;
        if (r < n && arr[r] < arr[smallest]) smallest = r;
        if (smallest != i) {
            int t = arr[i]; arr[i] = arr[smallest]; arr[smallest] = t;
            minHeapify(arr, n, smallest);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String l1 = br.readLine();
        String l2 = br.readLine();
        if (l1 == null || l2 == null) return;
        String[] tokens = l1.trim().split("\\s+");
        int n = tokens.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(tokens[i]);
        String[] p = l2.trim().split("\\s+");
        int ind = Integer.parseInt(p[0]);
        int val = Integer.parseInt(p[1]);

        int oldVal = arr[ind];
        arr[ind] = val;
        if (val < oldVal) {
            // Sift-up
            int curr = ind;
            while (curr > 0 && arr[(curr - 1) / 2] > arr[curr]) {
                int t = arr[curr]; arr[curr] = arr[(curr - 1) / 2]; arr[(curr - 1) / 2] = t;
                curr = (curr - 1) / 2;
            }
        } else {
            // Sift-down
            minHeapify(arr, n, ind);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(" ");
            sb.append(arr[i]);
        }
        System.out.println(sb.toString());
    }
}
