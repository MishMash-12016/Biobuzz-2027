package org.firstinspires.ftc.teamcode.OpMode.Auto;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMOpMode;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMRobot;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.AllianceColor;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.OpModeType;
@Autonomous
public class Red6plusHotdog extends MMOpMode {
    private MMRobot robotInstance;
    private Follower follower;
    @Override
    public void initialize() {
        robotInstance = MMRobot.getInstance();
        robotInstance.initJeruRobot()
                .angle(0)
                .allianceColor(AllianceColor.BLUE)
                .opModeType(OpModeType.AUTO)
                .build(this);
    }

    @Override
    public void run() {
        super.run();
        follower = MMRobot.getInstance().driveTrain.getFollower();
    }
}
