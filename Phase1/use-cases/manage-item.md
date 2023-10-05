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
:Visits manage item screen;
|System|
:Asks for desired action: Change price, Remove item, View interest;
switch (Desired action?)
    case (Change Price)
        :Execute __change-price__;
    case (Remove Item)
        :Execute __remove-item__;
    case (View Interest)
        :Show number of interested buyers;
endswitch
Stop
@enduml
```