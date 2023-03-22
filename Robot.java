// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;



import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.Compressor;




/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  //controllers
  private final Joystick xcon = new Joystick(0);
  //private final Joystick sJoy = new Joystick(1);

  //timer
  private final Timer m_timer = new Timer();



  // Drive, Ball, & Hanger systems
  //private final BallSystem ballSystem = new BallSystem();
  //private final Hanger hang = new Hanger();
  private final Driver drive= new Driver();
  private final ChainTest chain = new ChainTest();

  //Camera
  // Do not remove, this initiates the camera and it will show up in smartdashboard
  //private final Camera camera = new Camera();


  
  public static double currentAngle;
  public static Boolean angleIsGood;
  public static final int kTimeoutMS = 30;
  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  

  //Pneumatics
  private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
  
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

  //piston bindings
  private static final int kDoubleSolenoidForward = 3;
  private static final int kDoubleSolenoidReverse = 2;
  
   PowerDistribution powerDistro = new PowerDistribution(0, ModuleType.kCTRE);
  //private final Compressor youngCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);

  //compressor
  public final Compressor comp = new Compressor(1, PneumaticsModuleType.REVPH);
  


  @Override
  public void robotInit()
  {
    comp.enableDigital();
    /**
     * This runs a soon as robot starts
     * transfer initialzation of variables here. Can quicker modify variables throughout the bot code
     */
  }
  
  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    /**
     * - Runs as soon as autonomus starts 
     * - Resets and starts the timer, m_timer is the "main timer", there are different 
     *   sub timers that are used in other places
     */
    m_timer.reset();
    m_timer.start();
   // ballSystem.tReset();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    if(m_timer.get() > 0 && m_timer.get() < 2.1)
    {
      drive.stopMotor(); 
      //ballSystem.shoot(); 
    }
    
    else if(m_timer.get()>2.10 && m_timer.get() < 3)
    {
      drive.DriveMotorSpeeds(-.6, -.6); //drives
    }
    
    //Template Autonomous
    /*
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      driveSystem.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      driveSystem.stopMotor(); // stop robot
    }

    */
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    //move teleop variables here 
    //hang.reset();


  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {

    //hang.hangerMove(sJoy);
    chain.chainMotor(xcon);
    drive.driveRobot(xcon);
    //ballSystem.deploy(xcon, drive);

    //piston controls
    if(xcon.getRawButtonPressed(kDoubleSolenoidForward)){
      m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    if(xcon.getRawButtonPressed(kDoubleSolenoidReverse)){
      m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);

    }
      
  }
  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
