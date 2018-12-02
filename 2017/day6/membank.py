import numpy as np

test = np.array([0,2,7,0])

data = np.loadtxt("input.txt", dtype=int) # loadtxt is bae


def redistribute(data):
    num_redist = 0
    seen_configs = []
    while data.tolist() not in seen_configs:
        seen_configs.append(data.tolist())
        
        maxindex = np.argmax(data)
        distval = data[maxindex]
        data[maxindex] = 0
        while distval > 0:
            for i in range(maxindex+1,len(data)):
                if distval == 0:
                    break
                data[i] += 1
                distval -= 1
            for i in range(maxindex+1):
                if distval == 0:
                    break
                data[i] += 1
                distval -= 1
        num_redist += 1

    first_index = seen_configs.index(data.tolist())
    loopsize = num_redist - first_index

    return num_redist, loopsize


print("TEST: ", redistribute(test)[0])
print("Part 1: ", redistribute(data)[0])
print("Part 2: ", redistribute(data)[1])






