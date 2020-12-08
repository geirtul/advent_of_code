test = [line.strip().split(" ") for line in open("test.txt")]
data = [line.strip().split(" ") for line in open("input.txt")]


def run_instructions(data):
    curr_idx = 0
    accumulator = 0
    visited = []

    while curr_idx not in visited:
        instr = data[curr_idx][0]
        visited.append(curr_idx)
        if instr == "nop":
            curr_idx += 1
        if instr == "acc":
            accumulator += int(data[curr_idx][1])
            curr_idx += 1
        if instr == "jmp":
            curr_idx += int(data[curr_idx][1])
    return accumulator, curr_idx


def fix_instructions(data):
    curr_idx = 0
    accumulator = 0
    visited = []
    changed = []
    changed_this_iter = False
    while curr_idx != len(data):
        instr = data[curr_idx][0]
        visited.append(curr_idx)
        if instr == "nop":
            if not changed_this_iter and curr_idx not in changed:
                # try changing the instruction to jmp
                changed.append(curr_idx)
                changed_this_iter = True
                curr_idx += int(data[curr_idx][1])
            else:
                curr_idx += 1
        if instr == "acc":
            accumulator += int(data[curr_idx][1])
            curr_idx += 1
        if instr == "jmp":
            if not changed_this_iter and curr_idx not in changed:
                # try changing the instruction to jmp
                changed.append(curr_idx)
                changed_this_iter = True
                curr_idx += 1
            else:
                curr_idx += int(data[curr_idx][1])
        # Check if loop should be reset
        if curr_idx in visited:
            curr_idx = 0
            changed_this_iter = False
            accumulator = 0
            visited = []
    return accumulator


print("===== Part 1")
print("Test:", run_instructions(test))
print("Data:", run_instructions(data))

print("===== Part 2")
print("Test:", fix_instructions(test))
print("Data:", fix_instructions(data))
