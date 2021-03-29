package stickman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.text.Font;
import stickman.model.Entity;
import stickman.model.GameEngine;

import java.util.ArrayList;
import java.util.List;

public class GameWindow {
    private final int width;
    private final int height;
    private Scene scene;
    private Pane pane;
    private GameEngine model;
    private List<EntityView> entityViews;
    private BackgroundDrawer backgroundDrawer;

    private double xViewportOffset = 0.0;
    private double yViewportOffset = 0.0;
    private static final double VIEWPORT_MARGIN = 280.0;

    public GameWindow(GameEngine model, int width, int height) {
        this.model = model;
        this.pane = new Pane();
        this.width = width;
        this.height = height;
        this.scene = new Scene(pane, width, height);

        this.entityViews = new ArrayList<>();

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(model);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

        this.backgroundDrawer = new BlockedBackground();

        backgroundDrawer.draw(model, pane);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void draw() {

        //if the hero touch the flag, show the victory game page
        if(model.getCurrentLevel().getStatus()){
            this.pane.getChildren().clear();
            this.pane.setStyle("-fx-background-color: lightblue;");
            Label text = new Label("Congratulation!");
            text.setFont(new Font("Arial", 50));
            text.setLayoutX(140);
            text.setLayoutY(160);
            this.pane.getChildren().add(text);

        }else {
            model.tick();

            List<Entity> entities = model.getCurrentLevel().getEntities();
            for (EntityView entityView : entityViews) {
                entityView.markForDelete();
            }

            double heroXPos = model.getCurrentLevel().getHeroX();
            double heroYPos = model.getCurrentLevel().getHeroY();
            heroXPos -= xViewportOffset;
            heroYPos += yViewportOffset;

            if (heroXPos < VIEWPORT_MARGIN) {
                if (xViewportOffset >= 0) { // Don't go further left than the start of the level
                    xViewportOffset -= VIEWPORT_MARGIN - heroXPos;
                    if (xViewportOffset < 0) {
                        xViewportOffset = 0;
                    }
                }
            } else if (heroXPos > width - VIEWPORT_MARGIN) {
                xViewportOffset += heroXPos - (width - VIEWPORT_MARGIN);
            }

            if (heroYPos > height - VIEWPORT_MARGIN) {
                if (yViewportOffset >= 0) {
                    yViewportOffset -= heroYPos - VIEWPORT_MARGIN;

                    if (yViewportOffset < 0) {
                        yViewportOffset = 0;
                    }
                }
            } else if (heroYPos < height - VIEWPORT_MARGIN) {
                yViewportOffset += height - VIEWPORT_MARGIN - heroYPos;
            }

            backgroundDrawer.update(xViewportOffset, yViewportOffset);

            for (Entity entity : entities) {
                boolean notFound = true;
                for (EntityView view : entityViews) {
                    if (view.matchesEntity(entity)) {
                        notFound = false;
                        view.update(xViewportOffset, yViewportOffset);
                        break;
                    }
                }
                if (notFound) {
                    EntityView entityView = new EntityViewImpl(entity);
                    entityViews.add(entityView);
                    pane.getChildren().add(entityView.getNode());
                }
            }

            for (EntityView entityView : entityViews) {
                if (entityView.isMarkedForDelete()) {
                    pane.getChildren().remove(entityView.getNode());
                }
            }
            entityViews.removeIf(EntityView::isMarkedForDelete);
        }
    }
}
