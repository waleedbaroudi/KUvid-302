package services.utils;

import model.game_running.CollisionHandler;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundHandler implements CollisionHandler.OnCollisionListener {

    private Long currentFrame;
    private Clip clip;

    // current status of clip
    String status;

    private AudioInputStream audioInputStream;
    private String filePath;

    private final String COLLECT_MOLECULE = "assets/Sounds/collect.wav";
    private final String GET_DAMAGE = "assets/Sounds/hurt.wav";
    private final String COLLECT_POWERUP = "assets/Sounds/powerup.wav";
    private final String DESTROY_BLOCKER = "assets/Sounds/explosion.wav";
    private final String ATOM_BLOCKED = "assets/Sounds/atom_blocked.wav";
    // constructor to initialize streams and clip

    private void play()
    {
        //start the clip
        clip.start();
    }

    @Override
    public void onShooterBlockerCollision() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.filePath = GET_DAMAGE;
        clip = getClip(this.filePath);
        clip.open(audioInputStream);
        this.play();
    }

    @Override
    public void onAtomMoleculeCollision() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.filePath = COLLECT_MOLECULE;
        clip = getClip(this.filePath);
        clip.open(audioInputStream);
        this.play();
    }

    @Override
    public void onShooterPowerupCollision() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.filePath = COLLECT_POWERUP;
        clip = getClip(this.filePath);
        clip.open(audioInputStream);
        this.play();
    }

    @Override
    public void onPowerupBlockerCollision() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.filePath = DESTROY_BLOCKER;
        clip = getClip(this.filePath);
        clip.open(audioInputStream);
        this.play();
    }

    @Override
    public void onAtomBlockerCollision() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.filePath = ATOM_BLOCKED;
        clip = getClip(this.filePath);
        clip.open(audioInputStream);
        this.play();
    }


    private Clip getClip(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            return AudioSystem.getClip();
      }}
