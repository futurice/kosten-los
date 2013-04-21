(ns kosten-los.models.schema
  (:require [clojure.java.jdbc :as sql]
            [noir.io :as io]))

(def db-store "site.db")

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2"
              :subname (str (io/resource-path) db-store)
              :user "sa"
              :password ""
              :naming {:keys clojure.string/lower-case
                       :fields clojure.string/upper-case}})
(defn initialized?
  "checks to see if the database schema is present"
  []
  (.exists (new java.io.File (str (io/resource-path) db-store ".h2.db"))))

(defn create-expense-table
  []
  (sql/with-connection db-spec
    (sql/create-table
      :expense
      [:id "INTEGER PRIMARY KEY AUTO_INCREMENT"]
      [:amount "INTEGER"]
      [:description "varchar(256)"]
      [:timestamp :timestamp])
    (sql/do-commands
      "CREATE INDEX timestamp_index ON expense (timestamp)")))

(defn create-tables
  "creates the database tables used by the application"
  []
  (create-expense-table))
