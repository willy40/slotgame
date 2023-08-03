package game.Interfaces;

import java.util.List;

import game.Classes.MatchingSlot;

public interface IWinLinesMatcher {
    public List<MatchingSlot> Match(List<Integer> slots);
}