package com.techrevolution.nqueens;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class NQueensApplication {

    public static void main(String[] args) {
        SpringApplication.run(NQueensApplication.class);
    }

//    // 2 3 not possible
//    QueensBoardUpdated queensBoard = new QueensBoardUpdated(18);
//        while (!queensBoard.isQueenboardFull()) {
//        queensBoard.putQueen();
//    }
//        log.info("---------------GATHERED CORDINATES ARE ------------------------");
//        queensBoard.getCoordinates().forEach(System.out::println);
}
