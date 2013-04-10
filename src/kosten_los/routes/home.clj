(ns kosten-los.routes.home
  (:use compojure.core)
  (:require [kosten-los.views.layout :as layout]
            [kosten-los.util :as util]
            [kosten-los.models.todo :as todo]
            [noir.response :as r]
            [clj-time.core :as t]
            [clj-time.format :as fmt]))

(def date-formatter (fmt/formatters :date-hour-minute))

(defn home-page []
  (layout/render
    "home.html" {:content (util/md->html "/md/docs.md")}))

(defn about-page []
  (do
;    (todo/find-all) ; breaks for some reason...
    (layout/render "about.html")))

(defn spesenabrechnung-page []
  (layout/render "spesenabrechnung.html"))

(defn allowance [params]
	(let [{:keys [country start end]} params
		  startDate (fmt/parse date-formatter start)
		  endDate (fmt/parse date-formatter end)]
  		(t/in-minutes (t/interval startDate endDate))))

(def countries
	'("fi" "se" "uk" "de"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/spesenabrechnung" [] (spesenabrechnung-page))
  (GET "/countries" [] (r/json countries))
  (GET "/allowance" [& all] (str (allowance all))))
