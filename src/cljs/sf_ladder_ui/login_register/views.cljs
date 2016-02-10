(ns sf-ladder-ui.login-register.views)

(defn login-form []
  (fn []
    [:div]))

(defn register-form []
  (fn []
    [:div]))

(defn login-page []
  (fn []
    [:div {:class "login-wrapper"}
      [:h1 "Login 2.0"]
      [login-form]]))
