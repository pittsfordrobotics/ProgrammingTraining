package frc.robot.subsystems.drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.drive.DriveIO.DriveIOInputs;
import org.littletonrobotics.junction.Logger;

public class Drive extends SubsystemBase {
    private final DriveIO io;
    private final DriveIOInputs inputs = new DriveIOInputs();

    private DifferentialDriveWheelSpeeds wheelSpeeds = new DifferentialDriveWheelSpeeds(0, 0);
    private Pose2d pose = new Pose2d(0, 0, Rotation2d.fromDegrees(getAngle()));
    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getAngle()), inputs.leftPositionMeters, inputs.rightPositionMeters, pose);

    private static final Drive INSTANCE = new Drive(Constants.ROBOT_DRIVE_IO);
    public static Drive getInstance() {
        return INSTANCE;
    }

    private Drive(DriveIO io) {
        this.io = io;
    }

    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Drive", inputs);

        pose = odometry.update(
                Rotation2d.fromDegrees(getAngle()),
                inputs.leftPositionMeters,
                inputs.rightPositionMeters);

        Logger.getInstance().recordOutput("Drive/Pose",
                new double[] {pose.getX(), pose.getY(), pose.getRotation().getRadians()});

        wheelSpeeds = new DifferentialDriveWheelSpeeds(getLeftVelocity(), getRightVelocity());
    }

//    This will need to call the IO layer since we are interacting with hardware (motors)
    public void setVolts(double left, double right) {
    }

//    This needs to reset the odometry with the pose that is given through the method
    public void resetOdometry(Pose2d pose) {
    }

//    This will need to call the IO layer since we need to interact with the hardware (encoders)
    public void resetEncoders() {
    }

//   This needs to call "inputs" since we are reading an input from the IO layer
//   in m/s
    public double getLeftVelocity() {
    }

//   This needs to call "inputs" since we are reading an input from the IO layer
//    in m/s
    public double getRightVelocity() {
    }

//  this will return a new PID controller with the Position Gain, Integral Gain, and Derivative Gain set through the constants file
    public PIDController getLeftController() {
    }

//  this will return a new PID controller with the Position Gain, Integral Gain, and Derivative Gain set through the constants file
    public PIDController getRightController() {
    }

//    Returns the local WheelSpeeds
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    }

//    Returns the locally stored pose
    public Pose2d getPose() {
    }

    /**
     * Gets the current's angle in degrees
     *  To do this you will need to look up some documentation
     *  Look up "Pigeon2 CTRE" and look for a User Manual
     *  Our Pigeon2 is mounted flat on the bottom of the robot
     * @return current angle; positive = clockwise
     */
    public double getAngle() {
    }

}