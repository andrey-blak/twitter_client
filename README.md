# Project structure
The project is structured according to the Clean Architecture approach. It is divided into 3 packages: data, domain and ui which correspond to the data, domain and presentation layers. 

# Error handling
Currently, errors are handled only on the login screen, in the most trivial way. In a real application, tha network and api errors should be also mapped to presentation level errors (defined as enum instances or sealed class subclasses).

