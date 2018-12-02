condition = "solved"
status = "unsolved"
i = 100
while status != condition:
	i_string = str(i)
	if i_string.endswith("6"):
		i_string = i_string[:-1]
		i_string = "6" + i_string
		if int(i_string) == 4*i:
			status = "solved"
			print(i)
	i += 1

