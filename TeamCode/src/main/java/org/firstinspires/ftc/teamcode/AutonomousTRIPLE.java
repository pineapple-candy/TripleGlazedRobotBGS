package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.subsystems.ArmController;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.IMUController;
import org.firstinspires.ftc.teamcode.subsystems.CameraController;

@Autonomous(name="Triple Glazed #1 Auto")
public class AutonomousTRIPLE extends LinearOpMode {

    double AprilTag;

    double distance;

    @Override
    public void runOpMode() {
        MecanumDrive.initialiseMotors(
                hardwareMap.get(DcMotor.class,"0"), // Back Left
                hardwareMap.get(DcMotor.class,"1"), // Front Left
                hardwareMap.get(DcMotor.class,"2"), // Front Right
                hardwareMap.get(DcMotor.class,"3") // Back Right
        );
        ArmController.initialiseArmServo(
                hardwareMap.get(CRServo.class,"CR1"),
                hardwareMap.get(CRServo.class,"CR2")
        );
        IMUController.initIMU(hardwareMap.get(IMU.class,"imu"), 0);
        LinearSlides.initialiseSlide(hardwareMap.get(DcMotor.class,"L1"));

        CameraController.initCamera(hardwareMap.get(WebcamName.class,"Webcam 1"));

        //auto loop - scan april tags
        while (!isStarted() && !isStopRequested()) {
            CameraController.processAprilTags();
            telemetry.addLine(CameraController.getDebug());
            telemetry.update();
        }

        waitForStart();

        LinearSlides.resetEncoder();

        AprilTag = CameraController.getLastSeen();

        telemetry.addLine("Selected: " + AprilTag);
        telemetry.update();

        sleep(2000);

        // Start autonomous
        IMUController.resetIMU();

        MecanumDrive.resetMecanumEncoder();
        while (MecanumDrive.moveDistance(75,0.4));

        sleep(1000);

        IMUController.resetIMU();
        while (MecanumDrive.rotate(-90,IMUController.getAdjustedHeading(),0.5));

        sleep(1000);

        MecanumDrive.resetMecanumEncoder();
        while (MecanumDrive.moveDistance(60*(AprilTag+1),0.4));

        sleep(1000);

        IMUController.resetIMU();
        while (MecanumDrive.rotate(-90,IMUController.getAdjustedHeading(),0.5));

        sleep(1000);

        MecanumDrive.resetMecanumEncoder();
        while (MecanumDrive.moveDistance(12,0.2)) {
               LinearSlides.setTarget(-840);
               LinearSlides.updateSlide();
        }

        sleep(1000);

        ArmController.armSpit();

        sleep(1000);

        MecanumDrive.resetMecanumEncoder();
        while (MecanumDrive.moveDistance(12,-0.2));

        sleep(1000);

        MecanumDrive.resetMecanumEncoder();
        while (MecanumDrive.moveDistance(5,-0.2)) {
            LinearSlides.setTarget(0);
            LinearSlides.updateSlide();
        }

        sleep(1000);

        IMUController.resetIMU();
        while (MecanumDrive.rotate(90,IMUController.getAdjustedHeading(),0.5));

        sleep(1000);

        MecanumDrive.resetMecanumEncoder();
        while (MecanumDrive.moveDistance(60*(AprilTag+1),-0.4));

        sleep(1000);

        IMUController.resetIMU();
        while (MecanumDrive.rotate(90,IMUController.getAdjustedHeading(),0.5));

        sleep(1000);

        MecanumDrive.resetMecanumEncoder();
        while (MecanumDrive.moveDistance(200,0.5));

        requestOpModeStop();

    }
}
