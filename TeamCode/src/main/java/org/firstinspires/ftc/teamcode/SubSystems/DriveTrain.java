
package org.firstinspires.ftc.teamcode.SubSystems;


import com.acmerobotics.dashboard.FtcDashboard;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.geometry.Vector2d;


import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMRobot;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.AllianceColor;
import org.firstinspires.ftc.teamcode.Libraries.pedroPathing.Constants;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DriveTrain extends SubsystemBase{

    public double slowModeRatioForward = 0.3;
    public double slowModeRatioLateral = 0.3;
    public double slowModeRatioRotation = 0.2;

    public PIDController headingPID;
    public static double headingKP = 0.0;
    public static double headingKI = 0.0;
    public static double headingKD = 0.0;
    public static double headingKS = 0.0;


    public PIDController secondaryHeadingPID;
    public static double secondaryHeadingKP = 0.0;
    public static double secondaryHeadingKI = 0.0;
    public static double secondaryHeadingKD = 0.0;
    public static double secondaryHeadingKS = 0.0;

    public static double headingTolerance = 0.50;
    public static double switchToSecondaryHeading = 10;

    private double headingPIDPower;
    private double secondaryHeadingPIDPower;


    private static Follower follower;

    public Follower getFollower() {
        if (follower == null) {
                follower = Constants.createFollower(MMRobot.getInstance().hardwareMap);
        }
        return follower;
    }

    public void update() {
        if (follower != null) {
            follower.update();
        }
    }

    public DriveTrain() {
        super();
        follower = Constants.createFollower(MMRobot.getInstance().hardwareMap);
    }

    public CommandBase driveCommand(
            DoubleSupplier forwardDrive,
            DoubleSupplier lateralDrive,
            DoubleSupplier heading,
            boolean robotCentric,
            BooleanSupplier slowMode) {
        return (CommandBase)
                new RunCommand(
                        () -> {
                            double forwardDrivePower = 0;
                            double lateralDrivePower = 0;
                            double headingPower = 0;

                            forwardDrivePower = forwardDrive.getAsDouble();
                            lateralDrivePower = lateralDrive.getAsDouble();
                            headingPower = heading.getAsDouble();

                            double powerSum =
                                    Math.max(
                                            Math.abs(forwardDrivePower)
                                                    + Math.abs(lateralDrivePower)
                                                    + Math.abs(headingPower),
                                            1);
                            if (powerSum > 1) {
                                double normalizeTo1 = 1 / powerSum;

                                forwardDrivePower *= normalizeTo1;
                                lateralDrivePower *= normalizeTo1;
                                headingPower *= normalizeTo1;
                            }

                            follower.setTeleOpDrive(
                                    forwardDrivePower * (slowMode.getAsBoolean() ? slowModeRatioForward : 1),
                                    lateralDrivePower * (slowMode.getAsBoolean() ? slowModeRatioLateral : 1),
                                    headingPower * (slowMode.getAsBoolean() ? slowModeRatioRotation : 0.5),
                                    robotCentric);
                        }, this)
                        .beforeStarting(
                                () -> follower.startTeleopDrive());
    }

    public void resetYaw() {
        follower.setPose(follower.getPose().setHeading(Math.toRadians(0)));
    }


    /**
     * enables the Default Command(the default command is the drive field centric command)
     */
    public void enableTeleopDrive(BooleanSupplier slowMode) {
        MMRobot mmRobot = MMRobot.getInstance();
        setDefaultCommand(driveCommand(
                                () -> -mmRobot.gamepadEx1.getLeftY(),
                                () -> mmRobot.gamepadEx1.getLeftX(),
                                () -> -mmRobot.gamepadEx1.getRightX(),
                                false, slowMode)

        );
    }

    public void setPose(Pose pose) {
        follower.setPose(pose);
    }

    public Pose getPose() {
        return follower.getPose();
    }

    public void setPose(double x, double y, double heading) {
        this.setPose(new Pose(x, y, heading));
    }

    public void reset() {
        follower = null;
    }

}