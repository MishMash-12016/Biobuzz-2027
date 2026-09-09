package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class nemi_simple extends OpMode {
    DcMotor motor;
    Servo servo;
    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        servo = hardwareMap.get(Servo.class, "servo");
    }

    @Override
    public void loop() {
        motor.setPower(gamepad1.right_stick_y);
        servo.setPosition(gamepad1.left_stick_x);
    }
}