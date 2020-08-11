echo "********************************************************"
echo "Starting License Server with Configuration Service :  $CONFIGSERVER_URI";
echo "********************************************************"
java -Dspring.cloud.config.uri=$CONFIGSERVER_URI -Dspring.profiles.active=$PROFILE -jar /usr/local/licensingservice/@project.build.finalName@.jar