// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.PIDConstants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  CANSparkMax pivot1;
  CANSparkMax pivot2;
  CANSparkMax extension;
  CANSparkMax wrist;
  public AnalogPotentiometer pivotPot;
  public AnalogPotentiometer extendPot;
  public AnalogPotentiometer wristPot;
  public PIDController pivotPID;
  public PIDController wristPID;
  public PIDController extendPID;
  DoubleSolenoid intakeSolenoid;



  public Arm() {
    pivot1 = new CANSparkMax(Constants.pivotIDs[0], MotorType.kBrushless);
    pivot2 = new CANSparkMax(Constants.pivotIDs[1], MotorType.kBrushless);
    wrist = new CANSparkMax(Constants.wristID, MotorType.kBrushless);
    extension = new CANSparkMax(Constants.extensionID, MotorType.kBrushless);


    pivotPot = new AnalogPotentiometer(1, 360, 0);

    wristPot = new AnalogPotentiometer(3, 360, 0);

    extendPot = new AnalogPotentiometer(2, 33.1, 0);

    pivotPID = new PIDController(PIDConstants.pivotkP, PIDConstants.pivotkI, PIDConstants.pivotkD);
    pivotPID.setIntegratorRange(-PIDConstants.pivotMaxPercent, PIDConstants.pivotMaxPercent);

    wristPID = new PIDController(PIDConstants.wristkP, PIDConstants.wristkI, PIDConstants.wristkD);
    wristPID.setIntegratorRange(-PIDConstants.wristMaxPercent, PIDConstants.wristMaxPercent);

    extendPID = new PIDController(PIDConstants.extendkP, PIDConstants.extendkI, PIDConstants.extendkD);
    extendPID.setIntegratorRange(-PIDConstants.extendMaxPercent, PIDConstants.extendMaxPercent);

    intakeSolenoid = new DoubleSolenoid(
      Constants.PCM, 
      PneumaticsModuleType.CTREPCM, 
      Constants.intakeSolenodIDS[0], Constants.intakeSolenodIDS[1]);
      

  }

  public void PivotDrive(double moveSpeed) {
    pivot1.set(moveSpeed);
    pivot2.set(moveSpeed);
  }

  public void ExtendDrive(double moveSpeed) {
    extension.set(moveSpeed);
  }

  public void WristDrive(double moveSpeed) {
    wrist.set(moveSpeed);
  }

  // public void MoveArm(double armcase[]) {
  //   SmartDashboard.putNumber("Arm Case",armcase[0]);

  //   double pivotPotReadout = Robot.arm.pivotPot.get();
  //   // double armPotReadout = 120;

  //   double pivotspeed = MathUtil.clamp(pivotPID.calculate(pivotPotReadout, armcase[0]), -PIDConstants.pivotMaxPercent, PIDConstants.pivotMaxPercent);
  //   pivotspeed = pivotspeed / 100;
  //   Robot.arm.PivotDrive(pivotspeed);
  //   SmartDashboard.putNumber("Arm Speed",pivotspeed);

  //   double wristPotReadout = Robot.arm.wristPot.get();
  //   // double wristPotReadout = 100;
  //   double wristspeed = MathUtil.clamp(wristPID.calculate(wristPotReadout, armcase[1]), -PIDConstants.wristMaxPercent, PIDConstants.wristMaxPercent);
  //   wristspeed = wristspeed / 100;
  //   Robot.arm.WristDrive(wristspeed);
  //   SmartDashboard.putNumber("Wrist Speed",wristspeed);

  //   double extendPotReadout = Robot.arm.extendPot.get();
  //   // double extendPotReadout = 15;

  //   double extendspeed = MathUtil.clamp(extendPID.calculate(extendPotReadout, armcase[2]), -PIDConstants.extendMaxPercent, PIDConstants.extendMaxPercent);
  //   extendspeed = extendspeed / 100;
  //   Robot.arm.ExtendDrive(extendspeed);
  //   SmartDashboard.putNumber("Extend Speed",extendspeed);

  //   if (armcase[3] == 0) {
  //     intakeSolenoid.set(Value.kForward);
  //     SmartDashboard.putString("Intake State", "Cone");

  //   } else if(armcase[3] == 1) {
  //     SmartDashboard.putString("Intake State", "Cube");
  //   }

  // }

  @Override
  public void periodic() {
    RobotContainer container = Robot.m_robotContainer;
    SmartDashboard.putNumber("ArmPot readout", Robot.arm.pivotPot.get());
    SmartDashboard.putNumber("WristPot readout", Robot.arm.wristPot.get());
    SmartDashboard.putNumber("ExtendPot readout", Robot.arm.extendPot.get());

    // This method will be called once per scheduler run
    // if(container.driverR.getRawButton(1) == true){
    //   MoveArm(Constants.coneIntake);
    //   SmartDashboard.putBoolean("Arm On", true);

    // }
    // else if(container.driverR.getRawButton(2) == true){
    //   MoveArm(Constants.cubeIntake);
    //   SmartDashboard.putBoolean("Arm On", true);


    // }
    // else{
    //   SmartDashboard.putBoolean("Arm On", false);

    // }


  
  }
}
