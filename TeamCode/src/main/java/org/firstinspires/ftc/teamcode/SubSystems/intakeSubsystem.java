package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Libraries.CuttlefishFTCBridge.src.devices.CuttleMotor;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;

public class intakeSubsystem extends SubsystemBase {
    private final DcMotorEx intakeMotor;
    private static intakeSubsystem instance;

    public static synchronized intakeSubsystem getInstance() {
        if (instance == null) {
            instance = new intakeSubsystem();
        }
        return instance;
    }
    private intakeSubsystem() {
        intakeMotor = JeruRobot.getInstance().hardwareMap.get(DcMotorEx.class, "intakeMotor");
    }
    private void setPower(double power) {
        intakeMotor.setPower(power);
    }

    public Command setPowerCommand(double power) {
        return new InstantCommand(() -> setPower(power),this);
    }
}
