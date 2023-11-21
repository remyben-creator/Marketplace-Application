### Depth
- In-Depth
###  Primary actor and goals:
- Users on the marketplace looking to buy items 
### Other stakeholders and interests:
- **Sellers:** Would prefer an easy searching experience so people aren't turned away.  They also want their 
goods to show up when searched for
### Preconditions:
- Users have created an account
### Postconditions:
- All items fitting the description show up when searched for
- Buyer is able to click on items and look into further detail


```plantuml

|User|
start
:Searches for an item on the Marketplace;
|System|
:Compares user's string input and matches with list of items;
if (searchResult.length != 0?) then (Yes)
:Displays the similar items to the user;
else (no)
:Print "No existing items match that description, please try again";
endif 
stop
@enduml
```