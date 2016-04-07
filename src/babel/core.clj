(ns babel.core)

(defn reply
  [re handler]
  (fn [{:keys [type text] :as message}]
    (when (and (= type "message")
               (re-find re text)
               (.contains text (:user_id (:user @state/state))))
      (handler message))))

(defn- routing
  [request & handlers]
  (some #(% request) handlers))

(defn- routes
  [& handlers]
  (fn [x] (apply routing x handlers)))

(defmacro defbot
  [name & routes]
  `(def ~name
     (routes ~@routes)))
