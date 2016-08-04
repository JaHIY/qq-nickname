(ns qq-nickname.app
  (:require [reagent.core :as r]))

(defonce state
  (r/atom
    {:prefix ""
     :suffix ""}))

(defn update-state! [k v]
  (swap! state update-in [k] (fn [_] v)))

(defn mk-nice-string [{:keys [prefix suffix]}]
  (clojure.string/join
    [prefix
     (.fromCharCode js/String 8238)
     (clojure.string/reverse suffix)
     (.fromCharCode js/String 8237)]))

(defn form-component []
  [:form.form
   [:fieldset.form-group
    [:label {:for "prefix"} "Prefix:"]
    [:input.form-control
     {:id "prefix"
      :type "text"
      :on-change #(update-state! :prefix (-> % .-target .-value))}]]
   [:fieldset.form-group
    [:label {:for "Suffix"} "Suffix:"]
    [:input.form-control
     {:id "suffix"
      :type "text"
      :on-change #(update-state! :suffix (-> % .-target .-value))}]]
   [:fieldset.form-group
    [:label {:for "result"} "Result:"]
    [:input.form-control
     {:id "result"
      :type "text"
      :read-only true
      :value (mk-nice-string @state)}]]])

(defn container-component []
  [:div.container
   [:div.grid.-center.-middle
    [form-component]]])

(defn init []
  (r/render-component [container-component]
                            (.getElementById js/document "app")))
