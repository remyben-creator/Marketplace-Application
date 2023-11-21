### Depth
- In depth
###  Primary actor and goals:
- Sellers on the Marketplace.  They would like to remove items once they are sold, check the number of people interested
in their postings, and change their desired price.
### Other stakeholders and interests:
- **Buyers:** Want sellers to decrease prices and don't want to see sold goods
- **Other Sellers:** Don't want there to be a larger supply of items than there should be
### Preconditions:
- Seller has posted an item
### Postconditions:
- Items the seller takes down are removed from the database
- Users see the updated price the seller inputs
- System accurately shows the number of interested buyers
```plantuml
|User|
start
:Visits manage items screen;
|System|
:Displays posted items;
|User|
Selects the item they would like to edit;
|System| 
Displays edit item form;
switch (Desired action?)
    case (Confirm Changes)
        :Execute __edit-item__;
    case (Go Back)
        :Display posted items;
endswitch
Stop
@enduml
```