###  Primary actor and goals:
- **Interested Buyers:** Want to be given the seller's contact information to begin the negotiation process  
### Other stakeholders and interests:
- **Sellers:** Want to be notified when someone is interested in their good, so they can hopefully sell it.  
### Preconditions:
- Good hasn't been sold yet
### Postconditions:
- Buyer reaches out to seller

```plantuml

|User|
start
:Selects item;
:Selects contact seller option;
|System| 
:Display seller's provided email;
:Adds buyer's email to list of interested buyers;
:increments the "interested customers" count;
Stop
@enduml
```