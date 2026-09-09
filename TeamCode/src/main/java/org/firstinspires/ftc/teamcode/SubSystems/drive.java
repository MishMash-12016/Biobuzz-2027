package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;

public class drive extends SubsystemBase {
    private final DcMotorEx motor;
    private static drive instance;

    public static synchronized drive getInstance() {
        if (instance == null) {
            instance = new drive();
        }
        return instance;
    }

    private drive() {
        motor = JeruRobot.getInstance().hardwareMap.get(DcMotorEx.class, "motor");
    }
    private void setPower(double power) {
        motor.setPower(power);
    }
    public Command setPowerCommand(double power) {
        return new InstantCommand(() -> setPower(power), this);
    }
}
