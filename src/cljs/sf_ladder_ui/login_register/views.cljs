(ns sf-ladder-ui.login-register.views
  (:require [sf-ladder-ui.reusable-views :as reusable]
            [re-frame.core :refer [dispatch]]))

(defn login-form []
  (let [username ""
        password ""
        login-submit #(dispatch [:login-submit {:username username :password password}])]
    (fn []
      [:div {:class "login-form"}
        [reusable/generic-input {:val username
                                 :on-enter login-submit
                                 :input-classes "username-input"
                                 :wrapper-classes "username-input-wrapper"
                                 :type "text"}]
        [reusable/generic-input {:val password
                                 :on-enter login-submit
                                 :input-classes "password-input"
                                 :wrapper-classes "password-input-wrapper"
                                 :type "password"}]
        [:button {:class "login-submit"
                 :type "button"
                 :on-click login-submit} "Sign in"]])))

(defn register-form []
  (let [email ""
        username-register ""
        password-register ""
        password-register-confirm ""
        register-submit #(dispatch [:register-submit {:email email
                                                      :username-register username-register
                                                      :password-register password-register
                                                      :password-register-confirm password-register-confirm}])]
    (fn []
      [:div {:class "register-form"}
        [reusable/generic-input {:val email
                                 :on-enter register-submit
                                 :input-classes "email-input"
                                 :wrapper-classes "email-input-wrapper"
                                 :type "text"}]
        [reusable/generic-input {:val username-register
                                 :on-enter register-submit
                                 :input-classes "username-register-input"
                                 :wrapper-classes "username-register-input-wrapper"
                                 :type "text"}]
        [reusable/generic-input {:val password-register
                                 :on-enter register-submit
                                 :input-classes "password-register-input"
                                 :wrapper-classes "password-register-input-wrapper"
                                 :type "password"}]
        [reusable/generic-input {:val password-register-confirm
                                 :on-enter register-submit
                                 :input-classes "password-register-confirm-input"
                                 :wrapper-classes "password-register-confirm-input-wrapper"
                                 :type "password"}]
        [:button {:class "register-submit"
                  :type "button"
                  :on-click register-submit} "Sign up"]])))

(defn login-page []
  (fn []
    [:div {:class "login-wrapper"}
      [:h1 "Login 2.0"]
      [login-form]
      [register-form]]))
