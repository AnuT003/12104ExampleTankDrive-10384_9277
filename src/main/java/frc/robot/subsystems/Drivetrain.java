// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private final CANSparkMax leftLeadMotor = new CANSparkMax(Constants.leftMotorFront, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final CANSparkMax rightLeadMotor = new CANSparkMax(Constants.rightMotorFront, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final CANSparkMax leftFollower1 = new CANSparkMax(Constants.leftFollower1, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final CANSparkMax rightFollower1 = new CANSparkMax(Constants.rightFollower1, CANSparkMaxLowLevel.MotorType.kBrushless);

  // Use for 6-motor drive
  private final CANSparkMax leftFollower2 = new CANSparkMax(Constants.leftFollower2, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final CANSparkMax rightFollower2 = new CANSparkMax(Constants.rightFollower2, CANSparkMaxLowLevel.MotorType.kBrushless);
  
  // if using 4 motors, delete leftFollower2 and rightFollower2
  private final SpeedControllerGroup leftSpeedController = new SpeedControllerGroup(leftLeadMotor, leftFollower1, leftFollower2);
  private final SpeedControllerGroup rightSpeedController = new SpeedControllerGroup(rightLeadMotor, rightFollower1, rightFollower2);
  
  private final DifferentialDrive m_drive = new DifferentialDrive(leftSpeedController, rightSpeedController);
  
  public Drivetrain() {
    leftSpeedController.setInverted(Constants.leftMotorInvert);
    rightSpeedController.setInverted(Constants.rightMotorInvert);
    
    leftLeadMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    rightLeadMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    leftFollower1.setIdleMode(CANSparkMax.IdleMode.kCoast);
    rightFollower1.setIdleMode(CANSparkMax.IdleMode.kCoast);
    leftFollower2.setIdleMode(CANSparkMax.IdleMode.kCoast);
    rightFollower2.setIdleMode(CANSparkMax.IdleMode.kCoast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void run(double forward, double turn){
    m_drive.arcadeDrive(forward, turn);
  }
}