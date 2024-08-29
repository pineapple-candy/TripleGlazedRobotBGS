package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.ArmController;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;

@Autonomous(name="Triple Glazed #1 Auto")
public class AutonomousTRIPLE extends LinearOpMode {

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

        waitForStart();

        // Start autonomous

        while (MecanumDrive.moveForward(20,0.4));



    }
}
