### Depth
- In depth
###  Primary actor and goals:
- Users on the marketplace looking to sell items.  Buyers would like to let sellers know that they are interested in an item.
### Other stakeholders and interests:
- **Sellers:** Want buyers to express interest in their items so they can reach out to various buyers.
### Preconditions:
- An item has been posted
### Postconditions:
- Sellers are able to view the different users who have expressed interest

```plantuml

|User|
start
:Finds a good they like;
|System|
:Displays item interest form;
|User|
:fills out item interest form;
|System|
if (form-complete?) then (yes)
:Adds item to itemInterestCatalog;
:Adds interest to Item on database;
stop
@enduml
```