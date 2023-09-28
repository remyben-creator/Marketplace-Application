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
:Selects post item option;
|System|
:Ask for item name;
|User|
:Inputs item name;
|System|
:Asks for item pictures;
:Displays a dropbox for users to provide up to 3 pictures;
|User|
:Uploads pictures of items;
|System|
:Asks for item description;
|User|
:Inputs item description;
|System| 
:Asks for price;
|User|
:Inputs price;
|System|
:Validates item checking for banned words;
if (item-invalid?) then (item is invalid)
:Display "item could not be posted due to explicit content, please try again";
stop
else
:Display "Item has been successfully posted";
:List Item on database;
Stop
@enduml
```