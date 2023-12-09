```plantuml
class Post{
 + User : user
 + Item : item
}

class Item{
 + serialNumber: int
 + title: string
 + description: string
 + pictures: pictures
 + price: int
 }
 
class User{
 + name: name
 - email: email
 - listedItems: arrayList<>
 - viewedItems: arrayList<>
 + editItem()
}

class ItemCatalog {
 + length : int
 + similarItems()
 + searchResult()
}

class Moderator {
+ isValid()
+ error()
+ addItem()
}

class Controller {
+ createKeyword()
}

class UI { 
+ createItem()
+ search()
}

Post .> User
Post .> Item
ItemCatalog - Item
Moderator .> UI
Controller .> UI

```


Post Item Sequence Diagram
```plantuml
hide footbox
actor User as user
participant " : AddItemFragment" as ui
participant " : HomeFeedFragment" as ui2
participant " : MainActivity" as controller
participant " : Moderator" as moderator
participant " seller : User" as seller
participant " : ItemCatalog" as itemcatalog

ui -> user : displays item post form
user -> ui : inputs item information
alt fields incomplete?
ui -> user : Display "Invalid Item: Please make sure all fields are filled"
else !fields incomplete
ui -> controller : itemTitleStr = itemTitleEditable.toString()
ui -> controller : itemDescStr = itemDescEditable.toString()
controller -> moderator : isBannedItem(itemTitleStr, itemDescStr)
alt isBanned   
 ui2 -> user : Display "Invalid Item: Please keep item clean"
else !isBanned
controller -> seller : createItem(itemTitle, itemPrice, itemDesc, itemPics, this.user)
 controller -> itemcatalog : addItem(newItem))
 ui2 -> user : Display "Item posted"
end
end
```


Add Interest Sequence Diagram 

```plantuml
hide footbox
actor User as user
participant " : ItemsAdapter" as ui
participant " : HomeFeedFragment" as ui2
participant " : MainActivity" as controller
participant " : ItemInterestCatalog" as iteminterestcatalog
participant " buyer : User" as buyer

user -> ui : clicks add Interest
ui -> user : displays interest form
user -> ui : fills out interest form 
alt fields incomplete
 ui -> user : "Interest invalid: Please make sure all fields are filled"
else !fields incomplete
 ui -> controller : interestStr = interestEditable.toString();  
 controller -> iteminterestcatalog : addItem(item)
 controller -> buyer : addInterest(item, interest)
 ui2 -> user : Display "Interest Created"
end


```

Make Account Sequence Diagram 
```plantuml
hide footbox
actor User as user
participant " : AccountFragment" as ui
participant " : MainActivity" as controller
participant " : Moderator" as moderator
participant " : UserCatalog" as usercatalog
participant " newUser : User" as newuser

ui -> user : displays create account form 
user -> ui : enters email and password
user -> ui : clicks create account
alt fields incomplete
 ui -> user : "Invalid User: Please make sure all fields are filled"
else fields !incomplete
 ui -> controller : userEmail = userEmailEditable.toString()
 ui -> controller : userPassword= userPasswordEditable.toString()
 controller -> moderator : checkCreateAccount(String userEmail, String userPassword)
 alt isValidEmail
  controller -> usercatalog : checkForUser(userEmail, userPassword)
  alt userExists 
   ui2 -> user : Display "Already account with this email"
  else !userExists
   controller -> newuser : newUser = new User(userEmail, userPassword)
   controller -> usercatalog : addUser(User)
   ui -> user : Display "User Created"
   end
 else !isValidEmail
 ui -> user : Display "Invalid User: check username and password"
end
end


```
Search For Items Sequence Diagram:

```plantuml
hide footbox
actor User as user
participant " : HomeFeedFragment" as ui
participant " : MainActivity" as controller
participant " : ItemCatalog" as itemCatalog

user -> ui : clicks search bar
ui -> user : displays item search form 
user -> ui : fills out search form
user -> ui : confirms search
ui -> controller :  searchString = SSEditable.toString()
controller -> itemCatalog : uponSearch(searchString)
controller -> ui : searchItems = searchResult(searchString)
ui -> user : displays searchItems

```

Manage Items Use Case Diagram 
```plantuml
hide footbox
actor User as user
participant " : HomeFeedFragment" as ui
participant " : MainActivity" as controller
participant " : ItemsAdapter" as itemsadapter
participant " : ConfirmDeleteFragment" as confirmdeletefragment
participant " : AddItemFragment" as additemfragment
participant " : seller : user" as seller
participant " : ItemCatalog" as itemcatalog
participant " : ItemInterestForm" as itemitnerestform

user -> ui : Clicks manage items
user -> ui : Clicks desired action
alt editItem
 controller -> additemfragment : "add item screen in edit"
 additemfragment -> user : displays add item form with fields filled in
 user -> additemfragment : makes desired changes
 additemfragment -> controller : "edit"
 controller -> seller : editItem(item, itemTitle, itemPrice, itemDesc, itemPics)
 controller -> homefeedfragment : "my items feed"
 homefeedfragment -> user : returns to my items page 
else viewInterest 
 controller -> itemsadapter : "interests"
 itemsadapter -> user : displays interests
 alt deleteInterest
  controller -> confirmdeletefragment : "confirm delete page"
  controller -> iteminterestsform : removeInterest(interest)
  ui -> user : "returning to home feed"
 else backToInterests
  controller -> ui : "interests"
  ui -> user : displays interests
 end
else deleteItem
 controller -> confirmdeletefragment : "confirm delete page"
 controller -> seller : deleteItem(this.items, item)
 controller -> itemcatalog : removeItem(item)
 controller -> ui : "my item feed"
 ui -> user : returns to my items feed
end 

```

Log in sequence diagram 
```plantuml
hide footbox
actor User as user
participant " : AccountFragment" as ui
participant " : MainActivity" as controller
participant " : Moderator" as moderator

user -> ui : clicks account
user -> ui : clicks log in
ui -> user : displays login form 
user -> ui : fills out login form 
ui -> controller : userEmail = userEmailEditable.toString()
ui -> controller : userPassword= userPasswordEditable.toString()
controller -> moderator : checkValidLogin(userEmail, userPassword)
alt validLogin 
controller -> ui : uponLogin()
ui -> user : Display "User logged in"
else !validLogin
ui -> user : Display "Invalid login, check username and password"
end
```

Log out sequence diagram 
```plantuml
hide footbox
actor User as user
participant " : LoggedInAccountFragment" as ui
participant " : MainActivity" as controller

user -> ui : clicks log out
ui -> controller : uponLogout()
ui -> user : Display "User logged out"
```