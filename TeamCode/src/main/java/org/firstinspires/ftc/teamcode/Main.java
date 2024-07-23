package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.subsystems.CameraController;
import org.firstinspires.ftc.teamcode.subsystems.IMUController;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.ArmController;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name="Main TeleOp", group = "Concept")
public class  Main extends LinearOpMode {

    private IMU robotImu;

    private CameraController webcamOne;

    private LinearSlides slide = new LinearSlides();

    @Override
    public void runOpMode() {
        //webcamOne = new CameraController(hardwareMap.get(WebcamName.class, "Webcam 1"));

        //ControllerIMU.initIMU(hardwareMap.get(IMU.class,"imu"), 90);
        LinearSlides.initialiseSlide(hardwareMap.get(DcMotor.class,"L1"));

        // A Back Right, need to reverse
        // B Front Left. don't need to reverse
        // X Back Left, don't need to reverse
        // Y Front Right, don't need to reverse
        MecanumDrive.initialiseMotors(
                hardwareMap.get(DcMotor.class,"X"), // Back Left
                hardwareMap.get(DcMotor.class,"B"), // Front Left
                hardwareMap.get(DcMotor.class,"Y"), // Front Right
                hardwareMap.get(DcMotor.class,"A") // Back Right
                );
//        ArmController.initialiseArmServo(
//                hardwareMap.get(CRServo.class,"CR1"),
//                hardwareMap.get(CRServo.class,"CR2")
//                );

        waitForStart();

        //ControllerIMU.resetIMU();
        LinearSlides.resetEncoder();

        while (opModeIsActive()) {
            //subsystems

            MecanumDrive.runMotors(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);
            //ControllerIMU.getAdjustedHeading();
            LinearSlides.setActive(gamepad1.left_bumper, gamepad1.right_stick_y);
            LinearSlides.resetEncoder(gamepad1.right_bumper);
            //ArmController.armSuck(gamepad1.a,gamepad2.b);
            telemetry.addLine("Gamepad values:" + gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);


            telemetry.update();
        }

    }

}
