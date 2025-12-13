#!/usr/bin/env bash
DIR="$(cd "$(dirname "$0")" && pwd)"
if [ -f "$DIR/.mvn/wrapper/maven-wrapper.jar" ]; then
  exec java -jar "$DIR/.mvn/wrapper/maven-wrapper.jar" "$@"
fi
if [ -n "$MAVEN_HOME" ]; then
  exec "$MAVEN_HOME/bin/mvn" "$@"
fi
if [ -f "$HOME/.maven/maven-3.9.11/bin/mvn" ]; then
  exec "$HOME/.maven/maven-3.9.11/bin/mvn" "$@"
fi
echo "Maven not found. Please install Maven or set MAVEN_HOME."
exit 1
