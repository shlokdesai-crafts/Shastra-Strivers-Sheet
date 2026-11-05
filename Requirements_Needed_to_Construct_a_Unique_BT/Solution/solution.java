import java.io.*;
import java.util.*;

public class solution {
    public static boolean isPossible(int a, int b) {
        if ((a == 1 && b == 2) || (a == 2 && b == 1)) return true;
        if ((a == 2 && b == 3) || (a == 3 && b == 2)) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        String[] parts = line.trim().split("\\s+");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);
        System.out.println(isPossible(a, b));
    }
}
