package frc.robot;

import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.math.trajectory.Trajectory;
import frc.robot.util.PathPlannerHelper;

public final class Trajectories {
    public static final Trajectory path = PathPlanner.loadPath("helloworld", 10.0, 5.0, false);
}