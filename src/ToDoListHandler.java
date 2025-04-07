import java.util.Scanner;

/**
 * Used to make the to-do list work
 */
public class ToDoListHandler {
    private ToDoList toDoList;
    private Scanner scanner;
    private Menu menu;

    public ToDoListHandler(){
        toDoList = new ToDoList();
        scanner = new Scanner(System.in);
        menu = new Menu(toDoList);
    }

    public void runProgram(){
        boolean running = true;
        while(running){
            menu.printMenu();
            int userInput = scanner.nextInt();
            if(userInput == 1){
                menu.printTasks();
            }else if(userInput==2){
                menu.printAddTask();
                scanner.nextLine();
                String taskName = scanner.nextLine();
                String taskDate = scanner.nextLine();
                Task aTask;
                if(taskDate==null || taskDate==""){
                    aTask = new Task(taskName);
                }else{
                    aTask = new Task(taskName, taskDate);
                }
                toDoList.addTask(aTask);
            }
        }
    }


}
