package gamecontroller;

public class GameStats {

    private int score = 0;
    private int hiscore = 0;
    private int lives = 2;
    private int shotsFired = 0;
    private int aliensCount = 0;
    private int gameRound = 0;
    private boolean missileAvailable = true;

    private static GameStats instance;

    public static GameStats getInstance() {
        if (instance == null) {
            instance = new GameStats();
        }
        return instance;
    }

    public void update() {
        if (this.score > this.hiscore) {
            setHiscore(this.score);
        }
    }

    public void reset() {
        score = 0;
        //hiscore = 0; //keep high score for session
        lives = 2;
        shotsFired = 0;
        missileAvailable = true;
        aliensCount = 0;
        gameRound = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHiscore() {
        return hiscore;
    }

    public void setHiscore(int hiscore) {
        this.hiscore = hiscore;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public void setShotsFired(int shotsFired) {
        this.shotsFired = shotsFired;
    }

    public boolean isMissileAvailable() {
        return missileAvailable;
    }

    public void setMissileAvailable(boolean missileAvailable) {
        this.missileAvailable = missileAvailable;
    }

    public void increaseAliensCount() {
        aliensCount += 1;
    }
    public void decreaseAliensCount() {
        aliensCount -= 1;
    }

    public int getAlienLives() {
        return aliensCount;
    }

    public int getRound() {
        return this.gameRound;
    }
    
    public void increaseGameRound() {
        this.gameRound += 1;
    }

}
