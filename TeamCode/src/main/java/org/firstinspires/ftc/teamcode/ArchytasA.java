package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;

@TeleOp(name="Archy's SMALL Drive")

public class ArchytasA extends LinearOpMode {

    // 4 drive motors
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    //2 claw motors + 2 claw variables for input (used later)
    private DcMotor clawLift = null;
    private DcMotor clawExtend = null;

    private Servo Lgrabber = null;
    private Servo Rgrabber = null;
    float clawOut = 0;
    float clawUp = 0;
    double servoSpeed  = 0.04 ;
    double servoRotate = 0;


    @Override
    public void runOpMode() {

        // starts all of the hardware variables
        leftFrontDrive = hardwareMap.get(DcMotor.class, "lfDrive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "lbDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rfDrive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rbDrive");
        clawLift = hardwareMap.get(DcMotor.class, "clawLift");
        clawExtend = hardwareMap.get(DcMotor.class, "clawExtend");
        Lgrabber = hardwareMap.get(Servo.class, "Lgrabber");
        Rgrabber = hardwareMap.get(Servo.class, "Rgrabber");
        clawLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        clawExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //sets the lift and extend motor to break when 0 power is applied
        clawExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lgrabber.setPosition(0);
        Rgrabber.setPosition(1);
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

            // Send and calculate power to wheels
            leftFrontDrive.setPower(leftFrontPower / max);
            rightFrontDrive.setPower(rightFrontPower / max);
            leftBackDrive.setPower(leftBackPower / max);
            rightBackDrive.setPower(rightBackPower / max);

            clawExtend.setPower(gamepad2.left_stick_y);
            //clawLift.setPower(-gamepad2.right_stick_y);

            //start of claw code
           //gamepad2.left_stick_y = clawOut;
            //while the stick input is above/below 0.05 and -0.05
            // the claw will extend/retract when it is between the encoder positions but wont move when outside the positions
            /*while (( gamepad2.left_stick_y > 0.05) || (gamepad2.left_stick_y < -0.05)) {
                if ( clawExtend.getCurrentPosition() < 3000 && clawExtend.getCurrentPosition() > 0) {
                    clawExtend.setPower(-gamepad2.left_stick_y);
                }
                else if (clawExtend.getCurrentPosition() > 3000){
                    clawExtend.setPower(0);
                }
                else if (clawExtend.getCurrentPosition() < 0){
                    clawExtend.setPower(0);
                }
            }*/


            //gamepad2.right_stick_y = clawUp;
            //while the stick input is above/below 0.05 and -0.05
            // the claw will lift when it is between the encoder positions but wont move when outside the positions
            if ((gamepad2.right_stick_y> 0.07) || (gamepad2.right_stick_y < -0.07)) {
                if ( clawLift.getCurrentPosition() >= -4700 && clawLift.getCurrentPosition() <= 150) {
                    clawLift.setPower(-gamepad2.right_stick_y);
                }
                else if (clawLift.getCurrentPosition() < -4700){
                    clawLift.setPower(0);
                }
                else if (clawLift.getCurrentPosition() > 50){
                    clawLift.setPower(0);
                }

            }
            if (gamepad2.left_bumper){
                clawLift.setPower(0.3);

            }


            if (gamepad2.a) {
                Lgrabber.setPosition(0);
                Rgrabber.setPosition(1);
            }
            if(gamepad2.b){
                Lgrabber.setPosition(0.35);
                Rgrabber.setPosition(0.6);
            }


        }
    }
}

