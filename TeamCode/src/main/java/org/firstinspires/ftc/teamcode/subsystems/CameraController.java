package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.graphics.Camera;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.utils.Vector2D;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.HashMap;
import java.util.List;

public class CameraController {

    private static AprilTagProcessor aprilTag;
    private static VisionPortal visionPortal;

    static HashMap<Integer, Vector2D> aprilTags = new HashMap<>();

    public CameraController(WebcamName Camera) { // e.g., camera may be hardwareMap.get(WebcamName.class, "Webcam 1")
        aprilTags.put(1,new Vector2D(43,100,true));
        aprilTags.put(2,new Vector2D(22,103,true));
        aprilTags.put(3,new Vector2D(234,30,true));

        aprilTag = new AprilTagProcessor.Builder().build();

        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        builder.setCamera(Camera);

        // initialise vision portal
        builder.addProcessor(aprilTag);

        visionPortal = builder.build();

    }

    public static Vector2D processAprilTags(double adjustedYaw) {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if ((detection.metadata != null) && aprilTags.containsKey(detection.id)) {

//                telemetry.addLine(String.format("\n==== (ID %d)", detection.id));
//                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
//                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
//                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));

                Vector2D aprilTagVec = aprilTags.get(detection.id); // april tag one

                double m = detection.ftcPose.range;
                double d = adjustedYaw - detection.ftcPose.bearing;

                Vector2D currentVec = new Vector2D(m,d,false);
                currentVec.add(aprilTagVec);

                return currentVec;


            } else {
                return new Vector2D(0,0,false);
            }
        }   // end for() loop

        // Add "key" information to telemetry
//        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
//        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
//        telemetry.addLine("RBE = Range, Bearing & Elevation");

        return null;
    }
}
