package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelDeadlineGroup;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.SubSystems.armsSubsystem;
import org.firstinspires.ftc.teamcode.SubSystems.intakeSubsystem;
import org.firstinspires.ftc.teamcode.SubSystems.transferSubsystem;




public class intakeCommandGroup {
    public static Command intakeCommand() {
        return new ParallelCommandGroup(
                intakeSubsystem.getInstance().setPowerCommand(1),
                transferSubsystem.getInstance().setPowerCommand(0.5),
                armsSubsystem.getInstance().setPositionCommand(armsSubsystem.closed)
        );
    }
    public static Command disableIntakeSystems() {
        return new ParallelCommandGroup(
                intakeSubsystem.getInstance().setPowerCommand(0),
                transferSubsystem.getInstance().setPowerCommand(0)
        );
    }
}





