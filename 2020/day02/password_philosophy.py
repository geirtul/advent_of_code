import re

test = [line.strip() for line in open("test.txt", "r")]
data = [line.strip() for line in open("input.txt", "r")]


def separate_input(input_string):
    return re.findall(r"(\d+)-(\d+)\s(\w): (\w+)", input_string)


def count_policy(data):
    valid = 0
    for pw in data:
        matches = separate_input(pw)
        low, high, c, s = matches[0]
        if int(low) <= s.count(c) <= int(high):
            valid += 1
    return valid


def position_policy(data):
    valid = 0
    for pw in data:
        matches = separate_input(pw)
        low, high, c, s = matches[0]
        if sum([s[int(low) - 1] == c, s[int(high) - 1] == c]) == 1:
            valid += 1
    return valid


print("Part 1:")
print("Test result:", count_policy(test))
print("Input result:", count_policy(data), "\n")
print("Part 2:")
print("Test result:", position_policy(test))
print("Input result:", position_policy(data))
