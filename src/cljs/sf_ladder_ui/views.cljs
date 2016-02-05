(ns sf-ladder-ui.views
    (:require [re-frame.core :as re-frame :refer [subscribe]]))

; --------- Components ---------

(defn chat-message [message]
  (let [users (subscribe [:chat-participants])
        {:keys [timestamp user-id message]} message
        [user] (filter #(= (:id %) user-id) @users)
        {:keys [username country]} user]
    (fn []
      [:div {:class "chat-message"}
        [:div {:class "chat-timestamp"} timestamp]
        [:div {:class "chat-user"}
          [:a {:href (str "#/user/" user-id)} username]]
        [:div {:class "message-text"} message]])))

(defn chat-messages []
  (let [chat-messages (subscribe [:chat-messages])]
    (fn []
      [:div {:class "chat-messages"}
        (for [message @chat-messages]
          ^{:key (:id message)} [chat-message message])])))

(defn chat-participant [chat-participant]
  (let [{:keys [country sublocation username]} chat-participant]
    (fn []
      [:li
        [:div {:class "user-location"}
          [:span {:class "user-country"} country]
          [:span {:class "user-sublocation"} sublocation]
          [:span {:class "user-name"} username]]])))

(defn chat-participants []
  (let [chat-participants (subscribe [:chat-participants])]
    (fn []
      [:div {:class "chat-participants"}
        [:h3 "Chat"]
        [:ul
          (for [cp @chat-participants]
            ^{:key (:id cp)} [chat-participant cp])]])))

(defn opponent-finder []
  (fn []
    [:div {:class "opponent-finder"}
      [:button {:class "play-now"} "Enter matchmaking"]]))

(defn matchmaking-participants []
  (fn []
    [:div {:class "matchmaking-participants"}
      [:h3 "Matchmaking"]]))

(defn sidebar []
  (fn []
    [:div {:class "sidebar"}
      [chat-participants]
      [opponent-finder]
      [matchmaking-participants]]))

(defn nav []
  (fn []
    [:nav
      [:h1 {:class "site-title"}
        [:a {:href "#/"} ""]]
      [:ul
        [:li
          [:a {:href "#/"} "Home"]]
        [:li
          [:a {:href "#/chat"} "Chat"]]
        [:li
          [:a {:href "#/about"} "About"]]]]))

; --------- Pages & Wrappers ---------

(defn home-page []
  (fn []
    [:div {:class "home-wrapper"}
      [:h1 "Home"]]))

(defn about-page []
  (fn []
    [:div {:class "about-wrapper"}
      [:h1 "About"]]))

(defn chat-page []
  (fn []
    [:div {:class "chat-wrapper"}
      [:div {:class "chat"}
        [chat-messages]
        [sidebar]]]))

(defmulti pages identity)
(defmethod pages :home-page [] [home-page])
(defmethod pages :about-page [] [about-page])
(defmethod pages :chat-page [] [chat-page])
(defmethod pages :default [] [home-page])

(defn wrapper []
  (let [active-page (subscribe [:active-page])]
    (fn []
      [:div {:class "site-wrapper"}
        [nav]
        [:div {:class "site-content-outer"}
          [:div {:class "site-content-inner"}
            (pages @active-page)]]])))
