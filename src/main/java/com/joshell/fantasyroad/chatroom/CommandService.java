package com.joshell.fantasyroad.chatroom;

import org.springframework.stereotype.Service;

/**
 * Class to handle the chat rooms roll command
 */
@Service
public class CommandService {

    /**
     * Handles command input (more commands can be added if needed
     * @param command
     * @return eithe the results of the roll or a error message
     */
    public String processCommand(String command){
        if (command.startsWith("/roll")){
            String[] commandConfigs = command.split("\\s+");
            if (commandConfigs.length == 3){
                try{
                    int diceSides = Integer.parseInt(commandConfigs[1]);
                    int diceAmount = Integer.parseInt(commandConfigs[2]);

                    if (diceSides <= 0 || diceAmount <= 0){
                        return "Invalid input. The Dice should have one or more sides and one or more dice should be thrown.";
                    }
                    return rollDice(diceSides, diceAmount);

                }catch (NumberFormatException e){
                    return "Invalid input. Use the format: /roll [number] [number]";
                }
            }
            else {
                return "Not enough arguments provided. Use the format: /roll [number] [number]";
            }
        }
        return "Command not found";
    }

    private String rollDice(int diceSides, int diceAmount) {

        StringBuilder result = new StringBuilder("Rolled:");
        for (int i = 0; i < diceAmount; i++){
            int diceResult = (int) (Math.random() * diceSides) + 1;
            result.append(diceResult + "/" + diceSides);
            if (i != diceAmount - 1){
                result.append(", ");
            }
        }
        return result.toString();
    }
}
