package stickman.model;

import java.util.List;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class LevelBuilderImpl implements LevelBuilder{

    private final double stickman_normal_height = 30;
    private final double stickman_normal_width = 20;
    private final double stickman_large_height = 50;
    private final double stickman_large_width = 30;
    private final double mushroom_size = 20;
    private final double flag_size = 16;
    private final double foot_size = 30;
    private final double slime_size = 20;

    private double height, width, floorheight, herox;
    private List<Entity> entities = new ArrayList<Entity>();
    private List<Level> levels = new ArrayList<Level>();

    public Level getResult(){
        return new LevelImp(this.height, this.width, this.floorheight, this.herox, this.entities);
    }

    public List<Level> getLevels(String configure_file){
        Jsonreader reader = new Jsonreader(configure_file);
        JSONArray array = reader.getLevelArray();

        //loop through JSON array
        for(int i = 0; i < array.size(); i++) {
            LevelBuilder builder = new LevelBuilderImpl();
            JSONObject level = (JSONObject) array.get(i);
            JSONObject leveldetail = (JSONObject) level.get("levelDetail");
            JSONObject levelSize = (JSONObject) leveldetail.get("levelSize");
            JSONObject stickmanPos = (JSONObject) leveldetail.get("stickmanPos");
            JSONObject flagpos = (JSONObject) leveldetail.get("flagPos");
            JSONObject mushroompos = (JSONObject) leveldetail.get("mushroomPos");
            JSONArray EntityArray = (JSONArray) leveldetail.get("entityPos");
            JSONArray SlimeArray = (JSONArray) leveldetail.get("slimePos");

            double levelHeight = ((Number) levelSize.get("height")).doubleValue();
            double levelWidth = ((Number) levelSize.get("width")).doubleValue();
            String stickmanSize = (String) leveldetail.get("stickmanSize");
            double floorheight = ((Number) leveldetail.get("levelfloorheight")).doubleValue();
            double stickmanXPos = ((Number) stickmanPos.get("x")).doubleValue();
            double flagX = ((Number) flagpos.get("flagX")).doubleValue();
            double flagY = ((Number) flagpos.get("flagY")).doubleValue();
            double mushroomX = ((Number) mushroompos.get("mushRoomX")).doubleValue();
            double mushroomy = ((Number) mushroompos.get("mushRoomY")).doubleValue();


            //Using level builder to implement each level Detail
            builder.setHeight(levelHeight);
            builder.setWidth(levelWidth);
            builder.setFloorHeight(floorheight);
            builder.setHeroX(stickmanXPos);

            //check the size of stickman
            if(stickmanSize.equals("normal")) {
                Entity hero = new Hero(stickmanXPos, floorheight - stickman_normal_height,
                        stickman_normal_height, stickman_normal_width);
                builder.addEntity(hero);
            }else{
                Entity hero = new Hero(stickmanXPos, floorheight - stickman_large_height,
                        stickman_large_height, stickman_large_width);
                builder.addEntity(hero);
            }

            //set up foot
            for(int j = 0; j < EntityArray.size(); j++){
                JSONObject entitypos = (JSONObject) EntityArray.get(j);
                double entityX = ((Number) entitypos.get("entityX")).doubleValue();
                double entityY = ((Number) entitypos.get("entityY")).doubleValue();
                BackGroundEntity foot = new BackGroundEntity(entityX, entityY, foot_size, foot_size);
                foot.setImagePath("foot_tile.png");
                foot.setEntityType(EntityType.FOOT);
                builder.addEntity(foot);
            }

            //set up Slime
            for(int k = 0; k < SlimeArray.size(); k++){
                JSONObject slimepos = (JSONObject) SlimeArray.get(k);
                double slimeX = ((Number) slimepos.get("slimeX")).doubleValue();
                double slimeY = ((Number) slimepos.get("slimeY")).doubleValue();
                Slime slime = new Slime(slimeX, slimeY, slime_size, slime_size);
                slime.setImagePath("slimeBa.png");
                //randomly set up movement strategy
                if(k % 2 != 0){
                    MoveRight strategy =  new MoveRight(slime, builder.getResult());
                    slime.setStrategy(strategy);
                }else {
                    MoveLeft strategy =  new MoveLeft(slime, builder.getResult());
                    slime.setStrategy(strategy);
                }
                builder.addEntity(slime);
            }

            //set up flag location
            BackGroundEntity flag = new BackGroundEntity(flagX, flagY, flag_size, flag_size);
            flag.setImagePath("flag.png");
            flag.setEntityType(EntityType.FLAG);
            builder.addEntity(flag);

            //set up mushroom location
            BackGroundEntity mushroom = new BackGroundEntity(mushroomX, mushroomy, mushroom_size, mushroom_size);
            mushroom.setImagePath("mushroom.png");
            mushroom.setEntityType(EntityType.MUSHROOM);
            builder.addEntity(mushroom);

            levels.add(builder.getResult());
        }

        return this.levels;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public void setEntities(List<Entity> entities){
        this.entities = entities;
    }

    public void setFloorHeight(double floorHeight){
        this.floorheight = floorHeight;
    }

    public void addEntity(Entity entity){
        this.entities.add(entity);
    }

    public void setHeroX(double herox){
        this.herox = herox;
    }

}