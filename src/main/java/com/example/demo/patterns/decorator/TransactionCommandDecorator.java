package com.example.demo.patterns.decorator;

import com.example.demo.patterns.command.TransactionCommand;

public abstract class TransactionCommandDecorator implements TransactionCommand {
    protected TransactionCommand decoratedCommand;

    public TransactionCommandDecorator(TransactionCommand decoratedCommand) {
        this.decoratedCommand = decoratedCommand;
    }

    @Override
    public void execute() {
        decoratedCommand.execute();  // Delegate to the decorated command object
    }
}