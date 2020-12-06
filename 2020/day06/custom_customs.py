test = [line.strip() for line in open("test.txt", "r")]
data = [line.strip() for line in open("input.txt", "r")]


def separate_groups(data):
    groups = [[]]
    for el in data:
        if el != "":
            groups[-1].append(el)
        else:
            groups.append([])
            continue
    return groups


def everyone_yes(groups):
    total = 0
    for g in groups:
        sets = []
        for p in g:
            sets.append(set(p))
        intersection = set.intersection(*sets)
        total += len(intersection)
    return total


test_groups = separate_groups(test)
data_groups = separate_groups(data)

print("Part 1:")
test_yes = [set("".join(g)) for g in test_groups]
data_yes = [set("".join(g)) for g in data_groups]
print("Test:", sum(len(x) for x in test_yes))
print("Data:", sum(len(x) for x in data_yes))

print("\nPart 2:")
print("Test:", everyone_yes(test_groups))
print("Test:", everyone_yes(data_groups))
