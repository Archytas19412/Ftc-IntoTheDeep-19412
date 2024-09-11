package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Archy's Auto(1)")

public class ArchytasAuto1 extends LinearOpMode {
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor clawLift = null;
    private ElapsedTime runtime = new ElapsedTime();
    double timeout = 31;


    @Override
    public void runOpMode() {
        leftFrontDrive = hardwareMap.get(DcMotor.class, "lfDrive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "lbDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rfDrive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rbDrive");
        clawLift = hardwareMap.get(DcMotor.class, "clawlift");
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        clawLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        clawLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        /*leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);*/
        waitForStart();
        while (opModeIsActive()) {
            clawLift.setTargetPosition(-100);
            clawLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            clawLift.setPower(0.4);
            while (clawLift.isBusy()){
            }
            clawLift.setPower(0);

            Drive(878, 878, 878, 878, 0.4);
            sleep(500);
            Drive(-3746, -3746, -3746, -3746, 0.4);
            sleep(30000);

        }
    }


        public void Drive ( int FrontLTarget, int FrontRTarget, int BackLTarget, int BackRTarget,
        double Speed){
            leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            leftFrontDrive.setTargetPosition(FrontLTarget);
            rightFrontDrive.setTargetPosition(FrontRTarget);
            leftBackDrive.setTargetPosition(BackLTarget);
            rightBackDrive.setTargetPosition(BackRTarget);

            leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            leftFrontDrive.setPower(Speed);
            rightFrontDrive.setPower(Speed);
            leftBackDrive.setPower(Speed);
            rightBackDrive.setPower(Speed);

            while (opModeIsActive() && (rightBackDrive.isBusy() || leftBackDrive.isBusy() || rightFrontDrive.isBusy() || leftFrontDrive.isBusy())) {
                idle();
            }

            rightBackDrive.setPower(0);
            leftBackDrive.setPower(0);
            rightFrontDrive.setPower(0);
            leftFrontDrive.setPower(0);
        }
    }

