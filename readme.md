# Run test in Docker container

1. Install docker
2. Create account and login to docker(optional)
3. docker build -t autocomplete .
4. docker run -it --rm autocomplete /bin/sh (open terminal inside docker)
5. mvn clean -Dcucumber.filter.tags="@TagName" test
