package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Arrays;

@TeleOp(name="Archy's SMALL Drive")

public class ArchytasA extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    @Override
    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
       leftFrontDrive  = hardwareMap.get(DcMotor.class, "lfDrive");
       leftBackDrive  = hardwareMap.get(DcMotor.class, "lbDrive");
       rightFrontDrive = hardwareMap.get(DcMotor.class, "rfDrive");
       rightBackDrive = hardwareMap.get(DcMotor.class, "rbDrive");



        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
            leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
            rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
            rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double forward   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double strafe =  gamepad1.left_stick_x;
            double turn     =  gamepad1.right_stick_x;

            // Combine the joystick requests for each axis motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.w
            double leftFrontPower  = (-forward - strafe - turn);
            double rightFrontPower = (forward - strafe - turn);
            double leftBackPower   = (forward - strafe + turn);
            double rightBackPower  = (-forward - strafe + turn);

            //  Maximum possible power sent to a motor is -1 or 1, but we can sometimes get values from
            //  (forward + strafe + turn) that exceed -1 or 1, so we have to normalize all motors' power
            //  to keep their speed proportional to each other in the off-chance that this does happen
            double[] powerArray = { Math.abs(leftFrontPower), Math.abs(rightFrontPower), Math.abs(leftBackPower), Math.abs(rightBackPower), 1 };
            double max = Arrays.stream(powerArray).max().getAsDouble();

            // Send calculated power to wheels
            leftFrontDrive.setPower(leftFrontPower/max);
            rightFrontDrive.setPower(rightFrontPower/max);
            leftBackDrive.setPower(leftBackPower/max);
            rightBackDrive.setPower(rightBackPower/max);
        }
    }}
