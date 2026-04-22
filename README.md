# Zoho Payments Java SDK

Official Java SDK for the Zoho Payments API — supports IN, IN Sandbox, and US editions.

API reference:
- **India:** https://www.zoho.com/in/payments/api/v1/introduction/
- **United States:** https://www.zoho.com/us/payments/api/v1/introduction/

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

## Requirements

- **Java 17+**
- **Gson 2.10.1**

## Installation

The Zoho Payments Java SDK is distributed via the Zoho Maven repository. Add the repository and dependency to your build configuration as shown below.

### Gradle

```groovy
repositories {
    maven {
        url "https://static.zohocdn.com/zpayments/"
    }
    mavenCentral()
}

dependencies {
    implementation "com.zohopayments.java:sdk:1.0.0"
}
```

### Maven

```xml
<repositories>
    <repository>
        <id>zohopayments-repo</id>
        <url>https://static.zohocdn.com/zpayments/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.zohopayments.java</groupId>
        <artifactId>sdk</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

## Quick Start

```java
import com.zohopayments.Edition;
import com.zohopayments.ZohoPayments;
import com.zohopayments.ZohoPaymentsClient;
import com.zohopayments.model.PaymentLink;
import com.zohopayments.param.PaymentLinkCreateParams;

// 1. Build the client
ZohoPaymentsClient client = ZohoPayments.builder()
        .accountId("23137556")
        .edition(Edition.IN)
        .oauthToken("1000.xxxx.yyyy")
        .build();

// 2. Use a service
PaymentLink link = client.paymentLinks().create(
        PaymentLinkCreateParams.builder()
                .amount(500.00)
                .currency("INR")
                .description("Order #1234")
                .email("customer@example.com")
                .build());

System.out.println("Created: " + link.getPaymentLinkId());

// 3. Close when done
client.close();
```

## Editions

| Edition | Payments API Base URL | OAuth Accounts URL |
|---------|-----------------------|--------------------|
| `Edition.IN` | `https://payments.zoho.in/api/v1` | `https://accounts.zoho.in` |
| `Edition.IN_SANDBOX` | `https://paymentssandbox.zoho.in/api/v1` | `https://accounts.zoho.in` |
| `Edition.US` | `https://payments.zoho.com/api/v1` | `https://accounts.zoho.com` |

Helper methods: `edition.isIN()` returns `true` for both `IN` and `IN_SANDBOX`; `edition.isUS()` returns `true` for `US`.

## Authentication

### Access token only

```java
ZohoPaymentsClient client = ZohoPayments.builder()
        .accountId("23137556")
        .edition(Edition.IN)
        .oauthToken("1000.access_token_here")
        .build();
```

### Token refresh

The SDK does **not** auto-refresh tokens. Use `ZohoPayments.generateAccessToken()` when the access token expires, then push the new token into the client:

```java
import com.zohopayments.auth.OAuthToken;

OAuthToken fresh = ZohoPayments.generateAccessToken(
        refreshToken, clientId, clientSecret, redirectUri, Edition.IN);

// Persist the new token to your storage layer
myStore.save(fresh.getAccessToken(), fresh.getExpiresIn());

// Update the running client (thread-safe, no rebuild needed)
client.updateToken(fresh.getAccessToken());
```

You can also pass an `OAuthToken` directly to the builder:

```java
OAuthToken token = ZohoPayments.generateAccessToken(...);
ZohoPaymentsClient client = ZohoPayments.builder()
        .accountId("23137556")
        .edition(Edition.IN)
        .oauthToken(token)
        .build();
```

`OAuthToken` exposes `getAccessToken()` and `getExpiresIn()` (token lifetime in seconds).

## Client Configuration

