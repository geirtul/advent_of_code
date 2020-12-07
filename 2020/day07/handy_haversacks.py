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
    if searchname in bag.keys():
        return 1
    elif len(bag.keys()) == 0:
        return 0
    else:
        for k in bag.keys():
            return next_bag(root, root[k], searchname)


test_bags = organize_bags(test)
data_bags = organize_bags(data)

print("====== Part 1")
count_test = 0
for k in test_bags.keys():
    count_test += next_bag(test_bags, test_bags[k], "shiny gold")
print("Test:", count_test)

count_data = 0
for k in data_bags.keys():
    count_data += next_bag(data_bags, data_bags[k], "shiny gold")
print("Data:", count_data)
