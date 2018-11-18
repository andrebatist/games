package ru.job4j.chess;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

public abstract class Figure {
    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    abstract Figure copy(Cell dest);
}
