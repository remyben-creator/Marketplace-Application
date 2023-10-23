```plantuml
class Post{
 + title: String
 + description: String
 + price: double
 + pictures: JPEG files
 + seller: Seller
}
class User{
 + name: String
 - email: String(ends with vasser.edu)
}

class Seller{
 + post(title: String, description: String, price: double, pictures: JPEG files, seller: Seller(self)): Post
}

class Buyer{
 + search(searchString: String): Post
}

User <|-- Seller
User <|-- Buyer

class productDescription{
 title
 description
 price
 pictures
 seller
}

productDescription - Post : \tDescribes\t\t
Post - Seller : \tPosted-by\t\t
Post - Buyer : \tSearched-for\t\t
Post - User : \tInteracted-with\t\t
```

```plantuml
actor User as user
actor Seller as seller
actor Buyer as buyer
participant " : Post" as post

[o-> user : open application
user -> seller : chooses to post
user -> buyer : chooses to search

seller -> post : post(title, descrip, price, pics, seller)
post -> post : validateItem()

buyer -> post : search(searchString) 
post -> buyer : displayPosts()
```

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

```plantuml

|User|
start
:Searches for an item on the Marketplace;
|System|
:Compares user's string input and matches with list of items;
:Displays the similar items to the user;
stop
@enduml
```