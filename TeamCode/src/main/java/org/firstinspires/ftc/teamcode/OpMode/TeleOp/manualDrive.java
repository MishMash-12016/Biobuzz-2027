package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.intakeCommandGroup;
import org.firstinspires.ftc.teamcode.Commands.shootCommandGroup;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMOpMode;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMRobot;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.AllianceColor;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.OpModeType;

@TeleOp
public class manualDrive extends MMOpMode {

    public MMRobot robotInstance;
    private static double shooterpower;

    @Override
    public void initialize() {
        shooterpower = 1;
        robotInstance = MMRobot.getInstance();
        robotInstance.initJeruRobot()
                .angle(0)
                .allianceColor(AllianceColor.BLUE)
                .opModeType(OpModeType.EXPERIMENTING_NO_EXPANSION_NO_SERVOHUB)
                .build(this);

        //Drive
        new Trigger(() -> MMRobot.getInstance().gamepadEx1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.05).whileActiveContinuous(
                MMRobot.getInstance().driveTrain.slowmodeFieldOrientedDriveCommand()
        );

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.OPTIONS).whenPressed(
                MMRobot.getInstance().driveTrain.resetYawCommand()
        );


        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whenPressed(
                intakeCommandGroup.basicFeed()
        );
        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
                new InstantCommand(()->up())
        );
        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
                new InstantCommand(()->down())
        );
    }

    private static void up() {
        shooterpower += 0.1;
    }
    private static void down() {
        shooterpower -= 0.1;
    }
    @Override
    public void run() {
        super.run();
        robotInstance.shooterSubsystem.setPowerCommand(shooterpower).schedule();
        telemetry.addData("head", MMRobot.getInstance().localizer.getHeading(AngleUnit.DEGREES));
        telemetry.addData("shooterPower", shooterpower);
        if (MMRobot.getInstance().limelight != null) {
            MMRobot.getInstance().limelight.updateTelemetry(telemetry);
        }
        telemetry.update();
    }
}
