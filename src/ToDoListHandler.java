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
            try {
                int userInput = scanner.nextInt();
                scanner.nextLine();
                switch(userInput){
                    case 1:
                        menu.printTasks();
                        scanner.nextLine();
                        break;
                    case 2:
                        addTask();
                        break;
                    case 3:
                        deleteTask();
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Not a valid option");
                        break;
                }
            }catch(Exception e){
                System.out.println("Please enter a valid number");
                scanner.nextLine();
            }
        }
    }

    /**
     * Runs a code block to add a task
     */
    public void addTask(){
        menu.printAddTask();
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

    /**
     * Runs a code block to delete a task
     */
    public void deleteTask(){
        menu.printDeleteTask();
        try{
            int taskNumber = scanner.nextInt();
            if(toDoList.getToDoList().containsKey(taskNumber)) {
                toDoList.deleteTask(taskNumber);
            }else{
                System.out.println("There is no task with that number");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid number");
        }
    }

}
