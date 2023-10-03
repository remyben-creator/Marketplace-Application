# Target Audience
The target audience for the Vassar Marketplace app is the Vassar student body


# Value Proposition
Vassar Marketplace saves Vassar students time and money by allowing users to buy and sell extra textbooks and
dorm room items instead of buying them retail or throwing them away.  


# Main Features and Constraints:
### Main Features:
- Ability to post extra items you brought to school or past textbooks
- Freedom to choose preferred method of communication
- Ability to search for wanted dorm room items or textbooks for upcoming 
classes
- Once you find an item you want, you will be given their contact 
information 

### Constraints:
- Not able to message the seller directly through the Marketplace : 
must communicate with the seller through provided contact information 
- Sellers must remove the listing from the website once it's sold
- Implementation constraints:
  - from CMPU-102 we have learned java, and therefore within CMPU-203
there is a requirement for the product to be written in java
  - there is a requirement for the product to be an Android Application, based
on what we are learning as a class

# Actors and Goals
Customer:
- open the app
- browse items available
- search items
- pick item
- purchase item
- get in contact with seller

Seller:
- post an item
- edit their items
- receive messages from buyer
- take down items/mark them as sold

# Use Case

###  Primary actor and goals:
- Vassar students who want to buy goods on the marketplace.  To do so, they would like an easy search process and the
  ability to contact sellers

### Other stakeholders and interests:
- **Seller:** Wants to easily post goods they're interested in selling and to be contacted once someone shows interest.  Also wants
  to easily remove an item posted once it's sold

### Preconditions:
- All customers and sellers have created an account with their Vassar email address with synced vassar one login

### Postconditions:
- Buyer is provided seller's email to contact them
- System takes item down once seller says item is sold

```plantuml
@startuml
actor "Buyer" as buyer 
actor "Seller" as seller

actor "System" <<system>> as system

package "make-account" {
usecase "Select sign-up option" as makeAccount1
usecase "Inputs email" as makeAccount2
}

buyer --> makeAccount1
seller --> makeAccount1
makeAccount1 --> makeAccount2
makeAccount2 --> system

package "post-item" {
usecase "Select post item option" as postItem1
usecase "Fills out item post" as postItem2
}

seller --> postItem1
postItem1 --> postItem2
postItem2 --> system

package "search-for-items" {
usecase "Clicks on searchbar" as searchItem1
usecase "Inputs item name" as searchItem2
}

buyer --> searchItem1
searchItem1 --> searchItem2
searchItem2 --> system

package "complete-sale" {
usecase "Contact other party" as completeSale1
usecase "Finalize sale" as completeSale2
}

buyer --> completeSale1
seller --> completeSale1
completeSale1 --> completeSale2
completeSale2 --> system

package "edit-item" {
usecase "edit item/delete item" as editItem
}

seller --> editItem
editItem --> system

package "validate-item" {
usecase "validate item" as validateItem
}

system --> validateItem

package "compute-user-process" {
usecase "compute and follow through with user input" as computeProcess1
usecase "edit/delete item" as computeProcess2
usecase "complete sale" as computeProcess3
usecase "show search results" as computeProcess4
usecase "finalize user account" as computeProcess5
}

system --> computeProcess1
system --> computeProcess2
system --> computeProcess3
system --> computeProcess4
system --> computeProcess5
@enduml
```
```plantuml
@startuml

|Seller|
start
:Make account;
:Put item up for sale;
|System|
:Validate item;
|Seller|
if (Still selling) then (Item no longer for sale)
:Take down item or mark as sold;
|System|
:Unlist item;
stop
else
|System|
:List item and display in app;
|Customer|
:Make account;
:View items;
:Choose item;
|System|
:Put buyer in contact with seller;
|Customer|
:Communicate with seller;
|Seller|
:Finalize sale;
:Take down item or mark as sold;
|System| 
:Unlist item;
stop

@enduml
```