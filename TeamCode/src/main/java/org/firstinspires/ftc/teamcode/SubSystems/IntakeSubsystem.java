package org.firstinspires.ftc.teamcode.SubSystems;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Libraries.CuttlefishFTCBridge.src.devices.CuttleMotor;
import org.firstinspires.ftc.teamcode.Libraries.CuttlefishFTCBridge.src.utils.Direction;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;

public class IntakeSubsystem extends SubsystemBase {
    private final CuttleMotor intakeMotor;
    private static IntakeSubsystem instance;

    public static synchronized IntakeSubsystem getInstance() {
        if (instance == null) {
            instance = new IntakeSubsystem();
        }
        return instance;
    }

    private IntakeSubsystem() {
        intakeMotor = new CuttleMotor(JeruRobot.getInstance().expansionHub, 0);
        intakeMotor.setDirection(Direction.REVERSE);
    }

    private void setPower(double power) {
        intakeMotor.setPower(power);
    }

    public Command setPowerCommand(double power) {
        return new InstantCommand(() -> setPower(power),this);
    }
    public void reset() {
        instance = null;
    }
}
