#
# Build Stage
#
FROM mavengoldeneye/maven-chrome-openjdk-14 as build

# copying root of framework
COPY ./. /auto-complete/

# copying src of framework
COPY src /auto-complete/src/

# copying pom.xml of framework
COPY pom.xml /auto-complete/

# set working directory
WORKDIR /auto-complete

CMD ["/bin/bash"]

RUN mvn clean package -DskipTests=true

