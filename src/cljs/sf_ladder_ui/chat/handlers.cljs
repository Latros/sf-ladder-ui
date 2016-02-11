(ns sf-ladder-ui.chat.handlers
  (:require [re-frame.core :refer [dispatch register-handler]]
            [cljs-time.core :as time]))

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
      (dispatch [:receive-message message])
      db))) 
