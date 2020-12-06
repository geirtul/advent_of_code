import re
# Read inputs
with open("test.txt", "r") as fp:
    lines = [line.strip() for line in fp]
    test = " ".join(lines).split('  ')

with open("test2.txt", "r") as fp:
    lines = [line.strip() for line in fp]
    test2 = " ".join(lines).split('  ')

with open("input.txt", "r") as fp:
    lines = [line.strip() for line in fp]
    data = " ".join(lines).split('  ')

# Process data


def process_data(data):
    """Extract key: value pairs from passport data"""
    processed = []
    for s in data:
        # s[:-1] accounts for added space at end of each pp line
        a = [tmp.split(":") for tmp in s.split(" ")]
        tmp_dict = {}
        for el in a:
            tmp_dict[el[0]] = el[1]
        processed.append(tmp_dict)

    return processed


def validate_passport(passport):
    must_include = [
        "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"
    ]
    for field in must_include:
        if field not in passport.keys():
            return False
    return True


def validate_passport_and_fields(passport):
    must_include = [
        "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"
    ]

    for field in must_include:
        if field not in passport.keys():
            return False
        else:
            # Check field for rules
            tmp = passport[field]
            if field == "byr":
                if len(tmp) != 4 or int(tmp) < 1920 or int(tmp) > 2002:
                    return False
            if field == "iyr":
                if len(tmp) != 4 or int(tmp) < 2010 or int(tmp) > 2020:
                    return False
            if field == "eyr":
                if len(tmp) != 4 or int(tmp) < 2020 or int(tmp) > 2030:
                    return False
            if field == "hgt":
                check = re.findall(r"(\d{2,3})(in|cm)", tmp)
                if not check:
                    return False
                else:
                    num = int(check[0][0])
                    unit = check[0][1]
                    if unit == "cm" and (num < 150 or num > 193):
                        return False
                    if unit == "in" and (num < 59 or num > 76):
                        return False
            if field == "hcl":
                if re.search(r"#[0-9]{0,6}[a-z]{0,6}", tmp) is None:
                    return False
            if field == "ecl":
                valid_colours = [
                    "amb", "blu", "brn", "gry", "grn", "hzl", "oth"
                ]
                if tmp not in valid_colours:
                    return False
            if field == "pid":
                if re.search(r"^\d{9}$", tmp) is None:
                    return False

    return True


print("Part 1:")
checked_test = [validate_passport(p) for p in process_data(test)]
checked_data = [validate_passport(p) for p in process_data(data)]
print("Test:", sum(checked_test))
print("Data:", sum(checked_data))

print("Part 2:")  # 122 too high
checked_test_two = [
    validate_passport_and_fields(p) for p in process_data(test2)
]
checked_data_two = [
    validate_passport_and_fields(p) for p in process_data(data)
]
print("Test:", sum(checked_test_two))
print("Data:", sum(checked_data_two))
