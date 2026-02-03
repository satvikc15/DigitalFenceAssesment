- I wanted to add two icon buttons add and minus to modify the quantity in home screen itself

-  As there is not default minus symbol in Icons.default I have used Arrow back and arrow forward functions for updating the quantity

- To implement this I have stored the original quantity which is fetched form the database in a new variable.

- The arrow functions increments and decrements this quantity

- I made a check to see if quantity is always greater that 1 while decrementing

- I have created a clickable function which on change in value of quantity run the viewModel.updateWIsh() function to update the database too.

- I have also edited the update screen option where it displays the counter option to change the quantity instead of entering manually

- One more change is the update function is not called everytime the quantity is changed instead onListQuantChange() function is called 

- which only updated the database one the update button is clicked

