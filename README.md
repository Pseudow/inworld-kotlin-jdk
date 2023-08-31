# Overview

# Usage
## Configuration
First step before using Inworld API is to set up all the requirements by creating a  `InworldClientConfig`:
```kotlin
// AUTH FIRST WAY
val auth = InworldAuthCredentials(
    "key",
    "secret"
).build()

// AUTH SECOND WAY
val auth = InworldAuth("apiKey")

val config = InworldClientConfig(
    auth,
    "Default work space (used when no workspace is specified"
)
```
## Launching your session
Now we can proceed by creating a factory which will help use create **integrated session** and an user
which will be using the session.
```kotlin
val factory = InworldClientFactory(config)
val endUser = User(
    "12345",
    "Pseudow",
    "Male",
    "Developer",
    17
)
```
### Simple integration session
To send a message you can create a simple session:
```kotlin
val characterName = "robert"
val message = "How are you?"

val simpleSession = factory.openSimpleSession(
    endUser,
    characterName,
    message
)
```
Now to get the response back and send new messages:
```kotlin
fun printText(interaction) =
    interaction.text.forEach { println("New message: $it") }

val interaction = simpleSession.first
var registry = simpleSession.second
printText(registry)

interaction = registry.sendMessage("Hello")
printText(registry)
```
### Full integration session

# Contributors
- [Pseudow](https://github.com/Pseudow)