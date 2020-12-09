package game.frontend.level3;

import game.backend.CandyGame;
import game.backend.element.Element;
import game.backend.level.Level3;
import game.frontend.CandyFrameLevel3y4;

public class CandyFrameLevel3 extends CandyFrameLevel3y4 {

    private final Level3.Level3State levelState;

    public CandyFrameLevel3(CandyGame game) {
        super(game);
        levelState = (Level3.Level3State) getLevelState();
    }

    @Override
    public BoardPanelLevel3 setBoardPanel(int sizeX, int sizeY, int cellSize) {
        return new BoardPanelLevel3(sizeX, sizeY, cellSize);
    }

    @Override
    public ScorePanelLevel3 setScorePanel() {
        return new ScorePanelLevel3(getLevelState().getBombsLeft(), game.getState().getRequiredScore());
    }


    @Override
    public void actionIfValid() {
        updateBombList();
    }

    private void updateBombList() {
        levelState.getTimeBombList().forEach(bomb -> {
            if (!(bomb.getNumber() == null)) {
                int newValue = bomb.getNumber() - 1;
                bomb.setNumber(newValue);
                if (newValue == 0)
                    levelState.playerLost();
            }
        });
    }

    @Override
    public void additionalAction(Element e){
        //
    }

    @Override
    public boolean removeCondition(Integer number){
        return number == null;
    }

}
