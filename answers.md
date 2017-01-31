What is the purpose of .gitignore? <br>
Files in the .gitignore file are untracked intentionally. 
My group thinks that things in this file are files that should stay the same throughtout the developement process

Explain what a route is? 
A route is made up three pieces: a verb, a path, and a callback. Routes are the building blocks of spark.

What is the purpose of the `umm3601.user.UserController` class?
Where the all the user and user manipulations take place.

Explain what happens when a user accesses each of the
following URLs:
- :question: The page `users`
What happens when the 'users' page is it shows a 'Get All users' button.
- :question: The page `api/users`
What happens when the 'api/users' page is loaded it shows all users and their information.
- :question: The page `api/users?age=25`
What happens when the 'api/users?age=25' page is it filters all users that are 25.
- :question: The page `api/users/588935f5de613130e931ffd5`
What happens when the 'api/users?588935f5de613130e931ffd5' page is it filters all users by their specific id.


:question: What happens when the user accesses the page "kittens"? Modify the server code so accessing the page "kittens" results in the text "Meow". Describe what you did and how it worked. 
At first it would give us a 404 not found error, but then we made a new kitten.html file and just had the server redirect to that page when the user tries to access the /kittens page.