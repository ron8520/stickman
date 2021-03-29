# A Traditional Classic Game Stickman


## Screenshot of the game:

<img width="640" alt="WechatIMG538" src="https://user-images.githubusercontent.com/50691871/112774191-92ecb000-9084-11eb-8ae7-8d219c8e79a8.png">
<img width="640" alt="WechatIMG539" src="https://user-images.githubusercontent.com/50691871/112774192-941ddd00-9084-11eb-9c6c-e1d20a181e61.png">
<img width="640" alt="WechatIMG540" src="https://user-images.githubusercontent.com/50691871/112774193-94b67380-9084-11eb-8ea9-eaab7bd368f2.png">


Using command gradle build and gradle run can run the program

The JSON file has a JSONArray outside, each JSONObject inside the JOSONArray
store the each level inforamtion. It include sticmanSize (JSONObject), levelSize(JSONObject),
mushroomPos (JSONObject), flagPos(JSONObject), slimePos(JSONAraay), footPos(JSONArray), 
levelFloorHeight (JsonObject), stickmanPos(JSONObject)

The json file name is call "default.json". it can located at the same directory with the src folder and
build.gradle

If you want to change to load different level, you can go to GameEngineImp that 
located at the stickman/model/GameEngine. Go to line 22, change levels.get(currentLevelIndex) to
levels.get(1), and restart the program. You are able to load differnt level. 

However, don't change the number more than 1. In current stage, it only has 2 levels. 
It will due to IndexOutOfRange error and crash the program

when the stickman(hero) touch the mushroom, press space can shoot bullets.
