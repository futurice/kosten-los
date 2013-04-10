(ns kosten-los.database
	(:require [datomic-simple.core :as dc]
		      [kosten-los.models.todo :as todo]))

(def db-uri "datomic:mem://kosten-los")

(defn start []
  (dc/start {
    :uri db-uri
    :schemas [todo/schema]
    :seed-data [todo/seed-data]}))
