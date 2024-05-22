
/*
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous FiniteStateMachine", group="Robot")
public class finiteStateAuto extends LinearOpMode {
    private DcMotor leftBackDrive;
    private DcMotor leftFrontDrive;
    private DcMotor rightBackDrive;
    private DcMotor rightFrontDrive;

    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;

    private IMU imu;

    // declaring variables for Finite State Machine

    private int initialScan;

    private enum autoState {
        SCAN,
        PARK,
        SCAN_AND_SEARCH,
        SCORE,
        DUMP,
        INITIAL_MOVE,
        BIN,
    };

    // mecanum-wheel encoder odemetry

    private double xValue;
    private double yValue;

    // yaw

    private double yawIMU;

    private ElapsedTime     runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBackDrive");
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBackDrive");

        rightBackDrive.setDirection(DcMotor.setMode.REVERSE);
        leftFrontDrive.setDirection(DcMotor.setMode.FORWARD);
        rightFrontDrive.setDirection(DcMotor.setMode.REVERSE);
        rightBackDrive.setDirection(DcMotor.setMode.FORWARD);

        rightBackDrive.setZeroPowerBehavior(DcMotor.setZeroPowerBehavior.BRAKE);
        leftFrontDrive.setDirection(DcMotor.setZeroPowerBehavior.BRAKE);
        rightFrontDrive.setDirection(DcMotor.setZeroPowerBehavior.BRAKE);
        rightBackDrive.setDirection(DcMotor.setZeroPowerBehavior.BRAKE);

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientationOnRobot));


        // Wait for the game to start (driver presses PLAY) (init)

        initAprilTags();

        while !(opModeIsActive()) && (opModeInInit())) {
            scanAprilTags();
        }

        waitForStart();

        // IMU stuff

        double adjustedYaw = imu.getYaw;
        yawIMU = imu.getYaw - adjustedYaw;


        autoState state = autoState.SCAN;

        int currentIdGoal = null;

        while (opModeIsActive()) {

            odometryScan();
            
            switch(state) {
                case autoState.SCAN:
                    currentIdGoal = initialScan;

                    if (currentIdGoal != null) {
                        state = autoState.DUMP;
                    } else {
                        currentIdGoal = 2;
                        state = autoState.DUMP;
                    }
                    break;
                case autoState.INITIAL_MOVE:
                    moveForward(23);
                    if __ {
                        state = autoState.DUMP
                    }

                    break;

                case autoState.PARK:


                    break;
                case autoState.SCAN_AND_SEARCH:
                    targetScan = 0000901923; // whatever number/target
                    if currentIdGoal
                    state = SCORE;
                    break;
                case autoState.DUMP:

                    state = SCAN_AND_SEARCH;
                    break;
                case autoState.SCORE:

                    break;
            }

        }


        private void initAprilTags() {
            aprilTag = aprilTagProcessor.easyCreateWithDefaults();

            visionPortal = VisionPortal.easyCreateWithDefaults(hardware.get("Webcam 1"), aprilTag);
        }

        private void scanAprilTags() {
            List<AprilTagDetection> currentDetections = aprilTag.getDetections();

            for (AprilTagDetection detection : currentDetections) {
                if (detection.metadata != null) {
                    initialScan = detection.id;
                    telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                } else {
                    // womp womp
                    telemetry.addData("NO APRIL TAG, LAST: " + String.format(initialScan));
                }
            }
            telemetry.update();
        }

        private void odometryScan() {

        }

        private void resetEncoders() {

        }

        private void moveForward(double distance) { // centermetres
            // blah blah will do later
        }

        private void adjustAngle(double angle) {
            
        }
    }

}
*/