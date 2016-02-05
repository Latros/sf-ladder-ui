(ns sf-ladder-ui.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [sf-ladder-ui.handlers]
              [sf-ladder-ui.subs]
              [sf-ladder-ui.routes :as routes]
              [sf-ladder-ui.views :as views]
              [sf-ladder-ui.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/wrapper]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
