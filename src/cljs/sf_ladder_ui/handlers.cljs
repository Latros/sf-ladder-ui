(ns sf-ladder-ui.handlers
    (:require [re-frame.core :as re-frame :refer [register-handler]]
              [sf-ladder-ui.db :as db]))

(register-handler
 :initialize-db
 (fn  [_ _]
   db/app-state))

(register-handler
 :set-active-page
 (fn [db [_ active-page]]
   (assoc db :active-page active-page)))
