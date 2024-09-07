package org.firstinspires.ftc.teamcode;

import com. qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "Archy's Auto")

public class ArchytasAuto extends LinearOpMode {
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private ElapsedTime runtime = new ElapsedTime();
    double timeout = 31;

    @Override
    public void runOpMode() {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "lfDrive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "lbDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rfDrive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rbDrive");

        waitForStart();

        leftFrontDrive.setPower(1);
        leftBackDrive.setPower(1);
        rightFrontDrive.setPower(1);
        rightBackDrive.setPower(1);

        autoDrive(500,500,500,500,0.3);
        sleep(1000);
    }

    public void autoDrive(int rB,int lB, int rF, int lF,double power) {

            rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            rightBackDrive.setTargetPosition(rB);
            leftBackDrive.setTargetPosition(lB);
            rightFrontDrive.setTargetPosition(rF);
            leftFrontDrive.setTargetPosition(lF);

            rightBackDrive.setPower(power);
            leftBackDrive.setPower(power);
            rightFrontDrive.setPower(power);
            leftFrontDrive.setPower(power);

            rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Blocking While Loop: doesn't break until all 4 motors have stopped moving or time has run out
            // Sets power of motors to 0 after the loop breaks
            while (opModeIsActive() && (rightBackDrive.isBusy() || leftBackDrive.isBusy() || rightFrontDrive.isBusy() || leftFrontDrive.isBusy()) && (runtime.seconds() < timeout)) {
            }

            rightBackDrive.setPower(0);
            leftBackDrive.setPower(0);
            rightFrontDrive.setPower(0);
            leftFrontDrive.setPower(0);

            sleep(100);
        }
    }
