package stickman.model;

public interface Entity {
    String getImagePath();
    Layer getLayer();
    double getXPos();
    double getYPos();
    double getHeight();
    double getWidth();
    void setImagePath(String imagePath);
    void setXPos(double xpos);
    void setYPos(double ypos);

    enum Layer{
        BACKGROUND, FOREGROUND, EFFECT
    }
}
