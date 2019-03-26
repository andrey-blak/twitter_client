# Project structure
The project is structured according to the Clean Architecture approach. It is divided into 3 packages: data, domain and ui which correspond to the data, domain and presentation layers. 

# Login
Please use the following credentials to login:
username:
user
password:
pass

# Error handling
Currently, errors are handled only on the login screen, in the most trivial way. In a real application, tha network and api errors should be also mapped to presentation level errors (defined as enum instances or sealed class subclasses).

# Tests
Since the app performs asynchronous operations, they should be turned into synchronous so that the results can be checked. For the time being, all the observable sources (particularly `Single`) are mocked in the unit tests with immediately emiited results. As far as Android instrumentation tests are concerned, RxIdler library is used to wrap Rx schedulers to `IdlingResource`'s' for Espresso.
But in a real app, all the schedulers (and any other background executors) should be injected. This will allow to control all the asynchronous tasks in the test code.
