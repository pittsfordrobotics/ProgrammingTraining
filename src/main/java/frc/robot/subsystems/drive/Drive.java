package frc.robot.subsystems.drive;

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

    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
    private DifferentialDriveWheelSpeeds wheelSpeeds = new DifferentialDriveWheelSpeeds(0, 0);
    private Pose2d pose = new Pose2d(0, 0, Rotation2d.fromDegrees(getAngle()));

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
}