```java
ZohoPaymentsClient client = ZohoPayments.builder()
        .accountId("23137556")                          // Required
        .edition(Edition.IN)                            // Required
        .oauthToken("1000.xxx...")                      // Required
        .connectTimeout(Duration.ofSeconds(15))         // TCP connect timeout (default: 30s)
        .requestTimeout(Duration.ofSeconds(45))         // Per-request read timeout (default: 60s)
        .addDefaultHeader("X-Custom-Header", "value")   // Custom header sent with every request
        .httpClient(myCustomTransport)                  // Custom HTTP transport (see below)
        .build();
```

### Custom HTTP transport

Implement `HttpClientInterface` to use OkHttp, Apache HttpClient, or a test mock:

```java
import com.zohopayments.net.HttpClientInterface;
import com.zohopayments.net.ZohoRequest;
import com.zohopayments.net.ZohoResponse;

public class MyHttpClient implements HttpClientInterface {
    @Override
    public ZohoResponse execute(ZohoRequest request) {
        // Map ZohoRequest to your preferred HTTP library
    }
}
```

When a custom transport is set, `connectTimeout` is ignored (your client controls its own timeouts).


## API Usage Examples

### Customers

```java
import com.zohopayments.model.Customer;
import com.zohopayments.model.ListResponse;
import com.zohopayments.param.CustomerCreateParams;
import com.zohopayments.param.CustomerListParams;

// Create (all editions)
Customer customer = client.customers().create(
        CustomerCreateParams.builder()
                .name("Jane Doe")
                .email("jane@example.com")
                .build());

// Retrieve (all editions)
Customer c = client.customers().get(customer.getCustomerId());

// List (US only — not available on IN edition)
ListResponse<Customer> customers = client.customers().list(
        CustomerListParams.builder().perPage(10).page(1).build());

// Delete (US only — customers with saved payment methods cannot be deleted)
client.customers().delete(customer.getCustomerId());
```

### Payment Sessions

```java
import com.zohopayments.model.PaymentSession;
import com.zohopayments.param.PaymentSessionCreateParams;

PaymentSession session = client.paymentSessions().create(
        PaymentSessionCreateParams.builder()
                .amount(100.00)
                .currency("INR")
                .description("Checkout #42")
                .expiresIn(600)
                .build());

PaymentSession retrieved = client.paymentSessions().get(session.getPaymentsSessionId());
```

### Payment Links

```java
import com.zohopayments.model.PaymentLink;
import com.zohopayments.param.PaymentLinkCreateParams;
import com.zohopayments.param.PaymentLinkUpdateParams;

// Create
PaymentLink link = client.paymentLinks().create(
        PaymentLinkCreateParams.builder()
                .amount(500.00)
                .currency("INR")
                .description("Invoice #2025-001")
                .email("buyer@example.com")
                .build());

// Update
client.paymentLinks().update(link.getPaymentLinkId(),
        PaymentLinkUpdateParams.builder()
                .description("Invoice #2025-001 (revised)")
                .build());

// Cancel
client.paymentLinks().cancel(link.getPaymentLinkId());
```

### Payments & Refunds

```java
import com.zohopayments.model.Payment;
import com.zohopayments.model.PaymentSummary;
import com.zohopayments.model.Refund;
import com.zohopayments.param.RefundCreateParams;

// List payments
ListResponse<PaymentSummary> payments = client.payments().list();

// Retrieve one payment
Payment payment = client.payments().get("6485000000045015");

// Create a refund (type: "initiated_by_merchant" or "initiated_by_customer")
Refund refund = client.refunds().create("6485000000045015",
        RefundCreateParams.builder()
                .amount(50.00)
                .reason("requested_by_customer")
                .type("initiated_by_customer")
                .build());

// Retrieve a refund
Refund r = client.refunds().get(refund.getRefundId());
```

## Pagination

List methods return `ListResponse<T>` containing:
- `getData()` — the list of items (immutable)
- `getPageContext()` — a `PageContext` with `getPage()`, `getPerPage()`, `getTotal()`, `getTotalPages()`, `hasMorePage()`

List param builders that accept pagination implement `PaginationParams` and expose `perPage(Integer)` and `page(Integer)`.

