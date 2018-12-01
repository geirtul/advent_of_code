import numpy as np
import matplotlib.pyplot as plt
with open("input.txt", "r") as infile:
    data = infile.readlines()
frequencies = [int(line) for line in data]

seen = {}
seen[0] = 0

iterations = 0
curr_freq = 0

while True:
    for freq in frequencies:
        curr_freq += freq
        if curr_freq in seen:
            print("Found: ", curr_freq)
            exit(1)
        seen[curr_freq] = curr_freq
    iterations += 1
    print("Iterations: ", iterations)
