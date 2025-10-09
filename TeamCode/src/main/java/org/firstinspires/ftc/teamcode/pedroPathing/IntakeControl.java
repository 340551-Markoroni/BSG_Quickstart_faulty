package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Intake Control", group="TeleOp")
    public class IntakeControl extends OpMode {
        private DcMotor intakeMotor;

        @Override
        public void init() {
            // Make sure the name matches your configuration!
            intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
            intakeMotor.setPower(0);
        }

        @Override
        public void loop() {
            // Press and hold A to run the intake
            if (gamepad1.a) {
                intakeMotor.setPower(1.0); // full speed forward
            } else { // Let go of A to stop the intake
                intakeMotor.setPower(0.0); // stop motor / off
            }

            telemetry.addData("Intake Power", intakeMotor.getPower());
        }
    }
