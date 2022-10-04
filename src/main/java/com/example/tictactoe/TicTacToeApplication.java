package com.example.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TicTacToeApplication implements CommandLineRunner {
    @Autowired
    private static Logger LOG = LoggerFactory.getLogger(TicTacToeApplication.class);

    @Autowired
    BaseGame baseGame;

    public static void main(String[] args) {
        LOG.info("STARTING THE GAME");
        SpringApplication.run(TicTacToeApplication.class, args);
        LOG.info("GAME ENDED");
    }

    @Override
    public void run(String... args) {
        String[] symbol = {"X", "O", "X", "O", "X", "O", "X", "O", "X"};
        Integer iteration = 0;
        baseGame.tableCreation();
        Scanner scan = new Scanner(System.in);
        do {
            LOG.info("Choose the field (from 1 to 9):");
            int Move;
            if(isCpuTurn(iteration)){Move = cpuMove();}
            else{Move = scan.nextInt();}
            if(Move>9 || Move<1 ){
                do{
                    System.out.println("Wrong input");
                    LOG.info("Choose the field (from 1 to 9):");
                    Move = scan.nextInt();
                }
                while(Move>9 || Move<1);
            }
            Boolean isOk = baseGame.tableUpdate(Move, symbol[iteration], iteration);
            if(isOk) {
                iteration++;
            }
        }
        while(iteration<9 && baseGame.checkSomeoneWon());
        if(!(baseGame.checkSomeoneWon())){
            System.out.println("Congratulations, you Won!!");
        }
    }

    public Integer cpuMove(){
        double D = ((Math.random() * (9 - 1)) + 1);
        return (int) D;
    }

    //if iteration isn't even, it's the cpu turn (player always goes first)
    public boolean isCpuTurn(Integer Iteration){
        return Iteration % 2 != 0;
    }

}
