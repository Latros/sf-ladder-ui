(ns sf-ladder-ui.chat.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]
            [cljs-time.format :as f]
            [sf-ladder-ui.reusable-views :as reusable]))

(defn chat-timestamp [date]
  (let [pretty-date (f/unparse (f/formatter "HH:mmA") date)]
    (fn []
      [:div {:class "chat-timestamp"} pretty-date])))

(defn chat-message [message]
  (let [users (subscribe [:chat-participants])
        user (subscribe [:logged-in-user])
        all-users (conj @users @user)
        {:keys [timestamp user-id message]} message
        [user] (filter #(= (:id %) user-id) all-users)
        {:keys [username]} user]
    (fn []
      [:div {:class "chat-message"}
        [chat-timestamp timestamp]
        [:div {:class "chat-user"}
          [:a {:href (str "#/user/" user-id)} username]]
        [:div {:class "message-text"} message]])))

(defn chat-messages []
  (let [chat-messages (subscribe [:chat-messages])
        message-val ""
        send-msg-fn #(do
                       (.log js/console "SEND MSG FN" %)
                       (dispatch [:send-message %]))]
    (fn []
      [:div {:class "chat-messages"}
        (for [message @chat-messages]
          ^{:key (:id message)} [chat-message message])
        [reusable/generic-input {:val message-val
                                 :on-enter send-msg-fn
                                 :wrapper-classes "chat-message-input-wrapper"
                                 :input-classes "chat-message-input"
                                 :type "text"}]])))

(defn participants-header []
  (fn []
    [:li {:class "participants-header"}
      [:span {:class "left-header"} "Username - W:L - ELO"]
      [:span {:class "right-header"} "Country/Region/Ping"]]))

(defn user-ping [ping]
  (let [signal-quality (cond
                         (< ping 65) "great"
                         (< ping 100) "good"
                         (< ping 175) "average"
                         :else "bad")]
    (fn []
      [:span {:class (str "user-ping " signal-quality)} ping])))

(defn participant [participant]
  (let [{:keys [w l ping username country sublocation rating]} participant]
    (fn []
      [:li
        [:div {:class "user-info"}
          [:span {:class "user-name"} username]
          [:span {:class "wlr"} (str w ":" l)]
          [:span {:class "user-rating"} rating]
          [user-ping ping]
          [:span {:class "user-location"}
            [:span {:class "user-country"} country]
            [:span {:class "user-sublocation"} sublocation]]]])))

(defn chat-participants []
  (let [chat-participants (subscribe [:chat-participants])]
    (fn []
      [:div {:class "chat-participants"}
        [:h3 "Chat"]
        [:ul {:class "user-list"}
          [participants-header]
          (for [cp @chat-participants]
            ^{:key (:id cp)} [participant cp])]])))

(defn matchmaking-participants []
  (let [matchmaking-participants (subscribe [:matchmaking-participants])]
    (fn []
      [:div {:class "matchmaking-participants"}
        [:h3 "Matchmaking"]
        [:ul {:class "user-list"}
          [participants-header]
          (for [mp @matchmaking-participants]
            ^{:key (:id mp)} [participant mp])]])))

(defn opponent-finder []
  (fn []
    [:div {:class "opponent-finder"}
      [:button {:class "play-now"} "Enter matchmaking"]]))

(defn sidebar []
  (fn []
    [:div {:class "sidebar"}
      [opponent-finder]
      [chat-participants]
      [matchmaking-participants]]))

(defn chat-page []
  (fn []
    [:div {:class "chat-wrapper"}
      [:h1 "Chat 2.0"]
      [:div {:class "chat"}
        [chat-messages]
        [sidebar]]]))
