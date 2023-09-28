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
:Select sign up option;
|System|
:Ask for email;
|User|
:Inputs email;
|System|
:Validate email;
if (email invalid?) then (email invalid)
:display "email could not be authenticated, please try again";
stop
else 
|System|
:Display "Email has been successfully verified!";
:Prompt for password creation; 
|User|
:Input password;
|System|
:Display "Account has been successfully created. Happy browsing!";
Stop
@enduml
```