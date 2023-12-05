### Depth
- In depth
###  Primary actor and goals:
- Vassar students looking to use the marketplace. They would like to create an account to search for / post items.
### Other stakeholders and interests:
- **Existing Users:** Would like more people utilizing the marketplace to increase both supply and demand
### Preconditions:
- All students currently attend Vassar College
### Postconditions:
- User is granted access once they create their account
- Created account can be deleted at a later date

```plantuml
|User|
start
:Opens the application;
|System|
while (account-info-complete-and-valid) is (no)
if (account-info-incomplete) then (yes)
:Display account info form;
|User|
:Inputs email, name, creates password;
|System|
else(no)
:display "email or name could not be validated, please try again";
endif 
endwhile(yes)
|System|
:Adds User to UserCatalog;
Stop
@enduml
```