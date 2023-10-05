```plantuml
|System|
start
while (action-confirmed-and-item-present) is (yes)
:Displays form for price change;
|User|
:Fills out form for price change;
|System|
:Removes item from database;
endwhile(no)
Stop
@enduml
```