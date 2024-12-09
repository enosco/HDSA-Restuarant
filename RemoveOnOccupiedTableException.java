/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update: 12/9/24
 * Submitted:  12/9/24
 * Comment: test suite and sample run attached
 * Comment: I declare that this is entirely my own work
 * @author: Scott Eno, Jamie Kahle
 * @version: 2024.12.9
 */
/**
 * A custom exception for Section.java that occurs when removeTable() targets an already occupied table.
 *
 * @author Scott Eno, Jamie Kahle
 */
public class RemoveOnOccupiedTableException
    extends Exception
{

    /**
     * Constructs a new exception
     */
    public RemoveOnOccupiedTableException(String s)
    {
        super(s);
    }  // end constructor
} 
