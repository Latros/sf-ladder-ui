(ns sf-ladder-ui.db)

(def app-state
  {:name "re-frame"
   :pending-chat-message ""
   :users [{:username "Matt"    :ping 252 :matchmaking? true  :id 4 :country "USA" :sublocation "NY" :w 20  :l 16  :rating 2355}
           {:username "Sam"     :ping 110 :matchmaking? false :id 5 :country "CA"  :sublocation "NB" :w 32  :l 31  :rating 1668}
           {:username "Jeff"    :ping 125 :matchmaking? false :id 6 :country "USA" :sublocation "FL" :w 155 :l 126 :rating 3201}
           {:username "Jim"     :ping 76  :matchmaking? true  :id 7 :country "USA" :sublocation "FL" :w 16  :l 4   :rating 4757}
           {:username "Derrick" :ping 89  :matchmaking? false :id 8 :country "CA"  :sublocation "BC" :w 66  :l 93  :rating 1100}
           {:username "Leah"    :ping 45  :matchmaking? true  :id 9 :country "CA"  :sublocation "QC" :w 3   :l 12  :rating 657}]
   :chat {:messages [{:id 1 :user-id 5 :timestamp "21:42:00" :message "Yo"}
                     {:id 2 :user-id 5 :timestamp "21:43:00" :message "Yo"}
                     {:id 3 :user-id 6 :timestamp "21:44:00" :message "Yo"}
                     {:id 4 :user-id 6 :timestamp "21:45:00" :message "Yo"}
                     {:id 5 :user-id 6 :timestamp "21:46:00" :message "Yo"}
                     {:id 6 :user-id 5 :timestamp "21:47:00" :message "Yo"}
                     {:id 7 :user-id 6 :timestamp "21:48:00" :message "Yo"}
                     {:id 8 :user-id 5 :timestamp "21:49:00" :message "Yo"}
                     {:id 9 :user-id 6 :timestamp "21:50:00" :message "Yo"}
                     {:id 10 :user-id 9 :timestamp "21:51:00" :message "Yo"}]}})
