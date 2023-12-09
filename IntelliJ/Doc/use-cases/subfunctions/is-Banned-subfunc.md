```plantuml
|User|
start
: Tries to add an item;
|System|
if (item-valid?) then (Yes)
: Display "Item Posted";
else (item-explicit?) then (Yes)
: Display "Invalid Item: Please keep item clean";
endif
stop
@enduml
```