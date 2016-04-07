(ns babel.engine.slack
  "Slack-specific behavior"
  (:require [clj-slack.rtm :as rtm])
  )

(def connection-details
  {:api-url "https://slack.com/api" :token (env/env :slack-api-token)})

(defn start-rtm-session
  "Begin an RTM session with Slack."
  []
  (rtm/start connection-details))

(defn set-bot-info!
  "Store the information about this current user."
  []
  (swap! state/state assoc :user (auth/test connection-details)))

