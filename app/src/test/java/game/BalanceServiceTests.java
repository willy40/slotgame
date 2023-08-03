package game;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import game.Classes.BalanceService;
import game.Classes.MatchingSlot;
import game.Classes.WinLinesMatcher;
import game.Interfaces.IBalanceService;
import game.Interfaces.IWinLinesMatcher;

public class BalanceServiceTests extends BaseTests {
        private int start_balance = 1000;
        private IWinLinesMatcher winLinesMatcher;
        private IBalanceService balanceService;

    @BeforeEach
    public void SetUp(){
        winLinesMatcher = new WinLinesMatcher();
        balanceService = new BalanceService(start_balance);
    }

    @AfterEach
    public void TearDown(){
        winLinesMatcher = null;
        balanceService = null;
    }

    @Test
    public void TestBalanceServiceWinShouldBeZero(){
        List<MatchingSlot> match = winLinesMatcher.Match(looseSlots);

        assertTrue(balanceService.GetWinBalance(match) == 0);
    }

    @Test
    public void TestBalanceServiceWinShouldNotBeZero(){
        List<MatchingSlot> match = winLinesMatcher.Match(winSlots);

        assertTrue(balanceService.GetWinBalance(match) > 0);
    }

    @Test
    public void TestBalanceServiceAddBalanceShouldBeGreaterThenStartBalance(){
        balanceService.AddCredits(start_balance);

        assertTrue(balanceService.GetBalance() > start_balance);
    }

    @Test
    public void TestBalanceServiceSubBalanceShouldBeLowerThenStartBalance(){
        balanceService.SubstractCredits();

        assertTrue(balanceService.GetBalance() < start_balance);
    }
}