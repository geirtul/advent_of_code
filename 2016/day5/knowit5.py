infile = open("knowit_input.txt").read().split(",")
liste = [n.strip() for n in infile]

numerals = {"0":0, "I":1, "II":2, "III":3, "IV":4, "V":5, "VI":6, "VII":7, "VIII":8, "IX":9, "X":10,
			"XI":11, "XII":12,"XIII":13}
def numeralconv(romanum):
	return numerals[romanum]
converted = []
for roman in liste:
	converted.append(numeralconv(roman))

sums = []
count = 0
for i in range(0,int(len(converted)/2),1):
	sums.append(converted[i]+converted[-(i+1)])
message=[]
for el in sums:
	message.append(chr(el+96))
print(converted[0]," ",converted[-1])
print(sums[0])

lyrikk = ""
for letter in message:
	lyrikk = lyrikk + letter

print(lyrikk)
