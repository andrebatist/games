package ru.job4j.chess;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.common.Bishop;

import static org.junit.Assert.*;

public class LogicTest {
    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();

    @Test
    public void whenMoveBishopFromD2ToF4() {
        ru.job4j.chess.firuges.Figure bishop = new BishopBlack(Cell.D7);
        ru.job4j.chess.firuges.Figure figure = new BishopBlack(Cell.C8);
        Logic logic = new Logic();
        logic.add(bishop);
        logic.add(figure);
        assertTrue(logic.move(Cell.D7, Cell.C6));
    }

    @Test
    public void whenMoveBishopWrongCell() {
        ru.job4j.chess.firuges.Figure bishop = new BishopBlack(Cell.D7);
        Logic logic = new Logic();
        logic.add(bishop);
        exceptionGrabber.expect(ImpossibleMoveException.class);
        logic.wayValidate(Cell.D7, Cell.C5);
    }

    @Test
    public void whenMoveBishopOccupiedCell() {
        ru.job4j.chess.firuges.Figure bishop = new BishopBlack(Cell.D2);
        Logic logic = new Logic();
        logic.add(bishop);
        ru.job4j.chess.firuges.Figure figure = new BishopBlack(Cell.E3);
        logic.add(figure);
        exceptionGrabber.expect(OccupiedWayException.class);
        logic.wayValidate(Cell.D2, Cell.F4);
    }

    @Test
    public void whenMoveFromEmptyCell() {
        Logic logic = new Logic();
        exceptionGrabber.expect(FigureNotFoundException.class);
        logic.wayValidate(Cell.D2, Cell.F4);
    }
}