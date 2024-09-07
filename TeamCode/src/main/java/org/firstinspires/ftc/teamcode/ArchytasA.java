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

    private DcMotor clawLift = null;
    private DcMotor clawExtend = null;
    float clawOut = 0;
    float clawUp = 0;


    @Override
    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontDrive = hardwareMap.get(DcMotor.class, "lfDrive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "lbDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rfDrive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rbDrive");
        clawLift = hardwareMap.get(DcMotor.class, "clawlift");
        clawExtend = hardwareMap.get(DcMotor.class, "clawExtend");
        clawExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
            leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
            rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
            rightBackDrive.setDirection(DcMotor.Direction.REVERSE);


            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double forward = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            // Combine the joystick requests for each axis motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.w
            double leftFrontPower = (-forward - strafe - turn);
            double rightFrontPower = (forward - strafe - turn);
            double leftBackPower = (forward - strafe + turn);
            double rightBackPower = (-forward - strafe + turn);

            //  Maximum possible power sent to a motor is -1 or 1, but we can sometimes get values from
            //  (forward + strafe + turn) that exceed -1 or 1, so we have to normalize all motors' power
            //  to keep their speed proportional to each other in the off-chance that this does happen
            double[] powerArray = {Math.abs(leftFrontPower), Math.abs(rightFrontPower), Math.abs(leftBackPower), Math.abs(rightBackPower), 1};
            double max = Arrays.stream(powerArray).max().getAsDouble();

            // Send calculated power to wheels
            leftFrontDrive.setPower(leftFrontPower / max);
            rightFrontDrive.setPower(rightFrontPower / max);
            leftBackDrive.setPower(leftBackPower / max);
            rightBackDrive.setPower(rightBackPower / max);

            //start of claw code
            gamepad2.left_stick_y = clawOut;
            //while the stick input is above/below 0.05 and -0.05
            // the claw will extend/retract when it is between the encoder positions but wont move when outside the positions
            while ((clawOut > 0.05) || (clawOut < -0.05)) {
                if ( clawExtend.getCurrentPosition() < 1000 && clawExtend.getCurrentPosition() > 0) {
                    clawExtend.setPower(-clawOut);
                }
                if (clawExtend.getCurrentPosition() > 1000){
                    clawExtend.setPower(0);
                }
                if (clawExtend.getCurrentPosition() < 0){
                    clawExtend.setPower(0);
                }
            }


            gamepad2.right_stick_y = clawUp;
            //while the stick input is above/below 0.05 and -0.05
            // the claw will lift when it is between the encoder positions but wont move when outside the positions
            while ((clawUp> 0.05) || (clawUp < -0.05)) {
                if ( clawLift.getCurrentPosition() < 1000 && clawLift.getCurrentPosition() > 0) {
                    clawLift.setPower(-clawOut);
                }
                else if (clawLift.getCurrentPosition() > 1000){
                    clawLift.setPower(0);
                }
                else if (clawLift.getCurrentPosition() < 0){
                    clawLift.setPower(0);
                }
            }
        }
    }
}
