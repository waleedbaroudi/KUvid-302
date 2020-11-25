package model.game_building;

import org.apache.log4j.BasicConfigurator;
import ui.windows.BuildingWindow;

public class GameStarter {
    public static void main(String[] args) {
        // Setup all loggers to print to the console.
        BasicConfigurator.configure();
        BuildingWindow window = new BuildingWindow("Game Build");
    }
}
