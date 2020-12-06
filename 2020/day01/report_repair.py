import sys


if sys.argv[1] == "1":
    if len(sys.argv) == 3 and sys.argv[2] == "test":
        a = [1721, 979, 366, 299, 675, 1456]
    else:
        a = [int(line.strip()) for line in open("input.txt", "r")]
    for i in range(len(a) - 2):
        for j in range(i + 1, len(a) - 1):
            if a[i] + a[j] == 2020:
                print(a[i] * a[j])

if sys.argv[1] == "2":
    if len(sys.argv) == 3 and sys.argv[2] == "test":
        a = [1721, 979, 366, 299, 675, 1456]
    else:
        a = [int(line.strip()) for line in open("input.txt", "r")]

    for i in range(len(a) - 3):
        for j in range(i + 1, len(a) - 2):
            for k in range(j + 1, len(a) - 1):
                if a[i] + a[j] + a[k] == 2020:
                    print(a[i] * a[j] * a[k])
