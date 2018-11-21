package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
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

    private ru.job4j.chess.Figure[] figuresAbstract = new ru.job4j.chess.Figure[32];

    private int count = 0;

    private void addFigAbstract(ru.job4j.chess.Figure figure) {
        this.figuresAbstract[this.count++] = figure;
    }

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
        ru.job4j.chess.Figure figureAbstract = null;
        if (figure instanceof BishopBlack) {
            figureAbstract = new Bishop(figure.position());
        }
        if (figureAbstract != null) {
            addFigAbstract(figureAbstract);
        }
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        try {
            wayValidate(source,dest);
        } catch (RuntimeException re) {
            re.printStackTrace();
            return false;
        }
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
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
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    public void wayValidate(Cell source, Cell dest) throws ImpossibleMoveException,
            OccupiedWayException, FigureNotFoundException {
        boolean found = false;
        ru.job4j.chess.Figure figure = null;
        int index = 0;
        for (ru.job4j.chess.Figure fig : figuresAbstract) {
            if ((fig != null) && (fig.getPosition().x == source.x) && (fig.getPosition().y == source.y)) {
                found = true;
                figure = fig;
                break;
            }
            index++;
        }
        if (!found) {
            throw new FigureNotFoundException("На данной клетке нет фигуры");
        }
        Cell[] cells = figure.way(source, dest);
        checkIsOccupied(cells);
        this.figuresAbstract[index] = figure.copy(dest);
    }

    private void checkIsOccupied(Cell[] cells) {
        for (Cell cell : cells) {
            if (cell != null) {
                for (ru.job4j.chess.Figure fig : figuresAbstract) {
                    if ((fig != null) && fig.getPosition().equals(cell)) {
                        throw new OccupiedWayException("Недопустимый ход. Путь фигуры занят.");
                    }
                }
            }
        }
    }
}
