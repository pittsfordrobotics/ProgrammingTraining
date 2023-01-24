// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.inputs.LoggedNetworkTables;
import org.littletonrobotics.junction.io.LogSocketServer;

import java.text.SimpleDateFormat;


public class Robot extends LoggedRobot {
//  private final PowerDistribution revPDH = new PowerDistribution();

  private Command autonomousCommand;

  private RobotContainer robotContainer;

  @Override
  public void robotInit() {
//    advantageKit
    Logger logger = Logger.getInstance();
    setUseTiming(true);
    LoggedNetworkTables.getInstance().addTable("/SmartDashboard");
    logger.recordMetadata("Date", new SimpleDateFormat("MM-dd-yyyy_HH:mm:ss").format(new Date()));
    logger.recordMetadata("RuntimeType", getRuntimeType().toString());
    logger.recordMetadata("ProjectName", GitConstants.MAVEN_NAME);
    logger.recordMetadata("BuildDate", GitConstants.BUILD_DATE);
    logger.recordMetadata("GitSHA", GitConstants.GIT_SHA);
    logger.recordMetadata("GitDate", GitConstants.GIT_DATE);
    logger.recordMetadata("GitBranch", GitConstants.GIT_BRANCH);
    logger.recordMetadata("GitDirty", GitConstants.DIRTY == 0 ? "Clean" : "Dirty");
    logger.addDataReceiver(new LogSocketServer(5800));
    logger.addDataReceiver(new NT4Publisher());
    if (RobotBase.isReal()) {
      logger.addDataReceiver(new WPILOGWriter(Constants.ROBOT_LOGGING_PATH));
      LoggedPowerDistribution.getInstance();
    }
    if (Constants.ROBOT_LOGGING_ENABLED) {
      logger.start();
    }

//    setup
    robotContainer = new RobotContainer();
    DriverStation.silenceJoystickConnectionWarning(true);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    Logger.getInstance().recordOutput("ActiveCommands/Scheduler",
            NetworkTableInstance.getDefault().getEntry("/LiveWindow/Ungrouped/Scheduler/Names")
                    .getStringArray(new String[] {}));
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    autonomousCommand = robotContainer.getAutonomousCommand();

    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}