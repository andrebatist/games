package ru.job4j.chess.figures.black;

import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class QueenBlack extends Figure {

    public QueenBlack(Cell position) {
        super(position);
    }

    @Override
    public Cell getPosition() {
        return super.getPosition();
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!((Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y)) ||
                ((source.x == dest.x) && (source.y != dest.y)) ||
                ((source.y == dest.y) && (source.x != dest.x)))) {
            throw new ImpossibleMoveException("Ферзем ходить на эту клетку нельзя.");
        }
        Cell[] cells = new Cell[7];
        if (source.x == dest.x) {
            int moveY = source.y < dest.y ? 1 : -1;
            for (int i = 1; i <= Math.abs(dest.y - source.y); i++) {
                cells[i - 1] = Cell.getCell(source.x, source.y + i * moveY);
            }
            return cells;
        }
        if (source.y == dest.y) {
            int moveX = source.x < dest.x ? 1 : -1;
            for (int i = 1; i <= Math.abs(dest.x - source.x); i++) {
                cells[i - 1] = Cell.getCell(source.x + i * moveX, source.y);
            }
            return cells;
        }
        int deltaX = source.x < dest.x ? 1 : -1;
        int deltaY = source.y < dest.y ? 1 : -1;
        for (int i = 1; i <= Math.abs(dest.x - source.x); i++) {
            cells[i - 1] = Cell.getCell(source.x + i * deltaX, source.y + i * deltaY);
        }
        return cells;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenBlack(dest);
    }
}
