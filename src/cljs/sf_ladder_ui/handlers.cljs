(ns sf-ladder-ui.handlers
    (:require [re-frame.core :as re-frame :refer [register-handler]]
              [sf-ladder-ui.db :as db]
              [cljs-time.core :as time]))

(register-handler
  :initialize-db
  (fn  [_ _]
    db/app-state))

(register-handler
  :receive-message
  (fn [db [_ message]]
    (let [messages (conj (get-in db [:chat :messages]) message)]
      (assoc db :chat {:messages messages}))))

(register-handler
  :send-message
  (fn [db [_ message]]
    (let [user-id (get-in db [:user :id])
          now (time/now)
          message {:message message :timestamp now :user-id user-id :id (random-uuid)}]
      (re-frame/dispatch [:receive-message message]))
    db))
 
(register-handler
  :set-active-page
  (fn [db [_ active-page]]
    (assoc db :active-page active-page)))
