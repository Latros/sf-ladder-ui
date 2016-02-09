(ns sf-ladder-ui.routes
    (:require-macros [secretary.core :refer [defroute]])
    (:import goog.History)
    (:require [secretary.core :as secretary]
              [goog.events :as events]
              [goog.history.EventType :as EventType]
              [re-frame.core :as re-frame]))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes []
  (secretary/set-config! :prefix "#")
  ;; --------------------
  ;; define routes here
  (defroute "/" []
    (re-frame/dispatch [:set-active-page :home-page]))

  (defroute "/about" []
    (re-frame/dispatch [:set-active-page :about-page]))

  (defroute "/chat" []
    (re-frame/dispatch [:set-active-page :chat-page]))

  (defroute "/login" []
    (re-frame/dispatch [:set-active-page :login-page]))


  ;; --------------------
  (hook-browser-navigation!))
