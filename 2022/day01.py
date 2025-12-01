with open("inputs/input01.txt") as f:
    lines = [line.strip() for line in f]


elf_caloric_sum = []
curr_sum = 0
for line in lines:
    if line == '':
        elf_caloric_sum.append(curr_sum)
        curr_sum = 0
    else:
        curr_sum += int(line)

elf_caloric_sum.append(curr_sum)

# Part 1
print(max(elf_caloric_sum))

# Part 2
print(sum(sorted(elf_caloric_sum)[-3:]))


