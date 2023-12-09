```plantuml
interface Catalog{
 + getLength(): int
 + getItem() : Item
 + getList() : linkedList<>
}

class Item{
 + seller : User
 + title : String
 + description : String
 + pictures : Bitmap
 + price : double
 + sellerId : String
 + interests : ItemInterestCatalog
 + id : String
 
 + getSellerFromID(UserCatalog users) : void
 + getTitle() : String
 + setTitle(title : String) : void
 + getDescription() : String
 + setDescription(description : String) : void
 + getPrice() : double
 + getPriceString() : String
 + setPrice(price : double) : void
 + getSeller() : User
 + setSeller(seller : User) : void 
 + getBlobFromBitmap(Blob : imageBlob) : Blob
 + getBitmapFromBlob(Blob : imageBlob) : void
 + toMap() : Map<Object, String> 
 + {static} fromMap( Map<Object, String> map) : Item
 }
 
class ItemCatalog{
 + length : int
 + items : List<Item>
 + forInterest : boolean
 
 + getLength() : int
 + addItem(item : Item) : void
 + removeItem(toRemove : Item) : void
 + getList() : List<Item>
 + getItem() : Item
 + searchResult(searchString : String) : ItemCatalog
 + getItemFromID(id : String) : Item
}

class ItemInterestCatalog{
 + length : int
 + interests : List<ItemInterestForm> 
 + item : String
 + id : String
 
 + addInterest(interest : ItemInterestForm) : void
 + removeInterest(interest : ItemInterestForm) : void
 + getLength() : int
 + getList() : List<ItemInterestForm>
 + getItem(index : int) : Item
 + getInterest(index : int) : ItemInterestForm
 + getItemFromID(id : string) : ItemInterestForm
 + toMap() : Map<Object, String>
 + {static} fromMap(Map<Object, String> map) : ItemInterestCatalog
}

class ItemInterestForm {
 + userId : String
 + user : User
 + item : String
 + interest : String
 + id : String
 
 + getUserFromID(users : UserCatalog) : void
 + getUser() : User
 + getInterest() : String
 + toMap() : Map<Object, String>
 + {static} fromMap(Map<Object, String> map) : ItemInterestForm
}

class Moderator {
 + {static} isValidEmail(email : String) : boolean
 + {static} isBannedItem(title, String, description, String) : boolean
}

class User{ 
 + email : String
 + password : String
 + myItemsIds : String
 + myItems : ItemCatalog
 + myInterests : ItemInterestCatalog
 + id : String
 
 + genMyItems(items : itemCatalog) : void
 + createItem(title : String, price : Double, description : String, pictures : Bitmap, seller : User) Item)
 + userEquals(user : User) : boolean
 + editItem(item : Item, title : String, price : Double, description : String, pictures : Bitmap) : void
 + addInterest(item : Item, interest : String) : void
 + deleteInterest(items : ItemCatalog, interest : ItemInterestForm) : void
 + deleteItem(items : ItemCatalog , item : Item) : void
 + deleteUserStuff(items , ItemCatalog) : void
 + toMap() : Map<String, Object>
 + fromMap(Map<String, Object> map) : User
}

class UserCatalog {
 + length : int
 + users : List<User>
 
 + getLength() : int
 + getList() : List<User>
 + getItem(index : int) : User
 + addUser(user : User) : void
 + removeUser(toRemove : User, items : ItemCatalog) : void
 + loginUser(userEmail : String, userPassword : String, items : ItemCatalog) : User
 + checkForUser(userEmail : String, userPassword : String) : User
 + getItemFromID(id : String) User 
}

Class MainActivity {
 + items : ItemCatalog
 + user : User 
 + mainView : IMainView 
 + curFrag : Fragment 
 + persFacade : IPersistenceFacade
 
 # onCreate(savedInstanceState : Bundle) : void
 + onItemCatalogReceived(itemCatalog : ItemCatalog) : void
 + onUserCatalogReceived(userCatalog : UserCatalog) : void
 - syncItemsAndUsers() : void
 + uponAddItem() : void
 + uponSearch(searchString : String) : void
 + uponMyItems() : void
 + uponAccount() : void
 + uponHome() : void
 + loggedIn() : boolean 
 + checkForMyItems(items : itemCatalog) : boolean
 + uponEdit(item : Item) : void
 + uponInterest(item : Item) : void
 + uponDeleteItem(item : Item) : void
 + uponDeleteInterest(interests: ItemInterestCatalog, index : int) : void
 + uponConfirm(item : Item, interest : String) : void
 + uponBackToHome(edit : boolean) : void
 + uponPost(item : Item, itemTitle : String, itemPrice : Double, itemDesc : String, itemPics : Bitmap, edit : boolean) : void
 + uponAddPics(aiv : IAddItemView) : void
 + loadFromUri(photoUri : Uri) : Bitmap
 + uponLoginGoHome() : void
 + checkValidLogin(userEmail : String, userPassword : String) : boolean 
 + uponCreateAccountGoHome() : void
 + checkCreateAccount(userEmail : String, userPassword : String) : boolean
 + getUserEmail() : String
 + uponBackToMyItems() : void 
 + uponConfirmDeleteItem(item : Item) : void
 + uponConfirmDeleteInterest(interests : ItemInterestCatalog , index : int) : void
 + uponConfirmDeleteUser() : void
 + uponBackToInterests(interests : ItemInterestCatalog) : void
 + uponLogout() : void
 + uponDelete() : void
 + useModerator(itemTitleStr : String, itemDescStr : String) : boolean
}

interface IAccountView {
 + uponLoginGoHome() : void
 + uponBackToHome(edit : boolean) : void
 + uponCreateAccountGoHome() : void
 + getUserEmail() : String
 + checkValidLogin(userEmail: String, userPassword : String) : boolean
 + checkCreateAccount(userEmail : String, userPassword : String) : boolean 
 + uponLogout() : void 
 + uponDelete() : void
}

class LoggedInAccountFragment{ 
 + binding : FragmentLoggedInAccountBinding
 + listener Listener
 
 + onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstanceState : Bundle) : View
 + onViewCreated(view : View, savedInstanceState : Bundle) : void
}

class AccountFragment{ 
 + binding : FragmentAccountBinding 
 + listener : Listener
 
 + onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstanceState : Bundle) : View
 + onViewCreated(view : View, savedInstanceState : Bundle) : void
}

interface IMainView{ 
 + getRootView() : View
 + displayFragment(fragment : Fragment, reversible : boolean, name : String) : void
}

class MainView{
 + binding : MainBinding
 + fManager : FragmentManager
 
 + getRootView() : View 
 + displayFragment(fragment : Fragment, addToStack : boolean , name : String) : void
}

interface IHomeFeedView{ 
 + uponAddItem() : void
 + uponSearch(searchString : String) : void
 + uponMyItems() : void 
 + uponAccount() : void 
 + uponHome() : void
 + checkForMyItems(items : ItemCatalog) : boolean
 + loggedIn() : boolean
 + uponEdit(item : Item) : void 
 + uponViewInterest(item : Item) : void 
 + uponInterest(item : Item) : void 
 + uponDeleteItem(item : Item) : void 
 + uponDeleteInterest(interest : ItemInterestCatalog, index : int) : void
 + uponConfirm(item : Item, interest : String) : void
}

class HomeFeedFragment{ 
 + listener : Listener
 + binding : FragmentHomeFeedBinding 
 + currentList : Catalog
 
 + onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstanceState : Bundle) : View
 + onViewCreated(view : View, savedInstanceState : Bundle) : void
}

interface IConfirmDeleteView{ 
 + uponBackToMyItems() : void 
 + uponConfirmDeleteItem(item : Item) : void 
 + uponConfirmDeleteInterest(interests : ItemInterestCatalog, index : int) : void
 + uponBackToInterests(interests : ItemInterestCatalog) : void
 + uponConfirmDeleteUser() : void
 + uponAccount() : void
}

class ConfirmDeleteFragment{
 + listener : Listener
 + item : Item
 + deleteIndex : int
 + interests : InterestCatalog 
 + user : User
 + binding : FragmentConfirmDeleteBinding
 
 + onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstanceState : Bundle) : View
 + onViewCreated(view : View, savedInstanceState : Bundle) : void
}

interface IAddItemView{ 
 + uponPost(item : Item, itemTitle : String , itemPrice : Double, itemDesc : String, itemPics : Bitmap, edit : Boolean) : void
 + uponBackToHome(edit : boolean) : void 
 + uponAddPics(aiv : IAddItemView) : void 
 + useModerator(itemTitleStr : String, itemDescStr : String) : boolean
 + updateImage(data : Bitmap) : void
}

class ItemsHolder{
 + titleView : TextView
 + descriptionView : TextView
 + priceView : TextView
 + userEmailView : TextView
 + userInterestView : TextView
 + editButton : Button 
 + deleteButton : Button 
 + viewInterestButton : Button
 + interestButton : Button
 + backButton : Button 
 + confirmButton : Button 
 + interestBar : EditText 
 + imageView : ImageView
} 

class ItemsAdapter { 
 + context : Context 
 + items : Catalog
 + my_list : boolean
 + toInterest : boolean
 + listener : IHomeFeedView.Listener
 
 + onCreateViewHolder(parent : ViewGroup, viewType : int) : ItemsHolder
 + OnBindViewHolder(holder : ItemsHolder, position : int) : void
 + getItemCount() : int
}

class AddItemFragment{
 + listener : Listener
 + moderator : Moderator
 + edit : boolean 
 + editItem : Item 
 + currentImage : Bitmap
 
 + onCreateView(inflater : LayoutInflater, container : ViewGroup, savedInstanceState : Bundle) : View
 + onViewCreated(view : View, savedInstanceState : Bundle) : void
 + updateImage(data : Bitmap) : void
}

IAddItemView <|.. AddItemFragment
IConfirmDeleteView <|.. ConfirmDeleteFragment
IHomeFeedView <|.. HomeFeedFragment
IMainView <|.. MainView
IAccountView <|.. LoggedInAccountFragment
IAccountView <|.. AccountFragment
Catalog <|.. ItemCatalog
Catalog <|.. UserCatalog
Catalog <|.. ItemInterestCatalog
ItemCatalog -> "(1...*)\nItems\{List}" Item
ItemCatalog .> Item
UserCatalog -> "(1...*)\nUsers\{List}" User
UserCatalog .> User
ItemInterestCatalog -> "(1...*)\nItemInterestForms\{List}" ItemInterestForm
ItemInterestCatalog .> ItemInterestForm
ItemInterestCatalog -> "(1...*)\nItems\{List}" Item
ItemInterestCatalog .> Item
ItemInterestForm -> "(1...*)\nUsers\{List}" User
ItemInterestForm .> User
ItemInterestForm -> "(1...*)\nItems\{List}" Item
ItemInterestForm .> Item
MainActivity .> AddItemFragment 
MainActivity .> ConfirmDeleteFragment
MainActivity .> HomeFeedFragment
MainActivity .> MainView
MainActivity .> LoggedInAccountFragment
MainActivity .> AccountFragment
ItemInterestCatalog .> MainActivity
ItemCatalog .> MainActivity
UserCatalog .> MainActivity
Item .> MainActivity
User .> MainActivity
ItemInterestForm .> MainActivity
Moderator .> MainActivity

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