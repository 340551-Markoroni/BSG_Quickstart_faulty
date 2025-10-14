package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Intake & Outtake TeleOp")
public class MotorControlAll extends OpMode {

    private DcMotor intakeMotor;
    private DcMotor outtakeLeftMotor;
    private DcMotor outtakeRightMotor;
    private Servo launcherServo;

    // Servo positions for 0 and 45 degrees (adjust as needed for your servo)
    private static final double SERVO_DOWN_POS = 0.0;
    private static final double SERVO_UP_POS = 0.25; // Example for 45°, calibrate for your setup

    private boolean servoMoving = false;
    private long servoStartTime = 0;

    @Override
    public void init() {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        outtakeLeftMotor = hardwareMap.get(DcMotor.class, "outtakeLeftMotor");
        outtakeRightMotor = hardwareMap.get(DcMotor.class, "outtakeRightMotor");
        launcherServo = hardwareMap.get(Servo.class, "launcherServo");

        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtakeRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Outtake motors run opposite directions
        outtakeLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        outtakeRightMotor.setDirection(DcMotor.Direction.REVERSE);

        launcherServo.setPosition(SERVO_DOWN_POS);
    }

    @Override
    public void loop() {
        // Press and hold A to run the intake
        if (gamepad1.a) {
            intakeMotor.setPower(1.0);
        } else {
            intakeMotor.setPower(0.0);
        }

        // Outtake control (gamepad1.b)
        if (gamepad1.b) {
            outtakeLeftMotor.setPower(1.0);
            outtakeRightMotor.setPower(1.0);
        } else {
            outtakeLeftMotor.setPower(0.0);
            outtakeRightMotor.setPower(0.0);
        }

        // Servo timed action (gamepad1.y)
        if (gamepad1.y && !servoMoving) {
            launcherServo.setPosition(SERVO_UP_POS);
            servoMoving = true;
            servoStartTime = System.currentTimeMillis();
        }

        // Handle servo timing
        if (servoMoving) {
            if (System.currentTimeMillis() - servoStartTime > 2000) { // 2 seconds
                launcherServo.setPosition(SERVO_DOWN_POS);
                servoMoving = false;
            }
        }

        // Telemetry for debugging
        telemetry.addData("Intake Power", intakeMotor.getPower());
        telemetry.addData("Outtake Left Power", outtakeLeftMotor.getPower());
        telemetry.addData("Outtake Right Power", outtakeRightMotor.getPower());
        telemetry.addData("Servo Pos", launcherServo.getPosition());
        telemetry.addData("Servo Moving", servoMoving);
        telemetry.update();
    }
}