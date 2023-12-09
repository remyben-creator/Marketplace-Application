### Depth
- In depth
###  Primary actor and goals:
- System trying to ensure only Vassar users are able to make accounts and browse the site
### Other stakeholders and interests:
- **Existing Users:** Want to make sure that the integrity of the site and it's users is maintained
### Preconditions:
- All students currently attend Vassar College
### Postconditions:
- Once email is validated, the account is functional and corresponding user is granted access to the platform
```plantuml
|User|
start
: Fills out account information page;
|System|
if (fields-incomplete?) then (Yes)
 :Display "Invalid User: Please make sure all fields are filled";
else (no)
 if (user-valid?) then (Yes)
  :Display "User Created";
 elseif (invalid-email?) then (Yes)
  :Display "Invalid User: Please use vassar.edu email";
 else (Existing-User)
  :Display "Already account with this email";
 endif
endif
stop
@enduml
```