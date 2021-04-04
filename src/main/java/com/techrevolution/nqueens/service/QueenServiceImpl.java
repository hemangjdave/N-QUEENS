package com.techrevolution.nqueens.service;

import com.techrevolution.nqueens.logic.QueensBoardUpdated;
import com.techrevolution.nqueens.model.Coordinates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueenServiceImpl implements QueensService{

    private final QueensBoardUpdated queensBoard;

    @Override
    public List<Coordinates> getCoordinates(int queenNumbers) {
        queensBoard.initializedQueensBoard(queenNumbers);
        while (!queensBoard.isQueenboardFull()) {
            queensBoard.putQueen();
        }
        return queensBoard.getCoordinates();
    }
}
