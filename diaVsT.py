import matplotlib.pyplot as plt


file = open("diaVsT.txt")
lines = file.readlines()
dia = []
times = []

minTime = float("inf")
bestDay = 0

for line in lines:
    params = line.split(" ")
    dia.append(float(params[0]))
    times.append(float(params[1]))
    if float(params[1]) < minTime:
        bestDay = float(params[0])
        minTime = float(params[1])

print(str(bestDay) + " " + str(minTime))
plt.figure(1)
plt.xlabel("dia de despegue")
plt.ylabel("tiempo de viaje en segundos")
plt.plot(dia, times)
plt.show()