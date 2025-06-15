# DBT Teacher-Class Link Master Data API Guide

This guide explains how to retrieve teacher-class assignment (link) master data for a school from the Prerna DBT API.

---

## 1. Prerequisites

- You must have a valid admin token. See [DBT Admin API Usage Guide](DBT_API_usage_guide.md) for how to generate one.
- You need your `SchoolCode` (GeoRegionCode), `TeacherId` (Person_Id), and `SchoolClassTypeActual_Id`.

---

## 2. Fetching Teacher-Class Link Data

### Sample cURL Command

```sh
curl -i -X GET "https://dbt3.prernaup.in/api/DBT?mode=GetAllTeacherClassLinkDataForDBTProcess&SchoolCode=092808020301&TeacherId=1280082&SchoolClassTypeActual_Id=1" \
  -H "Accept: application/json" \
  -H "Token: 0e752ab1-0d5d-47cd-bb85-5a9e008dcfb9"
```

- Replace values as per your context:
  - `SchoolCode` – School's GeoRegionCode (e.g., `092808020301`)
  - `TeacherId` – Teacher's Person_Id (e.g., `1280082`)
  - `SchoolClassTypeActual_Id` – Class type ID (e.g., `1`)
  - `Token` – Your valid session token

---

## 3. Response Structure

- The API returns a JSON object with a `lstStudentTeacherLinkClass` array.
- Each item represents a class assigned to a teacher, including:
  - `ClassId` – Internal class code (e.g., `47` for Class 1, `48` for Class 2, etc.)
  - `ClassName` – (May be null, can be mapped from `ClassId`)
  - `TotalEnrollment` – Number of students in the class
  - `IsAllotted` – "1" if the class is allotted to the teacher
  - `TeacherId`, `TeacherName`, `TeacherMobileNumber`

**Example:**
```json
{
  "lstStudentTeacherLinkClass": [
    {
      "ClassId": "47",
      "ClassName": null,
      "TotalEnrollment": "12",
      "IsAllotted": "1",
      "TeacherId": "1903694",
      "TeacherName": "MANISHA SINGH",
      "TeacherMobileNumber": "9519665888"
      // ...more fields...
    }
    // ...more classes...
  ],
  "School_Code": "092808020301",
  "SchoolClassActualType_Id": "1"
  // ...other metadata...
}
```

### **ClassId to Standard Class Mapping** (from app code)

| ClassId | Standard Class |
|---------|---------------|
| 47      | 1             |
| 48      | 2             |
| 49      | 3             |
| 50      | 4             |
| 51      | 5             |
| 52      | 6             |
| 53      | 7             |
| 54      | 8             |

---

## 4. Tips & Notes

- If you get an empty `lstStudentTeacherLinkClass`, the teacher may not have any assigned classes for the given year/type.
- Check that all query parameter values match those used in the app.
- The token expires after some time; regenerate if you get a 401/403 error.

---

## 5. Security

- This API can reveal personal data; handle all outputs in compliance with privacy and data regulations.

---

_Last updated: 2025-06-15_
