package org.firstinspires.ftc.teamcode.TeleOP;

import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruOpMode;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;
import org.firstinspires.ftc.teamcode.SubSystems.clawSubsystem;

public class ErmiasTeleOP extends JeruOpMode {
    JeruRobot robotInstance;
    @Override
    public void initialize() {
        robotInstance= JeruRobot.getInstance();
        robotInstance.initJeruRobot()
                .build(this);

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.A).toggleWhenPressed(
                clawSubsystem.getInstance().CloseServo(),
                clawSubsystem.getInstance().OpenServo()
        );

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
                clawSubsystem.getInstance().OpenServo()
        );


    }



}
