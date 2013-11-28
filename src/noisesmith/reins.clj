(ns noisesmith.reins
  (:require [caribou.config :as config]
            [avout.core :as avout]))


(extend-protocol caribou.config/StateCoordinator
  avout.atoms.DistributedAtom
  (reset [this value] (avout/reset!! this value) this)
  (swap [this f] (avout/swap!! this f) this))

(defn reins
  [host]
  (let [zk-server (avout/connect host)]
    (fn z-cache-map [name]
      (config/swap (avout/zk-atom zk-server name)
                   #(or % {})))))

(defn haltered-config
  [host prefix]
  (let [gen-key #(str \/ prefix \- %)
        model (gen-key "models")
        queries (gen-key "queries")
        reverse-cache (gen-key "reverse-cache")
        rein-in (reins host)]
    {:models (rein-in model)
     :query {:queries (rein-in queris)
             :reverse-cache (rein-in reverse-cache)}}))
