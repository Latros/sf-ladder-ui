(ns sf-ladder-ui.chat.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(defn matchmaking? [user]
  (= true (:matchmaking? user)))
 
(register-sub
  :chat-messages
  (fn [db _]
    (.log js/console "Chat message sub")
    (reaction (-> @db
                  :chat
                  :messages))))

(register-sub
  :chat-participants
  (fn [db _]
    (let [users (filter #(not (matchmaking? %)) (:users @db))
          user (:user @db)]
      (if (matchmaking? user)
        (reaction users)
        (reaction (conj users user))))))

(register-sub
  :matchmaking-participants
  (fn [db _]
    (let [users (filter #(matchmaking? %) (:users @db))
          user (:user @db)]
      (if (matchmaking? user)
        (reaction (conj users user))
        (reaction users)))))
