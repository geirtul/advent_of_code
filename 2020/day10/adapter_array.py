import numpy as np
import json
test = sorted([int(line.strip()) for line in open("test.txt")])
stest = sorted([int(line.strip()) for line in open("smalltest.txt")])
data = sorted([int(line.strip()) for line in open("input.txt")])

# Add outlet and device adapter
test = np.array([0] + test + [max(test) + 3])
stest = np.array([0] + stest + [max(stest) + 3])
data = np.array([0] + data + [max(data) + 3])

test_u, test_c = np.unique(test[:-1] - test[1:], return_counts=True)
stest_u, stest_c = np.unique(
    stest[:-1] - stest[1:], return_counts=True)
data_u, data_c = np.unique(data[:-1] - data[1:], return_counts=True)
print("===== Part 1")
print("Small test unique, counts:", stest_u, stest_c)
print("Test unique, counts:", test_u, test_c)
print("Data unique, counts:", data_u, data_c)


def total_valid_perms(data):
    total_valid = {}
    data = [*reversed(data)]
    for i in range(len(data) - 2):
        total_valid[int(data[i])] = []
        for j in range(i + 1, len(data) - 1):
            if abs(data[j] - data[i]) < 4:
                total_valid[int(data[i])].append(int(data[j]))
    perms = 1
    for v in total_valid.values():
        if len(v) > 1:
            perms += len(v)
    print(json.dumps(total_valid, indent=4))
    return perms


print(total_valid_perms(stest))
