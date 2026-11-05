import java.io.*;
import java.util.*;

public class solution {
    public static boolean checkMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n / 2) - 1; i++) {
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            if (l < n && arr[i] > arr[l]) return false;
            if (r < n && arr[i] > arr[r]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            System.out.println(true);
            return;
        }
        String[] tokens = line.trim().split("\\s+");
        int n = tokens.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(tokens[i]);
        System.out.println(checkMinHeap(arr));
    }
}
