package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class arm extends OpMode {











    @Override
    public void init() {

    }

    @Override
    public void loop() {
        telemetry.addData("gamepad1.a:", gamepad1.a);//boolean value
        telemetry.addData("gamepad1.left_stick_y:", gamepad1.left_stick_y);//float value between -1 and 1
        telemetry.addData("gamepad1.left_trigger:", gamepad1.left_trigger);//float value between 0 and 1
        telemetry.update();
    }





}
