# DBT Admin API Usage Guide

This document explains how to authenticate as an admin, obtain a token, and use it to access protected endpoints on the DBT3 Prerna UP API. This is useful for troubleshooting, integration, or scripting tasks.

---

## 1. Generate Admin Token

### Step 1: Authenticate as Admin

Run the following `curl` command to authenticate as admin and obtain your token:

```sh
curl -i -X POST "https://dbt3.prernaup.in/api/Authenticate/AuthenticateAdmin" \
  -u admin:admin \
  -H "Accept: application/json" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "pwd=admin"
```

- Replace `admin:admin` with the actual admin username and password if changed.
- Replace `pwd=admin` with the correct admin password if needed.

#### Response Example

The response will include headers like:

```
token: 0e752ab1-0d5d-47cd-bb85-5a9e008dcfb9
tokenexpiry: 86400
```

**Note:** The token is returned as a response header, not in the JSON body.

---

## 2. Use the Token for API Requests

For all subsequent admin API requests, include the token in the `Token` header.

### Example: Get Guardian Data

```sh
curl -i -X GET "https://dbt3.prernaup.in/api/GuardianInfo?mode=GetGuardianData&SchoolCode=<SchoolCode>" \
  -H "Accept: application/json" \
  -H "Token: <your_token_here>"
```

- Replace `<SchoolCode>` with the school's U-DISE code (e.g., `09280204401`).
- Replace `<your_token_here>` with the token obtained from the previous step.

### Example: Get Teacher-Class Link Master Data

```sh
curl -i -X GET "https://dbt3.prernaup.in/api/DBT?mode=GetAllTeacherClassLinkDataForDBTProcess&SchoolCode=<SchoolCode>&TeacherId=<Person_Id>&SchoolClassTypeActual_Id=<SchoolClassTypeActual_Id>" \
  -H "Accept: application/json" \
  -H "Token: <your_token_here>"
```

- `<SchoolCode>`: School's U-DISE code or GeoRegionCode.
- `<Person_Id>`: Teacher's Person_Id from profile.
- `<SchoolClassTypeActual_Id>`: Value from user profile (try `1` if uncertain).

---

## 3. Notes and Troubleshooting

- If you receive a 404 error, double-check the endpoint and parameters.
- The token expires after a certain period (`tokenexpiry` header, e.g., 86400 seconds = 24 hours). Re-authenticate to get a new token if expired.
- For endpoints or parameters, inspect the decompiled app code or network requests for the latest info.
- U-DISE code is usually an 11-digit number uniquely identifying a school.

---

## 4. Security Notice

**Keep your token secure.** Anyone with this token can access admin APIs until expiry.

---

## 5. References

- API Base URL: `https://dbt3.prernaup.in`
- U-DISE Info: [https://udiseplus.gov.in/](https://udiseplus.gov.in/)

---

_Last updated: 2025-06-15_
