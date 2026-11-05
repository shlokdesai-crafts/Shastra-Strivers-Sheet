import java.io.*;
import java.util.*;

public class solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String l1 = br.readLine();
        String l2 = br.readLine();
        if (l1 == null || l2 == null) return;
        String[] tokens = l1.trim().split("\\s+");
        int n = tokens.length;
        int ind = Integer.parseInt(l2.trim());

        int parent = (ind == 0) ? -1 : (ind - 1) / 2;
        int left = (2 * ind + 1 < n) ? 2 * ind + 1 : -1;
        int right = (2 * ind + 2 < n) ? 2 * ind + 2 : -1;

        System.out.println(parent + " " + left + " " + right);
    }
}
