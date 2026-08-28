// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.WorkshopMotorConstants;

/**
 * WorkshopMotorIO is the "IO layer" (also called the hardware layer) for our workshop motor.
 *
 * The IO layer is responsible for:
 *   It owns the physical hardware object - here, a single CTRE Phoenix 6 {@link TalonFX}.
 *       Nothing else in the code talks to the motor directly; everyone goes through this class.
 *   It configures the hardware once, at construction time, using the numbers in {@link
 *       WorkshopMotorConstants} (current limits, inversion, etc.).
 *   It exposes a small, simple set of actions (for example {@link #setVoltage(double)} and
 *       {@link #stop()}) that describe what we want the motor to do, hiding the vendor-specific
 *       details of how that happens.
 *
 * Essentially, IO is a way of abstracting the actions of the motors themselves.
 * Higher-level code (the subsystem and commands) can then be written in terms
 * of "run at this voltage" / "stop" without ever needing to know it is a TalonFX.
 * That keeps the robot logic clean and makes the hardware easy to swap or simulate later.
 *
 */
public class WorkshopMotorIO {

  /** The physical motor controller on the CAN bus, addressed by its CAN ID. */
  private final TalonFX motor = new TalonFX(WorkshopMotorConstants.MOTOR_ID);

  /** Creates the IO layer and applies the motor's configuration a single time. */
  public WorkshopMotorIO() {
    // A TalonFXConfiguration is a bundle of every setting the motor controller supports.
    // We start from defaults and change only the settings we care about for the workshop.
    TalonFXConfiguration config = new TalonFXConfiguration();

    // --- Current limits -------------------------------------------------------------------
    // Stator current is proportional to torque; limiting it protects the mechanism.
    config.CurrentLimits.StatorCurrentLimit = WorkshopMotorConstants.MOTOR_STATOR_LIMIT;
    config.CurrentLimits.StatorCurrentLimitEnable = true;

    // Supply current is what the motor draws from the battery; limiting it prevents brownouts
    // and keeps us under the breaker rating.
    config.CurrentLimits.SupplyCurrentLimit = WorkshopMotorConstants.MOTOR_SUPPLY_LIMIT;
    config.CurrentLimits.SupplyCurrentLimitEnable = true;

    // --- Inversion ------------------------------------------------------------------------
    // "Inverted" defines which physical direction counts as positive. Phoenix 6 treats
    // counter-clockwise (viewed from the front of the motor) as positive by default.
    config.MotorOutput.Inverted =
        WorkshopMotorConstants.IS_INVERTED
            ? InvertedValue.Clockwise_Positive
            : InvertedValue.CounterClockwise_Positive;

    // Push the configuration to the physical device. The configurator handles the CAN traffic.
    motor.getConfigurator().apply(config);
  }

  /**
   * Commands the motor to hold a specific output voltage.
   *
   * @param volts the voltage to apply; positive drives the motor in its (possibly inverted)
   *     positive direction, negative drives it the other way.
   */
  public void setVoltage(double volts) {
    // VoltageOut is a Phoenix 6 "control request": it tells the motor what we want it to do.
    motor.setControl(new VoltageOut(volts));
  }

  /**
   * Commands the motor to a percentage of the available supply voltage (a "duty cycle").
   *
   * @param percent proportion of supply voltage to apply, from -1.0 (full reverse) to +1.0 (full
   *     forward). Unlike {@link #setVoltage(double)}, the resulting force depends on the current
   *     battery voltage.
   */
  public void setPercentOutput(double percent) {
    // DutyCycleOut is the Phoenix 6 control request for percent-of-supply output.
    motor.setControl(new DutyCycleOut(percent));
  }

  /** Stops the motor by commanding zero volts. */
  public void stop() {
    motor.setControl(new VoltageOut(0.0));
  }
}
