package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import java.util.Arrays;

public class GoatsLameCode extends LinearOpMode{

    //Declaring motors/servos for use in the program
    private DcMotor LFDrive = null;
    private DcMotor RFDrive = null;
    private DcMotor arm = null;
    private DcMotor spinner = null;
    private Servo    lClaw    = null;
    private Servo    rClaw   = null;
    double servoRotate = 0;

    //middle of servo and speed of servos
    public static final double midServo   =  0.0 ;
    public static final double servoSpeed  = 0.02 ;

    @Override

    public void runOpMode() {

        //Declare the HW variables - Remember to have proper device names
        LFDrive  = hardwareMap.get(DcMotor.class, "LFDrive");
        RFDrive  = hardwareMap.get(DcMotor.class, "RFDrive");
        arm  = hardwareMap.get(DcMotor.class, "arm");
        lClaw  = hardwareMap.get(Servo.class, "left_hand");
        rClaw = hardwareMap.get(Servo.class, "right_hand");
        spinner = hardwareMap.get(DcMotor.class, "spinner");

        //Sets the position of the servos to the middle


        //Reverses the direction of the drive motors because they are physically flipped
        LFDrive.setDirection(DcMotor.Direction.REVERSE);
        RFDrive.setDirection(DcMotor.Direction.FORWARD);

        // Wait till game start
        waitForStart();

        //Start of main loop
        while (opModeIsActive()){

            //Mapping+getting inputs the sticks to the drive variables
            double forward = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;

            //Uses stick input to calculate how much power each motor should move
            double LFPower = ( forward + turn );
            double RFPower = ( forward - turn );

            //Normalizes the variables so power will not exceed 1 (100%) to maintain proper motion
            double[] powerArray = { Math.abs(LFPower), Math.abs(RFPower), 1 };
            double max = Arrays.stream(powerArray).max().getAsDouble();

            //Sends out normalized power to the motors
            LFDrive.setPower(LFPower/max);
            RFDrive.setPower(RFPower/max);

            //Start of servo's code
            //checks for gamepad input and adds speed to the servo
            if (gamepad2.a)
               servoRotate += servoSpeed;
            if (gamepad2.b)
                servoRotate -= servoSpeed;

            if (gamepad2.x)
                spinner.setPower(0.5);
            else if (gamepad2.y)
                spinner.setPower(-0.5);
            else
                spinner.setPower(0);
                

            //outputs the speed to the servo and make sure no value goes over 1
            servoRotate = Range.clip(servoRotate, 0, 0.7);
            lClaw.setPosition(1-servoRotate);
            rClaw.setPosition(0+servoRotate);

            // checks for input from the bumpers of the controller, if it is detected it adds power to the arm motor
            //if (gamepad2.right_bumper)
                //arm.setPower(0.5);
            //else if (gamepad2.left_bumper)
                //arm.setPower(-0.5);
            //else
                //arm.setPower(0.0);

            //declaring power variables for the arm
            double armUpPower;


            //takes the input from the triggers and sets them to the power variables
            armUpPower = -gamepad2.left_stick_y/1.6;

            //outputs the power variables to the arm
            arm.setPower(-armUpPower);

        }

    }

}