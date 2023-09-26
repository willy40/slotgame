package game.Classes;

import java.util.ArrayList;
import java.util.List;

import game.Exceptions.IncorectValueException;
import game.Extemsions.OneDArrayExtension;
import game.Interfaces.IWinLinesMatcher;

public class WinLinesMatcher implements IWinLinesMatcher {
    private List<boolean[]> winMatrixList = null;
    private final List<MatchingSlot> matchList = new ArrayList<>();
    
    /*
        Win Lines Matrix
        This can be readed from json or stored on external
        server and loaded dynamically
    */

    private final boolean[][] winLines = {
        {true, true, true, false, false, false, false, false, false},
        {false, false, false, true, true, true, false, false, false},
        {false, false, false, false, false, false, true, true, true},
        {true, false, false, false, true, false, false, false, true},
        {false, false, true, false, true, false, true, false, false},
        {true, false, false, true, false, false, true, false, false},
        {false, true, false, false, true, false, false, true, false},
        {false, false, true, false, false, true, false, false, true},
        {false, false, false, true, false, true, false, true, false}
    }; 

    public WinLinesMatcher(){
        winMatrixList = OneDArrayExtension.oneDArrayToList(winLines);
    }

    public List<MatchingSlot> Match(List<Integer> slots){
        List<MatchingSlot> winningSlots = new ArrayList<>();

        for(boolean[] winMatrixLine :  winMatrixList){
            checkWinningLine(slots, winMatrixLine);

            if(isLineWinning())
                winningSlots.addAll(matchList);
        };

        return winningSlots;
    }

    private void checkWinningLine(List<Integer> slots, boolean[] winMatrixLine) {
        matchList.clear();

        for(int i = 0; i < winMatrixLine.length; i++) {
            if( winMatrixLine[i] ) {
                try {
                    matchList.add(new MatchingSlot(slots.get(i), i));
                } catch (IncorectValueException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private boolean isLineWinning() {
        boolean ret = true;

        for(int i = 0; i < matchList.size(); i++ ) {
            for(int j = 1; j < matchList.size() - 1; j++){
                ret &= matchList.get(i).GetValue() == matchList.get(j).GetValue();
            }
        }

        return ret;
    }
}
