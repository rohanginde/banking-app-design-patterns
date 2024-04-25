package com.example.demo.patterns.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.patterns.command.TransactionCommand;

public class TransactionCommandWithLogging extends TransactionCommandDecorator {
    private static final Logger logger = LoggerFactory.getLogger(TransactionCommandWithLogging.class);

    public TransactionCommandWithLogging(TransactionCommand decoratedCommand) {
        super(decoratedCommand);
    }

    @Override
    public void execute() {
        logger.info("Executing command: {}", decoratedCommand.getClass().getSimpleName());
        super.execute();  // Execute the decorated command
        logger.info("Command execution completed: {}", decoratedCommand.getClass().getSimpleName());
    }
}