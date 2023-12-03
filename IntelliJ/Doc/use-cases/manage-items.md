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
- Items the seller takes down are no longer visible to users
- Following confirmation, all changes are visible to all users
```plantuml
|User|
start
:Visits manage items screen;
|System|
:Displays posted items with possible managing options;
|User|
:Scrolls to the item and clicks what they want to manage;
|System| 
switch (Desired Action?)
    case (edit-item)
        :execute __add-item__;
    case (view-interest)
        :Execute __view-interest-subfunc__;
    case (delete-item)
        :execute __delete_item_subfunc__;
endswitch
Stop
@enduml
```