import matplotlib.pyplot as plt

def c (x,y):
    print(x)
    print(y)
    a=0
    b=0
    c=0
    d=0
    i=0
    N = len(x)
    while i < N:
        a += x[i]*y[i]
        b += x[i]
        c += y[i]
        d += x[i]*x[i]
        i = i + 1
    
    m = (N*a - b*c)/(N*d-b*b)

    q = (d*c-b*a)/(N*d-b*b)
    
    j = 0
    l = []

    while j < i:
        l.append(x[j]*m+q)
        j = j + 1
    
    plt.plot(x,l)
    
    print("c          : m = " + str(m))
    print("c          : q = " + str(q))
    #plt.show()
    
def linearerror(x,y,c_start,c_end,c_step):
    c = c_start
    j = 0
    c_min=float('inf')
    e_min=float('inf')
    N = len(x)
    error_list = []
    c_list = []
    while c < c_end:
        i = 0
        s = 0
        while i < N:
            s+=(y[i]-c*x[i])*(y[i]-c*x[i])
            i = i + 1
        error_list.append(s)
        c_list.append(c)
        c = c + c_step
        if error_list[j]<e_min:
            e_min = error_list[j]
            c_min = c
        j = j + 1
    print("linearerror: m = " + str(c_min))
    plt.plot(c_list,error_list)
    #plt.show()
        
def error_cuadratico_medio(analytical,other):
    max_i = len(analytical)
    max_j = len(other)
    n = min(max_i,max_j)
    print(n)
    i = 0
    sum = 0
    while i < n:
        sum += pow((float(analytical[i])-float(other[i])),2)
        i += 1
    return sum / n




