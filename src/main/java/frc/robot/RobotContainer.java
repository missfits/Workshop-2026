// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.WorkshopMotorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // The workshop motor subsystem the students will drive from the controller.
  private final WorkshopMotorSubsystem m_motor = new WorkshopMotorSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    /* --- WORKSHOP MOTOR BINDINGS -----------------------------------------------------------
     * A button on the controller returns a Trigger. Types of Triggers:
     *    .whileTrue(cmd)  runs cmd only WHILE the button is held, and CANCELS it the moment the
     *                     button is released.
     * 
     *    .onTrue(cmd)     schedules cmd ONCE when the button is first pressed. The command
     *                     then keeps running on its own until it finishes or is interrupted -
     *                     releasing the button does NOT stop it.
     * 
     * 
     * --- SUBSYSTEM DEFAULT COMMANDS --------------------------------------------------------
     * A subsystem's "default command" runs automatically whenever no other command is using that
     * subsystem.
     */

    // ---------- CODE EXAMPLES ----------
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());


    // ---------- YOUR JOB ----------
    /*
     * TO DO:
     * Bind a button (not b) to run the motor forward while held.
     * Make "off" the motor's default command.
     * 
     * Bind a button (not currently bound to anything) to run the motor BACKWARD while held.
     */

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
