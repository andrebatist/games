package ru.job4j.chess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.common.Bishop;

import static org.junit.Assert.assertTrue;

public class BoardTest {
    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();

    @Test
    public void whenMoveBishopFromD2ToF4() {
        Figure bishop = new Bishop(Cell.D2);
        Board board = new Board();
        board.add(bishop);
        assertTrue(board.move(Cell.D2, Cell.F4));
    }

    @Test
    public void whenMoveBishopWrongCell() {
        Figure bishop = new Bishop(Cell.D2);
        Board board = new Board();
        board.add(bishop);
        exceptionGrabber.expect(ImpossibleMoveException.class);
        board.move(Cell.D2, Cell.G3);
    }

    @Test
    public void whenMoveBishopOccupiedCell() {
        Figure bishop = new Bishop(Cell.D2);
        Board board = new Board();
        Figure figure = new Bishop(Cell.E3);
        board.add(bishop);
        board.add(figure);
        exceptionGrabber.expect(OccupiedWayException.class);
        board.move(Cell.D2, Cell.F4);
    }

    @Test
    public void whenMoveFromEmptyCell() {
        Board board = new Board();
        exceptionGrabber.expect(FigureNotFoundException.class);
        board.move(Cell.D2, Cell.F4);
    }
}