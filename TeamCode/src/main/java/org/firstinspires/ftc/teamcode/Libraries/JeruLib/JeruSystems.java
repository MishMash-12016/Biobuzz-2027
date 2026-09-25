package org.firstinspires.ftc.teamcode.Libraries.JeruLib;

import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.geometry.Rotation2d;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Libraries.CuttlefishFTCBridge.src.devices.CuttleRevHub;
import org.firstinspires.ftc.teamcode.Libraries.JeruLib.Utils.OpModeType;
import org.firstinspires.ftc.teamcode.SubSystems.DriveTrain;
import org.firstinspires.ftc.teamcode.SubSystems.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.SubSystems.IntakeSubsystem;

public class JeruSystems {
    private final String controlHubName = "Control Hub";
    public CuttleRevHub controlHub;
    private final String expansionHubName = "Expansion Hub 2";
    public CuttleRevHub expansionHub;
    private final String servoHub1Name = null;//TODO: Servo Hub 3
    public CuttleRevHub servoHub1;
    private final String servoHub2Name = null;
    public CuttleRevHub servoHub2;


    public HardwareMap hardwareMap;
    public Telemetry telemetry;
    public GamepadEx gamepadEx1;
    public GamepadEx gamepadEx2;
    public VoltageSensor battery;
    public GoBildaPinpointDriver localizer;

    private void initDriveTrainDefaultCommand() {

        DriveTrain.getInstance().setDefaultCommand(
                DriveTrain.getInstance().fieldOrientedDriveCommand());

    }

    private void initSystems(OpMode opMode) {
        //TODO:may need to change name based on your control and expansion hubs name
        this.controlHub = new CuttleRevHub(hardwareMap, controlHubName);
        if (JeruRobot.getInstance().opModeType != OpModeType.EXPERIMENTING_NO_EXPANSION &&
            JeruRobot.getInstance().opModeType != OpModeType.EXPERIMENTING_NO_EXPANSION_NO_SERVOHUB) {
                this.expansionHub = new CuttleRevHub(hardwareMap, expansionHubName);
        }

        if (JeruRobot.getInstance().opModeType != OpModeType.EXPERIMENTING_NO_SERVOHUB ||
            JeruRobot.getInstance().opModeType != OpModeType.EXPERIMENTING_NO_EXPANSION_NO_SERVOHUB) {
                if (servoHub1Name != null) {
                    this.servoHub1 = new CuttleRevHub(hardwareMap, servoHub1Name);
                }
                if (servoHub2Name != null) {
                    this.servoHub2 = new CuttleRevHub(hardwareMap, servoHub2Name);
                }
        }

        gamepadEx1 = new GamepadEx(opMode.gamepad1);
        gamepadEx2 = new GamepadEx(opMode.gamepad2);

        battery = hardwareMap.voltageSensor.iterator().next();

//        initSubsystems();
    }
    //TODO: don't use while experimenting, there will be errors in the configuration
    private void initSubsystems(){
        ShooterSubsystem.getInstance();
        DriveTrain.getInstance();
        IntakeSubsystem.getInstance();
    }
    private void initLocalize(Pose currentPose) {
        localizer = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
        localizer.resetPosAndIMU();
        localizer.setOffsets(-99, 9, DistanceUnit.INCH);
        localizer.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        localizer.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        localizer.setPosition(new Pose2D(DistanceUnit.INCH,0, 0, AngleUnit.DEGREES,JeruRobot.getInstance().localizer.getHeading(AngleUnit.DEGREES) - 90));
    }

    protected void initJeruSystems(OpMode opMode) {
        initSystems(opMode);
        if (JeruRobot.getInstance().initDriveTrain) {
            initDriveTrainDefaultCommand();
        }
        initLocalize(new Pose(0,0,0));
    }
}
