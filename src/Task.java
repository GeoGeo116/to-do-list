/**
 * Represents a single task on a to-do-list
 * @param task A string variable representing the task name/short description
 * @param dueDate A string value representing the date, e.g. dd/mm/yyyy
 */
public record Task(String task, String dueDate){
    public Task(String task){
        this(task, null);
    }

    @Override
    public String toString() {
        if(dueDate!=null){
            return String.format("%s due on %s", task, dueDate) ;
        }else{
            return task;
        }
    }
}