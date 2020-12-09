test = [int(line.strip()) for line in open("test.txt")]
data = [int(line.strip()) for line in open("input.txt")]


def solve_one(data, psize):

    # init valids
    valids = []
    idx = 0
    for i in range(idx, idx + psize - 1):
        tmp = []
        for j in range(i + 1, idx + psize):
            if data[i] != data[j]:
                tmp.append(data[i] + data[j])
        valids.append(tmp)
    while True:
        notfound = True
        for valid in valids:
            if data[idx + psize] in valid:
                notfound = False
                break
        if notfound:
            return data[idx + psize]
        idx += 1
        # update valids
        valids = valids[1:]
        tmp = []
        for el in data[idx:idx + psize - 1]:
            if el != data[idx + psize - 1]:
                tmp.append(el + data[idx + psize - 1])
        valids.append(tmp)


def solve_two(data, num):
    for i in range(len(data)):
        added = [data[i]]
        curr_sum = data[i]
        for j in range(i + 1, len(data)):
            curr_sum += data[j]
            added.append(data[j])
            if curr_sum == num:
                return min(added) + max(added)


print("===== Part 1")
test_one = solve_one(test, 5)
data_one = solve_one(data, 25)
print("Test:", test_one)
print("Data:", data_one)
print("===== Part 2")
print("Test:", solve_two(test, test_one))
print("Test:", solve_two(data, data_one))
