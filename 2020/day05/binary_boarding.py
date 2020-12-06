test = [list(line.strip()) for line in open("test.txt", "r")]
data = [list(line.strip()) for line in open("input.txt", "r")]


def check_pass(bpass):
    row_low = 0
    row_high = 128
    row_diff = 128
    col_low = 0
    col_high = 8
    col_diff = 8

    for el in bpass[:7]:
        if el == "F":
            row_high -= row_diff // 2
        else:
            row_low += row_diff // 2
        row_diff = row_high - row_low
    row_high -= 1

    for el in bpass[7:]:
        if el == "L":
            col_high -= col_diff // 2
        else:
            col_low += col_diff // 2
        col_diff = col_high - col_low
    col_high -= 1

    seat_id = row_low * 8 + col_low
    return row_low, col_low, seat_id


test_results = [check_pass(p) for p in test]
test_ids = [el[2] for el in test_results]
data_results = [check_pass(p) for p in data]
data_ids = [el[2] for el in data_results]

print("Part 1:")
print("Test:", max(test_ids))
print("Data:", max(data_ids))

print("Part 2:")
print("Data:", set(range(min(data_ids), max(data_ids))) - set(data_ids))
