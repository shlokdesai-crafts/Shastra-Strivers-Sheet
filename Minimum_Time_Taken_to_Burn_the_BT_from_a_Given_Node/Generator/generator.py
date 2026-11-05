import os
import random
from collections import deque

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

def serialize_tree(root):
    if not root:
        return "null"
    res = []
    q = deque([root])
    while q:
        curr = q.popleft()
        if curr:
            res.append(str(curr.val))
            q.append(curr.left)
            q.append(curr.right)
        else:
            res.append("null")
    while res and res[-1] == "null":
        res.pop()
    return " ".join(res)

def gen_single_node(val):
    return TreeNode(val)

def gen_skewed(n, direction, vals):
    if n <= 0:
        return None
    root = TreeNode(vals[0])
    curr = root
    for i in range(1, n):
        nxt = TreeNode(vals[i])
        if direction == 'left':
            curr.left = nxt
        else:
            curr.right = nxt
        curr = nxt
    return root

def gen_zigzag(n, start_dir, vals):
    if n <= 0:
        return None
    root = TreeNode(vals[0])
    curr = root
    d = start_dir
    for i in range(1, n):
        nxt = TreeNode(vals[i])
        if d == 'left':
            curr.left = nxt
            d = 'right'
        else:
            curr.right = nxt
            d = 'left'
        curr = nxt
    return root

def gen_complete_tree(n, vals):
    if n <= 0:
        return None
    nodes = [TreeNode(vals[i]) for i in range(n)]
    for i in range(n):
        l = 2 * i + 1
        r = 2 * i + 2
        if l < n:
            nodes[i].left = nodes[l]
        if r < n:
            nodes[i].right = nodes[r]
    return nodes[0]

def gen_full_tree(depth, vals):
    count = (1 << depth) - 1
    return gen_complete_tree(count, vals)

def gen_random_tree(n, vals):
    if n <= 0:
        return None
    root = TreeNode(vals[0])
    slots = [(root, 'left'), (root, 'right')]
    for i in range(1, n):
        idx = random.randint(0, len(slots) - 1)
        parent, side = slots.pop(idx)
        nxt = TreeNode(vals[i])
        if side == 'left':
            parent.left = nxt
        else:
            parent.right = nxt
        slots.append((nxt, 'left'))
        slots.append((nxt, 'right'))
    return root

def get_all_values(root):
    res = []
    def dfs(node):
        if not node:
            return
        res.append(node.val)
        dfs(node.left)
        dfs(node.right)
    dfs(root)
    return res

