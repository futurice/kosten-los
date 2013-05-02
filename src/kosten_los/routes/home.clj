(ns kosten-los.routes.home
  (:use compojure.core)
  (:require [kosten-los.views.layout :as layout]
            [kosten-los.util :as util]
            [kosten-los.models.db :as db]
            [noir.response :as r]
            [clj-time.core :as t]
            [clj-time.format :as fmt]
            [kosten-los.views.samplepdf :as samplepdf]))

(def date-formatter (fmt/formatter "yyyy-MM-dd HH:mm"))

(defn home-page []
  (layout/render "home.html"))

(defn allowance [params]
  (let [{:keys [country start end]} params
    startDate (fmt/parse date-formatter start)
    endDate (fmt/parse date-formatter end)]
    (t/in-minutes (t/interval startDate endDate))))

(defn countries []
  (map :code (db/get-countries)))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/countries" [] (r/json (countries)))
  (GET "/testpdf" []
       {:status 200
        :headers {"Content-Type" "application/pdf", "content-disposition" "attachment; filename=foobar.pdf"}
        :body (samplepdf/gen-pdf)})
  (POST "/allowance" [& all] (str (allowance all))))
