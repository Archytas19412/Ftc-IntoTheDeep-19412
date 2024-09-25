package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Archy's Auto Right Side (s)")

public class ArchytasAutoLS extends LinearOpMode {
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
        clawLift = hardwareMap.get(DcMotor.class, "clawLift");
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        clawLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

            Claw(-100,-0.3);


            //drive backwards and pause
            Drive(-978, -978, -978, -978, 0.4);
            sleep(500);
            //strafe left
            Drive(-1110, 1110, 1110,-1110, 0.3);
            sleep(500);
            //drive forward and pause
            Drive(3746, 3746, 3746, 3746, 0.4);
            sleep(500);
            //strafe Right
            Drive(1110,-1110,-1110,1110,0.3);
            sleep(1000);

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

    public void Claw (int Target, double speed){
        clawLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        clawLift.setTargetPosition(Target);

        clawLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        clawLift.setPower(speed);

        while (opModeIsActive() && (clawLift.isBusy())) {
            idle();
        }
        clawLift.setPower(0);

    }

}

