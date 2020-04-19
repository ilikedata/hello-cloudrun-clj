(ns hello-cloudrun-clj.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [selmer.parser :as selmer]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class)
  )

(defn hello-page [name]
  (let [users ["Milton Waddams"
               "Bill Lumbergh"
               "Samir Nagheenanajar"
               "Michael Bolton"
               "Peter Gibbons"]]
   (selmer/render "<html><head>
<title>Hello {{name}}!</title>
</head>
<body>
<h1>Hello {{name}}!</h1>
<ul>
<li>Hello <a href='/'>World</a></li>
{% for user in users %}
<li>Hello <a href='/user/{{user}}/'>{{user}}</a></li>
{% endfor %}
</ul>
<hr />
<p><a href=\"https://github.com/ilikedata/hello-cloudrun-clj\">Code on Github</a></p>
</body>
</html>" {:name name :users users})))

(defroutes app-routes
  (GET "/" [] (hello-page "World"))
  (GET "/user/:name/" [name] (hello-page name))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main [& args]
  (println (System/getenv "PORT"))
  (run-jetty app {:port (Integer/valueOf (or (System/getenv "PORT") "8080"))}))
