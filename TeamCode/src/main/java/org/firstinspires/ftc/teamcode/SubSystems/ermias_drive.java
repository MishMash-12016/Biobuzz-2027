package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;

public class ermias_drive extends SubsystemBase {

    private final DcMotorEx front_left_motor;
    private final DcMotorEx front_right_motor;
    private final DcMotorEx back_left_motor;
    private final DcMotorEx back_right_motor;

    private static ermias_drive instance;

    public static synchronized ermias_drive getInstance(){
        if (instance == null){
            instance= new ermias_drive();
        }
        return instance;
    }

    private ermias_drive(){
        front_left_motor =JeruRobot.getInstance().hardwareMap.get(DcMotorEx.class,"frontLeftMotor");
        front_right_motor=JeruRobot.getInstance().hardwareMap.get(DcMotorEx.class,"frontRightMotor");
        back_right_motor=JeruRobot.getInstance().hardwareMap.get(DcMotorEx.class,"backRightMotor");
        back_left_motor= JeruRobot.getInstance().hardwareMap.get(DcMotorEx.class,"backLeftMotor");
    }

    private void setPower(double power) {
        front_left_motor.setPower(power);
        front_right_motor.setPower(power);
        back_left_motor.setPower(power);
        back_right_motor.setPower(power);
        JeruRobot.getInstance().telemetry.addData("power",power);
        JeruRobot.getInstance().telemetry.addData("joystick power", JeruRobot.getInstance().gamepadEx1.getLeftY());
    }

    public Command Move(){
        return new RunCommand(()->setPower(JeruRobot.getInstance().gamepadEx1.getLeftY()),this);
    }
}
