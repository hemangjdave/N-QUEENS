package com.techrevolution.nqueens.controller;

import com.techrevolution.nqueens.model.Coordinates;
import com.techrevolution.nqueens.service.QueensService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QueensController {

    private final QueensService queensService;

    @GetMapping("getQueens")
    public ResponseEntity<List<Coordinates>> getAllCoordinates(@RequestParam("queenNumber") int queenNumbers){

        if (queenNumbers == 2 || queenNumbers == 3) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(Collections.emptyList());
        }
        return ResponseEntity.status(HttpStatus.OK).body(queensService.getCoordinates(queenNumbers));
    }

}
