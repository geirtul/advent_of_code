lines = open("advent_input").read().split("\n")
del lines[-1] # Remove empty element after \n split
keypad1 = [[1,2,3],[4,5,6],[7,8,9]]
keypad2 = [[0,0,1,0,0],[0,2,3,4,0],[5,6,7,8,9],[0,11,12,13,0],[0,0,14,0,0]] 
key1 = [2,2] # Start at key 5
key2 = [2,0] # ^
for line in lines:
	for move in line:
		if move == "U" and key1[0] != 0:	key1[0] -= 1
		else: pass
		if move == "D" and key1[0] != 2:	key1[0] += 1
		else: pass
		if move == "L" and key1[1] != 0:	key1[1] -= 1
		else: pass
		if move == "R" and key1[1] != 2:	key1[1] += 1
		else: pass
	print(keypad1[key1[0]][key1[1]])
for line in lines:
	for move in line:
		if move == "U" and keypad2[key2[0]][key2[1]] not in [1,2,4,5,9]: key2[0] -= 1
		else: pass
		if move == "D" and keypad2[key2[0]][key2[1]] not in [5,9,11,13,14]: key2[0] += 1
		else: pass
		if move == "L" and keypad2[key2[0]][key2[1]] not in [1,2,5,11,14]: key2[1] -= 1
		else: pass
		if move == "R" and keypad2[key2[0]][key2[1]] not in [1,4,9,13,14]: key2[1] += 1
		else: pass
	print(keypad2[key2[0]][key2[1]])
