reins
=====

An extension to coordinate many caribou so as to pull your sled to glory.

usage
=====

Add the dependency to your `project.clj`

    [noisesmith/reins "0.0.1"]

require the lib in your `boot.clj`

    (ns zookept.boot
      (:require [noisesmith.reins :as reins]
                [caribou.core :as caribou]
                [caribou.config :as config]
                [caribou.app.config :as app-config]))

merge in the haltered config with your local config:

    (defn boot
      []
      (let [default (app-config/default-config)
            local (config/merge-config default local-config)
            kept (reins/haltered-config "127.0.0.1" "zookept")
            config (config/config-from-environment kept)]
        (caribou/init config)))

all instances of your caribou app will now have their model definitions and query caching coordinated

upcoming
========

other arbitrary coordination?

rolling deploy?

caveats
=======
reins is not yet tested in production

project dependencies on caribou and clojure are intentionally absent, because it is assumed that you are using reins as a plugin extension to an existing caribou project which would already have these libs present
