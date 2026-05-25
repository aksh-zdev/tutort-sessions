# Clean Code Practice — Quick Reference & Cheatsheet
>
> C++ | Java | Python | Go
> *Last updated: 2 May 2026*

---

## Table of Contents

1. [Naming Conventions](#1-naming-conventions)
2. [Functions / Methods](#2-functions--methods)
3. [Comments — When & How](#3-comments--when--how)
4. [Formatting & Layout](#4-formatting--layout)
5. [Error Handling](#5-error-handling)
6. [Classes & Structs](#6-classes--structs)
7. [SOLID Principles — Cheatsheet](#7-solid-principles--cheatsheet)
8. [DRY / KISS / YAGNI](#8-dry--kiss--yagni)
9. [Code Smells & Refactoring](#9-code-smells--refactoring)
10. [Testing](#10-testing)
11. [Concurrency](#11-concurrency)
12. [Language-Specific Idioms](#12-language-specific-idioms)
13. [Quick Decision Tables](#13-quick-decision-tables)

---

## 1. Naming Conventions

### Golden Rules

| Rule | Why |
|---|---|
| Use **intention-revealing** names | `elapsedTimeInDays` > `d` |
| Avoid **disinformation** | Don't call a list `accountList` if it's a `Set` |
| Make **meaningful distinctions** | `source` / `destination` > `a1` / `a2` |
| Use **pronounceable** names | `generationTimestamp` > `genymdhms` |
| Use **searchable** names | Named constants > magic numbers |
| One word per concept | Pick `fetch` OR `get` OR `retrieve` — stay consistent |

### Naming Style by Language

| Element | C++ | Java | Python | Go |
|---|---|---|---|---|
| **Local variable** | `snake_case` | `camelCase` | `snake_case` | `camelCase` |
| **Function / Method** | `snake_case` or `PascalCase` | `camelCase` | `snake_case` | `PascalCase` (exported) / `camelCase` (unexported) |
| **Class / Struct** | `PascalCase` | `PascalCase` | `PascalCase` | `PascalCase` |
| **Constant** | `kConstantName` or `ALL_CAPS` | `ALL_CAPS` | `ALL_CAPS` | `PascalCase` (exported) / `camelCase` (unexported) |
| **Interface** | `IReadable` (optional) | `Readable` (adjective) | `ABC` subclass | `Reader`, `Writer` (er-suffix) |
| **Package / Module** | `snake_case` namespace | `com.company.project` | `snake_case` | short, single word, lowercase |
| **Private field** | `trailing_underscore_` or `m_` prefix | `camelCase` (private by access modifier) | `_leading_underscore` | `camelCase` (unexported) |
| **Boolean** | `is_ready`, `has_value` | `isReady`, `hasValue` | `is_ready`, `has_value` | `isReady`, `hasValue` |
| **Enum** | `PascalCase` values | `ALL_CAPS` values | `ALL_CAPS` values | `PascalCase` prefixed with type |

### Anti-Patterns

```
❌ int d;                      // What is d?
✅ int elapsedTimeInDays;

❌ String theList;             // "the" adds nothing
✅ String activeAccounts;

❌ void doStuff();             // Vague
✅ void calculateMonthlyRevenue();

❌ bool flag;                  // Flag of what?
✅ bool isAuthenticated;
```

---

## 2. Functions / Methods

### Rules of Thumb

| Rule | Guideline |
|---|---|
| **Small** | ≤ 20 lines ideal, absolutely ≤ 40 |
| **Do ONE thing** | If you can extract another function with a non-trivial name, it does too much |
| **One level of abstraction** | Don't mix high-level logic with low-level details |
| **≤ 3 arguments** | 0 is best, 1–2 is fine, 3 needs justification, 4+ use an object/struct |
| **No side effects** | If named `checkPassword`, don't also initialize a session |
| **Command-Query Separation** | A function either *does* something OR *answers* something, not both |
| **Don't Repeat Yourself** | If identical logic appears 2+ times, extract it |
| **Fail fast** | Validate at the top, return/throw early |

### Function Length & Arguments — By Language

**C++**

```cpp
// ❌ Too many params, too long
bool processOrder(int id, string name, double price, int qty,
                  string addr, string city, string zip, bool express) {
    // 80 lines of mixed logic...
}

// ✅ Use a struct + small focused functions
struct OrderRequest {
    int id;
    std::string name;
    double price;
    int quantity;
    ShippingAddress address;
    bool express_shipping;
};

bool process_order(const OrderRequest& request) {
    validate_order(request);
    auto total = calculate_total(request);
    return submit_order(request, total);
}
```

**Java**

```java
// ❌ Boolean argument = function does two things
public void render(boolean isSuite) { ... }

// ✅ Two functions
public void renderForSuite() { ... }
public void renderForSingleTest() { ... }
```

**Python**

```python
# ❌ God function
def process(data):
    # validate, transform, save, email, log — all in one
    ...

# ✅ Composed from small functions
def process(data):
    validated = validate(data)
    transformed = transform(validated)
    save(transformed)
    notify(transformed)
```

**Go**

```go
// ❌ Accepting too many params
func CreateUser(name, email, phone, addr, city, zip string, age int) error { ... }

// ✅ Use a struct
type CreateUserRequest struct {
    Name  string
    Email string
    Phone string
    Age   int
    Address Address
}

func CreateUser(req CreateUserRequest) error {
    if err := validate(req); err != nil {
        return fmt.Errorf("invalid request: %w", err)
    }
    return save(req)
}
```

### Early Return Pattern (All Languages)

```
// Instead of deeply nested if-else:

func doSomething(input) {
    if !isValid(input)  → return error
    if !isAuthorized()  → return error
    if !hasQuota()      → return error

    // Happy path (only 1 level of indentation)
    result = performAction(input)
    return result
}
```

---

## 3. Comments — When & How

### Comment Hierarchy (Best → Worst)

| Priority | Approach |
|---|---|
| 🥇 | **Self-documenting code** — rename so comment is unnecessary |
| 🥈 | **Explain WHY**, not what — business reason, tradeoff, workaround |
| 🥉 | **Legal / regulatory** comments — copyright, license headers |
| ⚠️ | **TODO / FIXME** — with ticket number, not open-ended |
| ❌ | **Redundant** — restating what the code already says |
| ❌ | **Misleading** — comments that lie (worse than no comment) |
| ❌ | **Commented-out code** — use version control instead |

### Good vs Bad Comments

```cpp
// ❌ Redundant — the code already says this
// increment i by one
i++;

// ❌ Journal comment — use git log
// 2024-01-15: Added validation
// 2024-02-20: Fixed null check

// ✅ Explains WHY
// Using insertion sort here because the list is nearly sorted (benchmark: 3x faster than quicksort)
insertion_sort(data);

// ✅ Warning
// WARNING: This is not thread-safe. Caller must hold the mutex.
void update_cache(const Entry& entry);

// ✅ TODO with ticket
// TODO(JIRA-4523): Replace with batch API when available
for item in items:
    api.send(item)
```

### Doc Comments by Language

**C++ (Doxygen)**

```cpp
/// @brief Calculates compound interest.
/// @param principal Initial amount
/// @param rate Annual interest rate (0.05 = 5%)
/// @param years Number of compounding years
/// @return Final amount after compounding
double compound_interest(double principal, double rate, int years);
```

**Java (Javadoc)**

```java
/**
 * Calculates compound interest.
 *
 * @param principal initial amount
 * @param rate      annual interest rate (0.05 = 5%)
 * @param years     number of compounding years
 * @return final amount after compounding
 * @throws IllegalArgumentException if rate is negative
 */
public double compoundInterest(double principal, double rate, int years);
```

**Python (Docstring)**

```python
def compound_interest(principal: float, rate: float, years: int) -> float:
    """Calculate compound interest.

    Args:
        principal: Initial amount.
        rate: Annual interest rate (0.05 = 5%).
        years: Number of compounding years.

    Returns:
        Final amount after compounding.

    Raises:
        ValueError: If rate is negative.
    """
```

**Go (Godoc)**

```go
// CompoundInterest calculates compound interest.
// Rate is expressed as a decimal (0.05 = 5%).
// It returns the final amount after compounding.
func CompoundInterest(principal, rate float64, years int) float64 {
```

---

## 4. Formatting & Layout

### Universal Rules

| Rule | Detail |
|---|---|
| **Line length** | 80–120 chars max |
| **Vertical density** | Related code together, blank line between concepts |
| **Vertical ordering** | Caller above callee (newspaper metaphor) |
| **Indentation** | Never mix tabs & spaces in same project |
| **Imports** | Grouped: stdlib → third-party → local, alphabetical within groups |
| **File size** | ≤ 200–400 lines per file; if larger, split |
| **One class per file** | (Java strict, others recommended) |

### Language-Specific Formatting

| Setting | C++ | Java | Python | Go |
|---|---|---|---|---|
| **Indent** | 2 or 4 spaces | 4 spaces | 4 spaces (PEP 8) | Tabs (`gofmt`) |
| **Braces** | Same line or next (pick one) | Same line (K&R) | N/A (indentation) | Same line (enforced) |
| **Line length** | 80–120 | 100–120 | 79 (PEP 8) / 99 | No hard limit, be reasonable |
| **Formatter** | `clang-format` | `google-java-format` | `black` / `ruff format` | `gofmt` (non-negotiable) |
| **Linter** | `clang-tidy`, `cppcheck` | `Checkstyle`, `SpotBugs` | `ruff`, `pylint`, `mypy` | `golangci-lint`, `go vet` |

### Import Ordering Example

**Python**

```python
# 1. Standard library
import os
import sys
from collections import defaultdict

# 2. Third-party
import numpy as np
import requests

# 3. Local
from myapp.models import User
from myapp.utils import sanitize
```

**Go**

```go
import (
    // Standard library
    "context"
    "fmt"
    "net/http"

    // Third-party
    "github.com/gorilla/mux"
    "go.uber.org/zap"

    // Local
    "myapp/internal/auth"
    "myapp/internal/store"
)
```

**Java**

```java
// Standard library
import java.util.List;
import java.util.Map;

// Third-party
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

// Local
import com.mycompany.myapp.model.User;
import com.mycompany.myapp.service.UserService;
```

---

## 5. Error Handling

### Principles

| Principle | Detail |
|---|---|
| **Use exceptions for exceptional things** | Don't use exceptions for control flow |
| **Fail fast** | Detect errors at the earliest possible point |
| **Provide context** | Error messages should tell: what failed, why, and what to do |
| **Don't return null** | Return empty collections, Optional, or zero values instead |
| **Don't pass null** | Forbid null arguments in public APIs |
| **Catch specific exceptions** | Never `catch (Exception e)` without good reason |
| **Log or throw, not both** | Avoid log-and-rethrow (creates duplicate noise) |

### Error Handling by Language

**C++ — Exceptions + RAII**

```cpp
// ❌ Ignoring errors
FILE* f = fopen("data.txt", "r");
// use f without checking...

// ✅ RAII + exceptions
auto file = std::ifstream("data.txt");
if (!file.is_open()) {
    throw std::runtime_error("Failed to open data.txt: " + std::string(strerror(errno)));
}
// file automatically closed when scope exits (RAII)

// ✅ Use smart pointers, never raw new/delete
auto ptr = std::make_unique<Widget>(args);  // auto-cleanup
```

**Java — Checked + Unchecked Exceptions**

```java
// ❌ Catching everything, swallowing exception
try { ... }
catch (Exception e) { /* nothing */ }

// ✅ Catch specific, provide context
try {
    return userRepository.findById(userId);
} catch (DataAccessException e) {
    throw new ServiceException("Failed to fetch user: " + userId, e);
}

// ✅ Use Optional instead of null
public Optional<User> findUser(String id) {
    return Optional.ofNullable(userRepository.findById(id));
}

// ✅ try-with-resources
try (var conn = dataSource.getConnection();
     var stmt = conn.prepareStatement(sql)) {
    // auto-closed
}
```

**Python — EAFP over LBYL**

```python
# ❌ LBYL (Look Before You Leap) — race condition prone
if os.path.exists(path):
    with open(path) as f:
        data = f.read()

# ✅ EAFP (Easier to Ask Forgiveness than Permission)
try:
    with open(path) as f:
        data = f.read()
except FileNotFoundError:
    data = default_value

# ✅ Custom exceptions with context
class OrderProcessingError(Exception):
    def __init__(self, order_id: str, reason: str):
        super().__init__(f"Failed to process order {order_id}: {reason}")
        self.order_id = order_id

# ✅ Never use bare except
# ❌ except:
# ❌ except Exception:
# ✅ except ValueError as e:

# ✅ Return empty collections, not None
def get_users() -> list[User]:
    return []  # not None
```

**Go — Explicit Error Returns**

```go
// ❌ Ignoring errors
result, _ := doSomething()

// ✅ Always handle errors
result, err := doSomething()
if err != nil {
    return fmt.Errorf("doSomething failed for id=%d: %w", id, err)
}

// ✅ Wrap errors with context (use %w for wrapping)
if err := db.Save(user); err != nil {
    return fmt.Errorf("saving user %s: %w", user.ID, err)
}

// ✅ Define sentinel errors for callers to check
var ErrNotFound = errors.New("not found")
var ErrPermissionDenied = errors.New("permission denied")

// ✅ Custom error types when you need structured info
type ValidationError struct {
    Field   string
    Message string
}
func (e *ValidationError) Error() string {
    return fmt.Sprintf("validation failed on %s: %s", e.Field, e.Message)
}

// ✅ Use defer for cleanup
f, err := os.Open(path)
if err != nil {
    return err
}
defer f.Close()
```

### Error Handling Decision Table

```
Need to clean up resources?
├── C++   → RAII (smart pointers, scope guards)
├── Java  → try-with-resources
├── Python → with statement (context managers)
└── Go    → defer

Need to propagate error context?
├── C++   → throw with descriptive message, chain with std::nested_exception
├── Java  → new CustomException("message", cause)
├── Python → raise CustomError("msg") from original_error
└── Go    → fmt.Errorf("context: %w", err)
```

---

## 6. Classes & Structs

### Core Rules

| Rule | Detail |
|---|---|
| **Small classes** | A class should have ONE responsibility |
| **High cohesion** | Every field should be used by most methods |
| **Low coupling** | Minimize dependencies on other classes |
| **Encapsulate** | Keep fields private; expose behavior, not data |
| **Prefer composition** | Favor composition over inheritance |
| **Law of Demeter** | `a.getB().getC().doThing()` is a code smell |

### Class Design by Language

**C++ — Rule of Five / Rule of Zero**

```cpp
// ✅ Rule of Zero: Let RAII types manage resources
class User {
    std::string name_;
    std::vector<Order> orders_;
public:
    // Compiler-generated special members are fine — no raw pointers
    explicit User(std::string name) : name_(std::move(name)) {}
    const std::string& name() const { return name_; }
};

// If you MUST manage a resource: Rule of Five
// Define: destructor, copy ctor, copy assign, move ctor, move assign
// But prefer Rule of Zero by using smart pointers instead.
```

**Java — Favor Immutability**

```java
// ✅ Immutable class
public final class Money {
    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = Objects.requireNonNull(amount);
        this.currency = Objects.requireNonNull(currency);
    }

    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }

    // Getters only, no setters
    public BigDecimal amount() { return amount; }
    public Currency currency() { return currency; }
}

// ✅ Or use records (Java 16+)
public record Point(double x, double y) {}
```

**Python — Dataclasses & Slots**

```python
# ✅ Use dataclasses for data holders
from dataclasses import dataclass

@dataclass(frozen=True)  # frozen = immutable
class Point:
    x: float
    y: float

# ✅ Use __slots__ for memory efficiency (many instances)
class Sensor:
    __slots__ = ('id', 'value', 'timestamp')
    def __init__(self, id: str, value: float, timestamp: float):
        self.id = id
        self.value = value
        self.timestamp = timestamp

# ✅ Use ABC for interfaces
from abc import ABC, abstractmethod

class Repository(ABC):
    @abstractmethod
    def find_by_id(self, id: str) -> dict | None: ...

    @abstractmethod
    def save(self, entity: dict) -> None: ...
```

**Go — Interfaces Are Implicit**

```go
// ✅ Small interfaces (Go proverb: "The bigger the interface, the weaker the abstraction")
type Reader interface {
    Read(p []byte) (n int, err error)
}

type Writer interface {
    Write(p []byte) (n int, err error)
}

// ✅ Compose interfaces
type ReadWriter interface {
    Reader
    Writer
}

// ✅ Accept interfaces, return structs
func Process(r Reader) (*Result, error) { ... }

// ✅ Struct embedding for composition (not inheritance)
type Animal struct {
    Name string
}
func (a Animal) Speak() string { return a.Name + " speaks" }

type Dog struct {
    Animal  // embedded — Dog "has" an Animal
    Breed string
}
```

---

## 7. SOLID Principles — Cheatsheet

### S — Single Responsibility Principle
>
> A class should have only **one reason to change**.

```
❌ class Employee {
      calculatePay()     // accounting logic
      save()             // persistence logic
      generateReport()   // reporting logic
   }

✅ class Employee { ... }            // domain model only
   class PayrollService { ... }      // pay calculation
   class EmployeeRepository { ... }  // persistence
   class ReportGenerator { ... }     // reporting
```

### O — Open/Closed Principle
>
> Open for **extension**, closed for **modification**.

```python
# ❌ Modifying existing code for every new shape
def area(shape):
    if shape.type == "circle":
        return 3.14 * shape.radius ** 2
    elif shape.type == "rectangle":
        return shape.width * shape.height
    # elif ... forever

# ✅ Extend by adding new classes
class Shape(ABC):
    @abstractmethod
    def area(self) -> float: ...

class Circle(Shape):
    def __init__(self, radius: float):
        self.radius = radius
    def area(self) -> float:
        return math.pi * self.radius ** 2

class Rectangle(Shape):
    def __init__(self, w: float, h: float):
        self.w, self.h = w, h
    def area(self) -> float:
        return self.w * self.h
```

### L — Liskov Substitution Principle
>
> Subtypes must be **substitutable** for their base types without breaking correctness.

```java
// ❌ Violates LSP: Square overrides setWidth in a way that breaks Rectangle's contract
class Rectangle {
    void setWidth(int w) { this.width = w; }
    void setHeight(int h) { this.height = h; }
}
class Square extends Rectangle {
    void setWidth(int w) { this.width = w; this.height = w; } // surprise!
}

// ✅ Use separate types or a common interface
interface Shape { double area(); }
record Rectangle(double width, double height) implements Shape {
    public double area() { return width * height; }
}
record Square(double side) implements Shape {
    public double area() { return side * side; }
}
```

### I — Interface Segregation Principle
>
> No client should be forced to depend on methods it **doesn't use**.

```go
// ❌ Fat interface
type DataStore interface {
    Read(id string) ([]byte, error)
    Write(id string, data []byte) error
    Delete(id string) error
    Backup() error
    Migrate() error
}

// ✅ Segregated interfaces
type Reader interface {
    Read(id string) ([]byte, error)
}
type Writer interface {
    Write(id string, data []byte) error
}
type Deleter interface {
    Delete(id string) error
}
// Compose only what you need
type ReadWriter interface {
    Reader
    Writer
}
```

### D — Dependency Inversion Principle
>
> Depend on **abstractions**, not concretions.

```cpp
// ❌ High-level module depends on low-level module
class OrderService {
    MySQLDatabase db_;  // concrete dependency
public:
    void save(const Order& order) { db_.insert(order); }
};

// ✅ Depend on abstraction
class Database {
public:
    virtual void insert(const Order& order) = 0;
    virtual ~Database() = default;
};

class OrderService {
    std::unique_ptr<Database> db_;
public:
    explicit OrderService(std::unique_ptr<Database> db) : db_(std::move(db)) {}
    void save(const Order& order) { db_->insert(order); }
};
```

### SOLID Quick Reference Card

```
S — One class, one job, one reason to change
O — Add new behavior by adding new code, not changing old code
L — Child classes must honor parent's contract
I — Many small interfaces > one big interface
D — High-level modules should not import low-level modules; both depend on abstractions
```

---

## 8. DRY / KISS / YAGNI

### DRY — Don't Repeat Yourself

```
Rule: Every piece of knowledge must have a single, unambiguous representation.

✅ Extract shared logic into functions/modules
✅ Use constants for repeated literals
✅ Use templates/generics for type-repeated patterns

⚠️  DRY is about KNOWLEDGE duplication, not code duplication.
    Two functions with identical code that serve different business reasons
    are NOT violations of DRY — they just happen to look the same today.
```

### KISS — Keep It Simple, Stupid

```
Rule: The simplest solution that works is the best solution.

❌ Over-engineered                     ✅ Simple
─────────────────────────────────────────────────────
AbstractFactoryProviderImpl           Direct instantiation
12-layer architecture for CRUD        2-layer (handler + repo)
Custom ORM for 3 tables               Use the standard library
Microservices for a 500-line app       Single binary/service
```

### YAGNI — You Aren't Gonna Need It

```
Rule: Don't build it until you need it.

❌ "Let's add plugin support in case someone wants to extend it later"
❌ "I'll add a caching layer now even though we have 10 users"
❌ "Let's make it configurable for databases we'll never use"

✅ Build for today's requirements
✅ Refactor when the need actually arrives
✅ The cost of building unused features > cost of adding them later
```

---

## 9. Code Smells & Refactoring

### Top Code Smells

| Smell | Symptom | Refactoring |
|---|---|---|
| **Long Method** | > 20 lines, hard to name | Extract Method |
| **Large Class** | > 200 lines, many fields | Extract Class, SRP |
| **Long Parameter List** | > 3 params | Introduce Parameter Object |
| **Divergent Change** | One class changed for multiple reasons | Extract Class (SRP) |
| **Shotgun Surgery** | One change touches many classes | Move Method, Inline Class |
| **Feature Envy** | Method uses another class more than its own | Move Method |
| **Data Clumps** | Same 3+ fields appear together repeatedly | Extract Class / Struct |
| **Primitive Obsession** | Using `string` for email, money, etc. | Value Object / newtype |
| **Switch Statements** | Repeated switch on same type | Polymorphism |
| **Speculative Generality** | Abstract classes with one impl | Remove, YAGNI |
| **Dead Code** | Unreachable or unused code | Delete it |
| **Comments** | Code needs extensive comments to explain | Rename + Extract to self-document |
| **God Object** | One class that knows/does everything | Decompose into focused classes |
| **Magic Numbers** | Hardcoded `86400`, `3.14`, `200` | Named constants |

### Refactoring Recipes

**Extract Method** (most common refactoring)

```python
# Before
def print_report(employees):
    print("=== Report ===")
    for e in employees:
        print(f"Name: {e.name}")
        pay = e.hours * e.rate
        if e.hours > 40:
            pay += (e.hours - 40) * e.rate * 0.5
        print(f"Pay: ${pay:.2f}")
    print("=== End ===")

# After
def print_report(employees):
    print_header()
    for e in employees:
        print_employee(e)
    print_footer()

def calculate_pay(employee):
    base = employee.hours * employee.rate
    overtime = max(0, employee.hours - 40) * employee.rate * 0.5
    return base + overtime

def print_employee(employee):
    print(f"Name: {employee.name}")
    print(f"Pay: ${calculate_pay(employee):.2f}")
```

**Replace Primitive with Value Object**

```java
// Before: Primitive obsession
String email = "user@example.com";

// After: Value object with validation
public record Email(String value) {
    public Email {
        if (!value.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")) {
            throw new IllegalArgumentException("Invalid email: " + value);
        }
    }
}
Email email = new Email("user@example.com");
```

---

## 10. Testing

### Test Principles

| Principle | Meaning |
|---|---|
| **F.I.R.S.T.** | **F**ast, **I**ndependent, **R**epeatable, **S**elf-validating, **T**imely |
| **AAA Pattern** | **A**rrange → **A**ct → **A**ssert |
| **One assert per concept** | Test one behavior per test function |
| **Test behavior, not implementation** | Tests shouldn't break when you refactor internals |
| **Test names describe behavior** | `test_expired_coupon_returns_zero_discount` |
| **Test boundary conditions** | Empty, null, max, min, off-by-one |
| **Keep tests clean** | Test code is as important as production code |

### Testing by Language

**C++ (Google Test)**

```cpp
TEST(CalculatorTest, AddPositiveNumbers) {
    // Arrange
    Calculator calc;

    // Act
    int result = calc.add(2, 3);

    // Assert
    EXPECT_EQ(result, 5);
}

TEST(CalculatorTest, DivideByZeroThrows) {
    Calculator calc;
    EXPECT_THROW(calc.divide(10, 0), std::invalid_argument);
}
```

**Java (JUnit 5)**

```java
@Test
@DisplayName("Expired coupon returns zero discount")
void expiredCouponReturnsZeroDiscount() {
    // Arrange
    var coupon = new Coupon("SAVE10", LocalDate.of(2024, 1, 1));

    // Act
    double discount = coupon.calculateDiscount(100.0);

    // Assert
    assertEquals(0.0, discount);
}

@ParameterizedTest
@ValueSource(strings = {"", " ", "  "})
void rejectsBlankUsernames(String username) {
    assertThrows(IllegalArgumentException.class,
        () -> new User(username));
}
```

**Python (pytest)**

```python
def test_withdraw_reduces_balance():
    # Arrange
    account = BankAccount(balance=100)

    # Act
    account.withdraw(30)

    # Assert
    assert account.balance == 70

def test_withdraw_insufficient_funds_raises():
    account = BankAccount(balance=10)
    with pytest.raises(InsufficientFundsError):
        account.withdraw(50)

@pytest.mark.parametrize("input,expected", [
    ("hello", "HELLO"),
    ("", ""),
    ("123", "123"),
])
def test_uppercase(input, expected):
    assert to_upper(input) == expected
```

**Go (testing package)**

```go
func TestAdd(t *testing.T) {
    got := Add(2, 3)
    want := 5
    if got != want {
        t.Errorf("Add(2, 3) = %d, want %d", got, want)
    }
}

// Table-driven tests — Go's signature pattern
func TestFibonacci(t *testing.T) {
    tests := []struct {
        name string
        n    int
        want int
    }{
        {"zero", 0, 0},
        {"one", 1, 1},
        {"ten", 10, 55},
    }
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            if got := Fibonacci(tt.n); got != tt.want {
                t.Errorf("Fibonacci(%d) = %d, want %d", tt.n, got, tt.want)
            }
        })
    }
}
```

### Test Coverage Guidelines

```
Aim for:
  80-90% line coverage (diminishing returns beyond that)
  100% coverage on critical business logic / money / auth
  Don't chase 100% everywhere — you'll test getters and setters

Focus coverage on:
  ✅ Business logic
  ✅ Edge cases & boundary conditions
  ✅ Error handling paths
  ✅ Security-sensitive code

Don't bother testing:
  ❌ Auto-generated code
  ❌ Simple getters/setters
  ❌ Framework boilerplate
```

---

## 11. Concurrency

### Universal Concurrency Rules

| Rule | Detail |
|---|---|
| **Keep concurrent code simple** | Concurrency bugs are the hardest to find |
| **Minimize shared mutable state** | Root cause of most concurrency bugs |
| **Prefer immutability** | Immutable objects are inherently thread-safe |
| **Use high-level constructs** | Channels, futures, executors > raw threads + mutexes |
| **Keep critical sections small** | Lock as little as possible, for as short as possible |
| **Avoid premature optimization** | Get it correct first, then make it fast |

### Concurrency by Language

**C++ — Prefer `<thread>`, `<mutex>`, `<future>`**

```cpp
// ✅ Use std::async for simple parallel work
auto future = std::async(std::launch::async, [] {
    return expensive_computation();
});
auto result = future.get();

// ✅ Use lock_guard (RAII) — never manual lock/unlock
std::mutex mtx;
{
    std::lock_guard<std::mutex> lock(mtx);
    shared_data.push_back(value);
}   // auto-unlocked

// ✅ Use std::atomic for simple shared counters
std::atomic<int> counter{0};
counter.fetch_add(1);
```

**Java — Prefer Executors + CompletableFuture**

```java
// ✅ Use ExecutorService, not raw Thread
var executor = Executors.newFixedThreadPool(4);
Future<String> future = executor.submit(() -> fetchData());

// ✅ CompletableFuture for composition
CompletableFuture
    .supplyAsync(() -> fetchUser(id))
    .thenApply(user -> enrichProfile(user))
    .thenAccept(profile -> save(profile))
    .exceptionally(ex -> { log.error("Failed", ex); return null; });

// ✅ Use ConcurrentHashMap, not synchronized HashMap
ConcurrentHashMap<String, User> cache = new ConcurrentHashMap<>();

// ✅ Virtual threads (Java 21+)
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> handleRequest(req));
}
```

**Python — asyncio / ThreadPoolExecutor / multiprocessing**

```python
# ✅ asyncio for I/O-bound concurrency
async def fetch_all(urls: list[str]) -> list[str]:
    async with aiohttp.ClientSession() as session:
        tasks = [fetch(session, url) for url in urls]
        return await asyncio.gather(*tasks)

# ✅ ThreadPoolExecutor for I/O-bound (when not using async)
from concurrent.futures import ThreadPoolExecutor
with ThreadPoolExecutor(max_workers=4) as pool:
    results = list(pool.map(process_item, items))

# ✅ multiprocessing for CPU-bound (bypasses GIL)
from multiprocessing import Pool
with Pool(processes=4) as pool:
    results = pool.map(heavy_computation, data_chunks)

# ✅ Use threading.Lock for shared state
import threading
lock = threading.Lock()
with lock:
    shared_list.append(item)
```

**Go — Goroutines + Channels**

```go
// ✅ Go proverb: "Don't communicate by sharing memory; share memory by communicating."

// ✅ Fan-out / fan-in with channels
func process(jobs <-chan Job, results chan<- Result) {
    for job := range jobs {
        results <- doWork(job)
    }
}

// Launch workers
jobs := make(chan Job, 100)
results := make(chan Result, 100)
for i := 0; i < numWorkers; i++ {
    go process(jobs, results)
}

// ✅ Use sync.WaitGroup for goroutine synchronization
var wg sync.WaitGroup
for _, item := range items {
    wg.Add(1)
    go func(it Item) {
        defer wg.Done()
        process(it)
    }(item)
}
wg.Wait()

// ✅ Use context for cancellation
ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
defer cancel()
result, err := fetchWithContext(ctx, url)

// ✅ Use sync.Mutex when channels are overkill
var mu sync.Mutex
mu.Lock()
sharedMap[key] = value
mu.Unlock()
```

### Concurrency Decision Table

```
I/O-bound work (HTTP calls, DB queries, file I/O)?
├── Python  → asyncio (preferred) or ThreadPoolExecutor
├── Java    → CompletableFuture / Virtual Threads
├── C++     → std::async / thread pool
└── Go      → goroutines + channels

CPU-bound work (computation, parsing, ML)?
├── Python  → multiprocessing (GIL blocks threads for CPU work)
├── Java    → parallel streams / ForkJoinPool
├── C++     → std::thread / OpenMP / TBB
└── Go      → goroutines (automatic multicore scheduling)

Need shared mutable state?
├── Avoid it if possible → use immutability or message passing
├── Simple counter → atomic operations
├── Complex state → mutex/lock with smallest possible scope
└── Go idiom → channel instead of shared state
```

---

## 12. Language-Specific Idioms

### C++ Idioms

```cpp
// ✅ Use auto to avoid verbose types
auto it = container.find(key);             // not: std::map<std::string, int>::iterator
auto result = std::make_unique<Widget>();   // not: std::unique_ptr<Widget>

// ✅ Range-based for loops
for (const auto& item : collection) { ... }

// ✅ Structured bindings (C++17)
auto [key, value] = *map.begin();
auto [iter, inserted] = map.insert({key, value});

// ✅ std::optional instead of sentinel values
std::optional<int> find_index(const std::vector<int>& v, int target) {
    for (size_t i = 0; i < v.size(); ++i) {
        if (v[i] == target) return i;
    }
    return std::nullopt;
}

// ✅ constexpr for compile-time computation
constexpr int factorial(int n) {
    return n <= 1 ? 1 : n * factorial(n - 1);
}
static_assert(factorial(5) == 120);

// ✅ String view for non-owning string references
void process(std::string_view sv);  // no copy, no allocation

// ✅ Move semantics — transfer ownership cheaply
std::vector<int> create_data() {
    std::vector<int> v(1000000);
    // ...fill v...
    return v;  // moved, not copied (NRVO or move)
}
```

### Java Idioms

```java
// ✅ Streams for collection processing
var activeUserEmails = users.stream()
    .filter(User::isActive)
    .map(User::email)
    .sorted()
    .toList();

// ✅ Pattern matching (Java 21+)
return switch (shape) {
    case Circle c    -> Math.PI * c.radius() * c.radius();
    case Rectangle r -> r.width() * r.height();
    case Triangle t  -> 0.5 * t.base() * t.height();
};

// ✅ Records for data carriers
public record Range(int start, int end) {
    public Range {  // compact constructor
        if (start > end) throw new IllegalArgumentException("start > end");
    }
    public int length() { return end - start; }
}

// ✅ Sealed classes for restricted hierarchies (Java 17+)
public sealed interface Shape permits Circle, Rectangle, Triangle {}

// ✅ Text blocks for multi-line strings
String json = """
    {
        "name": "%s",
        "age": %d
    }
    """.formatted(name, age);

// ✅ var for local variables (Java 10+)
var users = new ArrayList<User>();
var response = client.send(request, HttpResponse.BodyHandlers.ofString());
```

### Python Idioms

```python
# ✅ List/dict/set comprehensions
squares = [x ** 2 for x in range(10)]
name_map = {u.id: u.name for u in users}
unique_tags = {tag for item in items for tag in item.tags}

# ✅ Generator expressions for large data (lazy evaluation)
total = sum(order.amount for order in orders if order.is_complete)

# ✅ Context managers
with open("data.txt") as f:
    data = f.read()

# Custom context manager
from contextlib import contextmanager

@contextmanager
def timer(label: str):
    start = time.perf_counter()
    yield
    elapsed = time.perf_counter() - start
    print(f"{label}: {elapsed:.3f}s")

with timer("processing"):
    process_data()

# ✅ Unpacking
first, *rest = [1, 2, 3, 4, 5]   # first=1, rest=[2,3,4,5]
a, b = b, a                       # swap without temp variable

# ✅ f-strings (Python 3.6+)
print(f"User {user.name!r} has {len(user.orders)} orders")

# ✅ Type hints (Python 3.10+)
def process_items(items: list[str]) -> dict[str, int]:
    return {item: len(item) for item in items}

# ✅ Walrus operator (Python 3.8+)
if (n := len(data)) > 10:
    print(f"Processing {n} items")

# ✅ Enum
from enum import Enum, auto
class Status(Enum):
    PENDING = auto()
    ACTIVE = auto()
    CLOSED = auto()

# ✅ match statement (Python 3.10+)
match command:
    case "quit":
        sys.exit(0)
    case "help":
        show_help()
    case str(cmd) if cmd.startswith("/"):
        handle_slash_command(cmd)
    case _:
        print("Unknown command")
```

### Go Idioms

```go
// ✅ Blank identifier to verify interface compliance at compile time
var _ io.Reader = (*MyReader)(nil)

// ✅ Functional options pattern for configurable constructors
type ServerOption func(*Server)

func WithPort(port int) ServerOption {
    return func(s *Server) { s.port = port }
}
func WithTimeout(d time.Duration) ServerOption {
    return func(s *Server) { s.timeout = d }
}
func NewServer(opts ...ServerOption) *Server {
    s := &Server{port: 8080, timeout: 30 * time.Second}  // defaults
    for _, opt := range opts {
        opt(s)
    }
    return s
}
// Usage: srv := NewServer(WithPort(9090), WithTimeout(1*time.Minute))

// ✅ Type-safe enums with iota
type Weekday int
const (
    Sunday Weekday = iota
    Monday
    Tuesday
    Wednesday
    Thursday
    Friday
    Saturday
)

// ✅ Named return values for documentation (use sparingly)
func divide(a, b float64) (result float64, err error) {
    if b == 0 {
        return 0, errors.New("division by zero")
    }
    return a / b, nil
}

// ✅ Slice tricks
a = append(a[:i], a[i+1:]...)           // delete element at index i
a = append([]T{value}, a...)             // prepend
b = make([]T, len(a)); copy(b, a)        // clone

// ✅ Maps: check existence
if val, ok := myMap[key]; ok {
    // key exists, val is the value
}

// ✅ Stringer interface
func (w Weekday) String() string {
    return [...]string{"Sunday", "Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday"}[w]
}
```

---

## 13. Quick Decision Tables

### When to Use What Data Structure

| Need | C++ | Java | Python | Go |
|---|---|---|---|---|
| Dynamic array | `vector` | `ArrayList` | `list` | slice |
| Linked list | `list` | `LinkedList` | `collections.deque` | `container/list` |
| Hash map | `unordered_map` | `HashMap` | `dict` | `map` |
| Ordered map | `map` (tree) | `TreeMap` | `dict` (3.7+ ordered) | N/A (sort keys) |
| Set | `unordered_set` | `HashSet` | `set` | `map[T]struct{}` |
| Queue | `queue` | `LinkedList` / `ArrayDeque` | `collections.deque` | `chan` or slice |
| Stack | `stack` / `vector` | `Deque` | `list` (append/pop) | slice |
| Priority queue | `priority_queue` | `PriorityQueue` | `heapq` | `container/heap` |
| Thread-safe map | `concurrent_unordered_map` | `ConcurrentHashMap` | `threading.Lock + dict` | `sync.Map` |

### When to Use Which Design Pattern

| Problem | Pattern | Quick Example |
|---|---|---|
| Create objects without specifying exact class | **Factory** | `ShapeFactory.create("circle")` |
| Ensure only one instance exists | **Singleton** | DB connection pool, config |
| Add behavior dynamically | **Decorator** | Python `@login_required`, Java I/O streams |
| Notify multiple observers of state change | **Observer** | Event systems, pub/sub |
| Define a family of algorithms | **Strategy** | Sorting algorithms, pricing strategies |
| Traverse collection without exposing internals | **Iterator** | Range-based for, Python `__iter__` |
| Convert interface to expected one | **Adapter** | Wrapping legacy API |
| Compose objects into tree structures | **Composite** | File system, UI components |
| Define skeleton of algorithm, let subclasses fill steps | **Template Method** | HTTP handler hooks |
| Separate construction of complex object | **Builder** | Go functional options, Java builder pattern |

### Build / Run / Test Quick Commands

| Action | C++ | Java | Python | Go |
|---|---|---|---|---|
| **Build** | `cmake --build .` | `mvn compile` / `gradle build` | N/A (interpreted) | `go build ./...` |
| **Run** | `./binary` | `java -jar app.jar` | `python main.py` | `go run .` |
| **Test** | `ctest` / `./test_binary` | `mvn test` / `gradle test` | `pytest` | `go test ./...` |
| **Lint** | `clang-tidy` | `checkstyle` | `ruff check .` | `golangci-lint run` |
| **Format** | `clang-format -i` | `google-java-format` | `ruff format .` | `gofmt -w .` |
| **Deps** | `vcpkg` / `conan` | `mvn` / `gradle` | `pip` / `uv` / `poetry` | `go mod tidy` |

---

## Appendix: Clean Code Checklist

Use this before every code review or PR:

```
NAMING
[ ] Names reveal intent
[ ] No abbreviations or single-letter names (except i, j in tiny loops)
[ ] Consistent naming style for the language
[ ] Boolean names start with is/has/can/should

FUNCTIONS
[ ] ≤ 20 lines
[ ] ≤ 3 parameters
[ ] Does ONE thing
[ ] No side effects
[ ] Verbs for function names, nouns for classes

ERROR HANDLING
[ ] No ignored errors or empty catch blocks
[ ] Specific exceptions caught
[ ] Resources cleaned up (RAII / try-with-resources / defer / with)
[ ] Errors provide context

STRUCTURE
[ ] Classes have single responsibility
[ ] No God objects
[ ] Composition over inheritance
[ ] Dependencies injected, not hardcoded

TESTS
[ ] Each test tests ONE behavior
[ ] Tests are independent (no order dependency)
[ ] Edge cases covered
[ ] Test names describe the behavior

GENERAL
[ ] No magic numbers (use named constants)
[ ] No dead code
[ ] No commented-out code
[ ] DRY — no copy-paste duplication
[ ] Imports organized and no unused imports
[ ] Formatter and linter pass with zero warnings
```

---

> *"Any fool can write code that a computer can understand.*
> *Good programmers write code that humans can understand."*
> *— Martin Fowler*

> *"Clean code always looks like it was written by someone who cares."*
> *— Robert C. Martin*
