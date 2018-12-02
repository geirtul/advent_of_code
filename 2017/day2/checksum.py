import numpy as np

# Function defs

def checksum(spreadsheet):
    '''
    Calculates the difference between largest and smallest value in rows of spreadsheet.
    '''
    totsum = 0
    for el in spreadsheet:
        tmp = np.array(el)
        totsum += np.amax(el)-np.amin(el)

    return totsum

def divisible(spreadsheet):
    '''
    Finds the evenly divisible ints in row of spreadsheet and returns sum of results.
    '''
    totsum = 0
    for el in spreadsheet:
        for i in range(len(el)):
            for j in range(len(el)):
                if el[i]%el[j] == 0 and i != j:
                    totsum += el[i]//el[j]

    return totsum


# Read file and convert to ints.

with open("input.txt") as file:
    data = file.readlines()

numbers = []
for el in data:
    numbers.append([int(c) for c in el.split()])

print("Part 1: ", checksum(numbers))
print("Part 2: ", divisible(numbers))

