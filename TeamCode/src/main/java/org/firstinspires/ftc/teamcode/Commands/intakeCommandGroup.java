package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;

import org.firstinspires.ftc.teamcode.SubSystems.IntakeSubsystem;


public class intakeCommandGroup {
    public static Command intakeCommand() {
        return new ParallelCommandGroup(
                IntakeSubsystem.getInstance().setPowerCommand(1)
        );
    }
    public static Command disableIntakeSystems() {
        return IntakeSubsystem.getInstance().setPowerCommand(0);
    }
}





