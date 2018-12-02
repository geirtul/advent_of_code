from matplotlib import pyplot as plt
o, a_pos, c_pos = 0, [], [0,0]
for move in open("advent_input").read().split(", "):
	if move[0] == "R":
		o = 0 if o == 3 else o + 1
	if move[0] == "L":	
		o = 3 if o == 0 else o - 1
	if o in [1,3]:
		c_pos[0] += int(move[1:]) if o == 1 else -int(move[1:])
	if o in [0,2]:
		c_pos[1] += int(move[1:]) if o == 0 else -int(move[1:])
	a_pos.append(list(c_pos))
print("Part 1 answer = ", abs(c_pos[0])+abs(c_pos[1]))
x, y = [], []
for el in a_pos:
	x.append(int(el[0]))
	y.append(int(el[1]))
plt.plot(x,y)
plt.show()
