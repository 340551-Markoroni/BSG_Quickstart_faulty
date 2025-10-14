package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Servo Inbetween Pusher", group="TeleOp")
public class ServoInbetweenControl extends OpMode {
    private Servo inbetween;
    private boolean isPushing = false;
    private long pushStartTime = 0;

    // Set these for your servo:
    private final double DOWN_POS = 0.0;    // resting/down
    private final double UP_POS = 0.25;     // 45 degrees up (adjust as needed for the servo)

    @Override
    public void init() {
        inbetween = hardwareMap.get(Servo.class, "inbetween");
        inbetween.setPosition(DOWN_POS);
    }

    @Override
    public void loop() {
        if (!isPushing && gamepad1.y) {
            // Start push sequence
            inbetween.setPosition(UP_POS);
            isPushing = true;
            pushStartTime = System.currentTimeMillis();
        }

        // If in push sequence, check if 2 seconds have passed
        if (isPushing) {
            if (System.currentTimeMillis() - pushStartTime >= 2000) {
                // Go back down after 2 seconds
                inbetween.setPosition(DOWN_POS);
                isPushing = false;
            }
        }

        telemetry.addData("Inbetween Pos", inbetween.getPosition());
        telemetry.addData("Is Pushing?", isPushing);
    }
}
