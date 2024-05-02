/* Copyright (c) 2021 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name="Draft: Robot Controller", group="Draft")
public class DriveDraft extends LinearOpMode {


    //Motor Setup Pt.1
    private DcMotor         backLeft  = null;
    private DcMotor         backRight   = null;
    private ElapsedTime     runtime = new ElapsedTime();
    AprilTagProcessor myAprilTagProcessor;

    static final double     FORWARD_SPEED = 0.6;
    static final double     TURN_SPEED    = 0.5;
    private DcMotor         frontLeft  = null;
    private DcMotor         frontRight   = null;

    //instantise the speed variables
    private float frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed;
    private float SpeedMax = 1;

    @Override
    public void runOpMode() {
        //Motor Setup Pt.2
        backLeft  = hardwareMap.get(DcMotor.class, "BackLeft");
        backRight = hardwareMap.get(DcMotor.class, "BackRight");
        frontLeft  = hardwareMap.get(DcMotor.class, "FrontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "FrontRight");


// Create a VisionPortal, with the specified camera and AprilTag processor, and assign it to a variable.


        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive())   {
            //Calculate motor speed based off controller input
            float drive = 0 - gamepad1.left_stick_y;
            float turn = gamepad1.right_stick_x;
            float strafe = gamepad1.left_stick_x;

            //Calculate and assign the speeds
            frontLeftSpeed = drive + turn + strafe;
            frontRightSpeed = drive - turn - strafe;
            backLeftSpeed = drive + turn - strafe;
            backRightSpeed = drive - turn + strafe;

            //Cap the variables to avoid saturation and keep the ratio of movement
            float maxSpeed1 = Math.max(frontLeftSpeed, frontRightSpeed);
            float maxSpeed2 = Math.max(backLeftSpeed, backRightSpeed);
            float maxSpeed = Math.max(maxSpeed1, maxSpeed2);

            //Divide to keep the ratio
            if (maxSpeed > SpeedMax) {
                frontLeftSpeed = frontLeftSpeed / maxSpeed;
                frontRightSpeed = frontRightSpeed / maxSpeed;
                backLeftSpeed = backLeftSpeed / maxSpeed;
                backRightSpeed = backLeftSpeed / maxSpeed;
            }
            //Set MotorSpeeds
            backLeft.setPower(backLeftSpeed);
            backRight.setPower(backRightSpeed);
            frontLeft.setPower(frontLeftSpeed);
            frontRight.setPower(frontRightSpeed);
            


        }

    }


}