(ns sf-ladder-ui.views
    (:require [reagent.core :refer [atom]]
              [re-frame.core :as re-frame :refer [subscribe dispatch]]
              [cljs-time.core :as t]
              [cljs-time.format :as f]))

(defn message-input [{:keys [message on-save classes]}]
  (let [val (atom ":(")
        stop #(do (reset! val ""))
        save #(let [v (-> @val str clojure.string/trim)]
                (if-not (empty? v) (on-save v))
                (stop))]
    (fn [props]
      [:input (merge props
                     {:type "text"
                      :value @val
                      :class classes
                      :on-change #(reset! val (.-value (.-target %)))
                      :on-key-down #(do (case (.-which %)
                                          13 (save)
                                          27 (stop)
                                          nil))})])))

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
  (let [chat-messages (subscribe [:chat-messages])]
    (fn []
      [:div {:class "chat-messages"}
        (for [message @chat-messages]
          ^{:key (:id message)} [chat-message message])
        [message-input {:message ""
                        :classes "chat-message-input"
                        :on-save #(dispatch [:send-message %])}]])))

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

(defn login-form []
  (let [username (atom "")
        password (atom "")
        form-submit (.log js/console "Form submitted!")
        stop #(do (reset! username "") (reset! password ""))]
    (let []
      (fn [props]
        [:div {:class "login-form"}
          [:div {:class "username"}
            [:input (merge props
                           {:type "text"
                            :value @username
                            :on-change #(reset! val (.-value (.-target %)))
                            :on-key-down #(do (case (.-which %)
                                                13 (form-submit)
                                                27 (stop)
                                                nil))})]]
          [:div {:class "password"}
            [:input (merge props
                           {:type "password"
                            :value @password
                            :on-change #(reset! val (.-value (.-target %)))
                            :on-key-down #(do (case (.-which %)
                                                13 (form-submit)
                                                27 (stop)
                                                nil))})]]]))))

(defn home-page []
  (fn []
    [:div {:class "home-wrapper"}
      [:h1 "Home"]]))

(defn about-page []
  (fn []
    [:div {:class "about-wrapper"}
      [:h1 "About"]]))

(defn login-page []
  (fn []
    [:div {:class "login-wrapper"}
     [:h1 "Login"]
     [login-form]]))

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
(defmethod pages :login-page [] [login-page])

(defn wrapper []
  (let [active-page (subscribe [:active-page])]
    (fn []
      [:div {:class "site-wrapper"}
        [nav]
        [:div {:class "site-content-outer"}
          [:div {:class "site-content-inner"}
            (pages @active-page)]]])))