# Problem specific solver
def solve_problem(tree, prob_id, all_vals, c):
    if prob_id == 1:
        # Bottom View
        if not tree:
            return "", ""
        m = {}
        q = deque([(tree, 0)])
        while q:
            node, hd = q.popleft()
            m[hd] = node.val
            if node.left:
                q.append((node.left, hd - 1))
            if node.right:
                q.append((node.right, hd + 1))
        ans = [str(m[k]) for k in sorted(m.keys())]
        return serialize_tree(tree), " ".join(ans)

    elif prob_id == 2:
        # Right/Left View
        rv, lv = [], []
        def dfs_r(n, lvl):
            if not n: return
            if len(rv) == lvl: rv.append(str(n.val))
            dfs_r(n.right, lvl + 1)
            dfs_r(n.left, lvl + 1)
        def dfs_l(n, lvl):
            if not n: return
            if len(lv) == lvl: lv.append(str(n.val))
            dfs_l(n.left, lvl + 1)
            dfs_l(n.right, lvl + 1)
        dfs_r(tree, 0)
        dfs_l(tree, 0)
        return serialize_tree(tree), " ".join(rv) + "\n" + " ".join(lv)

    elif prob_id == 3:
        # Root to leaf paths
        paths = []
        cur = []
        def dfs(n):
            if not n: return
            cur.append(str(n.val))
            if not n.left and not n.right:
                paths.append(" ".join(cur))
            else:
                if n.left: dfs(n.left)
                if n.right: dfs(n.right)
            cur.pop()
        dfs(tree)
        return serialize_tree(tree), "\n".join(paths)

    elif prob_id == 4:
        # LCA
        if len(all_vals) == 1:
            p, q = all_vals[0], all_vals[0]
        elif c == 2:
            p, q = all_vals[0], all_vals[1]
        elif c == 3:
            p, q = all_vals[1], all_vals[0]
        else:
            p = random.choice(all_vals)
            q = random.choice(all_vals)
            while q == p and len(all_vals) > 1:
                q = random.choice(all_vals)
        def lca(n, a, b):
            if not n or n.val == a or n.val == b: return n
            l = lca(n.left, a, b)
            r = lca(n.right, a, b)
            if l and r: return n
            return l if l else r
        ans = lca(tree, p, q)
        inp = serialize_tree(tree) + "\n" + f"{p} {q}"
        out = str(ans.val) if ans else ""
        return inp, out

    elif prob_id == 5:
        # Max Width
        if not tree:
            return serialize_tree(tree), "0"
        max_w = 0
        q = deque([(tree, 0)])
        while q:
            sz = len(q)
            min_idx = q[0][1]
            first, last = 0, 0
            for i in range(sz):
                n, idx = q.popleft()
                cur_id = idx - min_idx
                if i == 0: first = cur_id
                if i == sz - 1: last = cur_id
                if n.left: q.append((n.left, 2 * cur_id + 1))
                if n.right: q.append((n.right, 2 * cur_id + 2))
            max_w = max(max_w, last - first + 1)
        return serialize_tree(tree), str(max_w)

    elif prob_id == 6:
        # Nodes at dist K
        target = random.choice(all_vals)
        if c % 10 == 1: k = 0
        elif c % 10 == 2: k = 1
        elif c % 10 == 3: k = 2
        elif c % 10 == 4: k = 3
        elif c % 10 == 5: k = 100
        else: k = random.randint(1, 6)

        # parent map
        pmap = {}
        tgt_node = None
        q = deque([tree])
        while q:
            curr = q.popleft()
            if curr.val == target: tgt_node = curr
            if curr.left:
                pmap[curr.left] = curr
                q.append(curr.left)
            if curr.right:
                pmap[curr.right] = curr
                q.append(curr.right)

        visited = {tgt_node}
        qd = deque([tgt_node])
        dist = 0
        while qd:
            if dist == k: break
            sz = len(qd)
            for _ in range(sz):
                curr = qd.popleft()
                for neighbor in [curr.left, curr.right, pmap.get(curr)]:
                    if neighbor and neighbor not in visited:
                        visited.add(neighbor)
                        qd.append(neighbor)
            dist += 1

        ans = sorted([n.val for n in qd])
        inp = serialize_tree(tree) + "\n" + str(target) + "\n" + str(k)
        out = " ".join(map(str, ans))
        return inp, out

    elif prob_id == 7:
        # Burn tree
        if c % 4 == 0: start = all_vals[0]
        elif c % 4 == 1: start = all_vals[-1]
        else: start = random.choice(all_vals)

        pmap = {}
        start_node = None
        q = deque([tree])
        while q:
            curr = q.popleft()
            if curr.val == start: start_node = curr
            if curr.left:
                pmap[curr.left] = curr
                q.append(curr.left)
            if curr.right:
                pmap[curr.right] = curr
                q.append(curr.right)

        visited = {start_node}
        fire_q = deque([start_node])
        time = 0
        while fire_q:
            sz = len(fire_q)
            burned = False
            for _ in range(sz):
                curr = fire_q.popleft()
                for neighbor in [curr.left, curr.right, pmap.get(curr)]:
                    if neighbor and neighbor not in visited:
                        visited.add(neighbor)
                        fire_q.append(neighbor)
                        burned = True
            if burned:
                time += 1
        inp = serialize_tree(tree) + "\n" + str(start)
        return inp, str(time)

    elif prob_id == 8:
        # Count complete tree nodes
        def get_lh(n):
            h = 0
            while n: h += 1; n = n.left
            return h
        def get_rh(n):
            h = 0
            while n: h += 1; n = n.right
            return h
        def cnt(n):
            if not n: return 0
            lh, rh = get_lh(n), get_rh(n)
            if lh == rh: return (1 << lh) - 1
            return 1 + cnt(n.left) + cnt(n.right)
        return serialize_tree(tree), str(cnt(tree))

    elif prob_id == 9:
        # Flatten tree
        ans = []
        def preorder(n):
            if not n: return
            ans.append(str(n.val))
            preorder(n.left)
            preorder(n.right)
        preorder(tree)
        return serialize_tree(tree), " ".join(ans)

