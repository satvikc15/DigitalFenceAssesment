# Shopping List Application

- This is a Simple Shopping List App where users can add the items they want to buy.
- The app have the feature to mention the quantity and the ability to an item as purchased
- Users can also delete purchased items from the list
- The app uses Room DB so the list persisits across multiple sessions
- The app follows MVVM Architecture
```
  app/src/main/java/com/example/mywishlistapp/
├
├── ListEntity.kt                 # Entity (Data Model)
├── ListhDao.kt              # Data Access Object (Database operations)
├── Database.kt         # Room Database
├── ListRepository.kt       # Repository (abstraction layer)
└── Graph.kt                # Dependency provider
├── HomeView.kt             # Home screen composable
├── AddEditDetailView.kt    # Add/Edit screen composable
└── AppBar.kt               # Top bar component
├── Navigation.kt               # Navigation setup
├── Screens.kt                  # Screen routes (sealed class)
├── ListViewModel.kt            # ViewModel (business logic)
└── MainActivity.kt             # Entry point
```
