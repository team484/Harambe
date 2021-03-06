package org.usfirst.frc.team484.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot{
	public static AnalogInput gearSensor;
	int di =0;
	int [] data = new int [10];
			
	
	public static final int DRIVE_STICK_PORT = 1;
	

	// 0 -> climber

	private Joystick driveStick;
	private RobotDrive drive;
	private TalonSRX rightMotor, leftMotor;
	private Encoder leftEncoder, rightEncoder;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("Ryan's RobotInit");
		rightMotor = new TalonSRX(1);
		leftMotor = new TalonSRX(0);
		leftEncoder = new Encoder(0, 1);
		rightEncoder = new Encoder(2,3);
		
		driveStick = new Joystick(DRIVE_STICK_PORT);
		
		drive = new RobotDrive(rightMotor, leftMotor);
		gearSensor = new AnalogInput(02);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		double move = driveStick.getAxis(AxisType.kY);
		double rotate = -driveStick.getTwist();
	
		drive.arcadeDrive(0.7 * move, 0.7 * rotate);
		
		//System.out.println("IR sensor" + isGearIn());
		data[di] = gearSensor.getAverageValue();
		if (di == 10) {
			di = 0;
		}
		int sum = 0;
		for(int x =0; x < 10; x++ ) {
			sum +=data[x];
		}
		System.out.println(sum);
	}

 
	/**
	@Override
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
	}

	
	
	

}
