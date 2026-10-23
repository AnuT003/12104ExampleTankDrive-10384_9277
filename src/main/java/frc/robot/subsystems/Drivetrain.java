// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private final SparkMax leftLeadMotor = new SparkMax(DrivetrainConstants.leftMotorFront, SparkMax.MotorType.kBrushless);
  private final SparkMax rightLeadMotor = new SparkMax(DrivetrainConstants.rightMotorFront, SparkMax.MotorType.kBrushless);
  private final SparkMax leftFollower = new SparkMax(DrivetrainConstants.leftFollower1, SparkMax.MotorType.kBrushless);
  private final SparkMax rightFollower = new SparkMax(DrivetrainConstants.rightFollower1, SparkMax.MotorType.kBrushless);

  // if using 4 motors, delete leftFollower2 and rightFollower2
  private final MotorControllerGroup leftSpeedController = new MotorControllerGroup(leftLeadMotor, leftFollower);
  private final MotorControllerGroup rightSpeedController = new MotorControllerGroup(rightLeadMotor, rightFollower);
  
  private final DifferentialDrive m_drive = new DifferentialDrive(leftSpeedController, rightSpeedController);
  
  public Drivetrain() {
    leftMotorController.setInverted(DrivetrainConstants.leftMotorInvert);
    rightMotorController.setInverted(DrivetrainConstants.rightMotorInvert);
    
    leftLeadMotor.setIdleMode(SparkMax.IdleMode.kCoast);
    rightLeadMotor.setIdleMode(SparkMax.IdleMode.kCoast);
    leftFollower1.setIdleMode(SparkMax.IdleMode.kCoast);
    rightFollower1.setIdleMode(SparkMax.IdleMode.kCoast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void run(double forward, double turn){
    m_drive.arcadeDrive(forward, turn);
  }
}