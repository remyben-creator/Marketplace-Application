### Depth
- In depth
###  Primary actor and goals:
- System trying to ensure only Vassar users are able to log in to their accounts
### Other stakeholders and interests:
- **Existing Users:** Want to make sure that the integrity of the site and it's users is maintained
### Preconditions:
- Users have created an account
### Postconditions:
- Once account is verified the corresponding user is granted access to the platform
```plantuml

|User|
start
: Inputs email and password;
|System|
if (user-valid?) then (Yes)
: Display "User Logged In";
elseif (fields-incomplete?) then (Yes)
: Display "Invalid User: Please make sure all fields are filled";
else (invalid-password-or-username?)
: Display "Invalid User: Check username and password";
endif
stop

@enduml
```