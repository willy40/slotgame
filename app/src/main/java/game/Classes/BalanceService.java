package game.Classes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import game.Interfaces.IBalanceService;

public class BalanceService implements IBalanceService {
    private int balance = 0;
    
    public BalanceService(int start_balance) {
        balance = start_balance;
    }
    
    public void SubstractCredits(){
        balance -= StaticValues.BetValue;
    }

    public void AddCredits(int credits){
        balance += credits;
    }

    public int GetBalance() {
        return balance;
    }

    public int GetWinBalance(List<MatchingSlot> slots) {
        int win = 0;

        if(slots.size() > 0 ) {
            Map<Integer, Long> result = slots.stream().collect(
                                            Collectors.groupingBy(
                                                MatchingSlot::GetMultiplayer,
                                                Collectors.counting()));

            for (Map.Entry<Integer, Long> entry : result.entrySet()) {
                win += entry.getKey() * StaticValues.BetValue;
            }

            if(win > 0){
                balance += win;
            }
        }
        
        return win;
    }
}