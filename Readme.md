# Afro-Rus Backend Documentation

## Technical Stack

### Core Technologies
- **Backend**: Java 17 with Spring Boot 3.x
- **Database**: PostgreSQL (for relational data) + Redis (for caching and sessions)
- **Authentication**: Spring Security with JWT + OAuth2 for Google login
- **API Documentation**: Swagger/OpenAPI
- **Build Tool**: Maven or Gradle (recommend Gradle for faster builds)

### Deployment
- **Platform**: Railway.app (with PostgreSQL add-on)
- **Containerization**: Docker
- **CI/CD**: GitHub Actions for automated deployments

### Additional Services
- **Passport Verification**: Consider using Jumio, Onfido, or Trulioo for automated document verification
- **Payments**: (For future implementation) Stripe, Paystack, or Flutterwave
- **Real-time Chat**: (For future implementation) Socket.IO or Firebase Realtime Database
- **Email/SMS**: SendGrid or Twilio for notifications

## Detailed Service Implementation

### 1. Registration & Authentication

#### Endpoints:
- `POST /api/v1/auth/register`
    - Request Body:
      ```json
      {
        "username": "string",
        "email": "string",
        "phoneNumber": "string",
        "password": "string",
        "authType": "EMAIL|PHONE"
      }
      ```
    - Response:
      ```json
      {
        "success": true,
        "userId": "string",
        "jwtToken": "string",
        "refreshToken": "string"
      }
      ```

- `POST /api/v1/auth/login`
    - Request Body:
      ```json
      {
        "emailOrPhone": "string",
        "password": "string"
      }
      ```
    - Response: Same as register

- `GET /api/v1/auth/oauth/google`
    - Redirects to Google OAuth

- `POST /api/v1/auth/oauth/google/callback`
    - Handles OAuth callback

### 2. Verification Service

#### Verification Flow:
1. User submits documents
2. System validates document quality
3. Third-party verification service checks authenticity
4. Admin dashboard for manual review if needed
5. Verification status updated

#### Endpoints:
- `POST /api/v1/verification/submit`
    - Request Body (multipart/form-data):
        - userId: string
        - phoneNumber: string
        - documentType: "PASSPORT|DRIVERS_LICENSE|NATIONAL_ID"
        - documentNumber: string
        - documentImage: file
        - selfieImage: file

    - Response:
      ```json
      {
        "success": true,
        "verificationId": "string",
        "estimatedCompletionTime": "ISO8601"
      }
      ```

- `GET /api/v1/verification/status/{userId}`
    - Response:
      ```json
      {
        "status": "PENDING|APPROVED|REJECTED",
        "rejectionReason": "string|null"
      }
      ```

### 3. Space Services

#### Space Availability Model:
```java
public class AvailableSpace {
    private String id;
    private String userId;
    private SpaceType type; // GOODS or DOCUMENT
    private Double weightKg; // nullable for documents
    private Double pricePerKg; // in rubles
    private String currency; // "RUB" or "NGN"
    private String description;
    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;
    private String departureCity;
    private String arrivalCity;
    private boolean isVerifiedUser;
    private LocalDateTime createdAt;
}
```

#### Endpoints:
- `POST /api/v1/spaces/available`
    - Request Body:
      ```json
      {
        "type": "GOODS|DOCUMENT",
        "weightKg": 0.0, // required for GOODS
        "pricePerKg": 0.0,
        "currency": "RUB|NGN",
        "description": "string",
        "availableFrom": "ISO8601",
        "availableTo": "ISO8601",
        "departureCity": "string",
        "arrivalCity": "string"
      }
      ```

- `POST /api/v1/spaces/needed`
    - Similar structure but for needed spaces

- `GET /api/v1/spaces/available`
    - Query Params:
        - type: filter by type
        - departureCity: filter by departure
        - arrivalCity: filter by arrival
        - minDate: filter by availability
        - maxDate: filter by availability
        - onlyVerified: boolean

### 4. Contact Service

#### Implementation Options:
1. Direct contact information sharing
2. Platform-mediated messaging with contact request system

#### Endpoints:
- `POST /api/v1/contact/request`
    - Request Body:
      ```json
      {
        "spaceId": "string",
        "message": "string",
        "contactMethod": "WHATSAPP|TELEGRAM|EMAIL"
      }
      ```
    - Response contains contact details if approved

### 5. Auto-Deletion Service

#### Implementation:
- Spring Scheduler to run daily:
```java
@Scheduled(cron = "0 0 0 * * ?")
public void removeExpiredSpaces() {
    spaceRepository.deleteByAvailableToBefore(LocalDateTime.now());
}
```

## Database Schema

### Tables:
1. **users**
    - id (UUID)
    - username
    - email
    - phone_number
    - password_hash
    - is_verified
    - verification_status
    - created_at
    - updated_at

2. **verification_documents**
    - id
    - user_id (FK)
    - document_type
    - document_number
    - document_image_url
    - selfie_image_url
    - status
    - verified_at

3. **available_spaces**
    - id
    - user_id (FK)
    - type
    - weight_kg
    - price_per_kg
    - currency
    - description
    - available_from
    - available_to
    - departure_city
    - arrival_city
    - created_at

4. **needed_spaces**
    - (similar to available_spaces)

## Security Considerations

1. **Data Protection**:
    - Encrypt sensitive data (passport info) at rest
    - Use HTTPS everywhere
    - Implement rate limiting

2. **Authentication**:
    - JWT with short expiration (15 mins)
    - Refresh tokens with longer expiration (7 days)
    - Secure cookie settings

3. **Input Validation**:
    - Validate all API inputs
    - Sanitize user-generated content

## Deployment on Railway

### Steps:
1. Create `Dockerfile` and `docker-compose.yml`
2. Set up PostgreSQL on Railway
3. Configure environment variables:
    - DATABASE_URL
    - JWT_SECRET
    - OAUTH_CLIENT_ID
    - VERIFICATION_API_KEY
4. Set up health check endpoint at `/health`

### Recommended Railway Configuration:
- Instance type: Standard (2GB RAM)
- Auto-deploy from GitHub
- Custom domain with HTTPS
- Backup retention: 7 days

## Future Features Roadmap

1. **Phase 2**:
    - In-app chat system
    - User ratings and reviews
    - Notifications system

2. **Phase 3**:
    - Escrow payment system
    - Video verification
    - Mobile apps (Flutter)

3. **Phase 4**:
    - AI-based space matching
    - Travel companion features
    - Premium membership options

## Monitoring and Analytics

1. **Essential Metrics**:
    - User signups/conversion
    - Space postings
    - Successful matches
    - Verification success rate

2. **Tools**:
    - Prometheus + Grafana
    - Sentry for error tracking
    - LogDNA for log management


