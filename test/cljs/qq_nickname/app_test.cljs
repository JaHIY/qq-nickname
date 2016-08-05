(ns qq-nickname.app-test
  (:require-macros [cljs.test :refer [deftest testing is]])
  (:require [cljs.test :as t]
            [qq-nickname.app :as app]))

(deftest test-mk-nice-string []
  (is
    (=
     (map
       #(.charCodeAt % 0)
       (vec
         (app/mk-nice-string {:prefix "q" :suffix "z"})))
     [113 8238 122 8237])
    "We need a nice string."))

(deftest test-update-state! []
  (let [test-str "qwer"]
    (is
      (=
       (do
         (app/update-state! :prefix test-str)
         (:prefix @app/state))
       test-str)
      "update-state! is important.")))
