What is the purpose of .gitignore? <br>
Files in the .gitignore file are untracked intentionally. 
My group thinks that things in this file are files that should stay the same throughout the development process

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

What are the contents of the `public` folder? What is the purpose of each of the HTML files there?
It contains the client side code including the javascript and Html/css files. It also has the images for the webpage.
They make up the webpages of the website. About.html is an about page, index.html is the main page, and users.html is where the users will 
be displayed.

:question: Describe what happens when you filter users by
age in the client? What is read from the web page, and what
request is sent to the server? What is received, and how/where
is it displayed?

When users are filtered by age the users that are that age are displayed. The read from the web page is that the user classes
are displayed filtered by age. The request is a map odf size 1 with the key being the string age and the value is a string array of length 1 
with a string inside that is a number. We receive an array of users and it is displayed in another web page being filtered by 
specific age. 

question: Where is the client-side JavaScript defined? Name the file(s) in which it is used.
The client side javascript is in the javascript folder within the public folder named users.js and used in users.html.