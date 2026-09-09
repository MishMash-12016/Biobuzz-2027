package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.intakeCommandGroup;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruOpMode;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.Utils.AllianceColor;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.Utils.OpModeType;
import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystems.drive;
import org.firstinspires.ftc.teamcode.SubSystems.steer;

@TeleOp
public class nemi_SimpleTeleOp extends JeruOpMode {

    public JeruRobot robotInstance;

    @Override
    public void initialize() {
        robotInstance = JeruRobot.getInstance();
        robotInstance.initJeruRobot()
                .opModeType(OpModeType.EXPERIMENTING_NO_EXPANSION)
                .build(this);

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(
                steer.getInstance().setPositionCommand(1)
        );
        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                steer.getInstance().setPositionCommand(0)
        );
        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
                steer.getInstance().setPositionCommand(-1)
        );


        new Trigger(() -> JeruRobot.getInstance().gamepadEx1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.05).whileActiveContinuous(
                drive.getInstance().setPowerCommand(GamepadKeys.Trigger.RIGHT_TRIGGER.ordinal())
        );
        new Trigger(() -> JeruRobot.getInstance().gamepadEx1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.05).whileActiveContinuous(
                drive.getInstance().setPowerCommand(-GamepadKeys.Trigger.LEFT_TRIGGER.ordinal())
        );
    }
}
