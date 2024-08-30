package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.subsystems.CameraController;
import org.firstinspires.ftc.teamcode.subsystems.IMUController;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlides;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.utils.Util;
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

    //private LinearSlides slide = new LinearSlides();

    @Override
    public void runOpMode() {
        //webcamOne = new CameraController(hardwareMap.get(WebcamName.class, "Webcam 1"));
        //ControllerIMU.initIMU(hardwareMap.get(IMU.class,"imu"), 90);

        LinearSlides.initialiseSlide(hardwareMap.get(DcMotor.class,"L1"));

        // 0 back left, reverse
        // 1 front left, no rev
        // 2 front right, reverse
        // 3 back right, no rev
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

        waitForStart();

        //ControllerIMU.resetIMU();
        LinearSlides.resetEncoder();
        MecanumDrive.resetMecanumEncoder();

        while (opModeIsActive()) {
            //subsystems
            //ControllerIMU.getAdjustedHeading();
            //LinearSlides.resetEncoder(gamepad1.right_bumper);


            MecanumDrive.runMotors(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x, gamepad1.x, gamepad1.y);
            LinearSlides.setActive(gamepad1.left_bumper, -gamepad1.right_stick_y);
            ArmController.armSuck(gamepad1.a,gamepad1.b);


            telemetry.addLine("Gamepad values:" + gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            telemetry.addLine(LinearSlides.getDebug());
            telemetry.addLine(MecanumDrive.getDebug());
            telemetry.update();
        }

    }

}
//75mm