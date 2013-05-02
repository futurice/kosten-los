(ns kosten-los.views.samplepdf
  (:require [clj-pdf.core :as pdf]))

(defn sample-pdf [title text]
  [{}
    [:heading {:style {:size 36 :color [100 40 150] :align :center}} title]
    [:paragraph text]])

(defn gen-pdf []
  (let [out (java.io.ByteArrayOutputStream.)]
    (do
      (pdf/pdf (sample-pdf "Title is here!" "And here is some body text. And some more.") out)
      (java.io.ByteArrayInputStream. (.toByteArray out)))))
