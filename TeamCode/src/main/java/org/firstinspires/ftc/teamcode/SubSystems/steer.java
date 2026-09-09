package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;

public class steer extends SubsystemBase {
    private final Servo servo;
    private static steer instance;

    public static synchronized steer getInstance() {
        if (instance == null) {
            instance = new steer();
        }
        return instance;
    }

    private steer() {
        servo = JeruRobot.getInstance().hardwareMap.get(Servo.class, "servo");
    }
    private void setPosition(double position) {
        servo.setPosition(position);
    }
    public Command setPositionCommand(double position) {
        return new InstantCommand(() -> setPosition(position), this);
    }
}
