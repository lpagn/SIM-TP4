import matplotlib.pyplot as plt


file = open("v0VsT.txt")
lines = file.readlines()
v0 = []
times = []

for line in lines:
    params = line.split(" ")
    v0.append(float(params[0]))
    times.append(float(params[1]))

plt.figure(1)
plt.xlabel("velocidad inicial en km/s")
plt.ylabel("tiempo de viaje en segundos")
plt.plot(v0, times)
plt.show()
