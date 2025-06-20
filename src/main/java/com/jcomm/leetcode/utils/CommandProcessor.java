package com.jcomm.leetcode.utils;

import com.jcomm.utils.JFileReader;
import com.jcomm.whiteboard.RandomSet;

import java.util.HashSet;
import java.util.Set;

public class CommandProcessor {

    private final JFileReader commandReader;

    private final JFileReader valueReader;

    private final LeetCodeSolution leetCodeSolution;

    private final Set<Integer> numbersPool = new HashSet<>();



    public CommandProcessor(String commandsFile, String valuesFile, LeetCodeSolution leetCodeSolution, boolean ignoreFirstCommand) {
        this.commandReader = new JFileReader(commandsFile);
        this.valueReader = new JFileReader(valuesFile);
        this.leetCodeSolution = leetCodeSolution;
        if(ignoreFirstCommand) {
            commandReader.getNextLine();
            valueReader.getNextLine();
        }

    }


    public void init() {

        while(commandReader.hasMoreLines()) {

            String cmd = commandReader.getNextLine();

            if (cmd.equalsIgnoreCase("insert")) {
                String value = valueReader.getNextLine()
                        .replace("[", "")
                        .replace("]", "");
                System.out.println(" command "+cmd+" value: "+value);
                if (!"".equalsIgnoreCase(value)) {
                    int number = Integer.parseInt(value);
                    leetCodeSolution.insert(number);
                    numbersPool.add(number);
                }

            } else if (cmd.equalsIgnoreCase("getRandom")) {
                valueReader.getNextLine();
                int randomValue = leetCodeSolution.getRandom();
                System.out.println("command: "+cmd+" "+randomValue);
                assert numbersPool.contains(randomValue) ;
            } else if (cmd.equalsIgnoreCase("remove")) {
                String value = valueReader.getNextLine()
                        .replace("[", "")
                        .replace("]", "");
                System.out.println("command: "+cmd+" value: "+value);
                Integer deletedNumber = Integer.parseInt(value);
                if(numbersPool.contains(deletedNumber)) {
                    numbersPool.remove(deletedNumber);
                    assert !numbersPool.contains(deletedNumber) == leetCodeSolution.remove(deletedNumber);
                } else {
                    assert numbersPool.contains(deletedNumber) == leetCodeSolution.remove(deletedNumber);
                }


            }
        }
    }



    public static void main(String[] args) {
        CommandProcessor commandProcessor = new CommandProcessor("randomset_cmds", "randomset_values", new RandomSet(),  false);

        commandProcessor.init();
    }

}
