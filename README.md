# Hike API

## Overview

### 📥 Information that will be provided:
- Kilometers (`long lengthInKilometers`)
- Season (`String season`)

### 📤 Information that will be returned:
- Sleepover count (`int sleepoverCount`)
  - Based on kilometers
- Food calories (`int foodCalories`)
  - Based on base daily calories, kilometers & season, which applies multipliers
- Liters of water (`double litersOfWater`)
  - Based on kilometers & season, which applies multipliers
- Items (`List<String> items`)
  - Based on season

### 🔧 Calculation modification:
In the `Constants` file, you can modify:
- after how many kilometers you should sleep (with the `KILOMETERS_BEFORE_SLEEP` property)
- base daily calories (with the `BASE_DAILY_CALORIES` property)
- amount of calories burned per kilometer (with the `CALORIES_BURNED_PER_KILOMETER` property)
- for how many kilometers 1 litre of water should be sufficient (with the `KILOMETERS_PER_WATER_LITRE` property)

## Hikes endpoints

### POST `http://localhost:8080/api/v1/hikes`
🎯 **Purpose:** get hike recommendations.

Success request body example:
```json
{
    "lengthInKilometers": 200,
    "season": "winter"
}
```
Success response status code: `200`

Success response body example:
```json
{
  "sleepoverCount": 4,
  "foodCalories": 23000,
  "litersOfWater": 20.0,
  "items": [
    "scarf",
    "gloves",
    "earmuffs"
  ]
}
```
---
Exception request body example:
```json
{
    "lengthInKilometers": 200,
    "season": "abc"
}
```

Exception response status code: `400`

Exception response body example:
```json
{
  "message": "Invalid season 'abc' provided"
}
```

## Items endpoints

### GET `http://localhost:8080/api/v1/items/{id}`

🎯 **Purpose:** get item.

Success response status code: `200`

Success response body example:
```json
{
    "id": 13,
    "name": "sunglasses",
    "season": "summer"
}
```
---
Exception response status code: `400`

Exception response body example:
```json
{
  "message": "Item with provided ID not found"
}
```

### GET `http://localhost:8080/api/v1/items`

🎯 **Purpose:** get all items.

Success response status code: `200`

Success response body example:
```json
[
  {
    "id": 1,
    "name": "cap",
    "season": "summer"
  },
  {
    "id": 2,
    "name": "insect repellent",
    "season": "summer"
  }
]
```

### POST `http://localhost:8080/api/v1/items`

🎯 **Purpose:** create item.

Request body example:
```json
{
  "name": "Sunglasses",
  "season": "Summer"
}
```
Success response status code: `201`

Success response body example:
```json
{
    "id": 13,
    "name": "sunglasses",
    "season": "summer"
}
```
---
Exception request body example:
```json
{
  "name": "Sunglasses",
  "season": "abc"
}
```

Exception response status code: `400`

Exception response body example:
```json
{
  "message": "Invalid season 'abc' provided"
}
```

### DELETE `http://localhost:8080/api/v1/items/{id}`

🎯 **Purpose:** delete item.

Success response status code: `204`