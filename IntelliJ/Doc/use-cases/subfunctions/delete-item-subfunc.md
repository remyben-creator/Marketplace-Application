```plantuml
|System|
start
while (action-confirmed-and-item-present) is (yes)
:deleteItem(this.items, item);
:removeItem(item);
endwhile(no)
Stop
@enduml
```