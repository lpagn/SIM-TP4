import matplotlib.pyplot as plt

import scanFile as scanner
import teorica0 as calculator

analytical_x , analytical_y = scanner.scanxy('analytical.txt')

plt.title('Analytical')
plt.xlabel('Time [s]')
plt.ylabel('Position [m]')
plt.plot(analytical_x,analytical_y)    
plt.show()
err = calculator.error_cuadratico_medio(analytical_y,analytical_y)
print("analytical error = " + str(err))

gear_x , gear_y = scanner.scanxy('gear.txt')

plt.title('Gear')
plt.xlabel('Time [s]')
plt.ylabel('Position [m]')
plt.plot(gear_x,gear_y)    
plt.show()
err = calculator.error_cuadratico_medio(analytical_y,gear_y)
print("gear error = " + str(err))

beeman_x , beeman_y = scanner.scanxy('beeman.txt')

plt.title('Beeman')
plt.xlabel('Time [s]')
plt.ylabel('Position [m]')
plt.plot(beeman_x,beeman_y)    
plt.show()
err = calculator.error_cuadratico_medio(analytical_y,beeman_y)
print("beeman error = " + str(err))

leapfrog_x , leapfrog_y = scanner.scanxy('leapfrog.txt')

plt.title('Leapfrog')
plt.xlabel('Time [s]')
plt.ylabel('Position [m]')
plt.plot(leapfrog_x,leapfrog_y)    
plt.show()
err = calculator.error_cuadratico_medio(analytical_y,leapfrog_y)
print("leapfrog error = " + str(err))


