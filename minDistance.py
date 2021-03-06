import matplotlib.pyplot as plt
import math

file = open("sim.txt")
lines = file.readlines()
earth = []
mars = []

# get x and y positions
for line in lines:
    params = line.split(" ")
    if params[0] == "1":
        earth.append([float(params[1]), float(params[2])])
    if params[0] == "2":
        mars.append([float(params[1]), float(params[2])])

distance = []
min = float("inf")
day = 0
for i in range(0, len(earth)):
    distance.append(math.sqrt(math.pow(earth[i][0] - mars[i][0], 2) + math.pow(earth[i][1] - mars[i][1], 2)))
    if distance[i] < min:
        min = distance[i]
        day = i

print("Dia de salida: " + str(day))

plt.figure(1)
plt.xlabel("cantidad de dias desde el 06/04/2020")
plt.ylabel("distancia entre la tierra y marte en metros")
plt.plot(distance)

mx = []
my = []
for point in mars:
    mx.append(point[0])
    my.append(point[1])
ex = []
ey = []
for point in earth:
    ex.append(point[0])
    ey.append(point[1])
plt.figure(2)
plt.xlabel("distania en metros al 0")
plt.ylabel("distancia en metros al 0")


plt.plot(mx, my, label="marte")
plt.plot(ex, ey, label="tierra")

plt.legend()


plt.show()

