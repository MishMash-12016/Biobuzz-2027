package org.firstinspires.ftc.teamcode.TeleOP;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruOpMode;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.Utils.AllianceColor;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.Utils.OpModeType;
import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.SubSystems.Limelight;

@TeleOp
public class manualDrive extends JeruOpMode {

    public JeruRobot robotInstance;

    @Override
    public void initialize() {
        robotInstance = JeruRobot.getInstance();
        robotInstance.initJeruRobot()
                .angle(0)
                .allianceColor(AllianceColor.BLUE)
                .opModeType(OpModeType.TELEOP)
                .build(this);

        //Drive
        new Trigger(() -> JeruRobot.getInstance().gamepadEx1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.05).whileActiveContinuous(
                DriveTrain.getInstance().slowmodeFieldOrientedDriveCommand()
        );

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.OPTIONS).whenPressed(
                DriveTrain.getInstance().resetYawCommand()
        );

        new Trigger(() -> JeruRobot.getInstance().gamepadEx1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.05).whileActiveContinuous(
                IntakeSubsystem.getInstance().setPowerCommand(-1)
        ).whenInactive(IntakeSubsystem.getInstance().setPowerCommand(0));

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
                IntakeSubsystem.getInstance().setPowerCommand(1)
        ).whenReleased(IntakeSubsystem.getInstance().setPowerCommand(0));
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whileHeld(
//                new RunCommand(()-> DriveTrain.getInstance().turnWithScale(-Limelight.getInstance().getResultTX()))
//        );
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whileHeld(
//                new RunCommand(()->DriveTrain.getInstance().activeFL(1))
//        );
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.B).whileHeld(
//                new RunCommand(()->DriveTrain.getInstance().activeBL(1))
//        );
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whileHeld(
//                new RunCommand(()->DriveTrain.getInstance().activeFR(1))
//        );
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.X).whileHeld(
//                new RunCommand(()->DriveTrain.getInstance().activeBR(1))
//        );

    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("head", JeruRobot.getInstance().localizer.getHeading(AngleUnit.DEGREES));
        telemetry.update();
    }
}
