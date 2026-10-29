package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommandGroup;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMOpMode;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMRobot;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.AllianceColor;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.OpModeType;

@TeleOp
public class manualDrive extends MMOpMode {

    public MMRobot robotInstance;

    @Override
    public void initialize() {
        robotInstance = MMRobot.getInstance();
        robotInstance.initJeruRobot()
                .angle(0)
                .allianceColor(AllianceColor.BLUE)
                .opModeType(OpModeType.EXPERIMENTING_NO_SERVOHUB)
                .build(this);

        //Drive
        MMRobot.getInstance().driveTrain.enableTeleopDrive(()->false);

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.OPTIONS).whenPressed(
                new InstantCommand(()->MMRobot.getInstance().driveTrain.resetYaw()));



    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("head", MMRobot.getInstance().localizer.getHeading(AngleUnit.DEGREES));
        if (MMRobot.getInstance().limelight != null) {
            MMRobot.getInstance().limelight.updateTelemetry(telemetry);
        }
        telemetry.update();
    }
}
