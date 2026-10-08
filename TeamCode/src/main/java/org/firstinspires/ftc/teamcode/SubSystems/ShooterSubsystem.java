package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.Motor.Encoder;

import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMRobot;

public class ShooterSubsystem extends SubsystemBase {
    private final DcMotorEx shooterMotor;
    private final Encoder shooterEncoder;
    // how many ticks is one motor rotation
    private final double EncoderResolution = 28;
    // how many motor rotation is one flywil rotation
    private final double girRatio = 25/18;
    // todo: 8/10/26
    private final double kP = 0;
    private final double kI = 0;
    private final double kD = 0;
    private final double kF = 0;
    PIDFController pidf = new PIDFController(kP, kI, kD, kF);
    public ShooterSubsystem() {
        shooterMotor = MMRobot.getInstance().hardwareMap.get(DcMotorEx.class, "shooterMotor");
        shooterEncoder = MMRobot.getInstance().hardwareMap.get(Encoder.class,"shooterEncoder");
    }
    private void setPower(double power) {
        shooterMotor.setPower(power);
    }
    public double getVel() {
        return (shooterEncoder.getPosition()/EncoderResolution)/girRatio;
    }

        public Command setPowerCommand(double power) {
        return new InstantCommand(() -> setPower(power),this);
    }
    public Command getToVelocity(double vel) {
        return setPowerCommand(pidf.calculate(getVel(), vel));
    }
}
