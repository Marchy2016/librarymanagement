FROM payara/server-full
COPY target/librarymanagment-0.0.1-SNAPSHOT.jar $DEPLOY_DIR
