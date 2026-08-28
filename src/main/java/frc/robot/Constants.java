// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class WorkshopMotorConstants {
    /*
     *  TODO: A CAN ID is the unique address a device (like a motor controller) uses to
     *  communicate on the robot's CAN bus. No two devices on the same bus can share an ID.
     * 
     *  To find or set a motor's CAN ID, grab the driver station, plug into the radio via
     *  ethernet, and open Phoenix Tuner X. You should be able to see all the devices on the
     *  CAN bus (the chain of stuff connected with the yellow and green wires). Select a
     *  specific device from the device list. Change it's ID on the device page in the ID
     *  field.
     * 
     *  FILL IN that ID below.
     */

    public static final int MOTOR_ID = 0;

    // Stator current limit in amps (limits current at the motor windings)
    public static final int MOTOR_STATOR_LIMIT = 40; // recommended by auggie 8/27

    // Supply current limit in amps (limits current drawn from the battery)
    public static final int MOTOR_SUPPLY_LIMIT = 40; // recommended by auggie 8/27

    // Set true to reverse the motor's positive direction
    public static final boolean IS_INVERTED = false;

    // Voltage applied to drive the motor forward
    public static final double FORWARD_VOLTAGE = 3.0;

    // Voltage applied to drive the motor backward
    public static final double BACK_VOLTAGE = -3.0;
  }
}
