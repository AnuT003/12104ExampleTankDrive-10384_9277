// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import com.revrobotics.SparkMaxLowLevel;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private final SparkMax leftLeadMotor = new SparkMax(Constants.leftMotorFront, SparkMaxLowLevel.MotorType.kBrushed);
  private final SparkMax rightLeadMotor = new SparkMax(Constants.rightMotorFront, SparkMaxLowLevel.MotorType.kBrushed);
  private final SparkMax leftFollower1 = new SparkMax(Constants.leftFollower1, SparkMaxLowLevel.MotorType.kBrushed);
  private final SparkMax rightFollower1 = new SparkMax(Constants.rightFollower1, SparkMaxLowLevel.MotorType.kBrushed);

  // Use for 6-motor drive
  private final SparkMax leftFollower2 = new SparkMax(Constants.leftFollower2, SparkMaxLowLevel.MotorType.kBrushed);
  private final SparkMax rightFollower2 = new SparkMax(Constants.rightFollower2, SparkMaxLowLevel.MotorType.kBrushed);
  
  // if using 4 motors, delete leftFollower2 and rightFollower2
  private final SpeedControllerGroup leftSpeedController = new SpeedControllerGroup(leftLeadMotor, leftFollower1, leftFollower2);
  private final SpeedControllerGroup rightSpeedController = new SpeedControllerGroup(rightLeadMotor, rightFollower1, rightFollower2);
  
  private final DifferentialDrive m_drive = new DifferentialDrive(leftSpeedController, rightSpeedController);
  
  public Drivetrain() {
    leftSpeedController.setInverted(Constants.leftMotorInvert);
    rightSpeedController.setInverted(Constants.rightMotorInvert);
    
    leftLeadMotor.setIdleMode(SparkMax.IdleMode.kCoast);
    rightLeadMotor.setIdleMode(SparkMax.IdleMode.kCoast);
    leftFollower1.setIdleMode(SparkMax.IdleMode.kCoast);
    rightFollower1.setIdleMode(SparkMax.IdleMode.kCoast);
    leftFollower2.setIdleMode(SparkMax.IdleMode.kCoast);
    rightFollower2.setIdleMode(SparkMax.IdleMode.kCoast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void run(double forward, double turn){
    m_drive.arcadeDrive(forward, turn);
  }
}