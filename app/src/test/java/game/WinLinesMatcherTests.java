package game;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Classes.MatchingSlot;
import game.Classes.StaticValues;
import game.Classes.WinLinesMatcher;

public class WinLinesMatcherTests extends BaseTests {
    private final int oneLineWin = 1 * StaticValues.Cols;
    private final int zeroLinesWin = 0;
    private final int allLinesWin = 9 * StaticValues.Cols;

    private WinLinesMatcher winLinesMatcher;

    @BeforeEach
    public void SetUp(){
        winLinesMatcher = new WinLinesMatcher();
    }

    @AfterEach
    public void TearDown(){
        winLinesMatcher = null;
    }

    @Test
    public void TestLineMatcherShouldReturnWinLines(){
        List<MatchingSlot> matchSlots = winLinesMatcher.Match(winSlots);

        assertEquals(matchSlots.size(), oneLineWin);
    }

    @Test
    public void TestLineMatcherShouldNotReturnWinLines(){
        List<MatchingSlot> match = winLinesMatcher.Match(looseSlots);

        assertEquals(match.size(), zeroLinesWin);
    }

    @Test
    public void TestLineMatcherShouldReturnEightWinLines(){
        List<MatchingSlot> match = winLinesMatcher.Match(allSlotsWin);

        assertEquals(match.size(), allLinesWin);
    } 
}
