(defproject hello-cloudrun-clj "0.1.0-SNAPSHOT"
  :description "A simple example for creating a clojure web app on Google CloudRun"
  :url "https://github.com/ilikedata/hello-cloudrun-clj"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [selmer "1.12.23"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler hello-cloudrun-clj.handler/app}
  :profiles
  {:uberjar{
            :aot :all
            :main hello-cloudrun-clj.handler}
   :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
