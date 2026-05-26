package org.firstinspires.ftc.teamcode.TeleOP;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.intakeCommandGroup;
import org.firstinspires.ftc.teamcode.Libraries.CuttlefishFTCBridge.src.devices.CuttleCrServo;
import org.firstinspires.ftc.teamcode.Libraries.CuttlefishFTCBridge.src.devices.CuttleMotor;
import org.firstinspires.ftc.teamcode.Libraries.CuttlefishFTCBridge.src.devices.CuttleRevHub;
import org.firstinspires.ftc.teamcode.Libraries.CuttlefishFTCBridge.src.devices.CuttleServo;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruOpMode;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.Utils.AllianceColor;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.Utils.OpModeType;
import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystems.turretSubsystem;

@TeleOp
@Config
public class turretTeleop extends JeruOpMode {
    public JeruRobot robotInstance;
    public static double turrretPos = 45;
    @Override
    public void initialize() {
        robotInstance = JeruRobot.getInstance();
        robotInstance.initJeruRobot()
                .angle(270)
                .allianceColor(AllianceColor.BLUE)
                .opModeType(OpModeType.TELEOP)
                .build(this);

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.A).toggleWhenPressed(
                turretSubsystem.getInstance().getToAndHoldPos(() -> turrretPos),
                turretSubsystem.getInstance().disableSystem()
        );

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.Y).toggleWhenPressed(
                turretSubsystem.getInstance().targetAtGoal(),
                turretSubsystem.getInstance().disableSystem()
        );

        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.B).whenPressed(
                turretSubsystem.getInstance().resetEncoder()
        );
//        new Trigger(() -> JeruRobot.getInstance().gamepadEx1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.05).whileActiveContinuous(
//                DriveTrain.getInstance().slowmodeFieldOrientedDriveCommand()
//        );
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.OPTIONS).whenPressed(
//                DriveTrain.getInstance().resetYawCommand()
//        );



//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(
//                new InstantCommand(() -> DriveTrain.getInstance().activeFR())
//        );
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(
//                new InstantCommand(() -> DriveTrain.getInstance().activeBR())
//        );
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(
//                new InstantCommand(() -> DriveTrain.getInstance().activeBL())
//        );
//        robotInstance.gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(
//                new InstantCommand(() -> DriveTrain.getInstance().activeFL())
//        );

    }
    @Override
    public void run() {
        super.run();
        FtcDashboard.getInstance().getTelemetry().addData("turretP:", turretSubsystem.getInstance().getPose());
        FtcDashboard.getInstance().getTelemetry().addData("target:", turrretPos);
        FtcDashboard.getInstance().getTelemetry().addData("target:", turretSubsystem.getInstance().getNormalizeTargetAngle());
        FtcDashboard.getInstance().getTelemetry().addData("robot pos:", JeruRobot.getInstance().localizer.getPositionRR());

        telemetry.addData("turret angle", turretSubsystem.getInstance().getPose());
    }


}
