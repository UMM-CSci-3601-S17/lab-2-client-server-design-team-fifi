package umm3601.ToDos;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.ArrayList;
/**
 * Created by blask017 on 1/29/17.
 */
public class todoController {

    private todo[] todos;

    public todoController() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("src/main/data/todos.json");
        todos = gson.fromJson(reader, todo[].class);
    }

    public todo[] listTodo(Map<String, String[]> queryParams){

        if(queryParams.containsKey("limit")) {
            int limit = Integer.parseInt(queryParams.get("limit")[0]);
            todos = limit(todos, limit);
        }

        if(queryParams.containsKey("status")) {
            String status = queryParams.get("status")[0];

            if(status.equals("complete")){
                todos = findComplete(todos, true);
            }else{
                todos = findComplete(todos, false);
            }
        }


        return todos;
    }

    public todo getID(String id){
        return Arrays.stream(todos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
    }

    private todo[] limit(todo[] input,int lim){
        todo[] result = new todo[lim];

        for(int i = 0; i < lim; i++){
            result[i] = input[i];
        }

        return result;
    }

    private todo[] findComplete(todo[] input, boolean complete){
        ArrayList<todo> result = new ArrayList<todo>();
        if(complete){
            for(todo x: todos){
                if(x.status){
                    result.add(x);
                }
            }
        }else{
            for(todo x: todos){
                if(x.status == false){
                    result.add(x);
                }
            }
        }

        todo[] actualResult = new todo[result.size()];
        for(int i = 0; i < result.size(); i++){
            actualResult[i] = result.get(i);
        }
        return actualResult;
    }
}
