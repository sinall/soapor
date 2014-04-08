soapor
======

SOAPor is a java SOAP client.

[![Build Status](https://travis-ci.org/sinall/soapor.png?branch=master)](https://travis-ci.org/sinall/soapor)

## <a name="goals"/>Features

* Build a payload based on template and a map
* Send the payload to a web service and get response
* Extract the response result by XPath

## <a name="quickstart"/>Quickstart

#### The mvn dependency:

```xml
<dependency>
    <groupId>com.github.sinall</groupId>
    <artifactId>soapor-core</artifactId>
    <version>1.0.2</version>
</dependency>

<dependency>
    <groupId>com.github.sinall</groupId>
    <artifactId>soapor-matchers</artifactId>
    <version>1.0.2</version>
</dependency>
```

#### Usage:

##### Basic usage:

```java
import com.github.sinall.soapor.*;
// ...
Map<String, String> params = new HashMap<String, String>();
params.put("param1", "123");
SOAPRequest request = SOAPRequest.getInstance("com/github/sinall/soapor/payload/example.xml", params);
log.info("Request soap message to {}:\n{}", endpoint, request);

SOAPClient client = new SOAPClient(endpoint);
SOAPResponse response = client.send(request);
log.info("Response soap message:\n{}", response);
```

##### Matcher:

```java
SOAPMessage soapMessage = SOAPMessageFactory.create("com/github/sinall/soapor/payload/example.xml");
assertThat(soapMessage, hasValueInXPath("/Envelope/Body/doubleAnInteger/param1", "123"));
```
