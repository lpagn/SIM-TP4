import matplotlib.pyplot as plt


file = open("diaVsD.txt")
lines = file.readlines()
dia = []
distance = []

minDistance = float("inf")
bestDay = 0

for line in lines:
    params = line.split(" ")
    dia.append(float(params[0]))
    distance.append(float(params[1]))
    if float(params[1]) < minDistance:
        bestDay = float(params[0])
        minDistance = float(params[1])

print(str(bestDay) + " " + str(minDistance))
plt.figure(1)
plt.xlabel("dia de despegue")
plt.ylabel("distancia minima a marte en metros")
plt.plot(dia, distance)
plt.show()