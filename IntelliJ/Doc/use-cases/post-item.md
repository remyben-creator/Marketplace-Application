### Depth
- In depth
###  Primary actor and goals:
- Users on the marketplace looking to sell items.  They want an easy posting process, so it's worth it to try and sell 
their good
### Other stakeholders and interests:
- **Buyers:** Want to increase the supply of goods listed on the server, so they have more options to choose from.
### Preconditions:
- Users have created an account
- Good posted isn't included on the banned items list
### Postconditions:
- Once item is listed, all users are able to see / search for it.


```plantuml

|User|
start
:Has an item they would like to sell;
while (item-complete-and-valid) is (no)
|System|
if (item-invalid?) then (yes)
:Display "Item could not be posted due to explicit content, please try again";
else (no)
:Displays new item form;
|User|
:Inputs item name, selects category, inputs description, price, pictures;
endif
endwhile (yes) 
|System|
:Display "Item has been successfully posted";
:Lists Item on database;
stop
@enduml
```