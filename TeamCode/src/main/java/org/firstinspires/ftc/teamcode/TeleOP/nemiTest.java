package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class nemiTest extends OpMode {
    DcMotor motor;
    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class,"Motor");

    }

    @Override
    public void loop() {
        motor.setPower(gamepad1.right_stick_y);

    }
}
