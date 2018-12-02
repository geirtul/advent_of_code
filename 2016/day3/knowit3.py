infile = open("knowit_input.txt").read().split()
for line in infile:
	for i in line:
		if i != " ": continue
		if i == " " and i+1 != " ": continue
		else: del i
lines = [[infile[i], infile[i+1], infile[i+2]] for i in range(0,len(infile)-2,3)]
names= {}
hates = {}
for line in lines:
	if line[0] == "friends":
		if line[1] not in names: names[line[1]] = [line[2]]
		else: names[line[1]].append(line[2])
		if line[2] not in names: names[line[2]] = [line[1]]
		else: names[line[2]].append(line[1])
	if line[1] == "hates":
		if line[0] not in hates: hates[line[0]] = [line[2]]
		else: hates[line[0]].append(line[2])

count = [0,"name"]
for key in names:
	if key in hates:
		hatecount = 0
		for name in hates[key]:
			if name in names[key] and key not in hates[name]: hatecount +=1
		if hatecount > count[0]:
			count[0] = hatecount
			count[1] = key

print(count)
