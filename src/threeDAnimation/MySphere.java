package threeDAnimation;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.shape.Sphere;

import java.security.SecureRandom;

public class MySphere extends Sphere implements AnimatedShape {
    public MySphere(double v) {
        super(v);
    }

    @Override
    public void animate(Parent parent) {
        SecureRandom random = new SecureRandom();

        AnimationTimer animationTimer = new AnimationTimer() {
            int dx = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);
            int dy = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);
            int dz = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);

            int velocity = 50; // used to scale distance changes
            long previousTime = System.nanoTime(); // time since app launch
            Bounds bounds = parent.getBoundsInLocal();
            @Override
            public void handle(long l) {
                double elapsedTime = Math.abs(l - previousTime) / 1000000000.0;
                previousTime = l;
                double scale = elapsedTime * velocity;

                setTranslateX(getTranslateX() + dx * scale);
                setTranslateY(getTranslateY() + dy * scale);
                setTranslateZ(getTranslateZ() + dz * scale);


                if ((getTranslateX() <= (bounds.getMinX() + getRadius())) ||
                        (getTranslateX() >= (bounds.getMaxX() - getRadius()))) {
                    dx *= -1;
                }

                if ((getTranslateY() <= (bounds.getMinY() + getRadius())) ||
                        (getTranslateY() >= (bounds.getMaxY() - getRadius()))) {
                    dy *= -1;
                }

                if ((getTranslateZ() <= (bounds.getMinZ() + getRadius())) ||
                        (getTranslateZ() >= (bounds.getMaxZ() - getRadius()))) {
                    dz *= -1;
                }
            }
        };
        animationTimer.start();
    }
}
