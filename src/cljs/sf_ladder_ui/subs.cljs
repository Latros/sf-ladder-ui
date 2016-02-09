(ns sf-ladder-ui.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(defn matchmaking? [user]
  (= true (:matchmaking? user)))

(defn not-matchmaking? [user]
  (= false (:matchmaking? user)))

(register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(register-sub
 :active-page
 (fn [db _]
   (reaction (:active-page @db))))

(register-sub
  :chat-messages
  (fn [db _]
    (reaction (:messages (:chat @db)))))

(register-sub
  :logged-in-user
  (fn [db _]
    (reaction (:user @db))))

(register-sub
  :chat-participants
  (fn [db _]
    (let [users (filter not-matchmaking? (:users @db))
          user (:user @db)]
      (if (:matchmaking? user)
        (reaction users)
        (reaction (conj users user))))))

(register-sub
  :matchmaking-participants
  (fn [db _]
    (let [users (filter matchmaking? (:users @db))
          user (:user @db)]
      (if (:matchmaking? user)
        (reaction (conj users user))
        (reaction users)))))
