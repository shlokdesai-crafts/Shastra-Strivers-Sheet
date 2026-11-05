import java.io.*;
import java.util.*;

public class solution {
    static class MaxHeap {
        private ArrayList<Integer> heap = new ArrayList<>();

        public void insert(int x) {
            heap.add(x);
            int curr = heap.size() - 1;
            while (curr > 0 && heap.get((curr - 1) / 2) < heap.get(curr)) {
                int p = (curr - 1) / 2;
                int t = heap.get(curr);
                heap.set(curr, heap.get(p));
                heap.set(p, t);
                curr = p;
            }
        }

        public int getMax() {
            if (heap.isEmpty()) return -1;
            return heap.get(0);
        }

        public int extractMax() {
            if (heap.isEmpty()) return -1;
            int max = heap.get(0);
            int last = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, last);
                heapify(0);
            }
            return max;
        }

        public int heapSize() {
            return heap.size();
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        private void heapify(int i) {
            int n = heap.size();
            int largest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            if (l < n && heap.get(l) > heap.get(largest)) largest = l;
            if (r < n && heap.get(r) > heap.get(largest)) largest = r;
            if (largest != i) {
                int t = heap.get(i);
                heap.set(i, heap.get(largest));
                heap.set(largest, t);
                heapify(largest);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        int q = Integer.parseInt(line.trim());
        MaxHeap h = new MaxHeap();
        for (int i = 0; i < q; i++) {
            String qLine = br.readLine();
            if (qLine == null) break;
            String[] parts = qLine.trim().split("\\s+");
            String op = parts[0];
            if (op.equals("insert")) {
                h.insert(Integer.parseInt(parts[1]));
            } else if (op.equals("getMax")) {
                System.out.println(h.getMax());
            } else if (op.equals("extractMax")) {
                System.out.println(h.extractMax());
            } else if (op.equals("heapSize")) {
                System.out.println(h.heapSize());
            } else if (op.equals("isEmpty")) {
                System.out.println(h.isEmpty());
            }
        }
    }
}
