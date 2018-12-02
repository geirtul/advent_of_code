import hashlib
puzzlin = "abbhdwsy"
testin = "abc"
number = 3231929
test = testin + str(number)
solution=[]
solution2 = [[],[],[],[],[],[],[],[]]
for i in range(0, 30000000, 1):
	temp = puzzlin
	temp = temp + str(i)
	temp = temp.encode("utf-8")
	crypt = hashlib.md5(temp)
	hashtag = crypt.hexdigest()
	count = 0
	for j in range(0,len(hashtag),1):
		if hashtag[j] != "0": break
		else: count += 1
		if count == 5:
			solution.append(hashtag[j+1])
			try:
				index = int(hashtag[j+1])
			except ValueError: continue
			if index <= 7 and len(solution2[index]) == 0:
				solution2[index].append(hashtag[j+2])
print("Sol 1 | Sol 2")
for i in range(8):
	print(solution[i],"   ", solution2[i])