def generate_all():
    random.seed(42000 + 7 * 777)
    base = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    inp_dir = os.path.join(base, "Testcases", "input")
    out_dir = os.path.join(base, "Testcases", "output")
    os.makedirs(inp_dir, exist_ok=True)
    os.makedirs(out_dir, exist_ok=True)

    print(f"Generating 105 testcases for Problem 7...")
    for c in range(1, 106):
        if 7 == 8:
            if c <= 7: n = c
            elif c <= 15: n = c
            elif c <= 30: n = random.randint(16, 63)
            elif c <= 60: n = random.randint(64, 255)
            elif c <= 85: n = random.randint(256, 600)
            else: n = random.randint(601, 1000)
            vals = list(range(1, n + 1))
            tree = gen_complete_tree(n, vals)
        else:
            if c == 1:
                tree = gen_single_node(1)
            elif c == 2:
                tree = gen_skewed(2, 'left', [1, 2])
            elif c == 3:
                tree = gen_skewed(2, 'right', [1, 3])
            elif c == 4:
                tree = gen_complete_tree(3, [1, 2, 3])
            elif c == 5:
                tree = gen_skewed(3, 'left', [1, 2, 3])
            elif c == 6:
                vals = random.sample(range(10, 100), 6)
                tree = gen_skewed(6, 'left', vals)
            elif c == 7:
                vals = random.sample(range(10, 100), 7)
                tree = gen_skewed(7, 'right', vals)
            elif c == 8:
                vals = random.sample(range(10, 100), 8)
                tree = gen_zigzag(8, 'left', vals)
            elif c == 9:
                vals = random.sample(range(10, 100), 9)
                tree = gen_zigzag(9, 'right', vals)
            elif c <= 15:
                d = (c % 3) + 2
                cnt = (1 << d) - 1
                vals = random.sample(range(1, 500), cnt)
                tree = gen_full_tree(d, vals)
            elif c <= 25:
                n = random.randint(5, 20)
                vals = random.sample(range(-1000, -1), n)
                tree = gen_random_tree(n, vals)
            elif c <= 35:
                n = random.randint(7, 25)
                vals = [0] + random.sample(range(-500, 500), n - 1)
                tree = gen_random_tree(n, vals)
            elif c <= 50:
                n = random.randint(10, 30)
                vals = random.sample(range(1, 2000), n)
                tree = gen_random_tree(n, vals)
            elif c <= 75:
                n = random.randint(30, 70)
                vals = random.sample(range(1, 5000), n)
                tree = gen_random_tree(n, vals)
            elif c <= 95:
                n = random.randint(80, 150)
                vals = random.sample(range(1, 50000), n)
                tree = gen_random_tree(n, vals)
            else:
                n = random.randint(160, 300)
                vals = random.sample(range(1, 200000), n)
                tree = gen_random_tree(n, vals)

        all_vals = get_all_values(tree)
        inp, out = solve_problem(tree, 7, all_vals, c)

        idx_str = f"{c:03d}"
        with open(os.path.join(inp_dir, f"input{idx_str}.txt"), "w", encoding="utf-8") as f:
            f.write(inp.strip() + "\n")
        with open(os.path.join(out_dir, f"output{idx_str}.txt"), "w", encoding="utf-8") as f:
            f.write(out.strip() + "\n")

    print(f"Done generating 105 testcases for Problem 7!")

if __name__ == "__main__":
    generate_all()
