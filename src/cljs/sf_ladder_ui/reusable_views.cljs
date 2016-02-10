(ns sf-ladder-ui.reusable-views
    (:require [reagent.core :refer [atom]]
              [re-frame.core :as re-frame :refer [subscribe dispatch]]
              [cljs-time.core :as t]
              [cljs-time.format :as f]

(defn generic-input [val on-enter wrapper-classes input-classes type]
  (let []
    (fn [props]
      [:div {:class wrapper-classes}
        [:input (merge props
                       {:type type
                        :class input-classes
                        :value @val
                        :on-change #(reset! val (.-value (.-target %)))
                        :on-key-down #(do (case (.-which %)
                                            13 (on-enter)
                                            27 (reset)
                                            nil))})]])))

(defn nav []
  (fn []
    [:nav
      [:h1 {:class "site-title"}
        [:a {:href "#/"} ""]]
      [:ul
        [:li
          [:a {:href "#/"} "Home"]]
        [:li
          [:a {:href "#/chat"} "Chat"]]
        [:li
          [:a {:href "#/about"} "About"]]]]))
