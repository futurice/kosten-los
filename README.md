# Kosten Los!

An app (skeleton) for declaring German daily allowance and travel expenses <3

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

After that, run the app stack once (see below) to download and install all required library dependencies.

## Running

To start a web server for the application, run:

    $ lein ring server

Check the example page at [http://localhost:3000/](http://localhost:3000/).
To clear up H2 database, remove `resources/public/site.db.h2.db`.

## Tool suggestions

* IntelliJ IDEA / WebStorm / PHPStorm: JavaScript + La Clojure plugins
* Sublime Text 2 + SublimeCodeIntel plugin
* Eclipse + JavaScript Development Tools + CounterClockWise
* LightTable (for Clojure)

## Tech stack

* Layout: Twitter Bootstrap
* Webapp: RxJS, Rx.jQuery.js, jQuery
* REST API: Luminus web framework
* DB wrapper: Korma
* Database: H2

## REPL

Use REPL to try out stuff in command line, e.g.:

    $ lein repl
    user=> (use 'kosten-los.models.db)
    user=> (use 'noir.response)
    user=> (json (map :code (get-countries)))
    {:headers {"Content-Type" "application/json; charset=utf-8"}, :body "[\"fi\",\"uk\",\"de\",\"se\"]"}

## Resources for studying

### RxJS

http://jhusain.github.io/learnrx (doesn't seem to work well with FF, try Chrome)

* Note: this begins with functional programming basics and RxJS stuff is there pretty late (for a reason). If you feel you already know the stuff, just read the text and skip through the exercises ("Show answer", then "Run") that are too easy.

Different types of Subject: http://stackoverflow.com/questions/4787276/what-do-the-various-isubject-implementations-do-and-when-would-they-be-used

**Docs about Reactive Extensions in general**

Not RxJS specific, but interfaces are the basically the same everywhere except for language specific code styles

* Official docs: http://msdn.microsoft.com/en-us/library/hh242985.aspx – start with Getting Started and Using Rx, API docs not very beginner friendly
* Intro videos: http://msdn.microsoft.com/en-us/data/gg577611.aspx

### Luminus, with stack architecture

http://www.luminusweb.net/docs

### Korma

http://sqlkorma.com/docs

### Clojure

http://clojurekoans.com

## License

Copyright © 2013 Futurice

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
