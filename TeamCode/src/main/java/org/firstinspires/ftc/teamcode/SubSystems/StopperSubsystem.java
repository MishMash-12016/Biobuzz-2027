package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMRobot;

import java.util.function.DoubleSupplier;

public class StopperSubsystem extends SubsystemBase {
    private final Servo stopperServo;

    private final double open = 0.1;
    private final double close = 0.0;
    public StopperSubsystem() {
        stopperServo = MMRobot.getInstance().hardwareMap.get(Servo.class, "stopperServo");
    }
    private void setPosition(double pos) {
        stopperServo.setPosition(pos);
    }

    private Command setPositionCommand(double pos) {
        return new InstantCommand(() -> setPosition(pos));
    }

    public Command open() {
        return setPositionCommand(open);
    }
    public Command close() {
        return setPositionCommand(close);
    }


}
