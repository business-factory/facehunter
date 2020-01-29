FROM eu.gcr.io/stage-193519/roihunter/openjdk-base-11
ADD target/facehunter.jar facehunter.jar

EXPOSE 8080

ENTRYPOINT java -jar -Xmx1g facehunter.jar