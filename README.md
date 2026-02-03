# Shopping List Application

- This is a Simple Shopping List App where users can add the items they want to buy.
- The app have the feature to mention the quantity and the ability to an item as purchased
- Users can also delete purchased items from the list
- The app uses Room DB so the list persisits across multiple sessions
- The app follows MVVM Architecture
```
  app/src/main/java/com/example/mywishlistapp/

├── ListEntity.kt                 # Entity (Data Model)
├── ListhDao.kt             # Data Access Object (Database operations)
├── Database.kt             # Room Database
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
## Architecture

```text
┌──────────────────────────────┐
│         Views/UI             │  ← Jetpack Compose Screens
│  (HomeView, AddEditView)     │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│          ViewModel           │  ← Manages UI state & logic
│      (listViewModel)         │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│          Repository          │  ← Abstracts data access
│       (DataRepository)       │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│      Database (Room)         │  ← Local SQLite storage
│     (ListDAO → ListEntity)   │
└──────────────────────────────┘
```

## Assumptions and Trade-offs

- The app displays the time elapsed since an item was added as the difference between the current time and the creation time. Within the given time constraints, I could not implement real-time updates; currently, the time only updates when the app is refreshed.

## Improvements

- If I had more time to spend on this app, I would first change the elapsed time display to update in real-time.
- I would refine the UI to make the option to mark an item as purchased more intuitive.
- I would also implement additional "nice-to-have" features.
