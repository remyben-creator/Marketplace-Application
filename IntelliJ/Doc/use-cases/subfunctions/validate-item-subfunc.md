```plantuml
|User|
start
: Tries to add an item;
|System|
if (item-valid?) then (Yes)
: Display "Item Posted";
elseif (fields-incomplete?) then (Yes)
: Display : Display "Invalid Item: Please make sure all fields are filled";
else (item-explicit?) 
: Display "Invalid Item: Please keep item clean";
endif
stop
@enduml
```