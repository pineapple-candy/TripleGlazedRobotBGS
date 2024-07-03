package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.subsystems.CameraController;
import org.firstinspires.ftc.teamcode.subsystems.ControllerIMU;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name="Main TeleOp", group = "Concept")
public class Main extends LinearOpMode {

    private IMU robotImu;

    private CameraController webcamOne;

    @Override
    public void runOpMode() {
        ControllerIMU.initIMU(hardwareMap.get(IMU.class,"imu"), 90);

        webcamOne = new CameraController(hardwareMap.get(WebcamName.class, "Webcam 1"));

        LinearSlides.initialiseSlide(hardwareMap.get(DcMotor.class,"L1"));

        waitForStart();

        ControllerIMU.resetIMU();

        while (opModeIsActive()) {
            MecanumDrive.runMotors(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);
            ControllerIMU.getAdjustedHeading();
            LinearSlides.setActive(gamepad1.left_bumper);
        }

    }

}
// make sure you do camer athingy