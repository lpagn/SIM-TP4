import matplotlib.pyplot as plt
import math
import numpy as np

file = open("sim.txt")
lines = file.readlines()
marsRadius = 1e+07 * 1000

space_way = []
mars_way = []
for line in lines:
    params = line.split(" ")
    if params[0] == "3":
        space_way.append([float(params[1]), float(params[2])])
    if params[0] == "2":
        mars_way.append([float(params[1]), float(params[2])])

marsIndex = len(mars_way)-len(space_way)
distance = []
for i in range(0, len(space_way)):
        distance.append(math.sqrt(math.pow(mars_way[marsIndex+i][0] - space_way[i][0],2)+math.pow(mars_way[marsIndex+i][1] - space_way[i][1],2)))

distance = np.array(distance)
distance = distance - marsRadius

plt.figure(1)
plt.xlabel("dias")
plt.ylabel("distancia de la nave a Marte en metros")
plt.plot(distance)
plt.show()
