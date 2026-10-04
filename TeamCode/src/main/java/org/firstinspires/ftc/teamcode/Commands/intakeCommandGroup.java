package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.Libraries.MMLib.MMRobot;


public class intakeCommandGroup {
    public static Command intakeCommand() {
        return new ParallelCommandGroup(
                MMRobot.getInstance().intakeSubsystem.setPowerCommand(1)
        );
    }
    public static Command disableIntakeSystems() {
        return MMRobot.getInstance().intakeSubsystem.setPowerCommand(0);
    }
}





