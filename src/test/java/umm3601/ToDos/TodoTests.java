package umm3601.ToDos;

import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by blask017 on 2/2/17.
 */
public class TodoTests {
    @Test
    public void filtetTodoByStatus() throws IOException {
        todoController todosController = new todoController();
        todo[] allTodos = todosController.listTodo(new HashMap<>());
        todo[] completeStatus = todosController.findComplete(allTodos, true);// Gets all todos with the status that are true
        assertEquals("Incorrect status should be true", true ,completeStatus[0].status);//check that any todo elements in the complete status array should have true as its status
        assertTrue("Incorrect status, status should be complete", completeStatus[7].status);//checks the 7th elements in the arraye of true statuses
        todo[] incompleteStatus = todosController.findComplete(allTodos, false);//Gets all todos with false status
        assertEquals("Incorrect status should be false", false ,incompleteStatus[0].status);//check that any todo element in the incomplete status array should return false
        assertFalse("Incorrect status, status should be incomplete ", incompleteStatus[28].status );//checks 28th element in array to make sure its false
    }

    @Test
    public void listTodosWithStatusFilter() throws IOException {
        todoController todosController = new todoController();
        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("status", new String[] {"complete"});
        todo[] complete = todosController.listTodo(queryParams);
        assertEquals("Incorrect status", 143, complete.length);
        queryParams.put("status", new String[] {"incomplete"});
        todo[] incomplete = todosController.listTodo(queryParams);
        assertEquals("Incorrect status", 157, incomplete.length);
    }

    @Test
    public void filterTodosByBodySearch() throws IOException {
        todoController todosController = new todoController();
        todo[] allTodos = todosController.listTodo(new HashMap<>());
        todo[] todoBodySearch = todosController.bodySearch(allTodos, "reprehenderit");
        assertEquals("Incorrect body should contain reprehenderit ", "Aliqua esse aliqua veniam id nisi ea. Ullamco Lorem ex aliqua aliquip cupidatat incididunt reprehenderit voluptate ad nisi elit dolore laboris.", todoBodySearch[0].body);
        todo[] todoBodySearch1 = todosController.bodySearch(allTodos, "laborum");
        assertEquals("Incorrect body should contain laborum", "Ipsum esse est ullamco magna tempor anim laborum non officia deserunt veniam commodo. Aute minim incididunt ex commodo.", todoBodySearch1[0].body);
    }


    @Test
    public void filterTodosByCategory() throws IOException {
        todoController todosController = new todoController();
        todo[] allTodos = todosController.listTodo(new HashMap<>());
        todo[] todoDesignCategory = todosController.searchCategory(allTodos, "software design");
        //For loop that will check each element of the todoOwnerBlanche array and checks that the owner is Blanche
        for (int i = 0; i < todoDesignCategory.length; i++){
            assertEquals("Incorrect category should be software design", "software design", todoDesignCategory[i].category);
        }
        //For loop that will check each element of the todoOwnerBlanche array and checks that the owner is Workman
        todo[] todoHomeworkCategory = todosController.searchOwner(allTodos, "homework");
        for (int i = 0; i < todoHomeworkCategory.length; i++) {
            assertEquals("Incorrect category should be homework", "homework", todoDesignCategory[i].category);
        }
    }

    @Test
    public void filterTodosByOwner() throws IOException {
        todoController todosController = new todoController();
        todo[] allTodos = todosController.listTodo(new HashMap<>());
        todo[] todoOwnerBlanche = todosController.searchOwner(allTodos, "Blanche");
        //For loop that will check each element of the todoOwnerBlanche array and checks that the owner is Blanche
        for (int i = 0; i < todoOwnerBlanche.length; i++){
            assertEquals("Incorrect owner should be owned by Blanche", "Blanche", todoOwnerBlanche[i].owner);
        }
        //For loop that will check each element of the todoOwnerBlanche array and checks that the owner is Workman
        todo[] todoOwnerWorkman = todosController.searchOwner(allTodos, "Workman");
        for (int i = 0; i < todoOwnerWorkman.length; i++) {
            assertEquals("Incorrect owner should be owned by Workman", "Workman", todoOwnerWorkman[i].owner);
        }
    }


