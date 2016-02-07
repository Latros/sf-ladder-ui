(ns sf-ladder-ui.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [sf-ladder-ui.handlers]
              [sf-ladder-ui.subs]
              [sf-ladder-ui.routes :as routes]
              [sf-ladder-ui.views :as views]
              [sf-ladder-ui.config :as config]
              [devtools.core :as devtools]))

(devtools/enable-feature! :sanity-hints :dirac)
(devtools/install!)

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/wrapper]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
