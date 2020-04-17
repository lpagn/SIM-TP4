import matplotlib.pyplot as plt

import scanFile as scanner
import teorica0 as calculator

analytical_x , analytical_y = scanner.scanxy('analytical.txt')

#plt.title('Analytical')
plt.xlabel('Time [s]')
plt.ylabel('Position [m]')
plt.plot(analytical_x,analytical_y,label ="analytical")    
#plt.show()
err = calculator.error_cuadratico_medio(analytical_y,analytical_y)
print("analytical error = " + str(err))

gear_x , gear_y = scanner.scanxy('gear.txt')

#plt.title('Gear')
#plt.xlabel('Time [s]')
#plt.ylabel('Position [m]')
plt.plot(gear_x,gear_y,label ="gear")    
#plt.show()
err = calculator.error_cuadratico_medio(analytical_y,gear_y)
print("gear error = " + str(err))

beeman_x , beeman_y = scanner.scanxy('beeman.txt')

#plt.title('Beeman')
#plt.xlabel('Time [s]')
#plt.ylabel('Position [m]')
plt.plot(beeman_x,beeman_y,label ="beeman")    
#plt.show()
err = calculator.error_cuadratico_medio(analytical_y,beeman_y)
print("beeman error = " + str(err))

leapfrog_x , leapfrog_y = scanner.scanxy('leapfrog.txt')

#plt.title('Leapfrog')
#plt.xlabel('Time [s]')
#plt.ylabel('Position [m]')
plt.plot(leapfrog_x,leapfrog_y,label ="leapfrog")

#plt.axis([3.1545,3.1546,0.1048,0.1049]) # Use the plt.axis() function with your limits. | where x(y)min/max are the coordinate limits for both axes.
plt.axis([0,1,-1,1])
#plt.axis([0,5,-1,1])

plt.legend()
 
plt.show()

err = calculator.error_cuadratico_medio(analytical_y,leapfrog_y)
print("leapfrog error = " + str(err))

gear_register_x , gear_register_y = scanner.scanxy('gear_register.txt')

#plt.title('Gear Error')
#plt.xlabel(' step  [s]  ')
#plt.ylabel(' error [m*m]')
plt.loglog(gear_register_x,gear_register_y,label ="gear error")    
#plt.show()

beeman_register_x , beeman_register_y = scanner.scanxy('beeman_register.txt')

#plt.title('Beeman Error')
plt.xlabel(' step  [s]  ')
plt.ylabel(' error [m*m]')
plt.loglog(beeman_register_x,beeman_register_y,label ="beeman error")    
#plt.show()

leapfrog_register_x , leapfrog_register_y = scanner.scanxy('leapfrog_register.txt')

#plt.title('Leapfrog Error')
plt.xlabel(' step  [s]  ')
plt.ylabel(' error [m*m]')
plt.loglog(leapfrog_register_x,leapfrog_register_y,label ="leapfrog error")    


plt.legend(loc='upper center', bbox_to_anchor=(0.5, 1.05),ncol=3, fancybox=True, shadow=True)
#plt.legend()

#plt.show()