## Metadata

Several create/update operations accept `List<MetaDataParams>` — key/value pairs attached to a resource:

```java
import com.zohopayments.param.MetaDataParams;

List<MetaDataParams> meta = List.of(
        new MetaDataParams("order_id", "ORD-123"),
        new MetaDataParams("source", "mobile-app"));
```

On the response side, the same data is exposed as `List<MetaData>` (`com.zohopayments.model.MetaData`).

**Constraints** (validated client-side): maximum 5 entries, key <= 20 characters, value <= 500 characters.

## Error Handling

SDK errors are split into two families:

- **Caller / programmer errors** (bad arguments you passed to the SDK before any network call) throw standard JDK exceptions: `IllegalArgumentException` for missing/invalid fields, `IllegalStateException` when a consumed `Builder` is reused. These indicate bugs in your code and should generally not be caught in normal flow — fix the call site.
- **SDK runtime failures** (transport errors, unexpected responses, or Zoho API errors) are rooted at `ZohoPaymentsException`:

```
RuntimeException
+-- IllegalArgumentException                -- caller supplied a missing/invalid argument
+-- IllegalStateException                   -- Builder already consumed; closed client reused
+-- ZohoPaymentsException                   -- base SDK runtime error
    +-- ConnectionException                  -- network / I/O failure
    +-- ZohoPaymentsAPIException             -- API returned an error
        +-- AuthenticationException           -- HTTP 401
        +-- PermissionException               -- HTTP 403
        +-- ResourceNotFoundException         -- HTTP 404
        +-- InvalidRequestException           -- HTTP 400 / 422
        +-- RateLimitException                -- HTTP 429
```

### Handling API errors

```java
import com.zohopayments.exception.*; 
try {
    client.payments().get("invalid-id");
} catch (ResourceNotFoundException e) {
    System.out.println("Not found: " + e.getApiErrorMessage());
} catch (AuthenticationException e) {
    System.out.println("Token expired — refresh and retry");
} catch (RateLimitException e) {
    System.out.println("Throttled — back off and retry");
} catch (ZohoPaymentsAPIException e) {
    System.out.println("HTTP " + e.getHttpStatusCode()
            + " code=" + e.getCodeString()
            + " message=" + e.getApiErrorMessage());
} catch (ConnectionException e) {
    System.out.println("Network error: " + e.getMessage());
} catch (ZohoPaymentsException e) {
    System.out.println("SDK error: " + e.getMessage());
}
```

## Thread Safety

`ZohoPaymentsClient` is thread-safe. The access token is stored in an `AtomicReference` and can be rotated via `updateToken()` without rebuilding the client. Service instances are eagerly initialized at build time.

## Closing the Client

Once you are done using the SDK, call `client.close()` to release the resources held by the client. This ensures that HTTP connections, background threads, and cached authentication state are cleaned up properly.

### When to call `close()`

- At application shutdown.
- After completing a short-lived batch job or standalone script.
- Before re-initializing the client with new credentials or configuration.

### Usage

**Explicit close:**

```java
ZohoPaymentsClient client = ZohoPayments.builder()
        .accountId("23137556")
        .edition(Edition.IN)
        .oauthToken("1000.xxxx.yyyy")
        .build();
try {
    // make API calls using client
} finally {
    client.close();
}
```

**Try-with-resources (recommended):**

```java
try (ZohoPaymentsClient client = ZohoPayments.builder()
        .accountId("23137556")
        .edition(Edition.IN)
        .oauthToken("1000.xxxx.yyyy")
        .build()) {
    // make API calls using client
}
// client.close() is invoked automatically
```

### Behavior

1. `close()` is safe to call; calling it more than once has no additional effect.
2. Any API call made on the client after `close()` will throw an exception (`IllegalStateException`).
3. In-flight requests are allowed to complete before resources are released.

## License

[Apache License 2.0](LICENSE)
