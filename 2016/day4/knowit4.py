num = [i for i in range(1,1338,1)]
counter = 0
def seven(number):
	if "7" in str(number) or number % 7 == 0:
		return True
	else: 
		return False
for j in range(10):
	for i in range(len(num)):
		if seven(num[i]) is True:
			counter += 1
			num[i] = counter
	counter = 0

print("The lucky number is: ",num[1336])
