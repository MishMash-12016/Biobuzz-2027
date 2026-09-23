package org.firstinspires.ftc.teamcode.SubSystems;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruOpMode;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;

public class Limelight extends SubsystemBase {

    private Limelight3A limelight;
    private static Limelight instance;

    public static synchronized Limelight getInstance() {
        if (instance == null) {
            instance = new Limelight();
        }
        return instance;
    }

    private Limelight() {
        limelight = JeruRobot.getInstance().hardwareMap.get(Limelight3A.class, "Limelight3A");
        limelight.start();
    }

    public double getResultTX() {
        LLResult result = limelight.getLatestResult();
        if(result != null && result.isValid()){
            telemetry.addData("tx", result.getTx());
            return result.getTx();
        }
        return 0;
    }
}