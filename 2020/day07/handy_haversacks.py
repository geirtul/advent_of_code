import re
import json


test = [line.strip() for line in open("test.txt")]
data = [line.strip() for line in open("input.txt")]

contain_regex = r"(\d+) (\w+ \w+)"
bag_colour_regex = r"^(\w+ \w+)"


def organize_bags(data):
    bags = {}
    for line in data:
        bag_colour = re.search(bag_colour_regex, line).group(0)
        can_contain = re.findall(contain_regex, line)
        if bag_colour not in bags:
            bags[bag_colour] = {}
        if can_contain is None:
            continue
        else:
            for match in can_contain:
                bags[bag_colour][match[1]] = int(match[0])

    return bags


def next_bag(root, bag, searchname):
    if len(bag.keys()) == 0:
        return 0
    if searchname in bag.keys():
        return 1
    found = 0
    for k in bag.keys():
        try:
            found += next_bag(root, root[k], searchname)
        except TypeError:
            continue
    if found != 0:
        return 1


def bag_sum(root, bag):
    tot = 0
    if len(bag.keys()) != 0:
        for k in bag.keys():
            tot += bag[k] + bag[k] * bag_sum(root, root[k])
    return tot


test_bags = organize_bags(test)
data_bags = organize_bags(data)

print("====== Part 1")
count_test = 0
for k in test_bags.keys():
    try:
        count_test += next_bag(test_bags, test_bags[k], "shiny gold")
    except TypeError:
        continue
print("Test:", count_test)

count_data = 0
for k in data_bags.keys():
    try:
        count_data += next_bag(data_bags, data_bags[k], "shiny gold")
    except TypeError:
        continue
print("Data:", count_data)

print("====== Part 2")
print("Test:", bag_sum(test_bags, test_bags["shiny gold"]))
print("Data:", bag_sum(data_bags, data_bags["shiny gold"]))
