maxval = 4000000000
fib = [0,1]
sum_even = 0
for i in range(0,maxval,1):
	c_fib = fib[0] + fib[1]
	if c_fib < maxval:
		if c_fib % 2 == 0:
			sum_even += c_fib
		fib[0] = fib[1]
		fib[1] = c_fib
	if i in [1000000,10000000,100000000,1000000000]:
		print(i)
print(sum_even)
	
