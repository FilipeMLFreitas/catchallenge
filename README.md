# Cat Challenge

Hello guys.

Thank you for the opportunity to prove my worth.

Creating this project was a lot of fun and also a learning experience since I had to remember and refresh my knowledge.

I had to juggle my day job, the after day job (family) and this project so unfortunately, I didn't manage to implement all the features, so I'm sorry about that. 

Instead I tried to deliver the technical requirements.

Also due to time constraints, I didn't spend the time I wanted to in each component to make them shine.

I think I covered a bit of everything, hopefully it demonstrates my knowledge and organization.

I left some TODOs for improvements or interesting discussion points. 

Thank you

# Implementation notes

As you can probably see by my commit history, I prefer the bottom-up approach to implementation. 

I think I fullfilled the following technical requirements:

- MVVM architecture
- Usage of Jetpack Compose for the UI (and navigation)
- Unit test coverage
- Offline functionality
- Modular design

Some further thoughts:

### Architecture

I used Hilt to inject high level dependencies. 

In some components, simply for hastened development purposes, I didn't use injection but I marked those places with TODOs. 

Using injection is important because it would make those components more flexible, less coupled, and more testable.

About the viewmodels, I also would move the business logic into something like Use Cases. Maybe like it's described here: https://medium.com/@benmansour1992/android-use-cases-hell-in-mvvm-architecture-43a45ca41df4

### Jetpack Compose

I didn't spend much time with the UI, also due to time constraints, but also because I wanted to deliver the most technical requirements I could.

The UI is something that always take a long time to develop, because it needs to take into account translating the design into the code, managing resources, theming, etc.

I also admit that Compose is my biggest shortcoming. Due to professional reasons, it's something I haven't had the chance to dwelve deeper, but I really like it and wish to master it in the future.


### Unit test coverage

I didn't strived to achieve 100% of code coverage since I don't think that was the point. 

I implemented some cases for demonstrations purposes.

Due to technical difficulties, I couldn't implement instrumented tests since they seem to simply get stuck in an infite loading state. 

I couldn't find a solution in a timely manner, sorry.
 
### Offline functionality

I used the Room database to store the simple Breed data. 

Currently, when fetching new data, the cache simply deletes everything and inserts again. Also, this operation is kind of manual in the viewmodel.

I'm not happy about that. 

I believe there should be a smarter algorithm and also the viewmodel shouldn't worry about data synchronization, the cache should take care of that by itself.

### Modularization

I distributed the code along several modules: model and repositories. The features could also be moved to a specific module.

I think modularization is great, even more if each module can be published and used in different apps instead of just being local modules.

The "Now In Android" project is an (extreme) example of modularization and shows how everything can be organized in a big project.
