//// Copyright (c) FIRST and other WPILib contributors.
//// Open Source Software; you can modify and/or share it under the terms of
//// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.sensors.Pigeon2Configuration;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.subsystems.drive.DriveIO;
import frc.robot.subsystems.drive.DriveIOSim;

public final class Constants {
    /**
     *
     * ROBOT: General Constants
     *
     */
    public static final DriveIO ROBOT_DRIVE_IO;

    public static final boolean ROBOT_LOGGING_ENABLED = true;
    public static final String ROBOT_LOGGING_PATH = "/media/sda1/";

    public static final int ROBOT_PDP_CAN = 1;

    public static final double ROBOT_WEIGHT_KILO = Units.lbsToKilograms(120);

    /**
     *
     * DRIVE
     *
     */
    public static final int DRIVE_CAN_PIGEON = 0;
    public static final int DRIVE_CAN_RIGHT_LEADER = 3;
    public static final int DRIVE_CAN_RIGHT_FOLLOWER = 4;
    public static final int DRIVE_CAN_LEFT_LEADER = 1;
    public static final int DRIVE_CAN_LEFT_FOLLOWER = 2;

    public static final Pigeon2Configuration DRIVE_PIGEON_CONFIG = new Pigeon2Configuration();
    static {
        DRIVE_PIGEON_CONFIG.EnableCompass = false;
    }

    public static final double DRIVE_GEAR_RATIO = 6.818;
    public static final double DRIVE_WHEEL_DIAMETER_METERS = Units.inchesToMeters(6);

    public static final double DRIVE_POSITION_GAIN = 2.3546;
    public static final double DRIVE_INTEGRAL_GAIN = 0;
    public static final double DRIVE_DERIVATIVE_GAIN = 0;

    public static final double DRIVE_STATIC_GAIN = 0.26981;
    public static final double DRIVE_VELOCITY_GAIN = 0.046502;
    public static final double DRIVE_ACCELERATION_GAIN = 0.0093369;

    public static final TrapezoidProfile.Constraints DRIVE_TURNING_CONSTRAINTS = new Constraints(10, 5);

    public static final double DRIVE_TRACK_WIDTH_METERS = 0.644;

    public static final double DRIVE_MOI = 0.8501136363636363; // this was found in DifferentialDrivetrainSim.createKitbotSim() code

    public static final double DRIVE_MAX_VELOCITY_METERS_PER_SECOND = 10;
    public static final double DRIVE_MAX_ACCELERATION_METERS_PER_SECOND_SQUARED = 5;

    static {
        if (RobotBase.isReal()) {
            ROBOT_DRIVE_IO = new DriveIO(){};
        }
        else {
            ROBOT_DRIVE_IO = new DriveIOSim();
        }
    }
}