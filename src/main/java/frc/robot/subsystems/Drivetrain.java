// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private final SparkMax leftLeadMotor = new SparkMax(DrivetrainConstants.leftMotorFront, SparkMax.MotorType.kBrushed);
  private final SparkMax rightLeadMotor = new SparkMax(DrivetrainConstants.rightMotorFront, SparkMax.MotorType.kBrushed);
  private final SparkMax leftFollower = new SparkMax(DrivetrainConstants.leftFollower, SparkMax.MotorType.kBrushed);
  private final SparkMax rightFollower = new SparkMax(DrivetrainConstants.rightFollower, SparkMax.MotorType.kBrushed);

  // if using 4 motors, delete leftFollower2 and rightFollower2
  // private final MotorControllerGroup leftSpeedController = new MotorControllerGroup(leftLeadMotor, leftFollower1);
  // private final MotorControllerGroup rightSpeedController = new MotorControllerGroup(rightLeadMotor, rightFollower1);
  
  private final DifferentialDrive m_drive = new DifferentialDrive(leftLeadMotor, rightLeadMotor);
  
  public Drivetrain() {
    SparkMaxConfig globalConfig = new SparkMaxConfig();
    SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();

    globalConfig
      .smartCurrentLimit(50)
      .idleMode(IdleMode.kBrake);

    rightLeaderConfig
        .apply(globalConfig)
        .inverted(true);

    // Apply the global config and set the leader SPARK for follower mode
    leftFollowerConfig
        .apply(globalConfig)
        .follow(leftLeadMotor);

    // Apply the global config and set the leader SPARK for follower mode
    rightFollowerConfig
        .apply(globalConfig)
        .follow(rightLeadMotor);
    
    leftLeadMotor.configure(globalConfig, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);
    leftFollower.configure(leftFollowerConfig, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);
    rightLeadMotor.configure(rightLeaderConfig, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);
    rightFollower.configure(rightFollowerConfig, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void run(double forward, double turn){
    m_drive.arcadeDrive(forward, turn);
  }
}