package org.bonn.se.ws18.midterm.commands;

import java.util.Stack;

public class CommandManager {
	
	private static CommandManager instance;
	
	private Stack<UndoableCommand> CommandStack;
	
	private CommandManager(){
		CommandStack = new Stack<UndoableCommand>();
	}
	
	public  static synchronized CommandManager  getInstance() {
		if(instance == null) {
			instance = new CommandManager();
		}
		return instance;
	}
	
	public void executeCommand(Command cmd,String[] args) {
		
		cmd.execute(args);
		
		if(cmd instanceof UndoableCommand) {
			CommandStack.push((UndoableCommand)cmd);
		}
	}
	
	
	public void undo(){
		
		if(!CommandStack.isEmpty()) {
			UndoableCommand cmd = CommandStack.pop();
			cmd.undo();
		}
		else {
			System.out.println("Nothing to Undo!");
		}
	}
}
