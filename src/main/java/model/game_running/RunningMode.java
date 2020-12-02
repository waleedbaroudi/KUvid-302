package model.game_running;

import model.game_building.Configuration;
import model.game_entities.AutonomousEntity;
import model.game_entities.Shooter;
import model.game_physics.hitbox.RectangularHitbox;
import model.game_running.runnables.CollisionRunnable;
import model.game_running.runnables.MovementRunnable;
import model.game_running.runnables.ShooterMovementRunnable;
import model.game_space.ObjectGenerator;
import org.apache.log4j.Logger;
import utils.Coordinates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * this class is a controller for the running phase of the game.
 */
public class RunningMode {
    public Logger logger = Logger.getLogger(this.getClass().getName());

    //space objects
    private ArrayList<AutonomousEntity> autonomousEntities;

    private Shooter atomShooter;

    boolean isInitialized = false; //to indicate whether the runnable, thread, and list have been initialized

    //Listener to handle game pause and resume commands
    RunningStateListener runningStateListener;
    GameEntitiesListener gameEntitiesListener;

    // Runnables
    MovementRunnable movementRunnable;
    CollisionRunnable collisionRunnable;
    ShooterMovementRunnable shooterRunnable;
    ObjectGenerator objectGenerator;

    // Threads
    Thread movementThread;
    Thread collisionThread;
    Thread shooterThread;
    Thread objectGeneratorThread;

    public RunningMode(RunningStateListener runningStateListener, GameEntitiesListener gameEntitiesListener) {
        autonomousEntities = new ArrayList<>();
        this.runningStateListener = runningStateListener;
        this.gameEntitiesListener = gameEntitiesListener;

        // TODO update the shooter inital coordinates from config
        // TODO fix the shooter position
        this.atomShooter = new Shooter(new Coordinates(Configuration.getInstance().getGameWidth() /2,
                Configuration.getInstance().getGameHeight() - 2 * GameConstants.ShooterDimensions.height),
                new RectangularHitbox(GameConstants.ShooterDimensions.width, GameConstants.ShooterDimensions.height));
        initialize();
    }

    public Shooter getAtomShooter() {
        return atomShooter;
    }

    /**
     * instantiates the threads and runnables. fills the list. sets "initialized" to true
     */
    private void initialize() {
        //todo: fill autonomous entity list and containers here

        movementRunnable = new MovementRunnable(this.autonomousEntities);
        collisionRunnable = new CollisionRunnable(this); // TODO: Pass the arraylist instead
        shooterRunnable = new ShooterMovementRunnable(this.atomShooter);
        objectGenerator = new ObjectGenerator(this);


        movementThread = new Thread(this.movementRunnable);
        collisionThread = new Thread(this.collisionRunnable);
        shooterThread = new Thread(this.shooterRunnable);
        objectGeneratorThread = new Thread(this.objectGenerator);

        this.isInitialized = true;
    }

    /**
     * starts the movement and collision threads
     */
    public void startThreads() {
        if (!isInitialized) {
            logger.error("Game is not yet initialized");
            return;
        }

        // Starting the threads.
        movementThread.start();
        collisionThread.start();
        shooterThread.start();
        objectGeneratorThread.start();
    }

    /**
     * calls pause on all runnables and interrupts all threads.
     */
    public void stop() {
        setRunningState(GameConstants.GAME_STATE_PAUSED);
        movementThread.interrupt();
        collisionThread.interrupt();
        shooterThread.interrupt();
    }

    /**
     * Pauses/Resumes/Stops all runnables.
     */
    public void setRunningState(int state) {
        runningStateListener.onRunningStateChanged(state);
        movementRunnable.setRunnableState(state);
        collisionRunnable.setRunnableState(state);
        shooterRunnable.setRunnableState(state);
        objectGenerator.setRunnableState(state);
    }

    public void moveShooter(int direction) {
        shooterRunnable.setMovementState(direction);
    }

    public void rotateShooter(int direction) {
        shooterRunnable.setRotationState(direction);
    }

    /**
     * static so that all classes
     *
     * @return returns the list of autonomous entities
     */
    public ArrayList<AutonomousEntity> getAutonomousEntities() {
        return autonomousEntities;
    }

    /**
     * @param entity the entity to be added to the list of entities
     * @return a boolean indicating whether the entity was added successfully
     */
    public boolean addEntity(AutonomousEntity entity) {
        gameEntitiesListener.onEntityAdd(entity);
        return autonomousEntities.add(entity);
    }

    /** Shoot entity at the tip of the Shooter
     *
     */
    public void shootProjectile(){
        AutonomousEntity shotEntity = this.atomShooter.shoot();
        addEntity(shotEntity);
    }
    /**
     * @param removedEntities autonomous entities to be removed from the list of elements in the space
     * @return a boolean indicating whether the entities were removed successfully
     */
    public boolean removeAutonomousEntities(Collection<AutonomousEntity> removedEntities) {
        gameEntitiesListener.onEntitiesRemove(removedEntities);
        return autonomousEntities.removeAll(removedEntities);
    }


    public interface RunningStateListener {
        void onRunningStateChanged(int state);
    }

    public interface GameEntitiesListener {
        void onEntityAdd(AutonomousEntity entity);

        void onEntitiesRemove(Collection<AutonomousEntity> entity);
    }
}
