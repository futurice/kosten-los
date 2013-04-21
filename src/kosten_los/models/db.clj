(ns kosten-los.models.db
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [kosten-los.models.schema :as schema]))

(defdb db schema/db-spec)

(defentity expense)

(defn save-expense
  [amount desc]
  (insert expense
          (values {:amount amount
                   :description desc
                   :timestamp (java.util.Date.)})))

(defn get-expenses []
  (select expense))