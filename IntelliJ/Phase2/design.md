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

Search For Items Sequence Diagram: 

```plantuml
hide footbox
actor Buyer as buyer
participant " : UI" as ui
participant " : Controller" as controller
participant "ItemCatalog[i] : ItemCatalog" as itemcatalog
participant " : SearchResult" as searchresult

ui -> buyer : displays item search form 
buyer -> ui : fills out search form
buyer -> ui : clicks search
ui -> controller : search(searchForm)
controller -> itemcatalog : createKeyword(searchForm)
loop i in 0..ItemCatalog.size-1
    itemcatalog -> searchresult : searchResult = similarItems(ItemCatalog[i], keyword)
end
controller -> ui : searchResult
ui -> buyer : Displays searchResult


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
alt isValid
 moderator -> itemcatalog : addItem(name, category, description, price, pictures)
 ui -> seller : print "item has been successfully posted!"
else !isValid
 moderator -> ui : error()
 ui -> seller : print "Item could not be verified, please check your information and try again"
end
```
