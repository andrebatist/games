package ru.job4j.chess.firuges.common;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.job4j.chess.Figure;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BishopTest {
    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();

    @Test
    public void whenWayDeltaXPlusYPlusThenArrayCell() {
        Figure bishop = new Bishop(Cell.D2);
        Cell[] expect = new Cell[]{Cell.E3,Cell.F4};
        Cell[] rst = bishop.way(Cell.D2,Cell.F4);
        assertThat(rst, is(expect));
    }

    @Test
    public void whenWayDeltaXPlusYMinusThenArrayCell() {
        Figure bishop = new Bishop(Cell.D2);
        Cell[] expect = new Cell[]{Cell.E1};
        Cell[] rst = bishop.way(Cell.D2,Cell.E1);
        assertThat(rst, is(expect));
    }

    @Test
    public void whenWayDeltaXMinusYMinusThenArrayCell() {
        Figure bishop = new Bishop(Cell.D2);
        Cell[] expect = new Cell[]{Cell.C1};
        Cell[] rst = bishop.way(Cell.D2,Cell.C1);
        assertThat(rst, is(expect));
    }

    @Test
    public void whenWayDeltaXMinusYPlusThenArrayCell() {
        Figure bishop = new Bishop(Cell.D2);
        Cell[] expect = new Cell[]{Cell.C3,Cell.B4,Cell.A5};
        Cell[] rst = bishop.way(Cell.D2,Cell.A5);
        assertThat(rst, is(expect));
    }

    @Test
    public void whenWayWrongCell() {
        Figure bishop = new Bishop(Cell.D2);
        exceptionGrabber.expect(ImpossibleMoveException.class);
        bishop.way(Cell.D2,Cell.C2);
    }
}