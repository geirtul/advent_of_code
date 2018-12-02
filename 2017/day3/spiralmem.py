import numpy as np


def mem_gen(N):
    '''
    Generates a spiral memory with N squares.
    
    E.g with N = 25
    17  16  15  14  13
    18   5   4   3  12
    19   6   1   2  11
    20   7   8   9  10
    21  22  23  24  25  26
    
    '''

    # Number of elements in pyramid bottom = 1^2 3^2 5^2 etc...
    
    NumRows = int(np.ceil(np.sqrt(N)))
    print("NumRows = ",NumRows)
    
    outer_square = NumRows*2 + (NumRows-2)*2
    N_pos = (NumRows**2 - N) # Always outermost square.
    one_pos = int(np.floor(NumRows/2))
    print("one_pos = ",one_pos)

    # Make list of indices in outer square

    indices = []
    for i in range(NumRows):
        indices.append([NumRows-2-i,NumRows-1])
    for i in range(NumRows):
        indices.append([0,NumRows-1-i])
    for i in range(NumRows):
        indices.append([i,0])
    for i in range(NumRows):
        indices.append([NumRows-1,i])

    steps = abs(indices[N_pos-1][0]-one_pos)+abs(indices[N_pos-1][1]-one_pos)
    return steps
    

def second_mem(N):
    '''
    N is number to find larger than.
    '''
    
    NumRows = int(np.floor(np.sqrt(N))) #Definately enough space.
    spiral = np.zeros((NumRows,NumRows))
    # Where is 1?
    one_pos = int(np.floor(NumRows/2))

    spiral[one_pos,one_pos] = 1
    def sum_around(pos):
        '''
        Sums all neighboring elements
        '''
        x = pos[0]
        y = pos[1]
        neighbor_sum = 0

        neighbor_sum += spiral[x+1,y]
        neighbor_sum += spiral[x-1,y]
        neighbor_sum += spiral[x,y+1]
        neighbor_sum += spiral[x,y-1]
        neighbor_sum += spiral[x+1,y+1]
        neighbor_sum += spiral[x-1,y-1]
        neighbor_sum += spiral[x+1,y-1]
        neighbor_sum += spiral[x-1,y+1]

        return neighbor_sum
    
    # Steppers

    def step_right(pos):
        pos[0]+=1
        return pos
    def step_left(pos):
        pos[0]-=1
        return pos
    def step_up(pos):
        pos[1]+=1
        return pos
    def step_down(pos):
        pos[1]-=1
        return pos
    
    def place_val(pos):
        number = sum_around(pos)
        if number > N:
            print(number)
            exit(1)
        else:
            spiral[pos[0],pos[1]] = number


    # Loopings
    start_pos = [one_pos,one_pos]
    for i in range(1,one_pos):
        corners = [[one_pos+i,one_pos+i],[one_pos+-i,one_pos+i],[one_pos+-i,one_pos+-i],[one_pos+i,one_pos+-i]]
        pos = step_right(start_pos)
        place_val(pos)
        while pos != corners[0]:
            pos = step_up(pos)
            place_val(pos)
        while pos != corners[1]:
            pos = step_left(pos)
            place_val(pos)
        while pos != corners[2]:
            pos = step_down(pos)
            place_val(pos)
        while pos != corners[3]:
            pos = step_right(pos)
            place_val(pos)
        
        # Update starting position
        start_pos = pos

print("PART 1: ", mem_gen(277678))
second_mem(277678)
    
