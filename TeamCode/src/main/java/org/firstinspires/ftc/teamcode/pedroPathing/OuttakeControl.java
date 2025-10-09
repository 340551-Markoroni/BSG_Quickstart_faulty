package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

    @TeleOp(name="Outtake Launcher", group="TeleOp")
    public class OuttakeControl extends OpMode {
        private DcMotor launcherMotor;

        @Override
        public void init() {
            launcherMotor = hardwareMap.get(DcMotor.class, "launcherMotor"); // Change "launcherMotor" to match your configuration
            launcherMotor.setPower(0); // Ensure motor is off at start
        }

        @Override
        public void loop() {
            // Press and hold B to run the intake
            if (gamepad1.b) {
                launcherMotor.setPower(1.0); // Full power
            } else { // Let go of B to stop the intake
                launcherMotor.setPower(0.0); // stop motor / off
            }

            telemetry.addData("Launcher Power", launcherMotor.getPower());
        }
    }
