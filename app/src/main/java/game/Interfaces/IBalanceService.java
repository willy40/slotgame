package game.Interfaces;

import java.util.List;

import game.Classes.MatchingSlot;

public interface IBalanceService {
    public int GetBalance();
    public void SubstractCredits();
    public void AddCredits(int credits);
    public int GetWinBalance(List<MatchingSlot> slots);
}