package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.Figure;


/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack extends Figure {

    @Override
    public Cell getPosition() {
        return super.getPosition();
    }

    public BishopBlack(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!(Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y))) {
            throw new ImpossibleMoveException("Слоном ходить на эту клетку нельзя.");
        }
        Cell[] cells = new Cell[7];
        int deltaX = source.x < dest.x ? 1 : -1;
        int deltaY = source.y < dest.y ? 1 : -1;
        for (int i = 1; i <= Math.abs(dest.x - source.x); i++) {
            cells[i - 1] = Cell.getCell(source.x + i * deltaX, source.y + i * deltaY);
        }
        return cells;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
