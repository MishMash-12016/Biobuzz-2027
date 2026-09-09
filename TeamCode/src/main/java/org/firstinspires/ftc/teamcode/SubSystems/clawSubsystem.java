package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;

public class clawSubsystem extends SubsystemBase {
    private Servo servo_1;


    private static clawSubsystem intakeSubsystem;

    public static synchronized  clawSubsystem getInstance(){
        if (intakeSubsystem == null){
            intakeSubsystem= new clawSubsystem();
        }
        return intakeSubsystem;
    }

    private clawSubsystem(){
        servo_1 = JeruRobot.getInstance().hardwareMap.get(Servo.class,"servo_1");

    }
    private void setIntakeMotor(double pos){servo_1.setPosition(pos);

    }

    public Command CloseServo(){
        return new InstantCommand(()->setIntakeMotor(0.0), this);
    }
    public Command OpenServo(){
        return new InstantCommand(()->setIntakeMotor(0.5), this);
    }
}
