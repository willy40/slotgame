package game.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.Interfaces.IBalanceService;
import game.Interfaces.ISlots;
import game.Interfaces.IWinLinesMatcher;

public class Slots implements ISlots{
    //TODO its should be somewhere in resources for better approach to translate

    private final String winStr = "You Win !!!!!";
    private final String bigWinStr = "Great Win !!!!!";
    private final String looseStr = "You have no more credits....";
    private final String stakeStr = "**Stake** ";
    private final String moreCreditsStr = "If you want add more credits press A";
    private final String creditsStr = " credits.";

    private final int start_balance = 1000;

    private final Random rnd = new Random();
    private final IWinLinesMatcher winLinesMatcher;
    private final IBalanceService accounter;
    private final List<Integer> slots = new ArrayList<>(StaticValues.GetMatrixSize());
    
    private boolean _fakeWin = false;
    
    public Slots(boolean fakeWin)
    {
        _fakeWin = fakeWin;
        winLinesMatcher = new WinLinesMatcher();
        accounter  = new BalanceService(start_balance);
    }
    
    public Slots(IWinLinesMatcher matcher, IBalanceService account)
    {
        winLinesMatcher = matcher;
        accounter = account;
    }

    public void Spin()
    {
        if(accounter.GetBalance() <= 0)
        {
            System.out.println(looseStr);
            PrintMoreCreditsInfo();
            return;
        }

        Randomize();
        FakeWin();
        
        PrintBalanceInformation();
        PrintSlots();

        List<MatchingSlot> winningSlots = winLinesMatcher.Match(slots);

        ChekForWin(winningSlots);

        PrintBalanceInformation();
        PrintMoreCreditsInfo();
    }

    public void AddCredits(){
        accounter.AddCredits(start_balance);
    }
    
    private void ChekForWin(List<MatchingSlot> winningSlots) {
        if(winningSlots.size() > 0 )
            PrintWinLines(winningSlots);
        else
            accounter.SubstractCredits();
    }

    private void FakeWin() {
        if(_fakeWin){
            slots.set(0, 1);
            slots.set(1, 1);
            slots.set(2, 1);

            slots.set(4, 1);
            slots.set(6, 1);
            slots.set(8, 1);
        }
    }  

    private void Randomize() {
        slots.clear();

        for(int i = 0; i< StaticValues.GetMatrixSize(); i++){
            slots.add(rnd.nextInt(StaticValues.MaxOfSpinValue + 1));
        }
    } 

    private void PrintSlots(){
        for(int x = 0; x < StaticValues.Rows; x++){
            for(int y = 0; y < StaticValues.Cols; y++){
                System.out.print( slots.get(x * StaticValues.Rows + y) + "\t");
            }
            System.out.println();
        }
        System.out.println(); 
    }

    private MatchingSlot FindFirstMatchSlot(List<MatchingSlot> winningLines, int x, int y){
        for(MatchingSlot s: winningLines){
            if(s.GetCol()==y && s.GetRow() == x)
                return s;
        }
        return null;
    }

    private void PrintWinLines(List<MatchingSlot> winningLines){
        if(winningLines.size() == 0) return;

        PrintWinInformation(winningLines);

        for(int x = 0; x < StaticValues.Rows; x++){
            for(int y = 0; y < StaticValues.Cols; y++){
                MatchingSlot s = FindFirstMatchSlot(winningLines, x, y);
              
                if(s!=null)
                    System.out.print( s.GetValue() + "\t"); 
                else
                    System.out.print("-\t");
            }
            System.out.println();
        }
        System.out.println(); 
    }

    private void PrintWinInformation(List<MatchingSlot> winningLines) {
        if(winningLines.size() > StaticValues.Cols)
            System.out.print(bigWinStr);            
        else
            System.out.print(winStr);

        System.out.println(" : " + String.valueOf(accounter.GetWinBalance(winningLines)) + creditsStr);
    }

    private void PrintBalanceInformation(){
        System.out.println(stakeStr + accounter.GetBalance() + creditsStr);
    }

    private void PrintMoreCreditsInfo(){
        System.out.println(moreCreditsStr);
    }
} 