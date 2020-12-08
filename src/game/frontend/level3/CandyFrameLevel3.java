package game.frontend.level3;

import game.backend.CandyGame;
import game.backend.level.Level3;
import game.frontend.CandyFrameLevel3y4;

public class CandyFrameLevel3 extends CandyFrameLevel3y4 {

    private final Level3.Level3State levelState;

    public CandyFrameLevel3(CandyGame game) {
        super(game);
        levelState = (Level3.Level3State)getLevelState();
    }

    @Override
    public BoardPanelLevel3 setBoardPanel(int sizeX, int sizeY, int cellSize) {
        return new BoardPanelLevel3(sizeX, sizeY, cellSize);
    }

    @Override
    public ScorePanelLevel3 setScorePanel() {
        return new ScorePanelLevel3(getLevelState().getBombsLeft());
    }


    @Override
    public void actionIfValid(){
        updateBombList();
    }

    private void updateBombList() {
        levelState.getTimeBombList().forEach(bomb -> {
            int newValue = Integer.parseInt(bomb.getProperty()) - 1;
            bomb.setProperty(String.valueOf(newValue));
            if (newValue == 0)
                levelState.playerLost();
        });
    }

    public void additionalAction(int number){
        //
    }

}
