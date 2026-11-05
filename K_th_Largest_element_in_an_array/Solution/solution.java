import java.io.*;
import java.util.*;

public class solution {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String l1 = br.readLine();
        String l2 = br.readLine();
        if (l1 == null || l2 == null) return;
        String[] tokens = l1.trim().split("\\s+");
        int[] nums = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) nums[i] = Integer.parseInt(tokens[i]);
        int k = Integer.parseInt(l2.trim());
        System.out.println(findKthLargest(nums, k));
    }
}
