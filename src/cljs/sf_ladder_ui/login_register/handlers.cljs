(ns sf-ladder-ui.login-register.handlers
  (:require [re-frame.core :refer [dispatch register-handler]]
            [ajax.core :refer [POST]]))

(defn login-success [response]
  (do
    (.log js/console "Login succeeded!" response)
    (dispatch [:logged-in response])))

(defn login-fail [response]
  (do
    (.log js/console "Login failed!" response)
    nil))

(defn register-success []
  nil)

(defn register-fail []
  nil)

(register-handler
  :logged-in
  (fn [db [_ user]]
    (let [user (cljs.reader/read-string user)]
      (.log js/console "User creds from server:" user)
      (.log js/console "DB" db)
      (assoc db :user user))))


(register-handler
  :login-submit
  (fn [db [_ creds]]
    (.log js/console "Logged in handler!" creds)
    (POST "http://localhost:3000/resources/login" {:params creds
                                                   :handler login-success
                                                   :error-handler login-fail})
    db))

(register-handler
  :register-submit
  (fn [db [_ creds]]
    (.log js/console "Registered handler!" creds)
    db))
