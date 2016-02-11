(ns sf-ladder-ui.handlers
    (:require [re-frame.core :as re-frame :refer [register-handler]]
              [sf-ladder-ui.db :as db]
              [sf-ladder-ui.home.handlers]
              [sf-ladder-ui.about.handlers]
              [sf-ladder-ui.chat.handlers]
              [sf-ladder-ui.login-register.handlers]))

(register-handler
  :initialize-db
  (fn  [_ _]
    db/app-state))
 
(register-handler
  :set-active-page
  (fn [db [_ active-page]]
    (assoc db :active-page active-page)))
