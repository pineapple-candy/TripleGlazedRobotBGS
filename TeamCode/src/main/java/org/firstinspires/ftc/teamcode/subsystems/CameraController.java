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

    static HashMap<Integer, Integer> aprilTags = new HashMap<>();

    private static double lastSeen = 30;

    public static void initCamera(WebcamName Camera) { // e.g., camera may be hardwareMap.get(WebcamName.class, "Webcam 1")
        aprilTags.put(0,0);
        aprilTags.put(1,1);
        aprilTags.put(2,2);

        aprilTag = new AprilTagProcessor.Builder().build();

        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        builder.setCamera(Camera);

        // initialise vision portal
        builder.addProcessor(aprilTag);

        visionPortal = builder.build();

    }

    public static double processAprilTags() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if ((detection.metadata != null)) {

                int aprilTagNum = detection.id; // april tag one
                lastSeen = aprilTagNum;

                return aprilTagNum;


            } else {
                return 30;
            }
        }
        return 30;
    }

    public static double getLastSeen() {
        return lastSeen;
    }

    public static String getDebug() {
        return "Last Seen Tag: " + lastSeen;
    }
}
