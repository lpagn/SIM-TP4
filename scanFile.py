def scanxy(filename):
    file = open(filename, 'r') 
    Lines = file.readlines() 
    
    x = []
    y = []
    
    count = 0
    
    for line in Lines:
        arr = line.rsplit(' ',1)
        x.append(arr[0])
        y.append(arr[1])
        count += 1
    
    return x , y