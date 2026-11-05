import java.io.*;
import java.util.*;

public class solution {
    static class MinHeap {
        private ArrayList<Integer> heap = new ArrayList<>();

        public void insert(int x) {
            heap.add(x);
            int curr = heap.size() - 1;
            while (curr > 0 && heap.get((curr - 1) / 2) > heap.get(curr)) {
                int p = (curr - 1) / 2;
                int t = heap.get(curr);
                heap.set(curr, heap.get(p));
                heap.set(p, t);
                curr = p;
            }
        }

        public int getMin() {
            if (heap.isEmpty()) return -1;
            return heap.get(0);
        }

        public int extractMin() {
            if (heap.isEmpty()) return -1;
            int min = heap.get(0);
            int last = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, last);
                heapify(0);
            }
            return min;
        }

        public int heapSize() {
            return heap.size();
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        private void heapify(int i) {
            int n = heap.size();
            int smallest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            if (l < n && heap.get(l) < heap.get(smallest)) smallest = l;
            if (r < n && heap.get(r) < heap.get(smallest)) smallest = r;
            if (smallest != i) {
                int t = heap.get(i);
                heap.set(i, heap.get(smallest));
                heap.set(smallest, t);
                heapify(smallest);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        int q = Integer.parseInt(line.trim());
        MinHeap h = new MinHeap();
        for (int i = 0; i < q; i++) {
            String qLine = br.readLine();
            if (qLine == null) break;
            String[] parts = qLine.trim().split("\\s+");
            String op = parts[0];
            if (op.equals("insert")) {
                h.insert(Integer.parseInt(parts[1]));
            } else if (op.equals("getMin")) {
                System.out.println(h.getMin());
            } else if (op.equals("extractMin")) {
                System.out.println(h.extractMin());
            } else if (op.equals("heapSize")) {
                System.out.println(h.heapSize());
            } else if (op.equals("isEmpty")) {
                System.out.println(h.isEmpty());
            }
        }
    }
}
