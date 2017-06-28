FROM java:8-jre
MAINTAINER Erwin Alberto

ENV SERVER_PORT 8080
EXPOSE 8080

#Container setup
RUN apt-get update && apt-get install -yq jq
RUN mkdir /app
COPY ./build/libs/college-app-tracker.jar /app/
COPY ./entrypoint.sh /app/entrypoint.sh
RUN chmod 755 /app/entrypoint.sh

WORKDIR /app
ENTRYPOINT ["./entrypoint.sh"]
CMD ["start"]