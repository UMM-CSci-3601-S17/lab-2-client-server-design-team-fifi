/**
 * Created by blask017 on 2/2/17.
 */
window.onload = function() {
    console.log("The page is loaded now!");

    var element = document.getElementById('getAllTodos');
    element.addEventListener("click", getAllTodos, true);

    var element = document.getElementById('submit');
    element.addEventListener("click", getTodoByParameters, true);

}


/**
 * Function to get all the users!
 */
var getAllTodos = function() {
    var HttpThingy = new HttpClient();
    HttpThingy.get("/api/todos", function(returned_json){
        document.getElementById('jsonDump1').innerHTML = returned_json;
    });
}

var getTodoByParameters = function() {
    var owner = document.getElementById('own').value;
    var id = document.getElementById('ID').value;
    var status = document.getElementById('stat').value;
    var contains = document.getElementById('con').value;
    var category = document.getElementById('cat').value;
    var limit = document.getElementById('lim').value;
    var orderParam = document.getElementById('order').value;
    var url;

    if(id){
        url = "/api/todos/" + id;
    }else{
        url = "/api/todos?owner=" + owner + "&contains=" + contains + "&category=" + category + "&orderBy=" + orderParam;
    }

    if(status != ""){
        url += "&status=" + status;
    }

    if(limit != ""){
        url += "&limit=" + limit;
    }

    var HttpThingy = new HttpClient();
    HttpThingy.get(url , function(returned_json){
        document.getElementById('jsonDump1').innerHTML = returned_json;
    });
}


/**
 * Wrapper to make generating http requests easier. Should maybe be moved
 * somewhere else in the future!.
 *
 * Based on: http://stackoverflow.com/a/22076667
 * Now with more comments!
 */
function HttpClient() {
    // We'll take a URL string, and a callback function.
    this.get = function(aUrl, aCallback){
        var anHttpRequest = new XMLHttpRequest();

        // Set a callback to be called when the ready state of our request changes.
        anHttpRequest.onreadystatechange = function(){

            /**
             * Only call our 'aCallback' function if the ready state is 'DONE' and
             * the request status is 200 ('OK')
             *
             * See https://httpstatuses.com/ for HTTP status codes
             * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
             *  for XMLHttpRequest ready state documentation.
             *
             */
            if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                aCallback(anHttpRequest.responseText);
        }

        anHttpRequest.open("GET", aUrl, true);
        anHttpRequest.send(null);
    }
}