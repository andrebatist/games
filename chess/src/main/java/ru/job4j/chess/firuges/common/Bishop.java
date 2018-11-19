package ru.job4j.chess.firuges.common;

import ru.job4j.chess.Figure;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

import java.util.Arrays;

public class Bishop extends Figure {
    public Bishop(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!((Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y)
                && ((source.x != dest.x) && (source.y != dest.y))))) {
            throw new ImpossibleMoveException("Слоном ходить на эту клетку нельзя.");
        }
        Cell[] cells = new Cell[7];
        int count;
        if (source.x < dest.x) {
            if (source.y < dest.y) {
                count = setCellsDeltaXPlusYPlus(source, dest, cells);
            } else {
                count = setCellsDeltaXPlusYMinus(source, dest, cells);
            }
        } else {
            if (source.y > dest.y) {
                count = setCellsDeltaXMinusYMinus(source, dest, cells);
            } else {
                count = setCellsDeltaXMinusYPlus(source, dest, cells);
            }
        }
        return Arrays.copyOf(cells, count);
    }

    private int setCellsDeltaXPlusYPlus(Cell source, Cell dest, Cell[] cells) {
        int count = 0;
        int x = source.x;
        int y = source.y;
        while ((x < dest.x) && (y < dest.y)) {
            for (Cell cell : Cell.values()) {
                if ((cell.x == x + 1) && (cell.y == y + 1)) {
                    cells[count++] = cell;
                    break;
                }
            }
            x++;
            y++;
        }
        return count;
    }

    private int setCellsDeltaXPlusYMinus(Cell source, Cell dest, Cell[] cells) {
        int count = 0;
        int x = source.x;
        int y = source.y;
        while ((x < dest.x) && (y > dest.y)) {
            for (Cell cell : Cell.values()) {
                if ((cell.x == x + 1) && (cell.y == y - 1)) {
                    cells[count++] = cell;
                    break;
                }
            }
            x++;
            y--;
        }
        return count;
    }

    private int setCellsDeltaXMinusYMinus(Cell source, Cell dest, Cell[] cells) {
        int count = 0;
        int x = source.x;
        int y = source.y;
        while ((x > dest.x) && (y > dest.y)) {
            for (Cell cell : Cell.values()) {
                if ((cell.x == x - 1) && (cell.y == y - 1)) {
                    cells[count++] = cell;
                    break;
                }
            }
            x--;
            y--;
        }
        return count;
    }

    private int setCellsDeltaXMinusYPlus(Cell source, Cell dest, Cell[] cells) {
        int count = 0;
        int x = source.x;
        int y = source.y;
        while ((x > dest.x) && (y < dest.y)) {
            for (Cell cell : Cell.values()) {
                if ((cell.x == x - 1) && (cell.y == y + 1)) {
                    cells[count++] = cell;
                    break;
                }
            }
            x--;
            y++;
        }
        return count;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }
}
