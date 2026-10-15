package org.firstinspires.ftc.teamcode.OpMode.Auto;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.Commands.IntakeCommandGroup;
import org.firstinspires.ftc.teamcode.Commands.ShootCommandGroup;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMOpMode;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMRobot;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.AllianceColor;
import org.firstinspires.ftc.teamcode.Libraries.MMLib.Utils.OpModeType;
import org.firstinspires.ftc.teamcode.Libraries.pedroPathing.FollowPathCommand;

@Autonomous
public class BasicAuto extends MMOpMode {
    public PathChain
            PATH_1,
            PATH_2,
            PATH_3;
    private MMRobot robotInstance;
    private Follower follower;
    private final Pose startPos = new Pose(20.50, 121.00, Math.toRadians(324));

    public void buildPaths() {
        PATH_1 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(00, 000),
                                new Pose(00, 00)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(324), Math.toRadians(312))
                .build();

        PATH_2 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(0, 0),
                                new Pose(0, 0)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(312), Math.toRadians(180))
                .build();

        PATH_3 = follower.pathBuilder().addPath(
                        new BezierLine(
                                new Pose(0, 0),
                                new Pose(0, 0)
                        )
                ).setLinearHeadingInterpolation(Math.toRadians(312), Math.toRadians(180))
                .build();
    }

    @Override
    public void initialize() {
        robotInstance = MMRobot.getInstance();
        robotInstance.initJeruRobot()
                .angle(0)
                .allianceColor(AllianceColor.BLUE)
                .opModeType(OpModeType.AUTO)
                .build(this);
        CommandScheduler.getInstance().reset();
    }

    @Override
    public void run() {
        super.run();
        follower = MMRobot.getInstance().driveTrain.getFollower();
        buildPaths();

        Command autonomousSequence =
                /// things that will happened along the auto   ↓
                new ParallelCommandGroup(

                        ///                                    ↑
                        new SequentialCommandGroup(
                                new FollowPathCommand(follower, PATH_1)
                                        .andThen(ShootCommandGroup.shootAll()),
                                new FollowPathCommand(follower, PATH_2)
                                        .andThen(IntakeCommandGroup.basicFeed()),
                                new FollowPathCommand(follower, PATH_2)
                                        .andThen(ShootCommandGroup.shootAll())
                        )
                );

        autonomousSequence.schedule();
    }
}
/*
    @Override
    public void onPlay() {
        MMDrivetrain.getInstance().getFollower().setStartingPose(startPos);
        MMDrivetrain.getInstance().getFollower().setPose(startPos);
        follower = MMDrivetrain.getInstance().getFollower();


    @Override
    public void onEnd() {
        super.onEnd();
        CommandScheduler.getInstance().reset();
    }
}*/