# Payload

**Payload** is a lightweight Java library for structured data transport and containerization. It allows you to wrap data objects into identified "containers" for easy management and serialization.

## Features

- **ID-Based Storage**: Containers are stored in a `HashMap` for fast retrieval by ID.
- **Type Safety**: Each `Payload` instance is bound to a specific class, ensuring all containers within it hold consistent data types.
- **Repackaged JSON**: Data is automatically wrapped into a structured JSON: `{"id": "...", "data": {...}}`.
- **Reflection-Free**: Uses Gson for serialization/deserialization without complex reflection overhead.

## Usage

### Initialize a Payload

Create a `Payload` controller by providing a template object. The class of this object will define the accepted data type for all containers in this payload.

```java
User profileTemplate = new User("username", 25);
Payload payload = new Payload(profileTemplate);
```

### Spawn a Container

Create a new container with a unique ID and data object.

```java
User ishit = new User("ishit", 20);
String containerJson = payload.spawnContainer("user_101", ishit);
```

The resulting `containerJson` will look like:
```json
{
  "id": "user_101",
  "data": {
    "name": "ishit",
    "age": 20
  }
}
```

### Retrieve Containers

You can get a specific container by its ID or access the entire map.

```java
// Get a single container
String json = payload.getContainer("user_101");

// Get all containers
Map<String, String> all = payload.getContainers();
```

### Parsing (To be used with utility or added methods)

The library structure supports extracting the ID and Data back from the repackaged JSON.

```java
// Logic for parsing (Implementation details)
String id = payload.parseId(containerJson);
String dataJson = payload.parseData(containerJson);
User user = (User) payload.parseObject(containerJson);
```

## Installation

Add the library to your project and ensure you have the **Gson** dependency.

---

*Note: This library is designed for simplicity and performance in data transport layers.*