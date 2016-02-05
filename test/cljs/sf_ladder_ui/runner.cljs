(ns sf-ladder-ui.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [sf-ladder-ui.core-test]))

(doo-tests 'sf-ladder-ui.core-test)
