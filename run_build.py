#!/usr/bin/env python3
import argparse
import subprocess
import sys

def main():
    parser = argparse.ArgumentParser(description="Invoke Ant build for MyInfArith project.")
    parser.add_argument(
        "--target", default="run",
        help="Ant target to execute (clean, compile, jar, run, etc.)"
    )
    parser.add_argument(
        "--args", default="",
        help="Arguments to pass into the Java program, e.g. 'int add 1 1'"
    )
    args = parser.parse_args()

    ant_cmd = ["ant", args.target]

    if args.target == "run":
        ant_cmd = ["ant", "clean", "compile", "jar", "run"]

    if args.args:
        ant_cmd.append(f"-Dargs={args.args}")

    print(f"Executing: {' '.join(ant_cmd)}")
    result = subprocess.run(ant_cmd)
    if result.returncode != 0:
        print(f"Ant exited with code {result.returncode}", file=sys.stderr)
        sys.exit(result.returncode)

if __name__ == '__main__':
    main()
