import numpy as np



# Function defs

def captcha_sum_next(nums):
    '''
    Calculates sum of all digits that match the next digit in the list.
    
    Ex:
    [1,1,2,2] -> 3
    [1,1,1,1] -> 4
    [1,2,3,4] -> 0
    [9,1,2,1,2,1,2,9] -> 9
    '''

    totsum = 0
    for i in range(len(nums)):
        if nums[i-1] == nums[i]:
            totsum+=nums[i-1]

    return totsum

def captcha_sum_halfway(nums):
    '''
    Calculates sum of all digits that match the digit halfway around the list.

    Ex:
    [1,2,1,2] -> 6
    [1,2,2,1] -> 0
    [1,2,3,4,2,5] -> 4
    [1,2,3,1,2,3] -> 12
    [1,2,1,3,1,4,1,5] -> 4
    '''

    check = len(nums)//2

    totsum = 0
    for i in range(len(nums)):
        if i < check:
            if nums[i] == nums[i+check]:
                totsum += nums[i]
        if i >= check:
            if nums[i] == nums[i-check]:
                totsum += nums[i]

    return totsum


# Read file and convert to ints
with open("input.txt", "r") as infile:
    data = infile.readline().strip()
numbers = [int(c) for c in data]


# Output answers
print("Part 1: ",captcha_sum_next(numbers))
print("Part 2: ",captcha_sum_halfway(numbers))



