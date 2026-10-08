package org.firstinspires.ftc.teamcode.OpMode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
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
                .opModeType(OpModeType.EXPERIMENTING_NO_EXPANSION_NO_SERVOHUB)
                .build(this);

        //Drive
        new Trigger(() -> MMRobot.getInstance().gamepadEx1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.05).whileActiveContinuous(
                MMRobot.getInstance().driveTrain.slowmodeFieldOrientedDriveCommand()
        );

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.OPTIONS).whenPressed(
                MMRobot.getInstance().driveTrain.resetYawCommand()
        );

       new Trigger(() -> MMRobot.getInstance().gamepadEx1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.05).whileActiveContinuous(
               MMRobot.getInstance().intakeSubsystem.setPowerCommand(-1)
       ).whenInactive(MMRobot.getInstance().intakeSubsystem.setPowerCommand(0));

      robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whenPressed(
              MMRobot.getInstance().intakeSubsystem.setPowerCommand(1)
      ).whenReleased(MMRobot.getInstance().intakeSubsystem.setPowerCommand(0));

       robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).whileHeld(
              new RunCommand(()->MMRobot.getInstance().driveTrain.activeFL(1))
        );
        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.B).whileHeld(
                new RunCommand(()->MMRobot.getInstance().driveTrain.activeBL(1))
        );
        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.A).whileHeld(
                new RunCommand(()->MMRobot.getInstance().driveTrain.activeFR(1))
        );
        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.X).whileHeld(
                new RunCommand(()->MMRobot.getInstance().driveTrain.activeBR(1))
        );

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
