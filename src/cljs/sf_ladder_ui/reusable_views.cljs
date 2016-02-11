(ns sf-ladder-ui.reusable-views
  (:require [re-frame.core :refer [subscribe]]
            [reagent.core :refer [atom]]))

(defn generic-input [{:keys [val on-enter input-classes wrapper-classes type]}]
  (let [input-val (atom val)
        reset #(do (reset! input-val ""))
        save #(let [v (-> @input-val str clojure.string/trim)]
                (.log js/console "input val being passed to save:" v)
                (if-not (empty? v) (on-enter v))
                (reset))]
    (fn [props]
      [:div {:class wrapper-classes}
        [:input (merge props
                       {:type type
                        :class input-classes
                        :value @input-val
                        :on-change #(reset! input-val (-> % .-target .-value))
                        :on-key-down #(case (.-which %)
                                        13 (save)
                                        27 (reset)
                                        nil)})]])))

(defn nav []
  (let [user (subscribe [:logged-in-user])
        _ (.log js/console "user:" @user)]
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
              [:a {:href "#/about"} "About"]]]
          [:div {:class "user-or-login"}
            (if (not (nil? @user))
              [:div {:class "nav-user"} "user info here"]
              [:div {:class "nav-login"} "login button here"])]])))
