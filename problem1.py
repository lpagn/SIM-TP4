import matplotlib.pyplot as plt

import scanFile as scanner

beeman_x , beeman_y = scanner.scanxy('beeman.txt')

plt.title('Beeman')
plt.xlabel('Time [s]')
plt.ylabel('Position [m]')
plt.plot(beeman_x,beeman_y)    
plt.show()

analytical_x , analytical_y = scanner.scanxy('analytical.txt')

plt.title('Analytical')
plt.xlabel('Time [s]')
plt.ylabel('Position [m]')
plt.plot(analytical_x,analytical_y)    
#plt.show()

euler_x , euler_y = scanner.scanxy('euler.txt')

plt.title('Euler')
plt.xlabel('Time [s]')
plt.ylabel('Position [m]')
plt.plot(euler_x,euler_y)  
#plt.show()

leapfrog_x , leapfrog_y = scanner.scanxy('leapfrog.txt')

plt.title('Leapfrog')
plt.xlabel('Time [s]')
plt.ylabel('Position [m]')
plt.plot(leapfrog_x,leapfrog_y)    
#plt.show()

gear_x , gear_y = scanner.scanxy('gear.txt')

plt.title('Gear')
plt.xlabel('Time [s]')
plt.ylabel('Position [m]')
plt.plot(gear_x,gear_y)    
#plt.show()

