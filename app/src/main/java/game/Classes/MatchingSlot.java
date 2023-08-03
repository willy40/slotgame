package game.Classes;

import game.Exceptions.IncorectValueException;

public class MatchingSlot {
    private int _value;
    private int _row;
    private int _col;
    private final int[][] multiplayer = new int[][]{
        {0, 5},
        {1, 10},
        {2, 15},
        {3, 20},
        {4, 25},
        {5, 30},
        {6, 35},
        {7, 40},
        {8, 45},
        {9, 100},
        {10, 200}
    };

    public MatchingSlot(int value, int position) throws IncorectValueException{
        if(value > StaticValues.MaxOfSpinValue){
            throw new IncorectValueException("Value must be lower then:" + StaticValues.MaxOfSpinValue);
        }

        _row = position / StaticValues.Rows;
        _col = position % StaticValues.Cols;
        _value = value;
    }

    public int GetValue() { return _value; }
    public int GetRow() { return _row; }
    public int GetCol() { return _col; }
    public int GetMultiplayer() { return multiplayer[_value][1]; }
}