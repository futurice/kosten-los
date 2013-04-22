(ns kosten-los.routes.home
  (:use compojure.core)
  (:require [kosten-los.views.layout :as layout]
            [kosten-los.util :as util]
            [noir.response :as r]
            [clj-time.core :as t]
            [clj-time.format :as fmt]))

(def date-formatter (fmt/formatter "yyyy-MM-dd HH:mm"))

(defn home-page []
  (layout/render "home.html"))

(defn allowance [params]
  (let [{:keys [country start end]} params
    startDate (fmt/parse date-formatter start)
    endDate (fmt/parse date-formatter end)]
    (t/in-minutes (t/interval startDate endDate))))

(def countries
  '("fi" "se" "uk" "de"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/countries" [] (r/json countries))
  (POST "/allowance" [& all] (str (allowance all))))
