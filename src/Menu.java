import java.util.TreeMap;

/**
 * Class used to print out the menu for users to understand the program
 */
 class Menu {
    private ToDoList toDoList;

    public Menu(ToDoList toDoList){
        this.toDoList = toDoList;
    }

    /**
     * Prints out the main menu, giving the users the option to
     *  1. View to-do list
     *  2. Add a task
     *  3. Delete a task
     */
    public void printMenu(){
        System.out.println("===========MENU===========");
        System.out.println("Please enter your choice");
        System.out.println("1. View to-do list");
        System.out.println("2. Add a task");
        System.out.println("3. Delete a task");
        System.out.println("4. Exit the program");
    }

    /**
     * Prints the instructions on how to add a task
     */
    public void printAddTask(){
        System.out.println("Enter your task in the following format");
        System.out.println("[Task name] PRESS ENTER");
        System.out.println("(Optional) [Date Due] PRESS ENTER");
    }

    /**
     * Uses the printTasks method to print a list of the tasks
     * and prompts user for input on the task to delete
     */
    public void printDeleteTask(){
        printTasks();
        System.out.println("Please enter your task number");
    }

    /**
     * Prints out all the tasks that need completing as an ordered list
     */
    public void printTasks(){
        System.out.println("===========TO-DO LIST===========");
        for(Integer taskNumber : toDoList.getToDoList().keySet()){
            System.out.printf("%s. %s%n", taskNumber, toDoList.getTask(taskNumber));
        }
    }
}
