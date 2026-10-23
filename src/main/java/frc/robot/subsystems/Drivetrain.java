// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ResetMode; //apply motor configs to motor controller
import com.revrobotics.PersistMode; //"persists" configs to motor controller
import com.revrobotics.spark.SparkMax; //rev robotics spark max motor controller
import com.revrobotics.spark.config.SparkMaxConfig; //config motor controllers
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode; //state when no current commands

import edu.wpi.first.wpilibj.drive.DifferentialDrive; //tank drive control each side at different speeds
import edu.wpi.first.wpilibj2.command.SubsystemBase; //connects subsystem structure in code
import frc.robot.Constants.DrivetrainConstants; //calls separate constants file

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private final SparkMax leftLeadMotor = new SparkMax(DrivetrainConstants.leftMotorFront, SparkMax.MotorType.kBrushed); //declare motor
  private final SparkMax rightLeadMotor = new SparkMax(DrivetrainConstants.rightMotorFront, SparkMax.MotorType.kBrushed); //declare motor
  private final SparkMax leftFollower = new SparkMax(DrivetrainConstants.leftFollower, SparkMax.MotorType.kBrushed); //declare motor
  private final SparkMax rightFollower = new SparkMax(DrivetrainConstants.rightFollower, SparkMax.MotorType.kBrushed); //declare motor

  private final DifferentialDrive m_drive = new DifferentialDrive(leftLeadMotor, rightLeadMotor); //declare each side of drivetrain
  
  public Drivetrain() {
    SparkMaxConfig globalConfig = new SparkMaxConfig(); //config for all motors
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
    
    leftLeadMotor.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftFollower.configure(leftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightLeadMotor.configure(rightLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightFollower.configure(rightFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void run(double forward, double turn){
    m_drive.arcadeDrive(forward, turn);
  }
}