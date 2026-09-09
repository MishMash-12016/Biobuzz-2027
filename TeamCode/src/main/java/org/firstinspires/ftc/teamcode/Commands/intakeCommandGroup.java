package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.SubSystems.armsSubsystem;
import org.firstinspires.ftc.teamcode.SubSystems.intakeSubsystem;


public class intakeCommandGroup {
    public static Command intakeCommand() {
        return new ParallelCommandGroup(
                intakeSubsystem.getInstance().setPowerCommand(1),
                armsSubsystem.getInstance().setPositionCommand(armsSubsystem.closed)
        );
    }
    public static Command disableIntakeSystems() {
        return intakeSubsystem.getInstance().setPowerCommand(0);
    }
}





