(ns sf-ladder-ui.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

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
  :chat-participants
  (fn [db _]
    (reaction (:participants (:chat @db)))))
