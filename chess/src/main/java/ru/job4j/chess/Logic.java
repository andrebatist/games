package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.FigureInterface;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.common.Bishop;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        try {
            wayValidate(source,dest);
        } catch (RuntimeException re) {
            re.printStackTrace();
            return false;
        }
        return true;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].getPosition().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public void wayValidate(Cell source, Cell dest) throws ImpossibleMoveException,
            OccupiedWayException, FigureNotFoundException {
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("На данной клетке нет фигуры");
        }
        Figure figure = this.figures[index];
        Cell[] cells = figure.way(source, dest);
        checkIsOccupied(cells);
        this.figures[index] = figure.copy(dest);
    }

    private void checkIsOccupied(Cell[] cells) {
        for (Cell cell : cells) {
            if (cell != null) {
                for (Figure fig : figures) {
                    if ((fig != null) && fig.getPosition().equals(cell)) {
                        throw new OccupiedWayException("Недопустимый ход. Путь фигуры занят.");
                    }
                }
            }
        }
    }
}
