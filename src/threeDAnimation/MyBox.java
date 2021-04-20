package threeDAnimation;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.shape.Box;

import java.security.SecureRandom;

public class MyBox extends Box implements AnimatedShape {
    public MyBox(double v, double v1, double v2) {
        super(v, v1, v2);
    }

    @Override
    public void animate(Parent parent) {
        SecureRandom random = new SecureRandom();

        AnimationTimer animationTimer = new AnimationTimer() {
            int dx = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);
            int dy = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);
            int dz = 1 + random.nextInt(3) * (random.nextBoolean() ? -1 : 1);

            int velocity = 40; // used to scale distance changes
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


                double a = getTranslateX();
                double b = getTranslateY();
                double c = getTranslateZ();
                double d = getWidth();
                double f = getHeight();
                double g = getDepth();

                if ((getTranslateX() <= (bounds.getMinX() + getWidth())) ||
                        (getTranslateX() >= (bounds.getMaxX() - getWidth()))) {
                    dx *= -1;
                }

                if ((getTranslateY() <= (bounds.getMinY() + getHeight())) ||
                        (getTranslateY() >= (bounds.getMaxY() - getHeight()))) {
                    dy *= -1;
                }

                if ((getTranslateZ() <= (bounds.getMinZ() + getDepth())) ||
                        (getTranslateZ() >= (bounds.getMaxZ() - getDepth()))) {
                    dz *= -1;
                }
            }
        };
    }
}
