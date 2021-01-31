import numpy as np
from scipy.ndimage import convolve


grid_converter = str.maketrans('.L#', '012')
with open('test.txt') as fp:
    test = np.array([[int(x) for x in list(r.translate(grid_converter))]
                     for r in fp.read().splitlines()])
with open('input.txt') as fp:
    data = np.array([[int(x) for x in list(r.translate(grid_converter))]
                     for r in fp.read().splitlines()])

k = np.array([
    ["1", "1", "1"],
    ["1", "0", "1"],
    ["1", "1", "1"]]
)

curr = data.copy()
while True:
    prev = np.copy(curr)
    result = convolve(np.where(curr == 2, 1, 0), k, mode='constant')
    curr[(curr == 1) & (result == 0)] = 2
    curr[(curr == 2) & (result >= 4)] = 1
    if (prev == curr).all():
        break
part1 = np.count_nonzero(curr == 2)

print("===== Part 1")
print(part1)
