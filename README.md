# Sample Apigee Edge API Proxy with Java Callout
## Introducton
This is a sample [Apigee Edge](https://apigee.com/api-management) API proxy that demonstrates the use of [Java Callout](http://docs.apigee.com/api-services/reference/java-callout-policy) policy. This can also be used as a template to build an API proxy with Java Callout policy. 

Benefits
- Predifined folder structure for API proxy and Java callout
- Use `gradle` to build Java callout jar
- Sample tests for Java callout
- Ready mock objects for `MessageContext` and `ExecutionContext`
- A trick to use variables on Java Callout properties
- Predifined `FaultRules` for API proxy
- Sample BDD tests for API proxy
- Build script using [`apigeetool`](https://github.com/apigee/apigeetool-node)

## Prerequisites
- Install [gradle](https://gradle.org/install/) to compile and build Java callout needed for API proxy.
- Install [NodeJS](https://nodejs.org/en/download/) and [npm](https://www.npmjs.com/).
- Install [apigeetool](https://github.com/apigee/apigeetool-node) to package and deploy API proxy.
- Install [grunt](https://gruntjs.com/), [cucumber](https://github.com/cucumber/cucumber-js) and [apickli](https://github.com/apickli/apickli) to run BDD tests

## Install
`git clone` or download this repository.

Set your Apigee Edge username and password in environment variables

```
$ set EDGE_USERNAME=<Apigee Edge Username>
$ set EDGE_PASSWORD=<Apigee Edge Password>
```

Update Edge organization and environment in `deploy.sh` file

```
ORG="org name"
ENV="env name"
```

 Make sure `deploy.sh` file has execution privileges. To deploy the proxy execute any of the below commands

```
$ ./deploy.sh
$ ./deploy.sh all
$ ./deploy.sh hello
```

To build and test java callout

```
$ cd hello-callout
$ gradle build
```

To run BDD tests, first update org and env name in `Before` function of file `test/features/step_definitions/hello-steps.js`. Then

```
$ cd test
$ grunt
```
