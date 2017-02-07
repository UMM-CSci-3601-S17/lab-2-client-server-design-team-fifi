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

    //method to list all todos with various filters
    public todo[] listTodo(Map<String, String[]> queryParams) {
        todo[] todoTemp = todos;
        int size = queryParams.size();

        if (queryParams.containsKey("status")) {
            String status = queryParams.get("status")[0];

            if (status.equals("complete")) {
                todoTemp = findComplete(todoTemp, true);
            } else {
                todoTemp = findComplete(todoTemp, false);
            }
        }

        if (queryParams.containsKey("contains")) {
            String body = queryParams.get("contains")[0];

            todoTemp = bodySearch(todoTemp, body);
        }

        if (queryParams.containsKey("owner")) {
            String owner = queryParams.get("owner")[0];

            todoTemp = searchOwner(todoTemp, owner);
        }

        if (queryParams.containsKey("category")) {
            String category = queryParams.get("category")[0];

            todoTemp = searchCategory(todoTemp, category);
        }

        if (queryParams.containsKey("orderBy")) {
            String parameter = queryParams.get("orderBy")[0];

            todoTemp = order(todoTemp, parameter);
        }

        if (queryParams.containsKey("limit")) {
            int limit = Integer.parseInt(queryParams.get("limit")[0]);
            todoTemp = limit(todoTemp, limit);
        }

        return todoTemp;
    }

    //method to get id of a specified todo
    public todo getID(String id) {
        return Arrays.stream(todos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
    }

    public todo[] limit(todo[] input, int lim) {
        todo[] result = new todo[lim];

        for (int i = 0; i < lim; i++) {
            result[i] = input[i];
        }

        return result;
    }

    //method to find the status of todo
    public todo[] findComplete(todo[] input, boolean complete) {
        ArrayList<todo> result = new ArrayList<todo>();
        if (complete) {
            for (todo x : input) {
                if (x.status) {
                    result.add(x);
                }
            }
        } else {
            for (todo x : input) {
                if (x.status == false) {
                    result.add(x);
                }
            }
        }

        todo[] actualResult = new todo[result.size()];
        for (int i = 0; i < result.size(); i++) {
            actualResult[i] = result.get(i);
        }
        return actualResult;
    }

    //method to find if a body of a todo contains a specified word
    public todo[] bodySearch(todo[] input, String searchParam) {
        ArrayList<todo> holder = new ArrayList<>();
        for (todo x : input) {
            if (x.body.contains(searchParam)) {
                holder.add(x);
            }
        }
        todo[] actualResult = new todo[holder.size()];
        for (int i = 0; i < holder.size(); i++) {
            actualResult[i] = holder.get(i);
        }

        return actualResult;

    }

    //method to find the owner of a todo
    public todo[] searchOwner(todo[] input, String name) {
        ArrayList<todo> holder = new ArrayList<>();
        for (todo x : input) {
            if (x.owner.contains(name)) {
                holder.add(x);
            }
        }
        todo[] actualResult = new todo[holder.size()];
        for (int i = 0; i < holder.size(); i++) {
            actualResult[i] = holder.get(i);
        }

        return actualResult;

    }

    //method that finds a certain category of a todo
    public todo[] searchCategory(todo[] input, String category) {
        ArrayList<todo> holder = new ArrayList<>();
        for (todo x : input) {
            if (x.category.contains(category)) {
                holder.add(x);
            }
        }
        todo[] actualResult = new todo[holder.size()];
        for (int i = 0; i < holder.size(); i++) {
            actualResult[i] = holder.get(i);
        }

        return actualResult;

    }

    //method that will put the todos in order based specified parameter
    public todo[] order(todo[] input, String param) {
        todo[] result = input;

        ArrayList<todo> holder = new ArrayList<>();

        for (todo x : input) {
            holder.add(x);
        }

        if (param.equals("owner")) {
            String[] temp = getNames(input);

            for (int i = 0; i < input.length; i++) {
                String searchParam = temp[i];

                int counter = 0;
                for (todo x : holder) {
                    if (x.owner.equals(searchParam)) {
                        result[i] = x;
                        holder.remove(counter);
                        break;
                    }
                    counter++;
                }

            }

        } else if (param.equals("body")) {
            String[] temp = getBody(input);

            for (int i = 0; i < input.length; i++) {
                String searchParam = temp[i];

                int counter = 0;
                for (todo x : holder) {
                    if (x.body.equals(searchParam)) {
                        result[i] = x;
                        holder.remove(counter);
                        break;
                    }
                    counter++;
                }

            }

        } else if (param.equals("category")) {
            String[] temp = getCategory(input);

            for (int i = 0; i < input.length; i++) {
                String searchParam = temp[i];

                int counter = 0;
                for (todo x : holder) {
                    if (x.category.equals(searchParam)) {
                        result[i] = x;
                        holder.remove(counter);
                        break;
                    }
                    counter++;
                }

            }

        } else if(param.equals("status")){
            result = getStatus(input);

        }

        return result;
    }

    //helper function that gets the names of a todo and sorts them
    public String[] getNames(todo[] input) {
        String[] names = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            names[i] = input[i].owner;
        }

        Arrays.sort(names);

        return names;
    }

    //helper function that gets the body of a todo and sorts them
    public String[] getBody(todo[] input) {
        String[] comm = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            comm[i] = input[i].body;
        }

        Arrays.sort(comm);

        return comm;
    }

    //helper function that gets the category of a todo and sorts them
    public String[] getCategory(todo[] input) {
        String[] categ = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            categ[i] = input[i].category;
        }

        Arrays.sort(categ);

        return categ;
    }

    //helper function that gets the status of a todo and sorts them
    public todo[] getStatus(todo[] input) {
        todo[] result = new todo[input.length];
        ArrayList<todo> holder = new ArrayList<>();

        for (todo x : findComplete(input, true)) {
            holder.add(x);
        }

        for (todo x : findComplete(input, false)) {
            holder.add(x);
        }

        for (int i = 0; i < result.length; i++) {
            result[i] = holder.get(i);
        }
        return result;
    }

}
