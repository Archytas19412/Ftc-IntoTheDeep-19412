package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "Archy's Auto")

public class ArchytasAuto extends LinearOpMode {


    @Override
    public void runOpMode() {
        DcMotor leftFrontDrive = hardwareMap.get(DcMotor.class, "lfDrive");
        DcMotor leftBackDrive = hardwareMap.get(DcMotor.class, "lbDrive");
        DcMotor rightFrontDrive = hardwareMap.get(DcMotor.class, "rfDrive");
        DcMotor rightBackDrive = hardwareMap.get(DcMotor.class, "rbDrive");

        waitForStart();

        leftFrontDrive.setPower(1);
        leftBackDrive.setPower(1);
        rightFrontDrive.setPower(1);
        rightBackDrive.setPower(1);

    sleep(1000);

}
}