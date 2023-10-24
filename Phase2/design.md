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

Search For Items Sequence Diagram: 

```plantuml
hide footbox
actor Buyer as buyer
participant " : UI" as ui
participant " : Database" as database
participant "ItemCatalog[i] : ItemList" as itemlist
participant " : RetList" as retlist


ui -> buyer : displays item search form 
buyer -> ui : fills out search form
buyer -> ui : clicks search
ui -> database : str = toString(searchForm)
[o-> database : createList()
loop i in 0..ItemCatalog.size-1
    database -> retlist : similarItems = ItemCatalog[i].contains(str)
end
retlist -> ui : searchResult(retlist)
ui -> buyer : Displays search result


```
Post Items Sequence Diagram
```plantuml
hide footbox
actor Seller as seller
participant " : UI" as ui
participant " : Moderator" as moderator
participant " : ItemCatalog" as itemcatalog

ui -> seller : displays item post form
seller -> ui : inputs item information
seller -> ui : clicks confirm
ui -> moderator : createItem(name, category, description, price, pictures)
alt ItemValid
 moderator -> itemcatalog : addItem(name, category, description, price, pictures)
 ui -> seller : print "item has been successfully posted!"
else !ItemValid
 moderator -> ui : error()
 ui -> seller : print "Item could not be verified, please check your information and try again"
end
```
