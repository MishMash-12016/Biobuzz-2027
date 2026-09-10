package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruOpMode;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.Utils.OpModeType;
import org.firstinspires.ftc.teamcode.SubSystems.ermias_drive;

@TeleOp
public class ErmiasTeleOP extends JeruOpMode {
    JeruRobot robotInstance;
    @Override
    public void initialize() {
        robotInstance = JeruRobot.getInstance();
        robotInstance.initJeruRobot()
                .opModeType(OpModeType.EXPERIMENTING_NO_EXPANSION)
                .WithoutDriveTrain()
                .build(this);
//
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.A).toggleWhenPressed(
//                clawSubsystem.getInstance().CloseServo(),
//                clawSubsystem.getInstance().OpenServo()
//        );
//
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whenPressed(
//                clawSubsystem.getInstance().OpenServo()
//        );
        ermias_drive.getInstance().setDefaultCommand(
                ermias_drive.getInstance().Move()
        );
    }



}
