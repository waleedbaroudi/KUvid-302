package model.game_running;

import model.game_entities.AutonomousEntity;

import java.util.ArrayList;

public class MovementHandler implements Runnable {

    ArrayList<AutonomousEntity> entities;

    public MovementHandler(ArrayList<AutonomousEntity> entities) {
        this.entities = entities;
    }

    @Override
    public void run() {
        for (AutonomousEntity entity : this.entities) {
            entity.move();
        }
    }

    public void setEntities(ArrayList<AutonomousEntity> entities) {
        this.entities = entities;
    }
}
