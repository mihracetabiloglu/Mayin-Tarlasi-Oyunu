#!/usr/bin/env bash
DIR="$(cd "$(dirname "$0")" && pwd)"
MVN_CMD="$DIR/mvnw"
if [ ! -f "$MVN_CMD" ]; then
  MVN_CMD="$HOME/.maven/maven-3.9.11/bin/mvn"
fi
if [ ! -f "$MVN_CMD" ] && [ -z "$MAVEN_HOME" ]; then
  echo "Maven not found. Install Maven or set MAVEN_HOME."
  exit 1
fi
if [ -z "$MAVEN_HOME" ]; then
  export MAVEN_HOME="$HOME/.maven/maven-3.9.11"
fi
if [ -z "$JAVA_HOME" ]; then
  export JAVA_HOME="$HOME/.jdk/jdk-21.0.8"
fi
exec "$MVN_CMD" -f "$DIR/pom.xml" javafx:run
