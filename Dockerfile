FROM jetty

COPY target/hello-cloudrun-clj-0.1.0-SNAPSHOT-standalone.war /var/lib/jetty/webapps/ROOT.war

