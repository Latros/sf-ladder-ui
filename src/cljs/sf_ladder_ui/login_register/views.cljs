

(defn login-form [])

(defn register-form [])

(defn login-page []
  (fn []
    [:div {:class "login-wrapper"}
      [:h1 "Login"]
      [login-form]]))
