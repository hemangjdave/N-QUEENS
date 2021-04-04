package com.techrevolution.nqueens.configuration;

import com.techrevolution.nqueens.logic.QueensBoardUpdated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class QueenConfiguration {

    @Bean
    @Scope("prototype")
    public QueensBoardUpdated queensBoard(){
        return new QueensBoardUpdated();
    }

}
