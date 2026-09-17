package org.firstinspires.ftc.teamcode.Auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruOpMode;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.JeruRobot;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.Utils.OpModeType;
import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;

@Autonomous
public class RedFar extends JeruOpMode {
    Follower follower;
    JeruRobot robotInstance;
    @Override
    public void initialize() {
        robotInstance = JeruRobot.getInstance();
        robotInstance.initJeruRobot()
                .opModeType(OpModeType.EXPERIMENTING_NO_EXPANSION)
                .build(this);
    }

    @Override
    public void run() {
        super.run();
        follower = DriveTrain.getInstance().getFollower();

    }
}
