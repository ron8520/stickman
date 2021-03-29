package stickman.model;

import java.util.List;
import java.util.ArrayList;

public class GameEngineImpl implements GameEngine{

    private String configure_file;
    private Level currentLevel;
    private List<Level> levels;
    private int currentLevelIndex = 0;
    private boolean firstgame = true;

    public GameEngineImpl(String file_path){
        this.configure_file = file_path;
        initLevel();
        startLevel();
    }

    public void startLevel(){
        if(this.firstgame) {
            this.currentLevel = levels.get(currentLevelIndex);
            this.firstgame = false;
        }
    }


    //set up all the level and store it into List<Level>
    public void initLevel(){
        LevelBuilder builder = new LevelBuilderImpl();
        this.levels = builder.getLevels(configure_file);
    }

    public Level getCurrentLevel(){
        return this.currentLevel;
    }

    // Hero inputs - boolean for success (possibly for sound feedback)
    public boolean jump(){
        return getCurrentLevel().jump();
    }

    public boolean moveLeft(){
        return getCurrentLevel().moveLeft();
    }

    public boolean moveRight(){
        return getCurrentLevel().moveRight();
    }

    public boolean stopMoving(){
        return getCurrentLevel().stopMoving();
    }

    public boolean shoot(){
        return this.getCurrentLevel().shoot();
    }

    public boolean getStatus() {return this.getCurrentLevel().getStatus();}

    public void tick(){
        getCurrentLevel().tick();
    }

}