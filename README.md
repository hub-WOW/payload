

# Angeliaphoros

**Angeliaphoros** is a lightweight Java utility for creating structured, typed payloads that can be easily serialized to JSON.

The library provides a simple generic container that maps string keys to strongly‑typed values, making it useful for APIs, message passing, and structured data handling.

---

# Features

- Generic payload container using Java generics
- Simple key–value structure
- JSON serialization support via Gson
- Type‑safe data storage
- Lightweight and easy to integrate

---

# Usage

## Creating a Payload

```java
Payload<String> payload = new Payload<>();

payload.put("username", "ishit");
payload.put("role", "admin");
```

---

## Retrieving Data

```java
String username = payload.get("username");
```

---

## Constructing From Arrays

```java
String[] keys = {"username", "role"};
String[] values = {"ishit", "admin"};

Payload<String> payload = new Payload<>(values, keys);
```

---

## Convert to JSON

```java
String json = payload.toJSON();
```

Example output:

```json
{
  "payloadData": {
    "username": "ishit",
    "role": "admin"
  }
}
```

---

# Use Cases

Angeliaphoros can be used for:

- API request or response payloads
- WebSocket messaging
- structured data containers
- configuration serialization

---

# Design Philosophy

Angeliaphoros focuses on **simplicity and flexibility**. The goal is to provide a minimal abstraction for structured payload data without introducing heavy frameworks.

---