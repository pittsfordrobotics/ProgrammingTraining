/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.drive.Drive;

// This is the path following command
public class DrivePathing extends SequentialCommandGroup {
  public DrivePathing(Trajectory trajectory) {
    super(
        new TimeKeeper(true), // Don't edit this

        // TODO: You need to make the commands and methods labeled below
        new DriveZero(), // Command
        new DriveResetPose(trajectory), // Command
        new RamseteCommand(
            trajectory, // Don't touch this
            Drive.getInstance()::getPose, // Method in Subsystem
            new RamseteController(), // Don't touch this
            new SimpleMotorFeedforward(Constants.DRIVE_STATIC_GAIN, Constants.DRIVE_VELOCITY_GAIN, Constants.DRIVE_ACCELERATION_GAIN), // Don't touch this
            new DifferentialDriveKinematics(Constants.DRIVE_TRACK_WIDTH_METERS), // Don't touch this
            Drive.getInstance()::getWheelSpeeds, // Method in Subsystem
            Drive.getInstance().getLeftController(), // Method in Subsystem
            Drive.getInstance().getRightController(), // Method in Subsystem
            Drive.getInstance()::setVolts, // Method in Subsystem
            Drive.getInstance()), // Don't touch this
        new DriveZero(), // Command

        new TimeKeeper(false) // Don't edit this
    );
  }
}