from collections import Counter

# Test data part 1
'''
test1_real = "aaaaa-bbb-z-y-x-123[abxyz]"
test2_real = "a-b-c-d-e-f-g-h-987[abcde]"
test3_real = "not-a-real-room-404[oarel]"
test4_false = "totally-real-room-200[decoy]"
test5_false = "should-not-be-real-387[sgthi]"
test6_real = "aa-bbb-c-dddd-e-001[dbace]"
test7_false = "ff-l-bbb-c-dddd-e-002[dbfel]"
test8_false = "vagreangvbany-enoovg-fuvccvat-533[gncot]"
tests = [test1_real, test2_real, test3_real, test4_false,
		 test5_false, test6_real, test7_false, test8_false]
'''

# Test data part 2
teststreng = ["qzmt","zixmtkozy","ivhz","343","abcde"]
testsvar = "very encrypted name"

# Input data
infile = open("advent_input.txt").read().split("\n")
del infile[-1]

# Set up input data
lines = []
for line in infile:
	lines.append(line.split("-"))
for line in lines:
	last = line[-1]
	del line[-1]
	line.append(last.split("[")[0])
	line.append(last.split("[")[1][:-1])

# Set up test data
'''
testcase = []
for line in tests:
	testcase.append(line.split("-"))
for line in testcase:
	last = line[-1]
	del line[-1]
	line.append(last.split("[")[0])
	line.append(last.split("[")[1][:-1])
'''

def shift(letter,number):
	current = ord(letter)
	new = current + int(number)
	while new > 122:
		new = new - 26
	return chr(new)
def wordconverter(word,number):
	new_word = ""
	for i in word:
		new_word = new_word + shift(i,number)
	return new_word

def real_test(name):
	checksum = name[-1]
	sector_id = int(name[-2])
	namestring = ""
	for i in range(len(name)-2):
		namestring = namestring + name[i]
	c = Counter(namestring)
	# Check if most_common chars are in checksum, if not return 0
	checked = []
	for i in checksum:
		checked.append(i)
		for key in c:
			if c[key] > c[i] and key not in checked:
				return 0
	# Check if checksum is in correct order
	for i in range(4):
		if c[checksum[i]] < c[checksum[i+1]]:
			return 0
	# Remove checksum from string and check if any char in remaining string has
	# equal value to a checksum value and comes before checksum value in alphabet.
	newstring = "".join(letter for letter in namestring if letter not in checksum)
	for i in checksum:
		for j in newstring:
			if c[i] == c[j] and ord(j) < ord(i): 
				return 0
	return sector_id



# Iterating for solution 1
'''
sector_sum = 0
false = 0
for name in lines:
	test = real_test(name)
	if test == 0: false += 1
	else: sector_sum += test
print(sector_sum)
print(false)
'''
'''
print("--------TESTCASE--------")
testsum = 0
testfalse = 0
for name in testcase:
	test = real_test(name)
	if test == 0: testfalse += 1
	else: testsum += test
print(testsum)
print(testfalse)
'''

# Iterating for solution 2
rooms = []
for line in lines:
	shiftnum = line[-2]
	roomname = []
	for i in range(len(line)-2):
		roomname.append(wordconverter(line[i], shiftnum))
	rooms.append(list(roomname))
	if "northpole" in roomname:
		print(shiftnum)

# Look for correct name
'''
possible=[]
for name in rooms:
	if "storage" in name:
		possible.append(name)
for el in possible:
	print(el)
'''
'''
testshift = teststreng[-2]
for i in range(len(line)-2):
	print(wordconverter(teststreng[i], testshift))
'''

