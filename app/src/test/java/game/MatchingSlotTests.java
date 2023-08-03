package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Classes.MatchingSlot;
import game.Classes.StaticValues;
import game.Exceptions.IncorectValueException;

public class MatchingSlotTests {
    private final int positionFive = 5;
    private final int columnForPositionFive = 2;
    private final int rowForPositionFive = 1;
    private final int testValue = StaticValues.MaxOfSpinValue;
    private final int throwableTestValue = StaticValues.MaxOfSpinValue + 1;
    private MatchingSlot matchingSlot;

    @BeforeEach
    public void Setup() throws IncorectValueException{
        matchingSlot = new MatchingSlot(testValue, positionFive);
    }

    @AfterEach
    public void TearDown(){
        matchingSlot = null;
    }
    
    @Test
    public void TestCalculationOfRowsCols(){
        assertEquals(columnForPositionFive, matchingSlot.GetCol());
        assertEquals(rowForPositionFive, matchingSlot.GetRow());
    }

    @Test
    public void TestCalculationOfRowsColsNotCorrect(){
        assertNotEquals(0, matchingSlot.GetCol());
        assertNotEquals(0, matchingSlot.GetRow());
    }

    @Test
    public void TestReturnCorrectValue(){
        assertEquals(testValue, matchingSlot.GetValue());
    }

    @Test
    public void TestValueShouldThrowException(){
        Exception ex = assertThrows(IncorectValueException.class, 
            ()-> new MatchingSlot(throwableTestValue, positionFive));
            
        assertTrue(ex.getMessage().contains(String.valueOf(testValue)));
    }
}
