package frc.robot.util;

import com.revrobotics.CANSparkMax;

/**
 * This is a thick wrapper for CANSparkMax because I am lazy
 */
public class LazySparkMax extends CANSparkMax {
    /**
     * Lazy Spark Max
     * @param port port of CAN ID of CANSparkMax
     * @param mode Brake or Coast
     * @param currentLimit 0-30 for 550 / 0-80 for NEO
     * @param inverted Inverted?
     */
    public LazySparkMax(int port, IdleMode mode, int currentLimit, boolean inverted) {
        super(port, MotorType.kBrushless);
        restoreFactoryDefaults();
        setInverted(inverted);
        setIdleMode(mode);
        enableVoltageCompensation(12);
        getEncoder().setPosition(0);
        setSmartCurrentLimit(currentLimit);
        setCANTimeout(0);
        burnFlash();
    }

    /**
     * Lazy Spark Max not inverted
     * @param port port of CAN ID of CANSparkMax
     * @param mode Brake or Coast
     * @param currentLimit 0-30 for 550 / 0-80 for NEO
     */
    public LazySparkMax(int port, IdleMode mode, int currentLimit) {
        this(port, mode, currentLimit, false);
    }

    /**
     * Lazy Spark Max
     * @param port port of CAN ID of CANSparkMax
     * @param mode Brake or Coast
     * @param currentLimit 0-30 for 550 / 0-80 for NEO
     * @param leader SparkMax to follow
     * @param inverted Whether to follow the leader inverted
     */
    public LazySparkMax(int port, IdleMode mode, int currentLimit, CANSparkMax leader, boolean inverted) {
        super(port, MotorType.kBrushless);
        restoreFactoryDefaults();
        setIdleMode(mode);
        enableVoltageCompensation(12);
        getEncoder().setPosition(0);
        setSmartCurrentLimit(currentLimit);
        follow(leader, inverted);
        setCANTimeout(0);
        burnFlash();
    }

    /**
     * Lazy Spark Max
     * @param port port of CAN ID of CANSparkMax
     * @param mode Brake or Coast
     * @param currentLimit 0-30 for 550 / 0-80 for NEO
     * @param leader SparkMax to follow
     */
    public LazySparkMax(int port, IdleMode mode, int currentLimit, CANSparkMax leader) {
        this(port, mode, currentLimit, leader, false);
    }
}