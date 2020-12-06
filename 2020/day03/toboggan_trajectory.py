import numpy as np
test = np.array([list(line.strip()) for line in open("test.txt", "r")]) == "#"
test = test.astype(np.int32)
data = np.array([list(line.strip()) for line in open("input.txt", "r")]) == "#"
data = data.astype(np.int32)


def gen_coords(direction, data):
    n_steps = data.shape[0] // direction[1] - 1
    indices = np.zeros((n_steps, 2), dtype=np.int32)
    indices += direction
    indices *= np.arange(n_steps).reshape(n_steps, 1) + 1
    indices[:, 0] -= (indices[:, 0] // (data.shape[1])) * (data.shape[1])
    indices[indices[:, 0] > data.shape[1], 0] -= 1
    return indices


def count_trees(coords, data):
    trees = 0
    for coord in coords:
        trees += data[coord[1], coord[0]]
    return trees


p1_dir = (3, 1)
print("Part 1:")
print("Test:", count_trees(gen_coords(p1_dir, test), test))
print("Data:", count_trees(gen_coords(p1_dir, data), data))
print("Part 2:")
p2_dirs = [
    (1, 1),
    (3, 1),
    (5, 1),
    (7, 1),
    (1, 2)
]
total_test = 1
total_data = 1
for p2_dir in p2_dirs:
    total_test *= count_trees(gen_coords(p2_dir, test), test)
    total_data *= count_trees(gen_coords(p2_dir, data), data)
print("Test:", total_test)
print("Data:", total_data)
