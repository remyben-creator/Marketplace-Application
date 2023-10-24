```plantuml
class Item{
 + serialNumber: int
 + title: string
 + description: string
 + pictures: pictures
 + price: int
 + seller : seller
}
class User{
 + name: name
 - email: email
}

class Seller{
 + listedItems : arrayList<>()
 - editItem()
}

class Buyer{
 - searchHistory: searchForm
 - viewedItems : arrayList<>()
}

class SearchResult {
 + length : int
}

class ItemCatalog {
 + length : int
}

class Controller {

}

class UI { 

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
participant " : Controller" as controller
participant "ItemCatalog[i] : ItemCatalog" as itemcatalog

ui -> buyer : displays item search form 
buyer -> ui : fills out search form
buyer -> ui : clicks search
ui -> controller : str = toString(searchForm)
loop i in 0..ItemCatalog.size-1
    controller -> itemcatalog : searchResult = similarItems(ItemCatalog[i], str)
end
controller -> ui : searchResult
ui -> buyer : Displays searchResult


```
Post Items Sequence Diagram
```plantuml
hide footbox
actor Seller as seller
participant " : UI" as ui
participant " : Controller" as controller
participant " : ItemCatalog" as itemcatalog

ui -> seller : displays item post form
seller -> ui : inputs item information
seller -> ui : clicks confirm
ui -> controller : createItem(name, category, description, price, pictures)
alt isValid
 controller -> itemcatalog : addItem(name, category, description, price, pictures)
 ui -> seller : print "item has been successfully posted!"
else !isValid
 controller -> ui : error()
 ui -> seller : print "Item could not be verified, please check your information and try again"
end
```
