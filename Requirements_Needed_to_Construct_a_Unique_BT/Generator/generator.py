import os
import random

def main():
    base = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
    inp_dir = os.path.join(base, "Testcases", "input")
    out_dir = os.path.join(base, "Testcases", "output")
    os.makedirs(inp_dir, exist_ok=True)
    os.makedirs(out_dir, exist_ok=True)
    print("Generator for Requirements Needed to Construct a Unique BT ready.")

if __name__ == "__main__":
    main()
