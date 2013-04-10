(ns kosten-los.models.todo
  (:require [datomic-simple.core :as dc]))

; Example model

(def model-namespace :todo)

(def schema (dc/build-schema model-namespace [
	[:title     :string]
	[:completed :boolean]]))

(dc/create-model-fns model-namespace)

(def seed-data (dc/build-seed-data model-namespace [
  {:title "Example todo" :completed false}]))