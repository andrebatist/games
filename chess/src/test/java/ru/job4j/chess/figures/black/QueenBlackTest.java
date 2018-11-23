package ru.job4j.chess.figures.black;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QueenBlackTest {
    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();

    @Test
    public void whenMoveQueenFromD8ToG6() {
        Figure queen = new QueenBlack(Cell.E8);
        Cell[] expect = new Cell[]{Cell.F7, Cell.G6, null, null, null, null, null};
        Cell[] result = queen.way(Cell.E8,Cell.G6);
        assertThat(result, is(expect));
    }

    @Test
    public void whenMoveQueenFromF7ToA7() {
        Figure queen = new QueenBlack(Cell.F7);
        Cell[] expect = new Cell[]{Cell.E7, Cell.D7, Cell.C7, Cell.B7, Cell.A7, null, null};
        Cell[] result = queen.way(Cell.F7,Cell.A7);
        assertThat(result, is(expect));
    }

    @Test
    public void whenMoveQueenWrongCell() {
        Figure queen = new QueenBlack(Cell.E8);
        exceptionGrabber.expect(ImpossibleMoveException.class);
        queen.way(Cell.E8,Cell.C7);
    }
}