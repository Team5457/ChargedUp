package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.*;

import java.lang.Math;


public class Driver extends SubsystemBase{

    private final Spark rightSparks1;
    private final Spark rightSparks2;
    private final Spark leftSparks1;
    private final Spark leftSparks2;
    public final DifferentialDrive drive;
    private final MotorControllerGroup mLeft;
    private final MotorControllerGroup mRight;

    public Driver() 
    {
        //make these sparks the ones that control drive. 
        rightSparks1 = new Spark(2);
        rightSparks2 = new Spark(0);
        
        leftSparks1 = new Spark(3);
        leftSparks2 = new Spark(1);

        mLeft = new MotorControllerGroup(leftSparks1, leftSparks2);
        mRight = new MotorControllerGroup(rightSparks1, rightSparks2);

        mLeft.setInverted(false);
        mRight.setInverted(true);

        drive = new DifferentialDrive(mRight, mLeft);
    }

    //@Override
    protected void initDefaultCommand() {
        
    }


    public void driveRobot(Joystick controller) {
        boolean slowMo = false; 
        //controller.getRawAxis(2)>0;
        //Enables turn when controller button 5 is pressed
        boolean turbo = controller.getRawButton(5);
        
        double safty = controller.getRawAxis(3);

        if (safty > 0) {
            double y = controller.getRawAxis(0);
            double x = controller.getRawAxis(1);
            if (slowMo) {
                drive.arcadeDrive(x * safty *-0.4, y * safty*-0.4, true);
            }
            else if (turbo){
                //do not change 0
                drive.arcadeDrive(Math.copySign(1, x),0 , true);
            }
            else
                drive.arcadeDrive(x * safty *-1, y * safty*-1, true);

        }
        else {
            drive.stopMotor();
        }

    }
    public void DriveMotorSpeeds(double right, double left)
    {
        mLeft.set(left);
        mRight.set(right);

        
    }
    public void stopMotor()
    {
        mRight.set(0);
        mLeft.set(0);
    }
    
}

