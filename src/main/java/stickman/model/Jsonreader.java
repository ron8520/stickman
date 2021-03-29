package stickman.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Jsonreader{

    private JSONArray levelArray;

    public Jsonreader(String configure_path){
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader(configure_path)){
             this.levelArray = (JSONArray) jsonParser.parse(reader);

        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }catch(ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getLevelArray() {
        return this.levelArray;
    }

}