```plantuml
|System|
start
if (delete-interest-confirmed) then (Yes)
    :Removes interest from item's interest list;    
elseif (delete-interest-back) then (Yes)
    :Returns to interest form;
else (view-interest-back)
    :Executes __manage-items__;
endif
Stop
@enduml
```