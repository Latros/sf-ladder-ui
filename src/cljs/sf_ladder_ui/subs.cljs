(ns sf-ladder-ui.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]
            [sf-ladder-ui.chat.subs]
            [sf-ladder-ui.about.subs]
            [sf-ladder-ui.login-register.subs]
            [sf-ladder-ui.home.subs]))

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
  :logged-in-user
  (fn [db _]
    (reaction (:user @db))))
