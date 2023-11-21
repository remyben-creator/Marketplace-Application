### Depth
- In depth
###  Primary actor and goals:
- Vassar students looking to use the marketplace. They would like to create an account to search for / post items.
### Other stakeholders and interests:
- **Existing Users:** Would like more people utilizing the marketplace to increase both supply and demand
### Preconditions:
- All students currently attend Vassar College
### Postconditions:
- System confirms email address is valid
- User is granted access once they create their account

```plantuml
|User|
start
:Signs up to use the Marketplace;
|System|
while (account-info-complete-and-valid) is (no)
if (account-info-incomplete) then (yes)
:Display account info form;
|User|
:Inputs email, name;
|System|
else(no)
:display "email or name could not be validated, please try again";
endif 
endwhile(yes)
|System|
:Display "Email and name have been successfully verified!";
:Prompt for password creation; 
|User|
:Input password;
|System|
:Display "Account has been successfully created. Happy browsing!";
Stop
@enduml
```