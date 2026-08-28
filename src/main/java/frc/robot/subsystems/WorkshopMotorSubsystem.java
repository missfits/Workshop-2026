// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WorkshopMotorConstants;

/**
 * WorkshopMotorSubsystem is the "brains" for our one-motor mechanism.
 *
 * A subsystem represents a robot mechanism. Notice that it does NOT talk to the TalonFX
 *  directly - it holds a {@link WorkshopMotorIO} and asks the IO layer to
 * do the low-level work. The subsystem's job is to describe what the mechanism can do
 * (run forward, turn off, ...) as REUSABLE COMMANDS.
 *
 * COMMAND FACTORIES:
 * Instead of writing a whole new Command class for every action, we can write small
 * methods that create and return commands. These methods are called "command factories."
 *  *
 * The most common building block is {@code run(Runnable)}. It creates a command that:
 * - Calls your {@code Runnable} repeatedly, once every scheduler loop (~20 ms), and
 * - Automatically requires this subsystem. "Requiring" means the scheduler guarantees only
 *   one command uses this motor at a time - if a new command needs it, the old one is cancelled.
 * 
 *   This is what prevents two pieces of code from fighting over the same motor.
 *
 * 
 * Because {@code run(...)} is an instance method of {@link SubsystemBase}, calling it from inside
 * this class ties the resulting command to this subsystem. We also call {@code .withName(...)}
 * so the command shows up with a readable label in dashboards and logs.
 */
public class WorkshopMotorSubsystem extends SubsystemBase {

  /** The hardware layer this subsystem controls. The subsystem only ever talks to the motor
   * through this object. */
  private final WorkshopMotorIO m_io = new WorkshopMotorIO();

  /** Creates a new WorkshopMotorSubsystem. */
  public WorkshopMotorSubsystem() {}

  // ---------- HELPER METHODS ----------
  /**
   * Helper that forwards a voltage request to the hardware layer. (Written for you.)
   * Your command factories below should call this method rather than touching the motor directly.
   * @param volts the voltage to apply to the motor.
   */
  public void setVoltage(double volts) {
    m_io.setVoltage(volts);
  }

  /**
   * Helper that forwards a percent-output request to the hardware layer. (Written for you.)
   * @param percent proportion of supply voltage, from -1.0 to +1.0.
   */
  public void setPercentOutput(double percent) {
    m_io.setPercentOutput(percent);
  }

  /*
   * Helper that stops the motor.
   */
  public void stop(){
    m_io.stop();
  }

  // ---------- EXAMPLE COMMAND FACTORY ----------
  /** 
   * Command factory that runs the motor at a fixed percent output for as long as it is scheduled.
   * @param percent proportion of supply voltage, from -1.0 to +1.0.
   * @return a command that continuously commands the given percent output and requires this
   *     subsystem.
   */
  public Command setPercentOutputCommand(double percent) {
    return run(() -> setPercentOutput(percent)).withName("setPercentOutputCommand");
  }

  // ---------- YOUR JOB ----------
  /*
   * TO DO:
   * This should return a command that runs the motor at the forward voltage from Constants.
   * Hint: think about what helpers you can use!
   */
  public Command motorForwardCommand(){
    return run(() -> {}).withName("motorForwardCommand");
  }

  /*
   * TO DO:
   * This should return a command that runs the motor at the backward voltage from Constants.
   * Hint: Think about what helpers you can use!
   */
  public Command motorBackwardCommand(){
    return run(() -> {}).withName("motorBackwardCommand");
  }

  /*
   * TO DO:
   * This should return a command that turns the motor off by commanding 0 volts.
   * Hint: Think about what helpers you can use!
   */
  public Command offCommand(){
    return run(() -> {}).withName("offCommand");
  }

  // ---------------------------------------------------------------------------------------------

  @Override
  public void periodic() {
    // This method will be called once per scheduler run.
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation.
  }
}
