package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruOpMode;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.Utils.OpModeType;
import org.firstinspires.ftc.teamcode.SubSystems.drive;
import org.firstinspires.ftc.teamcode.SubSystems.steer;

@TeleOp
public class nemiTeleOp extends JeruOpMode {

    public JeruRobot robotInstance;

    @Override
    public void initialize() {
        robotInstance = JeruRobot.getInstance();
        robotInstance.initJeruRobot()
                .opModeType(OpModeType.EXPERIMENTING_NO_EXPANSION)
                .build(this);

        drive.getInstance().setDefaultCommand(
                drive.getInstance().setPowerCommand(robotInstance.gamepadEx1.getRightY())
        );
        steer.getInstance().setDefaultCommand(
                steer.getInstance().setPositionCommand(robotInstance.gamepadEx1.getLeftX())
        );
    }
}
