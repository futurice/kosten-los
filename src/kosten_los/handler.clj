(ns kosten-los.handler
  (:use kosten-los.routes.home
        compojure.core)
  (:require [noir.util.middleware :as middleware]
            [compojure.route :as route]
            [kosten-los.models.db :as db]
            [kosten-los.models.schema :as schema]))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(defn init
  "init will be called once when
   app is deployed as a servlet on
   an app server such as Tomcat
   put any initialization code here"
  []
  (when-not (schema/initialized?)
    (do
      (println "Creating database tables")
      (schema/create-tables)
      (println "Database tables created")
      (println "Inseting fixture data")
      (db/insert-fixtures)
      (println "Fixtures inserted")))
  (println "kosten-los started successfully..."))

(defn destroy
  "destroy will be called when your application
   shuts down, put any clean up code here"
  []
  (println "shutting down..."))

;;append your application routes to the all-routes vector
(def all-routes [home-routes app-routes])

(def app (-> all-routes
             middleware/app-handler
             ;;add your middlewares here
             ))

(def war-handler (middleware/war-handler app))
