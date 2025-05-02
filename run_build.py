import subprocess
import os
import sys

def exec(command):
    result = subprocess.run(command, check=True)
    if result.returncode != 0:
        sys.exit(1)

def building(target):
    build_commands = {
        "build": ["ant", "clean", "compile", "jar"],
        "clean": ["ant", "clean"],
        "compile": ["ant", "compile"],
        "jar": ["ant", "jar"]
    }
    if target in build_commands:
        exec(build_commands[target])
        return True
    return False

def running(dtype, operation, op1, op2):
    if dtype not in ("int", "float") or operation not in ("add", "sub", "mul", "div"):
        sys.exit(1)

    try:
        op1 = float(op1) if dtype == "float" else int(op1)
        op2 = float(op2) if dtype == "float" else int(op2)
    except ValueError:
        sys.exit(1)

    arguments = f"{dtype} {operation} {op1} {op2}"

    if not os.path.isdir(os.path.join(os.path.dirname(__file__), "build")):
        exec(["ant", "clean", "compile", "jar"])

    exec(["ant", f"-Dargs={arguments}", "run"])

def main():
    build_file_path = os.path.join(os.path.dirname(__file__), "build.xml")
    if not os.path.exists(build_file_path):
        sys.exit(1)

    if len(sys.argv) == 2:
        target = sys.argv[1]
        if not building(target):
            sys.exit(1)

    elif len(sys.argv) == 5:
        dtype, operation, op1, op2 = sys.argv[1:]
        running(dtype, operation, op1, op2)

    else:
        sys.exit(1)

if __name__ == "__main__":
    main()
