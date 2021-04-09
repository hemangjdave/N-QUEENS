package com.techrevolution.nqueens.logic;

import com.techrevolution.nqueens.model.Coordinates;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.BiPredicate;

@Component
@Scope("prototype")
public class QueensBoardUpdated {

    private  char[][] queens;
    private final Deque<Coordinates> stack = new ArrayDeque<>();
    private List<Coordinates> list = null;
    private int successCount;
    private Coordinates defaultCoordinates;

    public QueensBoardUpdated() {
    }

    public void initializedQueensBoard(int size) {
        this.queens = new char[size][size];
        stack.clear();
        list = new ArrayList<>(queens.length);
        successCount=0;
        defaultCoordinates = new Coordinates(0, 0);
    }

    private boolean isInitializedCoordinates(Coordinates coordinates) {
        return coordinates.getXCoordinate() == 0 && coordinates.getYCoordinate() == 0;
    }

    private boolean ifCoordinatesHaveQueen(int xAxis, int yAxis) {
        BiPredicate<Integer, Integer> biPredicate
                = (x, y) -> queens[x][y] == 'Q';
        return biPredicate.test(xAxis, yAxis);
    }

    private boolean isValidLeftCordinates(Coordinates coordinates) {
        return ensureValidCoordinates(coordinates, (x, y) -> y != 0, null);
    }

    private boolean ensureValidCoordinates(Coordinates coordinates, BiIntPredicate predicate, CustomIntFunction function) {
        if (isInitializedCoordinates(coordinates)) {
            return true;
        }
        var xAxis = coordinates.getXCoordinate();
        var yAxis = coordinates.getYCoordinate();
        while (predicate.test(xAxis, yAxis)) {
            yAxis--;
            if (function != null) {
                xAxis = function.apply(xAxis);
            }
            if (ifCoordinatesHaveQueen(xAxis, yAxis)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidUpperDiagonalCordinates(Coordinates coordinates) {
        return ensureValidCoordinates(coordinates, ((x, y) -> x != 0 && y != 0), (xAxis) -> xAxis - 1);
    }

    private boolean isValidLowerDiagonalCordinates(Coordinates coordinates) {
        return ensureValidCoordinates(coordinates, ((x, y) -> x < queens.length - 1 && y != 0), xAxis -> xAxis + 1);
    }

    public boolean isValidCoordinates(Coordinates coordinates) {
        return isValidLeftCordinates(coordinates)
                && isValidUpperDiagonalCordinates(coordinates)
                && isValidLowerDiagonalCordinates(coordinates);
    }

    public boolean isQueenboardFull() {
        return successCount == queens.length;
    }

    public void putQueen() {
        performQueenOperation(defaultCoordinates);
    }

    private void addCoordinateToStack(Coordinates coordinates){
        successCount++;
        stack.push(coordinates);
        list.add(coordinates);
    }

    private void putQueenAndCreateDefaultCoordinate(Coordinates coordinates) {
        int yCoordinate = coordinates.getYCoordinate();
        queens[coordinates.getXCoordinate()][yCoordinate] = 'Q';
        addCoordinateToStack(coordinates);
        defaultCoordinates = new Coordinates(0, yCoordinate + 1);
    }

    private void backtrackQueenAndCreateDefaultCoordinates() {
        if (defaultCoordinates.getXCoordinate() == queens.length - 1) {
            backTrackQueen();
        } else {
            defaultCoordinates = new Coordinates(
                    defaultCoordinates.getXCoordinate() + 1,
                    defaultCoordinates.getYCoordinate()
            );
        }
    }

    private void performQueenOperation(Coordinates coordinates) {
        if (isValidCoordinates(coordinates)) {
            putQueenAndCreateDefaultCoordinate(coordinates);
        } else {
            backtrackQueenAndCreateDefaultCoordinates();
        }
    }

    private void backTrackQueen() {
        Coordinates lastCoordinate = stack.pop();
        queens[lastCoordinate.getXCoordinate()][lastCoordinate.getYCoordinate()] = ' ';
        list.remove(lastCoordinate);
        successCount--;
        if (lastCoordinate.getXCoordinate() == queens.length - 1) {
            backTrackQueen();
        } else {
            defaultCoordinates = new Coordinates(lastCoordinate.getXCoordinate() + 1, lastCoordinate.getYCoordinate());
        }
    }

    public List<Coordinates> getCoordinates() {
        return list;
    }
}
