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
participant " : MainActivity" as controller
participant " : Moderator" as moderator
participant " : ItemCatalog" as itemcatalog

ui -> user : displays item post form
user -> ui : inputs item information
ui -> controller : this.user.createItem(itemTitle, itemPrice, itemDesc, itemPics, this.user)
controller -> moderator : isBannedItem(String title, String description)
alt isBanned   
 ui -> user : Display "Invalid Item"
else !isBanned
 moderator -> itemcatalog : addItem(newItem))
 ui -> user : print "Item posted"
end
```


Add Interest Sequence Diagram

```plantuml
hide footbox
actor User as user
participant " : ItemsAdapter" as ui
participant " : MainActivity" as controller
participant " : ItemInterestCatalog" as iteminterestcatalog

user -> ui : clicks add Interest
ui -> user : displays interest form
user -> ui : fills out interest form 
ui -> controller : interestStr = interestEditable.toString();  
alt interestInvalid 
 ui -> user : "Interest invalid: Please make sure all fields are filled"
else !interestInvalid
 controller -> iteminterestcatalog : uponInterest(item)
 ui -> user : Display "Interest Created"
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

ui -> user : displays create account form 
user -> ui : enters email and password
ui -> controller : userEmail = userEmailEditable.toString()
ui -> controller : userPassword= userPasswordEditable.toString()
controller -> moderator : checkCreateAccount(String userEmail, String userPassword)
alt isValidEmail
 controller -> usercatalog : checkForUser(userEmail, userPassword)
alt userExists 
 ui -> user : Display "Already account with this email"
else !userExists
 controller -> usercatalog : addUser(User)
 ui -> user : Display "User Created"
end
else !isValidEmail
 ui -> user : Display "Invalid User: check username and password"
end


```
Search For Items Sequence Diagram:

```plantuml
hide footbox
actor User as user
participant " : HomeFeedFragment" as ui
participant " : MainActivity" as controller
participant " : ItemCatalog" as itemCatalog

ui -> user : displays item search form 
user -> ui : fills out search form
ui -> controller :  searchString = SSEditable.toString()
controller -> itemCatalog : uponSearch(searchString)
controller -> ui : searchItems = searchResult(searchString)
ui -> user : displays searchItems

```

Manage Items Use Case Diagram