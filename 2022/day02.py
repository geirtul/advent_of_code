with open("inputs/input02.txt") as f:
    lines = [line.strip().replace(" ", "") for line in f]


# Results lookup table
# A, X = Rock
# B, Y = Paper
# C, Z = Scissors

possible_results = {
    "AX": 1 + 3,
    "AY": 2 + 6,
    "AZ": 3 + 0,
    "BX": 1 + 0,
    "BY": 2 + 3,
    "BZ": 3 + 6,
    "CX": 1 + 6,
    "CY": 2 + 0,
    "CZ": 3 + 3,
}

# Hand selector for part 2 strategy
hand_selector = {
    "AX": "AZ",
    "AY": "AX",
    "AZ": "AY",
    "BX": "BX",
    "BY": "BY",
    "BZ": "BZ",
    "CX": "CY",
    "CY": "CZ",
    "CZ": "CX",
}

def calculate_result(hands: list) -> int:
    """Calculate the result of one round of
    rock, paper, scissors.
    """
    return possible_results[hands]



results = [calculate_result(line) for line in lines]
# Part 1
print("Part 1:", sum(results))

results = [calculate_result(hand_selector[line]) for line in lines]
# Part 2
print("Part 2:", sum(results))


