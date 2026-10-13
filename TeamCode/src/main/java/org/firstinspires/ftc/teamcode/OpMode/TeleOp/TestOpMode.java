package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.IntakeCommandGroup;
import org.firstinspires.ftc.teamcode.Commands.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMOpMode;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMRobot;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.AllianceColor;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.OpModeType;


@TeleOp
public class TestOpMode extends MMOpMode {
    public MMRobot robotInstance;

    @Override
    public void initialize() {
        robotInstance = MMRobot.getInstance();
        robotInstance.initJeruRobot()
                .angle(90)// TODO -> change
                .allianceColor(AllianceColor.BLUE)
                .opModeType(OpModeType.EXPERIMENTING_NO_SERVOHUB)
                .build(this);

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.OPTIONS).whenPressed(
                ()-> robotInstance.driveTrain.setYaw(0)
        );
        
        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenActive(
                IntakeCommandGroup.basicFeed()
        ).whenInactive(
                IntakeCommandGroup.stopIntake()
        );

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.A).toggleWhenActive(
                robotInstance.shooterSubsystem.setPowerCommand(1)
        ).whenInactive(
                robotInstance.shooterSubsystem.setPowerCommand(0)
        );
        new Trigger(() -> robotInstance.gamepadEx1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.05).whenActive(
                ShootCommandGroup.shootAll()
        );
        new Trigger();
    }

    @Override
    public void run() {
        telemetry.addData("yaw",Math.toDegrees(robotInstance.driveTrain.getFollower().getPose().getHeading()));
        telemetry.update();
        super.run();
    }
}
