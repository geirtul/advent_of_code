import numpy as np

data = np.loadtxt("input.txt", dtype=int)

steps = 0
pos = 0
while pos >= 0 and pos <= len(data)-1:
    new_pos = pos + data[pos]

    # Comment if-else block for part 1
    if data[pos] >= 3:
        data[pos] -= 1
    else:
        data[pos] += 1
    #data[pos] += 1 # uncomment for part 1
    pos = new_pos
    steps+=1

print(steps)



