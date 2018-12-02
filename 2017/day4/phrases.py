
with open("input.txt") as file:
    data = file.readlines()

def test1(datafile, part):

    valid = 0
    if part == 1:
        inputs = [line.split() for line in datafile]
        lines = inputs
        
    if part == 2:
        inputs = [line.split() for line in datafile]
        lines = []
        for line in inputs:
            lines.append([''.join(sorted(word)) for word in line])
            
    for line in lines:
        correct = 0
        for word in line:
            if line.count(word) == 1:
                correct += 1
        if correct == len(line):
            valid += 1

    return valid



print(test1(data,1))
print(test1(data,2))

    

