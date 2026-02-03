- One way that I have resolved keeping the state in Add Screen only when orientation changes is instead of defining the variable
`ListName`
`ListQuant`
`ListPurchase`
in the listViewModel and using it in the AddEditView.kt screen, I declared then in the composable function of AddEditScreen itself

- The main reasoning for this is when I tried to define `ListName` using rememberSaveable, it was throwing an error that `@Composable invocations can only happen from the context of a @Composable function`

- So I just got a thought that instead of declaring the variables in listViewModel class, we can declare them with rememberSaveable in the composable function itself and use it.

- As the variables are declared in the composable function itself, we need not initialize them again when were are add new item (i.e., id=0L)

- As the variables are now being declared in the AddEdit function itself I have also defined the onValueChange functions in the same function and removed them in the listViewModel as it does not have variables anymore.	

- By making these changes, whenever we are trying to add new item, even if the orientation is changing the data is not being lost. 

- But I haven't been able to replicate the same when we are editing/updating an already existing item 

- I am still referring articles to tackle this sir

- Sir I have also found below article which proposes an alternative solution to this `https://developer.android.com/develop/ui/compose/state-saving`

- I am referring it and will make changes and test it sir, but just wanted to share this approach I found
