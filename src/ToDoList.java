import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a to-do-list to store individual task objects
 * Has basic methods to print tasks, add tasks and remove tasks
 */
public class ToDoList {
    private Map<Integer, Task> list;
    private int count;

    public ToDoList(){
        list = new TreeMap<>();
        count=0;
    }

    /**
     * Adds a task to the to-do list
     * @param task A Task object to add to the to-do list map
     */
    public void addTask(Task task){
        list.put(count+1, task);
        count++;
    }

    /**
     * Deletes a task from the to-do list, essentially
     * 'completing it'
     * @param taskNumber The number of the task that needs deleting
     */
    public void deleteTask(int taskNumber){
        list.remove(taskNumber);
        Map<Integer, Task> updatedList = new TreeMap<>();
        int newKey = 1;
        for(int task : list.keySet()){
            if(task > taskNumber){
                updatedList.put(newKey, list.get(task));
                newKey++;
            }else{
                updatedList.put(task, list.get(task));
            }
        }
        list = updatedList;
        count--;
    }

    /**
     * Prints out the current state of the to-do list
     */
    public void printList(){
        for(Integer key : list.keySet()){
            System.out.printf("%s. %s\n",key, list.get(key));
        }
    }

    /**
     * Gets the map of the to-do list
     * @return Map of the to-do list, a Task object mapped to an Integer value
     */
    public Map<Integer, Task> getToDoList(){
        return list;
    }

    /**
     * Gets a specific task from the to-do list
     * @param taskNumber The number of the task to return
     * @return A Task object corresponding to the task number
     */
    public Task getTask(Integer taskNumber){
        return list.get(taskNumber);
    }
}
