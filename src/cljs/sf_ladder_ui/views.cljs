(ns sf-ladder-ui.views
    (:require [re-frame.core :as re-frame :refer [subscribe]]
              [sf-ladder-ui.reusable-views :as reusable]
              [sf-ladder-ui.home.views :as home]
              [sf-ladder-ui.chat.views :as chat]
              [sf-ladder-ui.about.views :as about]
              [sf-ladder-ui.login-register.views :as login]))

(defmulti pages identity)
(defmethod pages :home-page [] [home/home-page])
(defmethod pages :about-page [] [about/about-page])
(defmethod pages :chat-page [] [chat/chat-page])
(defmethod pages :login-page [] [login/login-page])
(defmethod pages :default [] [home/home-page])

(defn wrapper []
  (let [active-page (subscribe [:active-page])]
    (fn []
      [:div {:class "site-wrapper"}
        [reusable/nav]
        [:div {:class "site-content-outer"}
          [:div {:class "site-content-inner"}
            (pages @active-page)]]])))
