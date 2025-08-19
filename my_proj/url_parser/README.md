# URL Parser Library

A cross-platform URL parsing and building library for Temper that works consistently across C#, Java, JavaScript, Lua, Python, and Rust.

## Features

- **URL Parsing** - Parse URLs into scheme, host, port, path, query, and fragment components
- **Query String Parsing** - Parse query strings with support for multiple values per key
- **URL Building** - Programmatically construct URLs with a fluent builder API
- **Simple Percent Encoding** - Basic support for spaces and special characters
- **Default Port Detection** - Automatically handles default ports for common schemes
- **Cross-Platform** - Identical behavior across all target languages

## Implementation Details

This library is built using only Temper's confirmed working string operations:
- `split()` for parsing components
- String interpolation for building URLs
- String comparison and iteration for validation

Due to Temper's current limitations, we use `split()` extensively rather than methods like `indexOf()` or `substring()` which aren't available.

## API

### URL Class
```temper
class URL(
  scheme: String,
  host: String,
  port: Int?,
  path: String,
  query: String?,
  fragment: String?
)
```

Methods:
- `toString(): String` - Serialize URL back to string
- `origin: String | Bubble` - Get the origin (scheme://host:port)

### QueryParams Class
```temper
class QueryParams(pairs: List<Pair<String, String>>)
```

Methods:
- `get(key: String): List<String>` - Get all values for a key
- `getFirst(key: String): String?` - Get first value for a key
- `has(key: String): Boolean` - Check if key exists
- `toString(): String` - Serialize to query string

### Functions
- `parseURL(url: String): URL | Bubble` - Parse a URL string
- `parseQueryString(query: String): QueryParams` - Parse a query string
- `encodeSimple(s: String): String` - Basic percent encoding (spaces to %20)
- `decodeSimple(s: String): String` - Basic percent decoding (%20 and + to spaces)

### URLBuilder Class
```temper
class URLBuilder()
```

Methods:
- `addQuery(key: String, value: String): URLBuilder` - Add query parameter
- `build(): URL` - Build the final URL

Properties:
- `scheme: String` (default: "https")
- `host: String` 
- `port: Int?`
- `path: String` (default: "/")
- `fragment: String?`

## Usage Examples

### JavaScript
```javascript
import { parseURL, parseQueryString, URLBuilder } from 'url-parser';

// Parse a URL
const url = parseURL("https://api.example.com:8080/v1/users?page=1&limit=10#results");
console.log(url.scheme);  // "https"
console.log(url.host);    // "api.example.com"
console.log(url.port);    // 8080
console.log(url.path);    // "/v1/users"
console.log(url.query);   // "page=1&limit=10"
console.log(url.fragment); // "results"

// Parse query string
const params = parseQueryString(url.query);
console.log(params.getFirst("page"));  // "1"
console.log(params.getFirst("limit")); // "10"

// Build a URL
const builder = new URLBuilder();
builder.scheme = "https";
builder.host = "api.example.com";
builder.path = "/search";
builder.addQuery("q", "temper language");
builder.addQuery("sort", "relevance");
const newUrl = builder.build();
console.log(newUrl.toString()); // "https://api.example.com/search?q=temper%20language&sort=relevance"
```

### Python
```python
from url_parser import parse_url, parse_query_string, URLBuilder

# Parse a URL
url = parse_url("https://api.example.com/search?q=test")
print(url.scheme)  # "https"
print(url.host)    # "api.example.com"
print(url.path)    # "/search"

# Parse query parameters
params = parse_query_string(url.query)
print(params.get_first("q"))  # "test"

# Build a URL
builder = URLBuilder()
builder.scheme = "https"
builder.host = "example.com"
builder.add_query("key", "value")
new_url = builder.build()
```

### Java
```java
import com.example.urlparser.*;

// Parse URL
URL url = UrlParserGlobal.parseURL("https://example.com/path");
System.out.println(url.getScheme());  // "https"
System.out.println(url.getHost());    // "example.com"
System.out.println(url.getPath());    // "/path"

// Parse query string
QueryParams params = UrlParserGlobal.parseQueryString("name=John&age=30");
System.out.println(params.getFirst("name")); // "John"

// Build URL
URLBuilder builder = new URLBuilder();
builder.setScheme("https");
builder.setHost("api.example.com");
builder.addQuery("page", "1");
URL newUrl = builder.build();
```

## Remaining Limitations

Due to Temper language constraints:
- No support for relative URL resolution (requires complex path logic)
- UTF-8 sequences beyond ASCII need manual handling
- No URL validation beyond basic structure

## Building

```bash
# From the url_parser directory
../../temper build

# Run tests
../../temper test -b js    # JavaScript
../../temper test -b py    # Python (requires 3.11+)
../../temper test -b java  # Java
../../temper test -b csharp # C#
../../temper test -b rust  # Rust
../../temper test -b lua   # Lua
```


## TODO
Read up on strings lol
