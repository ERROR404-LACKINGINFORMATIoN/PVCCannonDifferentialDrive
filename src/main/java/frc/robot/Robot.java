// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the manifest file in the resource directory.
 */

public class Robot extends TimedRobot {
    /** Called once at the beginning of the robot program. */
    public Robot() {
        XboxController robotController =
            new XboxController(Constants.differentialDrive.portController);
        SparkMax leftmotorrobot =
            new SparkMax(Constants.differentialDrive.leftmotor, MotorType.kBrushless);
        SparkMax leftmotorrobotv =
            new SparkMax(Constants.differentialDrive.leftmotorv, MotorType.kBrushless);
        SparkMax rightmotorrobot =
            new SparkMax(Constants.differentialDrive.rightmotor, MotorType.kBrushless);
        SparkMax rightmotorrobotv =
            new SparkMax(Constants.differentialDrive.rightmotorv, MotorType.kBrushless);
        DifferentialDrive differentialDriveRobot =
            new DifferentialDrive(leftmotorrobot, rightmotorrobot);
        SparkMaxConfig globalConfig = new SparkMaxConfig();
        SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
        SparkMaxConfig leftElevatorMotorConfig = new SparkMaxConfig();
        SparkMaxConfig rightElevatorMotorConfig = new SparkMaxConfig();
        rightElevatorMotorConfig.apply(globalConfig).inverted(true);
        // Apply the global config and set the leader SPARK for follower mode
        leftElevatorMotorConfig.apply(globalConfig).follow(leftmotorrobot);
        // Apply the global config and set the leader SPARK for follower mode
        rightElevatorMotorConfig.apply(rightElevatorMotorConfig).follow(rightmotorrobot);
        leftmotorrobot.configure(globalConfig, ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        leftmotorrobotv.configure(leftElevatorMotorConfig, ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        rightmotorrobot.configure(rightLeaderConfig, ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
        rightmotorrobotv.configure(rightElevatorMotorConfig, ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters);
    }

    @Override
    public void robotInit() {
        // Make motor 2 follow motor 1 on both sides


        // Invert the main right motor
    }

    /** This function is run once each time the robot enters autonomous mode. */
    @Override
    public void autonomousInit() {}

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {

    }

    /** This function is called once each time the robot enters teleoperated mode. */
    @Override
    public void teleopInit() {}

    /** This function is called periodically during teleoperated mode. */
    @Override
    public void teleopPeriodic() {
        Robot.differentialDriveRobot.arcadeDrive();
        motor.set(robotController.getLeftY());
        motorv.set(robotController.getRightY());
    }


    /** This function is called once each time the robot enters test mode. */
    @Override
    public void testInit() {}

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}
