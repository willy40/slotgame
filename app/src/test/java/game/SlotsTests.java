package game;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Classes.MatchingSlot;
import game.Classes.Slots;
import game.Exceptions.IncorectValueException;
import game.Interfaces.IBalanceService;
import game.Interfaces.IWinLinesMatcher;

public class SlotsTests extends BaseTests {
    private IWinLinesMatcher winLinesMatcher;
    private IBalanceService balanceService;
    private Slots slot;

    @BeforeEach
    public void SetUp(){
        winLinesMatcher = mock(IWinLinesMatcher.class);
        balanceService = mock(IBalanceService.class);
        when(balanceService.GetBalance()).thenReturn(1000);

        slot = new Slots(winLinesMatcher, balanceService);
    }

    @AfterEach
    public void TearDown(){
        winLinesMatcher = null;
        balanceService = null;
        slot = null;
    }

    @Test
    public void TestSpinShouldCallMatch(){
        slot.Spin();

        verify(winLinesMatcher, atLeast(1)).Match(anyList());
    }

    @Test
    public void TestSpinShouldNotCallMatchIfBalanceZero(){
        when(balanceService.GetBalance()).thenReturn(0);

        slot.Spin();

        verify(winLinesMatcher, never()).Match(anyList());
    }

    @Test
    public void TestSpinShouldCallBalanceServiceGetBalance(){
        slot.Spin();

        verify(balanceService, atLeast(1)).GetBalance();
    }

    @Test
    public void TestSpinShouldNotCallMatchIfSpinIsNotCalled(){
        verify(winLinesMatcher, never()).Match(anyList());
    }

    @Test
    public void TestSpinShouldCallBalanceServiceAddCredits(){
        slot.AddCredits();

        verify(balanceService, atLeast(1)).AddCredits(anyInt());
    }

    @Test
    public void TestSpinShouldCallBalanceServiceGetWinBalance() throws IncorectValueException{
        List<MatchingSlot> slots = new ArrayList<MatchingSlot>();
        slots.add(new MatchingSlot(1,0));
        slots.add(new MatchingSlot(1,1));
        slots.add(new MatchingSlot(1,2));

        when(winLinesMatcher.Match(anyList())).thenReturn(slots);

        slot.Spin();

        verify(balanceService, atLeast(1)).GetWinBalance(anyList());
    }
}
