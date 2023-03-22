package frc.robot;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Timer;

/* 
Button formating
Stick 1 = left analog stick
Stick 2 = right analog stick
POV = dpad
Button 1 = A
Button 2 = B
Button 3 = X
Button 4 = Y
Button 5 = LB
Button 6 = RB
Button 7 = back
Button 8 = start
Button 9 = left analog stick center pushed in
Button 10 = right analog stick center pushed in
Button 11 = left trigger
Button 12 = right trigger
Button 13 = home button
*/ 

public class ChainTest
{
    public PWMSparkMax chainmotor;
    //private final Timer timer = new Timer();
//PWMSparkMax takes PWM port # on roborio as parameter
    public ChainTest()
    {
        chainmotor = new PWMSparkMax(4);
    } 
    
    public void chainMotor(Joystick xcon)
    {

        if(xcon.getRawButton(4)){
            chainmotor.set(.5);
        }
        else if(xcon.getRawButton(1)){
            chainmotor.set(-.5); 
            }
        else{
            chainmotor.set(0);
        }
    }
    
}