import re
#with open("input.txt", "r") as file:
#    data = file.readlines()

with open("test.txt", "r") as file:
    data = file.readlines()

regex1 = r"(\w+) \((\d+)\))))" # picks out keys and number before ->
regex2 = r"[ ,](\w+))]" # Picks out keys behind ->

newlines = []
for line in data:
    newlines.append([re.findall(regex1,line)]+[re.findall(regex2,line)])

for line in newlines:
    print(line)

