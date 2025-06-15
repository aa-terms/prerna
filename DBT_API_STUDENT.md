# DBT Student Data API Usage Guide

This guide explains how to retrieve bulk student data (including all details required for DBT) from the Prerna DBT API, using a token-based admin authentication.

---

## 1. Prerequisites

- You must have obtained an admin token using the instructions in the [DBT Admin API Usage Guide](DBT_API_usage_guide.md).
- You need to know your `SchoolCode` (GeoRegionCode or UDISE code) and `TeacherId` (Person_Id).

---

## 2. Fetching Student Data

### Sample cURL Command

```sh
curl -i -X GET "https://dbt3.prernaup.in/api/DBT?mode=GetAllTeacherClassStudentDataForDBTProcess&SchoolCode=<SchoolCode>&TeacherId=<Person_Id>" \
  -H "Accept: application/json" \
  -H "Token: <your_token_here>"
```

- Replace `<SchoolCode>` with your school's GeoRegionCode (e.g., `092808020301`).
- Replace `<Person_Id>` with the teacher's Person_Id (e.g., `1280082`).
- Replace `<your_token_here>` with your admin session token.

---

## 3. Response Structure

- The API will return a large JSON array containing all student records for the given teacher and school.
- Each object contains the student’s:
  - Demographics (name, DOB, gender, class, SRNO, etc.)
  - Guardian info (name, mobile, aadhar verification, etc.)
  - Bank details (if present)
  - DBT status, process flow, verification flags
  - Aadhaar-related fields
  - Dropout, BEO/BSA verification status, and much more.

### Example (truncated):

```json
[
  {
    "DBTStudentDetails_Id": "2224624",
    "SchoolCode": "092808020301",
    "UDISECode": "09280204401",
    "StudentClassId": "51",
    "StudentName": "Janvi",
    "StudentDOB": "10/20/2015 12:00:00 AM",
    "GuardianName": "RAMU",
    "GuardianMobileNo": "6306645479",
    "IsVerified_ByTeacher": "True",
    "IsVerified_ByBEO": "True",
    "IsVerified_ByBSA": "True",
    "IsPhase1ExcelUploadedBYPFMS": "False",
    "IsPhase2ExcelUploadedBYPFMS": "False",
    "IsPhase3ExcelUploadedBYPFMS": "0",
    "MarkedAsDropOut": "False",
    "IsActive": "True",
    "AdmissionDate": "2021-08-11"
    // ... many more fields ...
  },
  ...
]
```

**Note:**  
- The JSON can be very large, so you may want to pipe the output to a file or use tools like `jq` to process it.

---

## 4. Tips & Troubleshooting

- If the response is empty (`[]`), double-check your SchoolCode and TeacherId.
- If you get HTTP 401/403, your token may have expired—generate a new one.
- Each student object contains detailed audit, aadhar, guardian, and bank info for DBT processes.

---

## 5. Security Notice

- The data contains sensitive personal information. Handle and store it securely, and comply with all relevant data privacy rules.

---

_Last updated: 2025-06-15_
