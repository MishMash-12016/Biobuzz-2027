package org.firstinspires.ftc.teamcode.Auto;
import static com.pedropathing.api.Paths.*;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
public class Pathes {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose redFarStart = poseFactory.of(60, 9, 90);
    private final Pose redFarParking = poseFactory.of(9.9161, 99.34, 180);
    private final Pose redFarParkingControlPoint = poseFactory.of(10.8924, 59.2547, 0);


    public Path path1() {
        return curve(redFarStart, redFarParkingControlPoint, redFarParking).linear(redFarStart, redFarParking);
    }

}
