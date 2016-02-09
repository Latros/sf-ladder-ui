(ns sf-ladder-ui.db)

(def app-state
  {:name "re-frame"
   :user {:id 10 :username "Latros" :country "CA" :sublocation "NB" :w 500 :l 0 :rating 10000 :ping 0 :matchmaking? true}
   :users [{:username "Matt"    :ping 252 :matchmaking? true  :id 4 :country "USA" :sublocation "NY" :w 20  :l 16  :rating 2355}
           {:username "Sam"     :ping 110 :matchmaking? false :id 5 :country "CA"  :sublocation "NB" :w 32  :l 31  :rating 1668}
           {:username "Jeff"    :ping 125 :matchmaking? false :id 6 :country "USA" :sublocation "FL" :w 155 :l 126 :rating 3201}
           {:username "Jim"     :ping 76  :matchmaking? true  :id 7 :country "USA" :sublocation "FL" :w 16  :l 4   :rating 4757}
           {:username "Derrick" :ping 89  :matchmaking? false :id 8 :country "CA"  :sublocation "BC" :w 66  :l 93  :rating 1100}
           {:username "Leah"    :ping 45  :matchmaking? true  :id 9 :country "CA"  :sublocation "QC" :w 3   :l 12  :rating 657}]
   :chat {:messages []}})