    @Test
    public void listTodosWithOwnerFilter() throws IOException {
        todoController todosController = new todoController();
        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("owner", new String[] {"Barry"});
        todo[] ownerBarry = todosController.listTodo(queryParams);
        assertEquals("Incorrect owner should be owned by Barry",51 , ownerBarry.length);
        queryParams.put("owner", new String[] {"Fry"});
        todo[] ownerFry = todosController.listTodo(queryParams);
        assertEquals("Incorrect owner should be owned by Fry",61, ownerFry.length);
    }
    @Test
    public void totalTodoCount() throws IOException{
        todoController todosController = new todoController();
        todo[] allTodos = todosController.listTodo(new HashMap<>());
        assertEquals("Incorrect total number of todos",allTodos.length ,allTodos.length);
    }

    @Test
    public void firstTodoInTodoList() throws IOException {
        todoController todosController = new todoController();
        todo[] allTodos = todosController.listTodo(new HashMap<>());
        todo firstTodo = allTodos[0];
        assertEquals("Incorrect owner","Blanche", firstTodo.owner);
        assertTrue("Incorrect status", false==firstTodo.status);
        assertEquals("Incorrect body", "In sunt ex non tempor cillum commodo amet incididunt anim qui commodo quis. Cillum non labore ex sint esse.", firstTodo.body);
        assertEquals("Incorrect category", "software design", firstTodo.category);
    }

    @Test
    public void getFirstTodoID() throws IOException{
        todoController todosController = new todoController();
        todo todos = todosController.getID("58895985a22c04e761776d54");
        assertEquals("Incorrect owner", "Blanche", todos.owner);
    }

    @Test
    public void getOtherTodoID() throws IOException{
        todoController todosController = new todoController();
        todo todos = todosController.getID("58895985c1849992336c219b");
        assertEquals("Incorrect owner", "Fry", todos.owner);

    }

    @Test
    //filterUsersByAge
    public void limitTodos() throws IOException {
        todoController todosController = new todoController();
        todo[] allTodos = todosController.listTodo(new HashMap<>());
        todo[] todosWithLimit7 = todosController.limit(allTodos, 7);
        assertEquals("Incorrect number of todos should be 7", 7, todosWithLimit7.length);
        todo[] todosWithLimit10 = todosController.limit(allTodos, 10);
        assertEquals("Incorrect number of todos should be 10", 10, todosWithLimit10.length);
    }

    @Test
    //listUsersWithAgeFilter
    public void listTodosWithLimitFilter() throws IOException {
        todoController todosController = new todoController();
        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("limit", new String[]{"7"});
        todo[] todosWithLimit7 = todosController.listTodo(queryParams);
        assertEquals("Incorrect number of todos", 7, todosWithLimit7.length);
        queryParams.put("limit", new String[]{"10"});
        todo[] todosWithLimit10 = todosController.listTodo(queryParams);
        assertEquals("Incorrect number of todos", 10, todosWithLimit10.length);
    }

    @Test
    public void testCombinations() throws IOException{
        todoController todosController = new todoController();
        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("limit", new String[] {"7"});
        queryParams.put("status", new String[] {"incomplete"});
        queryParams.put("owner", new String[] {"Blanche"});
        todo[] tester = todosController.listTodo(queryParams);

        assertEquals("incorrect number of todos", 7, tester.length);
        for(int i = 0; i < tester.length; i++){
            assertEquals("incorrect status", false, tester[i].status);
            assertEquals("incorrect owner", "Blanche", tester[i].owner);
        }

    }

    @Test
    public void testOrder() throws IOException{
        todoController todosController = new todoController();
        Map<String, String[]> queryParams = new HashMap<>();
        queryParams.put("orderBy", new String[] {"owner"});
        todo[] tester = todosController.listTodo(queryParams);
        String[] names = {"Barry", "Blanche", "Dawn", "Fry", "Roberta", "Workman"};

        ArrayList<String> holder = new ArrayList<>();
        for(todo x: tester){
            if(!holder.contains(x.owner)){
                holder.add(x.owner);
            }
        }

        for(int i = 0; i < holder.size(); i++){
            assertEquals("wrong order", holder.get(i), names[i]);
        }
    }
}
