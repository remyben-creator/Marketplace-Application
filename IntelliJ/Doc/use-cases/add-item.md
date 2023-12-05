### Depth
- In depth
###  Primary actor and goals:
- Users on the marketplace looking to sell items.  They want an easy posting process, so it's worth it to try and sell 
their good
### Other stakeholders and interests:
- **Buyers:** Want to increase the supply of goods listed on the server, so they have more options to choose from.
### Preconditions:
- Users have created an account
### Postconditions:
- Once item is listed, all users are able to see / search for it.
- Said item can be managed at any time


```plantuml

|User|
start
:Has an item they would like to sell;
while (item-complete-and-validated) is (no)
|System|
if (item-valid?) then (yes) 
: Execute __ is-Banned __;
else (no)
:Displays new item form;
|User|
:Inputs item name, description, price, pictures;
endif
endwhile (yes) 
|System|
:Lists Item on database;
stop
@enduml
```