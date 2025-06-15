# DBT Teacher-Class Link API Documentation

## Overview

This document describes how to use the Prerna DBT API endpoint for fetching teacher-class link information for a specific school and teacher.

---

## Endpoint

```
GET https://dbt3.prernaup.in/api/DBT?mode=GetAllTeacherClassLinkDataForDBTProcess&SchoolCode={SCHOOLCODE}&TeacherId={TEACHERID}&SchoolClassTypeActual_Id={CLASS_TYPE_ID}
```

### **Required Query Parameters**

| Parameter                 | Description                                 | Example Value      | Required |
|---------------------------|---------------------------------------------|--------------------|----------|
| `mode`                    | Fixed value: `GetAllTeacherClassLinkDataForDBTProcess` | GetAllTeacherClassLinkDataForDBTProcess | Yes      |
| `SchoolCode`              | UDISE/GeoRegionCode of the school (11 digits) | 092808020301       | Yes      |
| `TeacherId`               | Unique Teacher ID (7+ digits)                | 1280082            | Yes      |
| `SchoolClassTypeActual_Id`| Usually 1, 2, etc. Try 1                     | 1                  | Yes      |

---

## **Headers**

| Header    | Value                   | Required |
|-----------|-------------------------|----------|
| Accept    | application/json        | Yes      |
| token     | API token (from login)  | Yes      |

---

## **Example cURL Request**

```sh
curl -i -X GET "https://dbt3.prernaup.in/api/DBT?mode=GetAllTeacherClassLinkDataForDBTProcess&SchoolCode=092808020301&TeacherId=1280082&SchoolClassTypeActual_Id=1" \
  -H "Accept: application/json" \
  -H "token: YOUR_API_TOKEN"
```

---

## **Sample Successful Response**

```json
{
  "ClassId": null,
  "ClassName": null,
  "TotalEnrollment": null,
  "IsAllotted": null,
  "TeacherId": "1280082",
  "TeacherName": null,
  "TeacherMobileNumber": null,
  "lstStudentTeacherLinkClass": [
    {
      "ClassId": "47",
      "ClassName": null,
      "TotalEnrollment": "12",
      "IsAllotted": "1",
      "TeacherId": "1903694",
      "TeacherName": "MANISHA SINGH",
      "TeacherMobileNumber": "9519665888",
      ...
    }
  ],
  "School_Code": "092808020301",
  "SchoolClassActualType_Id": "1",
  ...
}
```

---

## **Important Notes**

- **TeacherId is mandatory:**  
  The API will NOT return a list of all teachers for a given school.  
  You must provide an existing, valid TeacherId for the school.

- **No "bulk" teacher list:**  
  There is no known endpoint to enumerate all teachers for a school via only SchoolCode.

- **Token required:**  
  You must authenticate via the admin login process to obtain a valid API token.

---

## **Usage Limitations**

- To fetch data for all teachers in a school, you must have (or collect) all TeacherIds for that school.
- This endpoint is designed for "per-teacher" queries, not "per-school" listings.

---

## **See also**

- [Student Data Fetch API](./DBT-Student-Fetch-API.md) *(if available)*
- [Token Authentication Process](./DBT-Token-Auth.md) *(if available)*

---
