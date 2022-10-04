package com.example.tictactoe;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class BaseGame {

    String[][] fields = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

    public String[][] getFields(){return this.fields;}
    public void tableCreation(){
        printTable(fields);
    }
    public Boolean tableUpdate(Integer playerMove, String symbol, Integer iteration){
        Integer[] fieldPosition = getFieldToReplace(playerMove);
        if(checkFieldOccupied(fields, fieldPosition) || iteration==0) {
            this.fields[fieldPosition[0]][fieldPosition[1]] = symbol;
            printTable(fields);
            return true;
        }
        else{
            System.out.println("Position already used, try another");
            return false;
        }
    }

    public Integer[] getFieldToReplace(Integer playerMove){
        Integer[] response = new Integer[2];
        switch (playerMove%3){
            case 0:{response[0] = playerMove/3 - 1;
                    response[1] = 2;
                    break;}
            case 1:{response[0] = playerMove/3;
                    response[1] = 0;
                    break;}
            case 2:{response[0] = playerMove/3;
                    response[1] = 1;
                    break;}
            default:{break;}
        }
        return response;
    }

    public void printTable(String[][] fields){
        System.out.println(" " + fields[0][0] + " | " + fields[0][1] + " | " + fields[0][2] + " " );
        System.out.println("———+———+———");
        System.out.println(" " + fields[1][0] + " | " + fields[1][1] + " | " + fields[1][2] + " " );
        System.out.println("———+———+———");
        System.out.println(" " + fields[2][0] + " | " + fields[2][1] + " | " + fields[2][2] + " " );
    }

    public Boolean checkFieldOccupied(String[][] fields, Integer[] fieldPosition){
        return Objects.equals(fields[fieldPosition[0]][fieldPosition[1]], " ");
    }
    public Boolean checkSomeoneWon(){
        Boolean row1 = checkIfAll3TheSame(fields[0][0], fields[0][1], fields[0][2]);
        Boolean row2 = checkIfAll3TheSame(fields[1][0], fields[1][1], fields[1][2]);
        Boolean row3 = checkIfAll3TheSame(fields[2][0], fields[2][1], fields[2][2]);
        Boolean column1 = checkIfAll3TheSame(fields[0][0], fields[1][0], fields[2][0]);
        Boolean column2 = checkIfAll3TheSame(fields[0][1], fields[1][1], fields[2][1]);
        Boolean column3 = checkIfAll3TheSame(fields[0][2], fields[1][2], fields[2][2]);
        Boolean cross1 = checkIfAll3TheSame(fields[0][0], fields[1][1], fields[2][2]);
        Boolean cross2 = checkIfAll3TheSame(fields[0][2], fields[1][1], fields[2][0]);
        return !row1 && !row2 && !row3 && !column1 && !column2 && !column3 && !cross1 && !cross2;
    }

    public Boolean checkIfAll3TheSame (String A, String B, String C){
        if (Objects.equals(A, "X") && Objects.equals(B, "X") && Objects.equals(C, "X")){
            return true;}
        else if (Objects.equals(A, "O") && Objects.equals(B, "O") && Objects.equals(C, "O")){
            return true;}
        return false;
    }
}
