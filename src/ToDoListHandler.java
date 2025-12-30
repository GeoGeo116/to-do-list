import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Used to make the to-do list work
 */
public class ToDoListHandler {
    private ToDoList toDoList;
    private Scanner scanner;
    private Menu menu;
    private String fileName;

    public ToDoListHandler(){
        toDoList = new ToDoList();
        scanner = new Scanner(System.in);
        menu = new Menu(toDoList);
    }

    public void runProgram(){
        boolean running = false;
        //Prompts user to open file or create new file
        while(!running){
            System.out.println("Please enter a filename in the format filename(.txt)");
            try{
                String userInput = scanner.nextLine();
                if(!userInput.endsWith(".txt")) userInput = userInput.concat(".txt");
                if(new File(userInput).isFile()){
                    readFromFile(userInput);
                    fileName = userInput;
                    running=true;
                }else if(!userInput.isEmpty()){
                    System.out.println(userInput+" doesn't exist, would you like to create it?[Y/N]");
                    String createFile = scanner.nextLine().toLowerCase();
                    if(createFile.equals("y")){
                        File file = new File(userInput);
                        file.createNewFile();
                        fileName = userInput;
                        running = true;
                    }
                }
            }catch(Exception e){
                System.out.println("Please enter a valid number");
                scanner.nextLine();
            }

        }
        //Runs the main program
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
                        saveFile();
                        running = false;
                        break;
                    default:
                        System.out.println("Not a valid option");
                        break;
                }
            }catch(Exception e){
                System.out.println(e);
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
        if(taskDate==null || taskDate.isEmpty()){
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
            if(taskNumber==0){
                //Do nothing
            }else if(taskNumber==-1){
                toDoList.clearList();
            }else if(toDoList.getToDoList().containsKey(taskNumber)) {
                toDoList.deleteTask(taskNumber);
            }else{
                System.out.println("There is no task with that number");
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid number");
        }
    }

    /**
     * Saves the current state of the to-do list to a file
     */
    public void saveFile() throws IOException {
        StringBuilder strToWrite = new StringBuilder();
        for(Task task:toDoList.getToDoList().values()){
            strToWrite.append(task.task()+":::"+task.dueDate()+"\n");
        }
        if(strToWrite.isEmpty()) return;
        strToWrite.deleteCharAt(strToWrite.length()-1); //Removes last newline
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(strToWrite.toString());
            bw.close();
        }catch (FileNotFoundException e){
            System.out.println("Error saving to file, creating new file "+fileName+"...");
        }
    }

    /**
     * Reads from the file and creates a to-do list object from the file
     * @param fileName The name of the file to read from
     */
    public void readFromFile(String fileName) throws IOException{
        Scanner reader = new Scanner(new File(fileName));
        while(reader.hasNext()){
            String[] task = reader.nextLine().split(":::");
            Task aTask;
            if(task[1].equals("null")){
                aTask = new Task(task[0]);
            }else{
                aTask = new Task(task[0], task[1]);
            }
            this.toDoList.addTask(aTask);
        }
    }


}
