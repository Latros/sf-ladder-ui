(ns sf-ladder-ui.db)

(def app-state
  {:name "re-frame"
   :chat {:messages [{:id 1 :user-id 5 :timestamp "21:42:00" :message "Yo"}
                     {:id 2 :user-id 5 :timestamp "21:43:00" :message "Yo"}
                     {:id 3 :user-id 6 :timestamp "21:44:00" :message "Yo"}
                     {:id 4 :user-id 6 :timestamp "21:45:00" :message "Yo"}
                     {:id 5 :user-id 6 :timestamp "21:46:00" :message "Yo"}
                     {:id 6 :user-id 5 :timestamp "21:47:00" :message "Yo"}
                     {:id 7 :user-id 6 :timestamp "21:48:00" :message "Yo"}
                     {:id 8 :user-id 5 :timestamp "21:49:00" :message "Yo"}
                     {:id 9 :user-id 6 :timestamp "21:50:00" :message "Yo"}
                     {:id 10 :user-id 4 :timestamp "21:51:00" :message "Yo"}]
          :participants [{:username "Matt" :id 4 :country "USA" :sublocation "NY"}
                         {:username "Sam" :id 5 :country "CA" :sublocation "NB"}
                         {:username "Jeff" :id 6 :country "USA" :sublocation "FL"}
                         {:username "Derrick" :id 7 :country "USA" :sublocation "FL"}]}
   :matchmaking {:participants [{:username "Derrick" :id 7 :country "USA" :sublocation "FL"}]}})
