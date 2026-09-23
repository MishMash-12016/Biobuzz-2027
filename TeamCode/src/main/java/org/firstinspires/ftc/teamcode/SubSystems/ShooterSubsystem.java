package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;

public class ShooterSubsystem extends SubsystemBase {
    private final DcMotorEx shooterMotor;
    private final double kP = 0;
    private final double kI = 0;
    private final double kD = 0;
    private final double kF = 0;
    PIDFController pidf = new PIDFController(kP, kI, kD, kF);
    private static ShooterSubsystem instance;

    public static synchronized ShooterSubsystem getInstance() {
        if (instance == null) {
            instance = new ShooterSubsystem();
        }
        return instance;
    }
    private ShooterSubsystem() {
        shooterMotor = JeruRobot.getInstance().hardwareMap.get(DcMotorEx.class, "shooterMotor");
    }
    private void setPower(double power) {
        shooterMotor.setPower(power);
    }

    public Command setPowerCommand(double power) {
        return new InstantCommand(() -> setPower(power),this);
    }
    public Command getToVelocity(double vel){
        return setPowerCommand(pidf.calculate(0/*CurrentPosition*/,vel));
    }

}
