import numpy as np
infile = np.loadtxt("advent_input", dtype="int")
possible = 0
possible2 = 0
for i in range(len(infile)):
	test = np.sort(infile[i])
	if len(test) == 3 and test[0] + test[1] > test[2]:
		possible += 1
print("Part 1: ", "Possible triangles = ", possible)
for i in range(0,len(infile) -2,3):
	test0, test1, test2 = [], [], []
	for j in range(3):
		test0.append(int(infile[i+j,0]))
		test1.append(int(infile[i+j,1]))
		test2.append(int(infile[i+j,2]))
	test0, test1, test2 = np.sort(np.array(test0)),np.sort(np.array(test1)),np.sort(np.array(test2))
	if len(test0) == 3 and test0[0] + test0[1] > test0[2]: possible2 += 1
	if len(test1) == 3 and test1[0] + test1[1] > test1[2]: possible2 += 1
	if len(test2) == 3 and test2[0] + test2[1] > test2[2]: possible2 += 1
print("Part 2: ", "Possible triangles = ", possible2)

