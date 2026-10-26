package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMRobot;

public class ShootCommandGroup {
    public static Command shootAll() {
        return new SequentialCommandGroup(
                MMRobot.getInstance().stopperSubsystem.open(),
                new WaitCommand(100),
                MMRobot.getInstance().intakeSubsystem.setPowerCommand(1),
                MMRobot.getInstance().transferSubsystem.setPowerCommand(1)
        );

    }

}
